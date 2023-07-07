/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1889.framework.servlet;

import etu1889.framework.*;

import modele.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.Class;
import java.lang.*;
import java.util.*;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
/**
 *
 * @author steve
 */
public class FrontServlet extends HttpServlet {

    ArrayList<Mapping> allMapping ;
    HashMap<String,Mapping> mappingUrls;
    
    

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
            throws ServletException, IOException{
                
            String[] endOfUrl = request.getRequestURI().toString().split("/");
            String url = endOfUrl[endOfUrl.length-1] ;

            try {
                String classesDir = FrontServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                // classesDir = "/D:/ITU/Donnees_logiciels/apache-tomcat-10.0.22/webapps/Framework/WEB-INF/classes/";
                System.out.println("classes : "+classesDir);
                System.out.println("url : "+url);
                String view = new Utilitaire().getView("emp-add",classesDir);
                System.out.println(view);   

                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Erreur</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>classes :"+classesDir+"</h5>");
                    out.println("<h5>url : "+url+"</h5>");
                    out.println("<h5>view : "+view+"</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }

                // String absolut = this.getClass().getClassLoader().getResource("").getPath().toString();
                // String[] classesDir = absolut.split("/WEB-INF/classes/");
                // System.out.println("url : "+url);
                // System.out.println("Absolut : "+absolut);
                // System.out.println("classes : "+classesDir[classesDir.length-1]);
                
                // String view = new Utilitaire().getView(url,absolut);
                // System.out.println(view);   
            }  catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Erreur</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h5>Error</h5>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            // System.out.println("View: "+ mView.getView());
            // response.sendRedirect("Framework/"+mView.getView());
            // RequestDispatcher dispatch = request.getRequestDispatcher("Index.jsp");
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
            throws ServletException, IOException{
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
