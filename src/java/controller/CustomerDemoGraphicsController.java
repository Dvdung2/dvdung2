/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CustomerDemoGraphics;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomerDemoGraphics;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "CustomerDemoGraphicsController", urlPatterns = {"/CustomerDemoGraphicsURL"})
public class CustomerDemoGraphicsController extends HttpServlet {

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
        DAOCustomerDemoGraphics dao = new DAOCustomerDemoGraphics();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteCustomerDemoGraphics")) {
                dao.deleteCustomGraphics(request.getParameter("ctid"));
                response.sendRedirect("CustomerDemoGraphicsURL?service=AllCustomerDemoGraphics");
            }
            if (service.equals("insertCustomerDemoGraphics")) {
                //getdata
                String CustomerTypeID = request.getParameter("CustomerTypeID");
                String CustomerDesc = request.getParameter("CustomerDesc");

                //check data
                //convert
                CustomerDemoGraphics re = new CustomerDemoGraphics(CustomerTypeID, CustomerDesc);
                int n = dao.insertCustomGraphics(re);
                response.sendRedirect("CustomerDemoGraphicsURL?service=AllCustomerDemoGraphics");
            }
            if (service.equals("AllCustomerDemoGraphics")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet CustomerDemoGraphicsController</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("""
                            <form action="CustomerDemoGraphicsURL" method="get">
                                    <p>
                                        SearchName: <input type="text" name="customtypeid" id="">
                                        <input type="submit" value="submit" name="submit">
                                        <input type="reset" value="clear">
                                        <input type="hidden" name="service" value="AllCustomerDemoGraphics">    
                                </p>
                                </form>""");
                String submit = request.getParameter("submit");
                out.println(" <p><a href=\"HTML/insertCustomerDemoGraphics.html\">insert CustomerDemoGraphics</a></p>");
                String sql = "";
                if (submit == null) {
                    sql = "select*from CustomerDemoGraphics";
                } else {
                    int customTypeid = Integer.parseInt(request.getParameter("customtypeid"));
                    sql = "select*from CustomerDemoGraphics where CustomerTypeID like '%" + customTypeid+"%'";
                }
                Vector<CustomerDemoGraphics> vector = dao.getCustomGraphics(sql);
                out.println("""
                            <table border=1  style=" background-color: aqua;">
                                    <tr>
                                        <th>CustomerTypeID</th>
                                        <th>CustomerDesc</th>
                                        <th>update</th>
                                        <th>delete</th>
                                    </tr>""");
                for (CustomerDemoGraphics cdg : vector) {
                    out.println("""
                                <tr>
                                            <td>""" + cdg.getCustomerTypeID()+ "</td>\n"
                            + "            <td>" + cdg.getCustomerDesc()+ "</td>\n"
                            + "            <td></td>\n"
                            + "            <td><a href=\"CustomerDemoGraphicsURL?service=deleteCustomerDemoGraphics&ctid=" + cdg.getCustomerTypeID()+ "\">delete</a></td>\n"
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
