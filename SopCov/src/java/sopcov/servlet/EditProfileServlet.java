package sopcov.servlet;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sopcov.models.*;


/**
 *
 * @author seb
 */
@WebServlet(urlPatterns = {"/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
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
        // get the name of user
        String name = request.getParameter("name");
        
        // get DB connection info from context init param
        String uid = getServletContext().getInitParameter("dbuserid");
        String pwd = getServletContext().getInitParameter("dbuserpwd");
        String cat = getServletContext().getInitParameter("dbcatalog");
        
        //set the DBi
        DBI dbi = new MySQLDBI(uid,pwd,cat);
        System.out.println(dbi.getConnectionDetails());
        System.out.println(dbi.getConnectionURL());
        
        //set the database manager
        DBManager dbm = new DBManager(dbi);
        try{
            if(!dbm.isConnected()){
                if(!dbm.openConnection()){
                    System.err.println("in EDIT PROFILE could not connect to database");
                }
            }
            
            // get the info of the user
            // should be done in model ..
            String query = "select * from utilisateur where uid = '"+ name +"' ";
            
            
            ResultSet rs = dbm.ExecuteResultSet(query);
            
            
            /*     a finir        déclarer tous les champs de l'utilisateur a renvoyer dans la page jsp
            String name = ;
            String
            
            */
            
            
        }catch (SQLException ex) {
            System.err.println(ex);
        }      
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
