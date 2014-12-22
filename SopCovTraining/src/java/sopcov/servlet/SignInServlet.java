/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sopcov.models.DBI;
import sopcov.models.DBManager;
import sopcov.models.MySQLDBI;

/**
 *
 * @author gb
 */
public class SignInServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destination = "index.jsp";
        //String destination = "userPage.jsp";

        //retreive the email and the pswd
        HttpSession s = request.getSession();
        String email = null;
        String pswd = null;
        if (s != null && !s.isNew()) {
            email = (String) s.getAttribute("email");
            pswd = (String) s.getAttribute("pswd");
        }
        if (email != null && !email.isEmpty() && pswd != null && !pswd.isEmpty()) {

            //retreive the database informations from the context
            String dbname = getServletContext().getInitParameter("dbuserid");
            String dbpswd = getServletContext().getInitParameter("dbuserpswd");
            String dbcatalog = getServletContext().getInitParameter("dbcatalog");

            //set the dbi for mysql
            DBI dbi = new MySQLDBI(dbname, dbpswd, dbcatalog);
            System.out.println(dbi.getConnectionDetails());
            System.out.println(dbi.getConnectionURL());

            DBManager dbm = new DBManager(dbi);

            if (!dbm.isConnected()) {
                //TO-DO redirect to sign in page with an error message            
                System.out.println("In SignInServlet : database is not connected, connecting it...");
                if (!dbm.openConnection()) {
                    System.err.println("Error in SignInServlet : could not connect to the database");
                }
                System.out.println("In SignInServlet : connection status : " + dbm.isConnected());

            }

            if (!dbm.isConnected()) {
                System.out.println("Error in SignInServlet : could not connect to the DB");
            } else {
                try {
                    //Creating the query
                    String query = "select pswd from utilisateur where email = '" + email + "'";

                    //Sending the query 
                    // /!\ should be done by the model actually
                    ResultSet res = dbm.ExecuteResultSet(query);
                    res.next();
                    
                    //Testing if the password match, normally there is just one row
                    if (res.getString("pswd").equals(pswd)) {
                        System.out.println("In SignInServlet : Password match");
                        destination = "userPage.jsp";
                    } else {
                        System.out.println("In SignInServlet : Password do not match");
                    }
                } catch (SQLException ex) {
                    System.err.println("Error in SignInServlet : " + ex.getLocalizedMessage());
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
