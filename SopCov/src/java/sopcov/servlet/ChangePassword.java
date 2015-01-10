/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import database.DB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ayoub
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {
    
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
        
            
            // Get the parameters to change password
            String mail= request.getParameter("email");
            String apwd = request.getParameter("apwd");
            String npwd = request.getParameter("npwd");
            String rnpwd = request.getParameter("rnpwd");
            HttpSession s = request.getSession();

            DB database=new DB();
            System.out.println("PASS: "+mail);
            if(!database.getPassword(mail).equals(apwd)){
                s.setAttribute("msgErreur", "Ancien mot de passe incorrect!");
                System.out.println("Old Password not correct!");
            }
            else{
                //The old password is correct
                if(npwd.length()<8){
                    //Password length < 8 carachters
                    s.setAttribute("msgErreur", "Mot de passe doit contenir au moins 8 caractères.");
                    System.out.println("New Password not correct!");
                }
                else{
                    if(!npwd.equals(rnpwd)){
                        //Not same repeated Password
                        s.setAttribute("msgErreur", "Veuillez entrer le même mot de passe deux fois.");
                        System.out.println("Please enter the same password twice!");
                    }
                    else{
                        database.setPassword(mail, npwd);
                        }
                    }
                }
            
            final RequestDispatcher rd = request.getRequestDispatcher("/changePass.jsp" );
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
