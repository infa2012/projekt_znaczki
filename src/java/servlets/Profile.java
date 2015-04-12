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
@WebServlet(name = "profile", urlPatterns =
{
    "/profile"
})
public class Profile extends HttpServlet
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

        if (!AccessHelper.checkIfLoggedAsSomeone(session))
        {
            response.sendRedirect("404");
        }
        else
        {
            DbUser dbUser = new DbUser();
            HashMap whereClause = new HashMap();
            whereClause.put("id", session.getAttribute("user_id").toString());

            if ("POST".equals(request.getMethod()))
            {
                boolean validate = true;
                String message = "";
                HashMap userProfileData = dbUser.get(whereClause);

                if (!userProfileData.get("email").toString().equals(request.getParameter("email")) && dbUser.checkIfEmailOccupied(request.getParameter("email")))
                {
                    validate = false;
                    message = "Wybrany adres e-mail jest już zajęty";
                }

                if (!userProfileData.get("login").toString().equals(request.getParameter("login")) && dbUser.checkIfLoginOccupied(request.getParameter("login")))
                {
                    validate = false;
                    message = "Wybrany login jest już zajęty";
                }

                if (validate)
                {
                    HashMap userPostedFormData = new HashMap();
                    userPostedFormData.put("login", request.getParameter("login"));
                    userPostedFormData.put("name", request.getParameter("name"));
                    userPostedFormData.put("surname", request.getParameter("surname"));
                    userPostedFormData.put("email", request.getParameter("email"));
                    userPostedFormData.put("address", request.getParameter("address"));
                    userPostedFormData.put("city", request.getParameter("city"));
                    userPostedFormData.put("country", request.getParameter("country"));

                    if (dbUser.update(userPostedFormData, whereClause))
                    {
                        request.setAttribute("message", MessageHelper.generateSuccessMessage("Pomyślnie zmieniono dane!"));
                    }
                    else
                    {
                        request.setAttribute("message", MessageHelper.generateDangerMessage("Wystąpił niespodziewany błąd!"));
                    }
                }
                else
                {
                    request.setAttribute("message", MessageHelper.generateDangerMessage(message));
                }
            }

            HashMap userProfileData = dbUser.get(whereClause);
            request.setAttribute("user", userProfileData);
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
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
