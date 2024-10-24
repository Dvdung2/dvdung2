/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import model.DAOCategories;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesURL"})
public class CategoriesController extends HttpServlet {

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
        HttpSession session=request.getSession(true);
        DAOCategories dao = new DAOCategories();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteCategories")) {
                dao.deleteCategories(Integer.parseInt(request.getParameter("cid")));
                response.sendRedirect("CategoriesURL?service=listAllCategories");
            }
            if (service.equals("insertCategories")) {
                String submit=request.getParameter("submit");
                if(submit==null){
                    request.getRequestDispatcher("/view/insertCategory.jsp").forward(request, response);
                }
                if(submit!=null){
                //getdata
                String CategoryID = request.getParameter("CategoryID");
                String CategoryName = request.getParameter("CategoryName");
                String Description = request.getParameter("Description");
                String Picture = request.getParameter("Picture");

                //check data
                if (CategoryName.equals("")) {
                    out.println("Categories name is empty");
                }
                //convert
                int CategoryId = Integer.parseInt(CategoryID);
                byte[] PicturE = Picture.getBytes();
                Categories cat = new Categories(CategoryId, CategoryName, Description, PicturE);
                int n = dao.insertCategories(cat);
                response.sendRedirect("CategoriesURL?service=listAllCategories");
                }
            }
            if (service.equals("listAllCategories")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Categories";
                } else {
                    String cname = request.getParameter("cname");
                    sql = "select*from Categories where CategoryName like '%" + cname + "%'";
                }
                Vector<Categories> vector = dao.getCategories(sql);
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/view/listCategory.jsp").forward(request, response);
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
