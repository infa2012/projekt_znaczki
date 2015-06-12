/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author krzysztof
 */
@WebServlet(name = "GetImage", urlPatterns = {"/img"})
public class GetImage extends HttpServlet {
    String filePath;
    
    @Override
    public void init(){
        this.filePath = getServletContext().getInitParameter("file-upload");
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
        response.setContentType("image/jpeg");
        
        String no = request.getParameter("id");
        ServletOutputStream out = response.getOutputStream();
        
        if (no == null || no.isEmpty())
            no = "noimage.jpg";
        else
            no += ".jpg";
        
        
        FileInputStream fin;
        try{
            fin = new FileInputStream(filePath+no);
        }catch(Exception e){
            fin = new FileInputStream(filePath+"noimage.jpg");
        }
        System.out.println("zostalo bytow: "+fin.available());
                
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch = 0;
        while ((ch=bin.read())!=-1)
            bout.write(ch);
        
        bin.close();
        fin.close();
        bout.close();
        out.close();
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
