/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import database.DB;
import database.DBInterface;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * @author gb
 */
public class GetReportServlet extends HttpServlet {

    public static String[] RAPPORTS = {"getNumberDrivers", "getNumberOfNonDrivers", "getPercentOfDrivers", "getNumberOfUsers"};
    public static String[] REPONSES = {"nombre_conducteurs", "nombre_non_conducteurs", "pourcentage_conducteurs", "nombre_utilisateurs"};

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
        DBInterface dbi = new DB();
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();

        if (request.getParameter("rapport") != null) {
            if (request.getParameter("rapport").equals(RAPPORTS[0])) {
                System.out.println("Demande de rapport : " + RAPPORTS[0]);
                int nbrCond = dbi.getNumberOfDrivers();
                String reponse = "{ \"" + this.REPONSES[0] + "\":\"" + nbrCond + "\" }";
                System.out.println("Renvoie : " + reponse);
                pw.println(reponse);
                pw.flush();
            } else if (request.getParameter("rapport").equals(RAPPORTS[1])) {
                System.out.println("Demande de rapport : " + RAPPORTS[1]);
                int nbrPasCond = dbi.getNumberOfNonDrivers();
                String reponse = "{ \"" + this.REPONSES[1] + "\":\"" + nbrPasCond + "\" }";
                System.out.println("Renvoie : " + reponse);
                pw.println(reponse);
                pw.flush();
            } else if (request.getParameter("rapport").equals(RAPPORTS[2])) {
                System.out.println("Demande de rapport : " + RAPPORTS[2]);
                double percent = dbi.getPercentOfDrivers();
                String reponse = "{ \"" + this.REPONSES[2] + "\":\"" + percent + "\" }";
                System.out.println("Renvoie : " + reponse);
                pw.println(reponse);
                pw.flush();
            } else if (request.getParameter("rapport").equals(RAPPORTS[3])) {
                System.out.println("Demande de rapport : " + RAPPORTS[3]);
                int nbrUtilisateurs = dbi.getNumberOfUsers();
                String reponse = "{ \"" + this.REPONSES[3] + "\":\"" + nbrUtilisateurs + "\" }";
                System.out.println("Renvoie : " + reponse);
                pw.println(reponse);
                pw.flush();
            } else {
                System.err.println("In GetReport : " + request.getParameter("rapport") + " inconnu ");

            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet qui doit renvoyer les rapports qui lui sont demandés.";
    }// </editor-fold>

}
