package sopcov.servlet;

import database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class ShowCovoiturage extends HttpServlet {

    DB dbmanager;
    private String message;

    @Override
    public void init() {
// Do required initialization
        message = "Hello World";
        dbmanager = new DB();
    }

    @Override
    public void doGet( HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException  {
        request.setAttribute("drivers", dbmanager.getAllDrivers());
        final RequestDispatcher rd = request.getRequestDispatcher("/listDrivers.jsp" );
        rd.forward(request, response);
       
        
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

           out.println("dsads");
    }

    @Override
    public void destroy() {
        // do nothing.
    }

}
