/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.DbCollection;
import db.DbStamp;
import helpers.AccessHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author krzysztof
 */
@WebServlet(name = "StampCollection", urlPatterns = {"/MyStamps"})
public class CollectionList extends HttpServlet {

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
        if (!AccessHelper.checkIfLoggedAsUser(session) && !AccessHelper.checkIfLoggedAsModerator(session))
        {
            response.sendRedirect("404");
        }
        else
        {
            int uid = Integer.parseInt(session.getAttribute("user_id").toString());
            DbStamp dbs = new DbStamp();
            DbCollection dbc = new DbCollection();
            
            request.setAttribute("page_title", "Znaczki w kolekcji");
            
            LinkedList userStamps = dbs.getUserStamps(uid);
            request.setAttribute("stamps_in_collection", userStamps);
            
            LinkedList userCollections = dbc.allCollectionsStats(uid);
            request.setAttribute("collections", userCollections);
            
            int allStampsNum = dbs.getUserStampNum(uid);
            request.setAttribute("allStampsNum", allStampsNum);
            
            int activeCollection = -1;
            request.setAttribute("activeCol", activeCollection);
            
            RequestDispatcher rd = request.getRequestDispatcher("collections_list.jsp");
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
