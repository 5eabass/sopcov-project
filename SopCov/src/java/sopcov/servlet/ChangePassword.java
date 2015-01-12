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
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword.do"})
public class ChangePassword extends HttpServlet {
    
    DB dbmanager;
    
    String emailToBeModified = "";
    String oldpwd = "";
    String isAdmin = "";
    
    @Override
    public void init() {
        // initialization
        dbmanager = new DB();
    }
    
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
        
        String destination = "changePass.jsp";
        HttpSession s = request.getSession();
        String sessionEmail =(String) s.getAttribute("email");
        System.out.println("entered  change password, session mail = " + sessionEmail);
        
        if ((String) s.getAttribute("emailToBeModified") != null){
            System.out.println("entered  change passwordemail param!= null");
            emailToBeModified = (String) s.getAttribute("emailToBeModified");
            System.out.println("emailtobemodified :" + emailToBeModified);
        }
              
        if(!sessionEmail.equals(emailToBeModified)){
            System.out.println("entered email to modify :  != email : " +emailToBeModified + " / " + sessionEmail);
            isAdmin = "true";           
        } else {
            isAdmin = "false";
            System.out.println("entered email to modify = session email : " +emailToBeModified + " / " + sessionEmail);
            
            oldpwd = dbmanager.getPassword(emailToBeModified);
        }
        request.setAttribute("isAdmin", isAdmin);
        System.out.println(" go to jsp");
            
        request.setAttribute("emailToBeModified", emailToBeModified);
        RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
        rd.forward(request, response);
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
        //processRequest(request, response);
        String destination = "";
        System.out.println("entered  change password POST");
        HttpSession s = request.getSession();
        // Get the parameters to change password
        String apwd = "";
        String npwd = request.getParameter("npwd");
        String rnpwd = request.getParameter("rnpwd");
        
        if(isAdmin.equals("true")){
            destination = "management.jsp";
            // si on est admin on a que le nouveau et sa répétition à tester
            System.out.println("entered  change password POST - is admin true !");
            if(npwd.length()<8){
                // si longueur du mdp < 8 : erreur
                s.setAttribute("msgErreur", "Mot de passe doit contenir au moins 8 caractères.");
                System.out.println("New Password not correct!");
            }
            else{
                if(!npwd.equals(rnpwd)){
                    //si le password n'est pas correctement répété : erreur
                    s.setAttribute("msgErreur", "Veuillez entrer le même mot de passe deux fois.");
                    System.out.println("Please enter the same password twice!");
                }
                else{
                    System.out.println("entered  change password POST - is admin true password correct -> query");
                    dbmanager.setPassword(emailToBeModified, npwd);
                }
            }
            
            
        } else {
            // si c'est pas l'admin qui change le mdp alors c'est un utilisateur ( qui peut etre admin à noter )
            // mais dans tous les cas il doit connaitre son ancien mdp
            System.out.println("entered  change password POST - not admin request !");
            destination = "userWelcome.jsp";
            apwd = (String) request.getParameter("apwd");
            
            if(!oldpwd.equals(apwd)){
                s.setAttribute("msgErreur", "Ancien mot de passe incorrect!");
                System.out.println("Old Password not correct!");
            }else{
                System.out.println("old password is correct");
                if(npwd.length()<8){
                    // si longueur du mdp < 8 : erreur
                    s.setAttribute("msgErreur", "Mot de passe doit contenir au moins 8 caractères.");
                    System.out.println("New Password not correct!");
                }
                else{
                    if(!npwd.equals(rnpwd)){
                        //si le password n'est pas correctement répété : erreur
                        s.setAttribute("msgErreur", "Veuillez entrer le même mot de passe deux fois.");
                        System.out.println("Please enter the same password twice!");
                    }
                    else{
                        System.out.println("old password is correct , new password are correct - > query");
                        dbmanager.setPassword(emailToBeModified, npwd);
                    }
                }
                
            }
            
        }
        
        
        final RequestDispatcher rd = request.getRequestDispatcher("/"+destination );
        rd.forward(request, response);
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
