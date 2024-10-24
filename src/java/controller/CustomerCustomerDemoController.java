/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CustomerCustomerDemo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomerCustomerDemo;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "CustomerCustomerDemoController", urlPatterns = {"/CustomerCustomerDemoURL"})
public class CustomerCustomerDemoController extends HttpServlet {

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
        DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteCustomerCustomerDemo")) {
                dao.deleteCustomDemo(Integer.parseInt(request.getParameter("cid")));
                response.sendRedirect("CustomerCustomerDemoURL?service=AllCustomerCustomerDemo");
            }
            if (service.equals("insertCustomerCustomerDemo")) {
                //getdata
                String CustomerTypeID = request.getParameter("CustomerTypeID");
                String CustomerID = request.getParameter("CustomerID");

                //check data
                //convert
                CustomerCustomerDemo re = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                int n = dao.insertCustomDemo(re);
                response.sendRedirect("CustomerCustomerDemoURL?service=AllCustomerCustomerDemo");
            }
            if (service.equals("AllCustomerCustomerDemo")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet CustomerCustomerDemoController</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("""
                            <form action="CustomerCustomerDemoURL" method="get">
                                    <p>
                                        SearchName: <input type="text" name="customid" id="">
                                        <input type="submit" value="submit" name="submit">
                                        <input type="reset" value="clear">
                                        <input type="hidden" name="service" value="AllCustomerCustomerDemo">    
                                </p>
                                </form>""");
                String submit = request.getParameter("submit");
                out.println(" <p><a href=\"HTML/insertCustomerCustomerDemo.html\">insert CustomerCustomerDemo</a></p>");
                String sql = "";
                if (submit == null) {
                    sql = "select*from CustomerCustomerDemo";
                } else {
                    int customid = Integer.parseInt(request.getParameter("customtypeid"));
                    sql = "select*from CustomerCustomerDemo where CustomerID like '%" + customid+"%'";
                }
                Vector<CustomerCustomerDemo> vector = dao.getCustomerDemo(sql);
                out.println("""
                            <table border=1  style=" background-color: aqua;">
                                    <tr>
                                        <th>CustomerID</th>
                                        <th>CustomerTypeID</th>
                                        <th>update</th>
                                        <th>delete</th>
                                    </tr>""");
                for (CustomerCustomerDemo ccd : vector) {
                    out.println("""
                                <tr>
                                            <td>""" + ccd.getCustomerID()+ "</td>\n"
                            + "            <td>" + ccd.getCustomerTypeID()+ "</td>\n"
                            + "            <td></td>\n"
                            + "            <td><a href=\"CustomerCustomerDemoURL?service=deleteCustomerCustomerDemo&cid=" + ccd.getCustomerID()+ "\">delete</a></td>\n"
                            + "        </tr>");
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
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
