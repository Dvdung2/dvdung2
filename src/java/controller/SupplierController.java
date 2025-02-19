package controller;

import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Stack;
import java.util.Vector;
import model.DAOSuppliers;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "SupplierController", urlPatterns = {"/SupplierURL"})
public class SupplierController extends HttpServlet {

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
        DAOSuppliers dao = new DAOSuppliers();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteSupplier")) {
                dao.deleteSuppliers(Integer.parseInt(request.getParameter("sid")));
                response.sendRedirect("SupplierURL?service=AllSupplier");
            }
            if (service.equals("insertSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/view/insertSupplier.jsp").forward(request, response);
                }
                if (submit != null) {
                    //getdata
                    String SupplierID = request.getParameter("SupplierID");
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
                    String HomePage = request.getParameter("HomePage");

                    //check data
                    //convert
                    int SupplierId = Integer.parseInt(SupplierID);
                    Suppliers sup = new Suppliers(SupplierId, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage);
                    int n = dao.insertSuppliers(sup);
                    response.sendRedirect("SupplierURL?service=AllSupplier");
                }
            }
            if (service.equals("updateSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int supId=Integer.parseInt(request.getParameter("sid"));
                    Vector<Suppliers> vector=dao.getSuppliers("select*from Suppliers where SupplierID="+supId);
                    request.setAttribute("vector", vector);
                    request.getRequestDispatcher("/view/updateSuplier.jsp").forward(request, response);
                }
                if (submit != null) {
                    //getdata
                    String SupplierID = request.getParameter("SupplierID");
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
                    String HomePage = request.getParameter("HomePage");

                    //check data
                    //convert
                    int SupplierId = Integer.parseInt(SupplierID);
                    Suppliers sup = new Suppliers(SupplierId, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage);
                    int n = dao.updateSuppliers(sup);
                    response.sendRedirect("SupplierURL?service=AllSupplier");
                }
            }
            if (service.equals("AllSupplier")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Suppliers";
                } else {
                    String cname = request.getParameter("cname");
                    sql = "select*from Suppliers where CompanyName like '%" + cname + "%'";
                }
                Vector<Suppliers> vector = dao.getSuppliers(sql);
                request.setAttribute("listSupplier", vector);
                request.getRequestDispatcher("/view/listSupplier.jsp").forward(request, response);
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
