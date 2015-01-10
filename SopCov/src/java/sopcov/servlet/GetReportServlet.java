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

    public static String[] RAPPORTS = {"getNumberDrivers"};
    public static String RAPPORT_REPONSE = "reponse";

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
                String reponse = "{ \"nombre_conducteurs\":\""+nbrCond+"\" }";
                System.out.println("Renvoie : " + reponse);
                pw.println(reponse);
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
