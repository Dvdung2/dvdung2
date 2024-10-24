/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOrders;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderURL"})
public class OrderController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            DAOrders dao = new DAOrders();
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteOrder")) {
                dao.deleteOrders(Integer.parseInt(request.getParameter("oid")));
                response.sendRedirect("OrderURL?service=AllOrder");
            }
            if (service.equals("insertOrder")) {
                String submit=request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsCus = dao.getData("SELECT  [CustomerID] ,[ContactName] FROM Customers");
                    ResultSet rsEm = dao.getData("SELECT [EmployeeID],[LastName] FROM Employees");
                    ResultSet rsSh = dao.getData("SELECT [ShipperID],[CompanyName] FROM Shippers");
                    request.setAttribute("rsCus", rsCus);
                    request.setAttribute("rsEm", rsEm);
                    request.setAttribute("rsSh", rsSh);
                    request.getRequestDispatcher("/view/insertOrder.jsp").forward(request, response);
                }
                if(submit!=null){
                String CustomerID = request.getParameter("CustomerID");
                String EmployeeID = request.getParameter("EmployeeID");
                String OrderDate = request.getParameter("OrderDate");
                String RequiredDate = request.getParameter("RequiredDate");
                String ShippedDate = request.getParameter("ShippedDate");
                String ShipVia = request.getParameter("ShipVia");
                String Freight = request.getParameter("Freight");
                String ShipName = request.getParameter("ShipName");
                String ShipAddress = request.getParameter("ShipAddress");
                String ShipCity = request.getParameter("ShipCity");
                String ShipRegion = request.getParameter("ShipRegion");
                String ShipPostalCode = request.getParameter("ShipPostalCode");
                String ShipCountry = request.getParameter("ShipCountry");
                //convert
                int EmployeeId = Integer.parseInt(EmployeeID);
                int ShipViA = Integer.parseInt(ShipVia);
                Double FreighT = Double.parseDouble(Freight);
                Orders ord = new Orders(0, CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                dao.insertOrders(ord);
                response.sendRedirect("OrderURL?service=AllOrder");
                }
            }
            if (service.equals("AllOrder")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Orders";
                } else {
                    int order = Integer.parseInt(request.getParameter("order"));
                    sql = "select*from Orders where OrderID=" + order;
                }
                Vector<Orders> vector = dao.getOrders(sql);
                RequestDispatcher dis= request.getRequestDispatcher("/view/listOrder.jsp");
                request.setAttribute("listOrder", vector);
                request.setAttribute("titleTable", "list of order");
                dis.forward(request, response);
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
