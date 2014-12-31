/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import database.DB;
import database.DBInterface;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        if (s != null) {
            //System.out.println("In SignInServlet : La session n'est pas null et pas nouvelle");
            email = (String) s.getAttribute("email");
            pswd = (String) s.getAttribute("password");
        }
        //System.out.println("In SignInServlet : email : " + email + " & pswd : " + pswd);

        if (email != null && !email.isEmpty() && pswd != null && !pswd.isEmpty()) {
            DBInterface dbi = new DB();
            dbi.connect();
            //System.out.println("In SignInServlet : Le mot de passe reçu de la base de données est : " + pswd);
            if (dbi.userExists(email, pswd)) {
                System.out.println("In SignInServlet : Login correct.");
                destination = "userPage.jsp";
            } else {
                System.out.println("In SignInServlet : Login ou mot de passe incorrect.");
                s.setAttribute("msgErreur", "Login ou mot de passe incorrect.");
                System.out.println((String)s.getAttribute("msgErreur"));
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
