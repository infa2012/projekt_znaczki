/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import com.sun.xml.internal.ws.api.message.Packet;
import db.DbCollection;
import helpers.AccessHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "CollectionEdit", urlPatterns = {"/CollectionEdit"})
public class CollectionEdit extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        if ((!AccessHelper.checkIfLoggedAsUser(session) && !AccessHelper.checkIfLoggedAsModerator(session)) )
        {
            response.sendRedirect("404");
        }
        else
        { 
            HashMap<String,Object> stampCol = new HashMap<>();

            int id = -1;
            if (request.getParameter("id") != null)
                try{
                id = Integer.parseInt(request.getParameter("id"));
                }catch(NumberFormatException nfe){};
                
            if (id!=-1) // edycja
            {
                DbCollection dc = new DbCollection();
                HashMap where = new HashMap();
                where.put("id", id);
                stampCol.putAll(dc.get(where));
                
//                for (Map.Entry e : stampCol.entrySet())
//                    System.out.println(e.getKey()+" : "+e.getValue());
                
                if (stampCol.get("user_id").equals(session.getAttribute("user_id"))){
                    request.setAttribute("col", stampCol);
                    request.setAttribute("page_title", "Edycja kolekcji "+stampCol.get("name"));
                    jspForwardRequest(request, response);
                }else{ // użytkownik nie jest właścicielem
                    response.sendRedirect("404");
                }
                    
            }else{ // nowa 
                
                HashMap emptyHashMap = new HashMap();
                emptyHashMap.put("id","");
                emptyHashMap.put("name","");
                emptyHashMap.put("user_id","");
                
                request.setAttribute("page_title", "Nowa Kolekcja");
                request.setAttribute("col", emptyHashMap);
                
                jspForwardRequest(request, response);
            }
            

        }
       
    }
    
    protected void jspForwardRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("collection_edit.jsp");
        rd.forward(request, response);
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
        HttpSession session = request.getSession();
        if ((!AccessHelper.checkIfLoggedAsUser(session) && !AccessHelper.checkIfLoggedAsModerator(session)) )
        {
            response.sendRedirect("404");
        }
        else
        { 
            int sUserId, id;
            sUserId = Integer.parseInt( session.getAttribute("user_id").toString() );
            DbCollection dbc = new DbCollection();
            
            if (request.getParameter("id") != null) // to edycja
            {
                id = Integer.parseInt( request.getParameter("id") );
                HashMap where = new HashMap();
                where.put("id", id);
                HashMap stampCol = dbc.get(where);
                if (Integer.parseInt( stampCol.get("user_id").toString() )==sUserId) // zgadza się własność
                {
                    System.out.println("Kasuje="+request.getParameter("remove"));
                    if (request.getParameter("remove") != null ) { //polecenie zniszczenia kolekcji
                        
                        dbc.delete(where);
                        response.sendRedirect("MyStamps");
                    }else{
                        if (!request.getParameter("name").equals(stampCol.get("name"))){ // save name
                            HashMap what = new HashMap();
                            what.put("name", request.getParameter("name"));
                            dbc.update(where, what);
                        }
                        response.sendRedirect("MyStamps");
                    }
                }else{ // to cudzy znaczek
                    response.sendRedirect("404");
                }
            }else{ // to świeże dodanie
                HashMap what = new HashMap();
                String name = "";
                if (request.getParameter("name")!=null)
                    name = request.getParameter("name");
                
                name = name.trim();
                if (!name.isEmpty()){
                    what.put("name", name);
                    what.put("user_id", sUserId);   
                    dbc.create(what);
                }
                response.sendRedirect("MyStamps");
            }
        }
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
