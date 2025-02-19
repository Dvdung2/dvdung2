/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customers;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import model.DAOCustomers;
import model.DAOProduct;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "Client", urlPatterns = {"/client"})
public class Client extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String submit = request.getParameter("submit");
            DAOProduct dao = new DAOProduct();
            String sql = "";
            if (submit == null) {
                sql = "select*from products";
            } else {
                String pname = request.getParameter("pname");
                sql = "select*from products where ProductName like '%" + pname + "%'";
            }
            String submit1 = request.getParameter("submit1");
            if (submit1 != null) {
                int catID = Integer.parseInt(request.getParameter("CategoryID"));
                sql = "select*from products where CategoryID =" + catID;
            }
            String cid = request.getParameter("cid");
            if (cid != null) {
                int catID = Integer.parseInt(request.getParameter("cid"));
                sql = "select*from products where CategoryID =" + catID;
            }
            String sid = request.getParameter("sid");
            if (sid != null) {
                int supID = Integer.parseInt(sid);
                sql = "select*from products where SupplierID=" + supID;
            }
            String sort = request.getParameter("sort");
            if (sort != null) {
                sql = "select*from products order by UnitPrice " + sort;
            }
            ResultSet rsSup = dao.getData("SELECT  [SupplierID] ,[CompanyName] FROM Suppliers");
            ResultSet rsSup1 = dao.getData("SELECT  [SupplierID] ,[CompanyName] FROM Suppliers");
            ResultSet rsCat = dao.getData("SELECT [CategoryID],[CategoryName] FROM Categories");
            request.setAttribute("rsCat", rsCat);
            request.setAttribute("rsSup", rsSup);
            Vector<Product> vector = dao.getProducts(sql);
            HashMap<Integer, String> hsSup = new HashMap<>();
            try {
                while (rsSup1.next()) {
                    hsSup.put(rsSup1.getInt(1), rsSup1.getString(2));
                }
            } catch (Exception e) {
            }
            request.setAttribute("hsSup", hsSup);
            //select view
            //set data for view
            request.setAttribute("listProduct", vector);
            request.setAttribute("titleTable", "list of product");
            //run
            RequestDispatcher dis = request.getRequestDispatcher("/view/client.jsp");
            dis.forward(request, response);
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
