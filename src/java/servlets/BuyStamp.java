/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbStamp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oghur
 */
@WebServlet(name = "BuyStamp", urlPatterns = {"/BuyStamp"})
public class BuyStamp extends HttpServlet {

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
        String userIdStr = String.valueOf(session.getAttribute("user_id"));
        int userId = 0;
        if (userIdStr != null && !userIdStr.equals("")) {
            userId = Integer.parseInt(userIdStr);
        }
        String stampIdStr = request.getParameter("stampid");
        int stampId = 0;
        if (stampIdStr != null && !stampIdStr.equals("")) {
            stampId = Integer.parseInt(stampIdStr);
        }
        DbStamp stamp = new DbStamp();
        HashMap<String, String> values = new HashMap();
        HashMap<String, String> where = new HashMap();

        values.put("user_id", userIdStr);
        values.put("price", "0");
        where.put("id", stampIdStr);
        String message;
        boolean updateValue = stamp.update(values, where);
        if (updateValue = true) {
            message = "Znaczek został dodany do twojej kolekcji";
        } else {
            message = "Wystąpił błąd podczas transakcji";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("stamp_buymessage.jsp").forward(request, response);
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
