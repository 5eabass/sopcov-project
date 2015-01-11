/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sopcov.servlet;

import database.DB;
import database.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author seb
 */



@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile.do"})
public class EditProfile extends HttpServlet {
    
    DB dbmanager;
    //private String message;
    
    @Override
    public void init() {
        // initialization
        dbmanager = new DB();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = "editProfilePage.jsp";
        String email = null;
        HttpSession s = request.getSession();
        
        if (s != null) {
            email = (String) s.getAttribute("email");
            
            
            // recuperation des infos
            User userInfo = dbmanager.queryInfo(email);
            ArrayList<String> lieuxTravail = dbmanager.getAllWorkplaces();
            request.setAttribute("lieuxTravail", lieuxTravail);
            
            request.setAttribute("user", userInfo);
        }else{
            destination = "index.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
        String destination = "userWelcome.jsp";
        
        // Récupération session
        HttpSession s = request.getSession();
        String email = (String) s.getAttribute("email");   
        
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String adresse = (String) request.getParameter("adresse");
        String tel = (String) request.getParameter("tel");
        String commune = (String) request.getParameter("commune");
        String code_postal = (String) request.getParameter("code_postal");
        String lieu_travail = (String) request.getParameter("lieu_travail");
        String heure_depart = (String) request.getParameter("heure_depart");
        String heure_retour = (String) request.getParameter("heure_retour");
        String conducteur = (String) request.getParameter("conducteur");
        String notif = (String) request.getParameter("notif");    
       
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
             
        dbmanager.editUserProfile(email,nom,prenom,adresse,
                    tel,commune,code_postal,lieu_travail,
                    heure_depart,heure_retour,joursTravail,
                    conducteur,notif);
        
        RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
        rd.forward(request, response);
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
