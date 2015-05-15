/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbStamp;
import helpers.AccessHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author krzysztof
 */
@WebServlet(name = "StampEdit", urlPatterns = {"/StampEdit"})
public class StampEdit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if ((!AccessHelper.checkIfLoggedAsUser(session) && !AccessHelper.checkIfLoggedAsModerator(session)) )
        {
            response.sendRedirect("404");
        }
        else
        {
            HashMap<String, String> what = null;
            HashMap<String, String> where = null;
            Integer id = null;
            DbStamp db = new DbStamp();
            
            if (request.getParameter("id")!=null){
                String tmp = request.getParameter("id");
                id = Integer.parseInt( request.getParameter("id") );
                if (id!=null){
                    where = new HashMap<>();
                    where.put("id", id.toString());
                    what = db.get(where);        
                }
            }
            
            if (what != null && AccessHelper.checkIfLoggedInAs(session, what.get("user_id"))) {
                if ("POST".equals(request.getMethod()))
                {
                    boolean errors = false;
                    HashMap<String,String> postValues = new HashMap();
                    postValues.put("name", request.getParameter("name").trim());
                    postValues.put("print_year", request.getParameter("print_year").trim());
                    
                    for (Object value: postValues.values()){
                        if (((String)value).isEmpty()){
                            errors = true;
                            break;
                        }
                    }

                    postValues.put("notes", request.getParameter("notes"));
                    
                    if (!errors){
                        //TODO: przekazać msg dlaczego nie poszło i że nie poszło!!!
                        
                        boolean changesMade = false;
                        for (Map.Entry<String,String> e : postValues.entrySet()){
                            if ( what.get(e.getKey()).compareTo(e.getValue()) != 0 ){
                                changesMade = true;
                                break;
                            }
                        }
                        
                        if (changesMade){
                            boolean effect = db.update(postValues, where);
                            if (effect){
                                response.sendRedirect("StampEdit?id="+id);
                            }else{
                                request.setAttribute("page_title", "Nowy Znaczek - Niezapisany");
                                request.setAttribute("message", MessageHelper.generateDangerMessage("Znaczek nie zapisany!"));
                            }  
                        }
                        
                    }else{
                        request.setAttribute("stamp", what);
                        //what.putAll(postValues);
                        request.setAttribute("page_title", "Edytuj Znaczek");
                        RequestDispatcher rd = request.getRequestDispatcher("stamp_edit.jsp");
                        rd.forward(request, response);;
                    }
                }else{   
                    request.setAttribute("stamp", what);
                    request.setAttribute("page_title", "Edytuj Znaczek");
                    RequestDispatcher rd = request.getRequestDispatcher("stamp_edit.jsp");
                    rd.forward(request, response);
                }
            }else
                response.sendRedirect("404");
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
