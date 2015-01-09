/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import database.DB;
import java.io.IOException;
import java.io.PrintWriter;
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

        String destination = "index.jsp";
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

        /*
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUpFinishedServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>");
            out.println("Paramètres entrés : <br/>");
            out.println("email: " + email + "<br/>");
            out.println("password: " + password + "<br/>");
            out.println("nom: " + nom + "<br/>");
            out.println("prenom: " + prenom + "<br/>");
            out.println("tel: " + tel + "<br/>");
            out.println("adresse: " + adresse + "<br/>");
            out.println("commune: " + commune + "<br/>");
            out.println("codePostal: " + codePostal + "<br/>");
            out.println("lieuTravail: " + lieuTravail + "<br/>");
            out.println("heureDepart: " + heureDepart + "<br/>");
            out.println("heureRetour: " + heureRetour + "<br/>");
            out.println("joursTravail: " + joursTravail + "<br/>");
            out.println("conducteur: " + conducteur + "<br/>");
            out.println("notif: " + notif + "<br/>");
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
        }
        */

        int creationMarche = dbi.addNewUser(admin, prenom, nom, password, tel, email, adresse, commune, codePostal, lieuTravail, heureDepart, heureRetour, joursTravail, conducteur, notif);

        switch (creationMarche) {
            case 0:
                destination = "userWelcome.jsp";
                break;
            case -1:
                s.setAttribute("msgErreur", "Création du profil impossible. Votre lieu de travail n'a pas été trouvé dans la base de données.");
                break;
            case -2:
                s.setAttribute("msgErreur", "Création du profil impossible. Problème d'acces à la base de données quand on a cherché votre lieu de travail");
                break;
            case -3:
                s.setAttribute("msgErreur", "Création du profil impossible. Problème d'acces à la base de données quand nous avons essayé de créer votre profil");
                break;
            default:
                s.setAttribute("msgErreur", "Problème lors de la création de votre profil");
                break;
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
