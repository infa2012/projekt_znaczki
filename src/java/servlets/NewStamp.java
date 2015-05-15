/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbStamp;
import helpers.AccessHelper;
import helpers.FormHelper;
import helpers.MessageHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "NewStamp", urlPatterns = {"/NewStamp"})
public class NewStamp extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        if ((!AccessHelper.checkIfLoggedAsUser(session) && !AccessHelper.checkIfLoggedAsModerator(session)) )
        {
            response.sendRedirect("404");
        }
        else
        {
            if ("POST".equals(request.getMethod()))
            {
                boolean errors = false;
                HashMap postValues = new HashMap();
                postValues.put("name", request.getParameter("name").trim());
                postValues.put("print_year", request.getParameter("print_year").trim());
                                
                for (Object value: postValues.values()){
                    if (((String)value).isEmpty()){
                        errors = true;
                        break;
                    }
                }
                
                postValues.put("notes", request.getParameter("notes"));
                System.out.println(postValues.get("notes"));
                postValues.put("user_id", session.getAttribute("user_id"));
                
                if (!errors){
                    DbStamp db = new DbStamp();
                    int id = db.create(postValues);
                    
                    //try me
                    if (id>0){
                        request.setAttribute("page_title", "Nowy Znaczek - Dodany");
                        request.setAttribute("message", MessageHelper.generateSuccessMessage("Znaczek zapisany!"));
                    }else{
                        request.setAttribute("page_title", "Nowy Znaczek - Niezapisany");
                        request.setAttribute("message", MessageHelper.generateDangerMessage("Znaczek nie zapisany!"));
                    }
                    
                    RequestDispatcher rd = request.getRequestDispatcher("stamp_edit.jsp");
                    rd.forward(request, response);
                }else{
                    response.sendRedirect("404");
                }
            }
            else
            {
                request.setAttribute("page_title", "Nowy Znaczek");
                RequestDispatcher rd = request.getRequestDispatcher("stamp_edit.jsp");
                rd.forward(request, response);
            }
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
