package sopcov.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
import database.DB;
import database.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class SearchRoute extends HttpServlet {

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
        //request.setAttribute("drivers", dbmanager.getAllDrivers());
        final RequestDispatcher rd = request.getRequestDispatcher("/searchroute.jsp" );
        rd.forward(request, response);
       
        
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        List<User> routes=dbmanager.searchRoute(request.getParameter("commune"), request.getParameter("wPlace"));
        request.setAttribute("drivers", routes);
        final RequestDispatcher rd = request.getRequestDispatcher("/listDrivers.jsp" );
        rd.forward(request, response);
           
    }

    @Override
    public void destroy() {
        // do nothing.
    }

}

