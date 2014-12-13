/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopcov.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gb
 */
public class SignInOrUpServlet extends HttpServlet {

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
        //get the parameter that were posted
        //to know wether the user wanted to sign in or up
        String email = request.getParameter("email");
        String password = request.getParameter("pswd");
        String[] btnClicked = request.getParameterValues("choiceBtn");

        //the dispatcher that will forward the request to the good servlet
        RequestDispatcher dispatcher;

        //If we have one btn clicked everything is normal else someone is messing around
        if (btnClicked.length == 1) {

            request.setAttribute("email", email);
            request.setAttribute("password", password);

            //Going to sign in or up options
            if (btnClicked[0].equals("SignInBtn")) {
                dispatcher = request.getRequestDispatcher("/SignInServlet.do");
                dispatcher.forward(request, response);
            } else if (btnClicked[0].equals("SignUpBtn")) {
                dispatcher = request.getRequestDispatcher("/SignUpRequestServlet.do");
                dispatcher.forward(request, response);
            } //Something is wrong there are only two buttons
            else {
                dispatcher = request.getRequestDispatcher("/MainServlet.do");
                dispatcher.forward(null, null);
            }
        } else {
            dispatcher = request.getRequestDispatcher("/MainServlet.do");
            dispatcher.forward(null, null);
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
