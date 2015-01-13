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
    String emailToBeModified = "";
    
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
        String isAdminRequest = "" ;
        String destination = "editProfilePage.jsp";
        System.out.println("entered servlet");
        
        if (request.getParameter("email") != null){
            System.out.println("entered email param!= null");
            emailToBeModified = (String) request.getParameter("email");
            System.out.println("emailtobemodified :" + emailToBeModified);
            isAdminRequest  = "true";
        }else {
            System.out.println("entered email param = null");
            HttpSession s = request.getSession();
            
            if (s != null) {
                System.out.println("entered s != null");
                emailToBeModified = (String) s.getAttribute("email");
            }else{
                destination = "index.jsp";
            }
        }
        request.setAttribute("isAdminRequest", isAdminRequest);
        
        if ((!emailToBeModified.equals("")) && (dbmanager.isUserInDB(emailToBeModified))){
            // recuperation des infos
            User userInfo = dbmanager.queryInfo(emailToBeModified);
            ArrayList<String> lieuxTravail = dbmanager.getAllWorkplaces();
            request.setAttribute("lieuxTravail", lieuxTravail);
            
            request.setAttribute("user", userInfo);
            request.setAttribute("emailToBeModified", emailToBeModified);
        }
    
        else {
            HttpSession s = request.getSession();
            s.setAttribute("msgErreur", "l'email n'existe pas !");
            destination = "management.jsp";
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = "";
        // Récupération session
        HttpSession s = request.getSession();
        String emailSession = (String) s.getAttribute("email");        
            if (emailSession != null){
                if (emailSession.equals(emailToBeModified)){
                    destination = "userWelcome.jsp";
                }else{
                    destination = "management.jsp";
                    String admin = (String) request.getParameter("admin");
                    dbmanager.setAdminRight(emailToBeModified,admin);
                }
            }
            
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
            
            dbmanager.editUserProfile(emailToBeModified,nom,prenom,adresse,
                    tel,commune,code_postal,lieu_travail,
                    heure_depart,heure_retour,joursTravail,
                    conducteur,notif);
        
        s.removeAttribute("emailToBeModified");
        RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
        rd.forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
