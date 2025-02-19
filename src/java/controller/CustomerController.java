/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCustomers;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerURL"})
public class CustomerController extends HttpServlet {

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
            DAOCustomers dao = new DAOCustomers();
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if(service.equals("loginCustomer")){
                 String submit =request.getParameter("submit");
                if(submit==null){
                    request.getRequestDispatcher("/view/loginCustomer.jsp").forward(request, response);
                }
                if(submit!=null){
                    Customers cus=dao.Login(request.getParameter("username"), request.getParameter("password"));
                    if(cus==null){
                        request.setAttribute("error", "login failed");
                        request.getRequestDispatcher("/view/loginCustomer.jsp").forward(request, response);
                    }else{
                        session.setAttribute("customer", cus);
                        response.sendRedirect("client");
                    }
                }
            }
            if(service.equals("logoutCustomer")){
                session.invalidate();
                response.sendRedirect("client");
            }
            if (service.equals("deleteCustomer")) {
                dao.deleteCustomers(request.getParameter("cid"));
                response.sendRedirect("CustomerURL?service=AllCustomer");
            }
            if (service.equals("insertCustomer")) {
                String submit =request.getParameter("submit");
                if(submit==null){
                    request.getRequestDispatcher("/view/insertCustomer.jsp").forward(request, response);
                }
                if(submit!=null){
                String CustomerID = request.getParameter("CustomerID");
                String CompanyName = request.getParameter("CompanyName");
                String ContactName = request.getParameter("ContactName");
                String ContactTitle = request.getParameter("ContactTitle");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String Phone = request.getParameter("Phone");
                String Fax = request.getParameter("Fax");
                Customers cus = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                int n = dao.insertCustomers(cus);
                response.sendRedirect("menu");
                }
            }
            if (service.equals("updateCustomer")) {
                String submit =request.getParameter("submit");
                if(submit==null){
                    String cid = request.getParameter("cid");
                    String sql = "select*from Customers where CustomerID like '%" + cid+"%'";
                    Vector<Customers> vector = dao.getCustomers(sql);
                    request.setAttribute("vector", vector);
                    request.getRequestDispatcher("/view/updateCustomer.jsp").forward(request, response);
                }
                if(submit!=null){
                String CustomerID = request.getParameter("CustomerID");
                String CompanyName = request.getParameter("CompanyName");
                String ContactName = request.getParameter("ContactName");
                String ContactTitle = request.getParameter("ContactTitle");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String Phone = request.getParameter("Phone");
                String Fax = request.getParameter("Fax");
                Customers cus = new Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                int n = dao.updateCustomers(cus);
                response.sendRedirect("CustomerURL?service=AllCustomer");
                }
            }
            if (service.equals("AllCustomer")) {
                String submit = request.getParameter("submit");
                String sql="";
                if (submit == null) {
                    sql = "select*from Customers";
                } else {
                    String cname = request.getParameter("cname");
                    sql = "select*from Customers where ContactName like '" + cname + "'";
                }
                Vector<Customers> vector = dao.getCustomers(sql);
                RequestDispatcher dis= request.getRequestDispatcher("/view/listCustomer.jsp");
                request.setAttribute("listCustomer", vector);
                request.setAttribute("titleTable", "list of customer");
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
