/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;
import java.sql.ResultSet;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteProduct")) {
                dao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsSup = dao.getData("SELECT  [SupplierID] ,[CompanyName] FROM Suppliers");
                    ResultSet rsCat = dao.getData("SELECT [CategoryID],[CategoryName] FROM Categories");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCat", rsCat);
                    request.getRequestDispatcher("/view/insertProduct.jsp").forward(request, response);
                }
                if (submit != null) {
                    //getdata
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data
                    if (ProductName.equals("")) {
                        out.println("product name is empty");
                    }
                    //convert
                    int SupplierId = Integer.parseInt(SupplierID);
                    int CategoryId = Integer.parseInt(CategoryID);
                    Double UnitPricE = Double.parseDouble(UnitPrice);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock), UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder), ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                    Product pro = new Product(0, ProductName,
                            SupplierId, CategoryId,
                            QuantityPerUnit, UnitPricE, UnitsInStocK,
                            UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                    int n = dao.addProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    String sql = "select*from products where ProductID=" + pid;
                    Vector<Product> vector = dao.getProducts(sql);
                    request.setAttribute("vector", vector);
                    ResultSet rsSup = dao.getData("SELECT  [SupplierID] ,[CompanyName] FROM Suppliers");
                    ResultSet rsCat = dao.getData("SELECT [CategoryID],[CategoryName] FROM Categories");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCat", rsCat);
                    request.getRequestDispatcher("/view/updateProduct.jsp").forward(request, response);
                }
                if (submit != null) {
                    //getdata
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data
                    if (ProductName.equals("")) {
                        out.println("product name is empty");
                    }
                    //convert
                    int productID = Integer.parseInt(request.getParameter("ProductID"));
                    int SupplierId = Integer.parseInt(SupplierID);
                    int CategoryId = Integer.parseInt(CategoryID);
                    Double UnitPricE = Double.parseDouble(UnitPrice);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock), UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder), ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                    Product pro = new Product(productID, ProductName,
                            SupplierId, CategoryId,
                            QuantityPerUnit, UnitPricE, UnitsInStocK,
                            UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                    int n = dao.updateProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }
            }

            if (service.equals("listAllProducts")) {//get request
                //call model-->get data
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select*from products where ProductName like '%" + pname + "%'";
                }
                String cid = request.getParameter("cid");
                if (cid != null) {
                    int catID = Integer.parseInt(request.getParameter("cid"));
                    sql = "select*from products where CategoryID ="+catID;
                }
                String sid = request.getParameter("sid");
                if (sid != null) {
                    int supID = Integer.parseInt(sid);
                    sql = "select*from products where SupplierID="+supID;
                }
                String sort = request.getParameter("sort");
                if (sort != null) {
                    sql = "select*from products order by UnitPrice "+sort;
                }
                ResultSet rsSup = dao.getData("SELECT  [SupplierID] ,[CompanyName] FROM Suppliers");
                ResultSet rsCat = dao.getData("SELECT [CategoryID],[CategoryName] FROM Categories");
                request.setAttribute("rsCat", rsCat);
                request.setAttribute("rsSup", rsSup);
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher dis
                        = request.getRequestDispatcher("/view/listProduct.jsp");
                //set data for view
                request.setAttribute("listProduct", vector);
                request.setAttribute("titleTable", "list of product");
                //run
                dis.forward(request, response);
            }
            if (service.equals("displayByCat")) {//get request
                //call model-->get data
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select*from products where ProductName like '%" + pname + "%'";
                }
                ResultSet rsCat = dao.getData("SELECT [CategoryID],[CategoryName] FROM Categories");
                request.setAttribute("rsCat", rsCat);
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher dis
                        = request.getRequestDispatcher("/view/displayProductByCat.jsp");
                //set data for view
                request.setAttribute("listProduct", vector);
                request.setAttribute("titleTable", "list of product");
                //run
                dis.forward(request, response);
            }
            if (service.equals("displayBySup")) {//get request
                //call model-->get data
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select*from products where ProductName like '%" + pname + "%'";
                }
                ResultSet rsSup = dao.getData("SELECT  [SupplierID] ,[CompanyName] FROM Suppliers");
                request.setAttribute("rsSup", rsSup);
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher dis
                        = request.getRequestDispatcher("/view/displayProductBySup.jsp");
                //set data for view
                request.setAttribute("listProduct", vector);
                request.setAttribute("titleTable", "list of product");
                //run
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
