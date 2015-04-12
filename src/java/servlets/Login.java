/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbUser;
import helpers.AccessHelper;
import helpers.MessageHelper;
import helpers.SessionHelper;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author gohzno
 */
@WebServlet(name = "login", urlPatterns =
{
    "/login"
})
public class Login extends HttpServlet
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
        if (AccessHelper.checkIfLoggedAsSomeone(session))
        {
            response.sendRedirect("404");
        }
        else
        {                                   
            boolean login_success = false;
            if ("POST".equals(request.getMethod()))
            {
                DbUser dbUser = new DbUser();

                HashMap fetchedUser = dbUser.accountAuthetication(request.getParameter("login"), DigestUtils.sha1Hex(request.getParameter("password")));
                if (fetchedUser == null)
                {
                    request.setAttribute("message", MessageHelper.generateDangerMessage("Wpisane dane są niepoprawne lub dane konto nie istnieje! <a href='register' class='alert-link'>Załóz konto na stronie rejestracji!</a>"));
                }
                else
                {
                    SessionHelper.logInto(session, fetchedUser);
                    login_success = true;
                }
            }

            if (login_success)
            {
                response.sendRedirect("main");
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
