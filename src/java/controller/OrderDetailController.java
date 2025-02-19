/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.sql.ResultSet;
import model.DAOOrderDetails;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetailURL"})
public class OrderDetailController extends HttpServlet {

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
        DAOOrderDetails dao = new DAOOrderDetails();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteOrderDetail")) {
                dao.deleteOrderDetails(Integer.parseInt(request.getParameter("oid")), Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("OrderDetailURL?service=listAllOrderDetail");
            }
            if (service.equals("insertOrderDetail")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsOrd = dao.getData("SELECT  [OrderID] FROM [SE1899].[dbo].[Orders]");
                    ResultSet rsPro = dao.getData("SELECT [ProductID],[ProductName] FROM [SE1899].[dbo].[Products]");
                    request.setAttribute("rsOrd", rsOrd);
                    request.setAttribute("rsPro", rsPro);
                    request.getRequestDispatcher("/view/insertOrderDetail.jsp").forward(request, response);
                }
                if (submit != null) {
                    //getdata
                    String OrderID = request.getParameter("OrderID");
                    String ProductID = request.getParameter("ProductID");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String Quantity = request.getParameter("Quantity");
                    String Discount = request.getParameter("Discount");

                    //check data
                    //convert
                    int OrderId = Integer.parseInt(OrderID);
                    int ProductId = Integer.parseInt(ProductID);
                    Double UnitPricE = Double.parseDouble(UnitPrice);
                    int QuantitY = Integer.parseInt(Quantity);
                    Double DiscounT = Double.parseDouble(Discount);
                    int n = dao.insertOrderDetails(new OrderDetails(OrderId, ProductId, UnitPricE, QuantitY, DiscounT));
                    response.sendRedirect("OrderDetailURL?service=listAllOrderDetail");
                }
            }
            if (service.equals("updateOrderDetail")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int oid = Integer.parseInt(request.getParameter("oid"));
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    String sql = "select*from [Order Details] WHERE OrderID =" + oid + " AND ProductID =" + pid;
                    Vector<OrderDetails> vector1 = dao.getOrderDetails(sql);
                    request.setAttribute("vector", vector1);
                    ResultSet rsOrd = dao.getData("SELECT  [OrderID] FROM [SE1899].[dbo].[Orders]");
                    ResultSet rsPro = dao.getData("SELECT [ProductID],[ProductName] FROM [SE1899].[dbo].[Products]");
                    request.setAttribute("rsOrd", rsOrd);
                    request.setAttribute("rsPro", rsPro);
                    request.getRequestDispatcher("/view/updateOrderDetail.jsp").forward(request, response);
                }
                if (submit != null) {
                    //getdata
                    String OrderID = request.getParameter("OrderID");
                    String ProductID = request.getParameter("ProductID");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String Quantity = request.getParameter("Quantity");
                    String Discount = request.getParameter("Discount");

                    //check data
                    //convert
                    int OrderId = Integer.parseInt(OrderID);
                    int ProductId = Integer.parseInt(ProductID);
                    Double UnitPricE = Double.parseDouble(UnitPrice);
                    int QuantitY = Integer.parseInt(Quantity);
                    Double DiscounT = Double.parseDouble(Discount);
                    int n = dao.updateOrderDetails(new OrderDetails(OrderId, ProductId, UnitPricE, QuantitY, DiscounT));
                    response.sendRedirect("OrderDetailURL?service=listAllOrderDetail");
                }
            }
            if (service.equals("listAllOrderDetail")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from [Order Details]";
                } else {
                    int order = Integer.parseInt(request.getParameter("order"));
                    sql = "select*from [Order Details] where OrderID=" + order;
                }
                Vector<OrderDetails> vector = dao.getOrderDetails(sql);
                request.setAttribute("listOrderDetail", vector);
                request.getRequestDispatcher("/view/listOrderDetail.jsp").forward(request, response);
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
