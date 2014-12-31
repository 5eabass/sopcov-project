/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gb
 */
public class SignUpFinishedServlet extends HttpServlet {

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
            //Récupère la session
            HttpSession s = request.getSession();
            //On se munit du DB helper :
            DB dbi = new DB();
            //Récupère tous les parametres nécessaire à la création de l'utilisateur
            boolean admin = false;
            String email = (String) s.getAttribute("email");
            String password = (String) s.getAttribute("password");
            
            String nom = (String) request.getAttribute("nom");
            String prenom = (String) request.getAttribute("prenom");
            String tel = (String) request.getAttribute("tel");
            String adresse = (String) request.getAttribute("adresse");
            String commune = (String) request.getAttribute("commune");
            String codePostal = (String) request.getAttribute("code_postal");
            String lieuTravail = (String) request.getAttribute("lieu_travail");
            String heureDepart = (String) request.getAttribute("heure_depart");
            String heureRetour = (String) request.getAttribute("heure_retour");
            
            //À faire
            //String joursTravail = (String) request.getAttribute("heure_depart");
            
            boolean conducteur = (boolean) request.getAttribute("conducteur");
            boolean notif = (boolean) request.getAttribute("notif");
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
