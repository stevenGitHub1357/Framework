/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1889.framework.servlet;

import etu1889.framework.Mapping;
import etu1889.framework.ModelView;
import etu1889.framework.Utilitaire;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ITU
 */
public class FrontServlet extends HttpServlet {
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

    public String setterName(String attr) {
        return "set" + attr.substring(0, 1).toUpperCase() + attr.substring(1);
    }

    public void callSetter(Method theSetter, Object objectInst, Object objectVal) {
        try {
            if (objectVal instanceof Integer) {
                //int intValue = (int) objectVal; // cast to int
                theSetter.invoke(objectInst, (int) objectVal);
            } else if (objectVal instanceof Double) {
                //double doubleValue = (double) objectVal; // cast to double
                theSetter.invoke(objectInst, (double) objectVal);
            } else if (objectVal instanceof Date) {
                //Date dateValue = (Date) objectVal; // cast to Date
                theSetter.invoke(objectInst, (Date) objectVal);
            } else {
                //String stringValue = (String) objectVal; // cast to String
                theSetter.invoke(objectInst, (String) objectVal);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

    public void fillAttribute(HttpServletRequest request, Object objectInst) {
        try {
            Field[] attributs = objectInst.getClass().getDeclaredFields();
            for(Field field : attributs) {
                String value = request.getParameter(field.getName());
                if(value != null) {
                    Method setter = objectInst.getClass().getMethod(setterName(field.getName()), field.getType());
                    Object attr = new Utilitaire().castToAppropriateClass(value, field.getType());
                    //setter.invoke(objectInst, value);
                    callSetter(setter, objectInst, attr);

                    System.out.println(field.getName() + ": " + value);
                    System.out.println("we will call --> " + setterName(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
      }
    }

    public Class<?>[] getParameterType(Method[] methods, String methodeName) {
        for (Method method : methods) {
            if(method.getName() == methodeName) {
                return method.getParameterTypes();
            }
        }

        return null;
    }

    public Object[] getParameterValues(HttpServletRequest request, Parameter[] args) {
        Object[] valueArgs = new Object[args.length];

        for (int i=0; i<args.length; i++) {
            System.out.println(">>> Attribut : " + args[i].getName());
            valueArgs[i] = new Utilitaire().castToAppropriateClass(request.getParameter(args[i].getName()), args[i].getType());
        }

        return valueArgs;
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
          try {
                String[] url = new Utilitaire().getDataFromURL(request.getRequestURI());
                String slug = url[url.length - 1];
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/index.html");
                if(this.mappingUrls.containsKey(slug)) {

                    System.out.println("url : " + slug);

                    Mapping relative = mappingUrls.get(slug);

                    Class<?> classInstance = Class.forName(relative.getClassName());
                    Object objectInstance = classInstance.newInstance();
                    fillAttribute(request, objectInstance);
                    
                    Class<?>[] functionParameters = getParameterType(classInstance.getDeclaredMethods(), relative.getMethode());
                    Method function = objectInstance.getClass().getMethod(relative.getMethode(), functionParameters);
                    Parameter[] args = function.getParameters();
                    Object[] valueArgs = getParameterValues(request, args);

                    System.out.println("*** Nb de parametre: " + args.length);

                    ModelView view = (ModelView) function.invoke(objectInstance, valueArgs);
                    dispatcher = request.getRequestDispatcher("/pages/" + view.getView());

                       System.out.println("countData : " + view.getData().size());
                       System.out.println("modelView : " + view.getView());

                    for(HashMap.Entry<String, Object> entry : view.getData().entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                        System.out.println("key : " + entry.getKey() + "\t value: " + entry.getValue());
                    }
               }
               dispatcher.forward(request, response);
        } catch (Exception e) {
              System.out.println("Tsy itany ilay page");
              e.printStackTrace();
        }
           
//        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    public  void init() {
        this.mappingUrls = new HashMap<String, Mapping>();
        new Utilitaire().fillMappingUrlValues(this.mappingUrls);
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
