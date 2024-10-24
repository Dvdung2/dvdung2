/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Region;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAORegion;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "RegionController", urlPatterns = {"/RegionURL"})
public class RegionController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        DAORegion dao = new DAORegion();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteRegion")) {
                dao.deleteRegion(Integer.parseInt(request.getParameter("rid")));
                response.sendRedirect("RegionURL?service=AllRegion");
            }
            if (service.equals("insertRegion")) {
                String submit=request.getParameter("submit");
                if(submit==null){
                    request.getRequestDispatcher("/view/insertRegion.jsp").forward(request, response);
                }
                if(submit!=null){
                //getdata
                String RegionID = request.getParameter("RegionID");
                String RegionDescription = request.getParameter("RegionDescription");

                //check data
                //convert
                int RegionId = Integer.parseInt(RegionID);
                Region re = new Region(RegionId, RegionDescription);
                int n = dao.insertRegion(re);
                response.sendRedirect("RegionURL?service=AllRegion");
                }
            }
            if (service.equals("AllRegion")) {
                String submit = request.getParameter("submit");
                out.println(" <p><a href=\"HTML/insertRegion.html\">insert Region</a></p>");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Region";
                } else {
                    int region = Integer.parseInt(request.getParameter("region"));
                    sql = "select*from Region where RegionID=" + region;
                }
                Vector<Region> vector = dao.getRegion(sql);
                request.setAttribute("listRegion", vector);
                request.getRequestDispatcher("/view/listRegion.jsp").forward(request, response);
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
