/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gb
 */
public class SignUpRequestServlet extends HttpServlet {

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
            String email = (String) request.getAttribute("email");
            String password = (String) request.getAttribute("password");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sign Up</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<form id=\"signInUpForm\" method=\"post\" action=\"/SopCovTraining/SignUpServlet.do\">");
            out.println("<table style=\"width=100%\">");
            out.println("<tr>");
            out.println("<td>Prénom</td>");
            out.println("<td><input type=\"text\" name=\"name\" value=\"name\"></td>");
            out.println("<td>Lieu de travail</td>");
            out.println("<td>");
            out.println("<select name=\"wPlace\">");
            out.println("<option>SopraOption1</option>");
            out.println("<option selected>SopraOption2</option>");
            out.println("<option>SopraOption3</option>");
            out.println("</select>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Nom</td>");
            out.println("<td><input type=\"text\" name=\"lastname\" value=\"lastname\"></td>");
            out.println("<td>Heure de départ</td>");
            out.println("<td><input type=\"time\" name=\"depTime\" value=\"08:00\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Téléphone</td>");
            out.println("<td><input type=\"tel\" name=\"phone\" value=\"06\"></td>");
            out.println("<td>Heure de retour</td>");
            out.println("<td><input type=\"time\" name=\"retTime\" value=\"08:00\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Adresse</td>");
            out.println("<td><input type=\"text\" name=\"hAddress\" value=\"123 rue paradis\"></td>");
            out.println("<td>Jour de travail</td>");
            out.println("<td><input type=\"week\" name=\"workDays\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Commune</td>");
            out.println("<td><input type=\"text\" name=\"commune\" value=\"Toulouse\"></td>");
            out.println("<td>Je serai conducteur</td>");
            out.println("<td><input type=\"checkbox\" name=\"driver\" value=\"false\"></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Code Postal</td>");
            out.println("<td><input type=\"text\" name=\"pCode\" value=\"31000\"></td>");
            out.println("<td>Je veux être notifié par mail</td>");
            out.println("<td><input type=\"checkbox\" name=\"notif\" value=\"false\"></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
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
        //processRequest(request, response);
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
