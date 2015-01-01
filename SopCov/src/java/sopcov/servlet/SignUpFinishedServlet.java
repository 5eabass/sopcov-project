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
            //Depuis la session
            String email = (String) s.getAttribute("email");
            String password = (String) s.getAttribute("password");
            //Depuis la requête            
            String nom = (String) request.getParameter("nom");
            String prenom = (String) request.getParameter("prenom");
            String tel = (String) request.getParameter("tel");
            String adresse = (String) request.getParameter("adresse");
            String commune = (String) request.getParameter("commune");
            String codePostal = (String) request.getParameter("code_postal");
            String lieuTravail = (String) request.getParameter("lieu_travail");
            String heureDepart = (String) request.getParameter("heure_depart");
            String heureRetour = (String) request.getParameter("heure_retour");

            //Récupère les jours de travail de la requete
            //Lundi Mardi Mercredi devient Lun,Mar,Mer
            String joursTravail = "";
            String[] joursRequete = request.getParameterValues("jours_travail");
            if (joursRequete != null) {
                for (int i = 0; i < joursRequete.length; i++) {
                    //On récupére les trois première lettre
                    joursTravail = joursTravail + joursRequete[i].substring(0, 3);
                    //On met une virgule si c'est pas le dernier string
                    if (i != joursRequete.length - 1) {
                        joursTravail = joursTravail + ",";
                    }
                }
            }

            //Si il y a quelque chose ici c'est forcément on...
            boolean conducteur = request.getParameter("conducteur") != null;
            boolean notif = request.getParameter("notif") != null;

            PrintWriter pw = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUpFinishedServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            pw.println("<p>");
            pw.println("Paramètres entrés : <br/>");
            pw.println("email: " + email + "<br/>");
            pw.println("password: " + password + "<br/>");
            pw.println("nom: " + nom + "<br/>");
            pw.println("prenom: " + prenom + "<br/>");
            pw.println("tel: " + tel + "<br/>");
            pw.println("adresse: " + adresse + "<br/>");
            pw.println("commune: " + commune + "<br/>");
            pw.println("codePostal: " + codePostal + "<br/>");
            pw.println("lieuTravail: " + lieuTravail + "<br/>");
            pw.println("heureDepart: " + heureDepart + "<br/>");
            pw.println("heureRetour: " + heureRetour + "<br/>");
            pw.println("joursTravail: " + joursTravail + "<br/>");
            pw.println("conducteur: " + conducteur + "<br/>");
            pw.println("notif: " + notif + "<br/>");
            pw.println("</p>");
            out.println("</body>");
            out.println("</html>");
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
