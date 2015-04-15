/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbMessage;
import db.DbUser;
import helpers.AccessHelper;
import helpers.MessageHelper;
import helpers.SessionHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gohzno
 */
@WebServlet(name = "message_write", urlPatterns =
{
    "/message_write"
})
public class MessageWrite extends HttpServlet
{

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
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if ((!AccessHelper.checkIfLoggedAsUser(session) && !AccessHelper.checkIfLoggedAsModerator(session)) || !AccessHelper.checkGETParamNumberCanBeNull(request, "message_answer") || !AccessHelper.checkGETParamNumberNotNull(request, "recipient"))
        {
            response.sendRedirect("404");
        }
        else
        {
            DbMessage dbMessage = new DbMessage();
            boolean error = false;

            if (request.getParameter("message_answer") != null)
            {
                HashMap whereClause = new HashMap();
                whereClause.put("id", request.getParameter("message_answer"));
                whereClause.put("recipient", session.getAttribute("user_id"));
                whereClause.put("sender", request.getParameter("recipient"));

                HashMap messageToAnswer = dbMessage.get(whereClause);
                System.out.println(messageToAnswer);
                if (!messageToAnswer.isEmpty())
                {
                    request.setAttribute("messageToAnswer", messageToAnswer);
                }
                else
                {
                    error = true;
                }
            }

            if ("POST".equals(request.getMethod()))
            {
                HashMap values = new HashMap();
                values.put("topic", request.getParameter("topic"));
                values.put("content", request.getParameter("content"));
                values.put("sender", session.getAttribute("user_id"));
                values.put("recipient", request.getParameter("recipient"));

                if (dbMessage.create(values) != 0)
                {
                    request.setAttribute("message", MessageHelper.generateSuccessMessage("Pomyślnie wysłano wiadomość!"));
                }
                else
                {
                    request.setAttribute("message", MessageHelper.generateDangerMessage("Wystąpił błąd! Spróbuj ponownie później."));
                }
            }

            if (error == true)
            {
                response.sendRedirect("404");
            }
            else
            {
                LinkedList receivedMessages = dbMessage.getReceivedMessages(Integer.parseInt(session.getAttribute("user_id").toString()));
                LinkedList sendedMessages = dbMessage.getSendedMessages(Integer.parseInt(session.getAttribute("user_id").toString()));

                request.setAttribute("sendedMessages", sendedMessages);
                request.setAttribute("receivedMessages", receivedMessages);
                RequestDispatcher rd = request.getRequestDispatcher("message_write.jsp");
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
