/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbUser;
import helpers.AccessHelper;
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
 * @author gohzno
 */
@WebServlet(name = "register", urlPatterns =
{
    "/register"
})
public class Register extends HttpServlet
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
        if (AccessHelper.checkIfLoggedAsUser(session) && AccessHelper.checkIfLoggedAsAdmin(session))
        {
            response.sendRedirect("404");
        }
        else
        {
            if ("POST".equals(request.getMethod()))
            {
                DbUser dbUser = new DbUser();
                boolean validate = true;
                String message = "";

                if (dbUser.checkIfEmailOccupied(request.getParameter("email")))
                {
                    validate = false;
                    message = "Wybrany adres e-mail jest już zajęty";
                }

                if (dbUser.checkIfLoginOccupied(request.getParameter("login")))
                {
                    validate = false;
                    message = "Wybrany login jest już zajęty";
                }

                if (validate)
                {
                    HashMap user = new HashMap();
                    user.put("login", request.getParameter("login"));
                    user.put("name", request.getParameter("name"));
                    user.put("password", request.getParameter("password"));
                    user.put("surname", request.getParameter("surname"));
                    user.put("email", request.getParameter("email"));
                    if (dbUser.create(user) != 0)
                    {
                        request.setAttribute("message", MessageHelper.generateSuccessMessage("Pomyślnie utworzono konto!"));
                    }
                    else
                    {
                        request.setAttribute("message", MessageHelper.generateDangerMessage("Wystąpił błąd!"));
                    }
                }
                else
                {
                    request.setAttribute("message", MessageHelper.generateDangerMessage(message));
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
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
