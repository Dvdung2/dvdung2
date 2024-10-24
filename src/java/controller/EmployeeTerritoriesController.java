/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.EmployeeTerritories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOEmployeeTerritories;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "EmployeeTerritoriesController", urlPatterns = {"/EmployeeTerritoriesURL"})
public class EmployeeTerritoriesController extends HttpServlet {

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
        DAOEmployeeTerritories dao = new DAOEmployeeTerritories();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteEmployeeTerritories")) {
                dao.deleteEmployeeTerritories(Integer.parseInt(request.getParameter("eid")));
                response.sendRedirect("EmployeeTerritoriesURL?service=AllEmployeeTerritories");
            }
            if (service.equals("insertEmployeeTerritories")) {
                //getdata
                String EmployeeID = request.getParameter("EmployeeID");
                String TerritoryID = request.getParameter("TerritoryID");

                //check data
                //convert
                int EmployeeId= Integer.parseInt(EmployeeID);
                EmployeeTerritories re = new EmployeeTerritories(EmployeeId, TerritoryID);
                int n = dao.insertEmployeeTerritories(re);
                response.sendRedirect("EmployeeTerritoriesURL?service=AllEmployeeTerritories");
            }
            if (service.equals("AllEmployeeTerritories")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EmployeeTerritoriesController</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("""
                            <form action="EmployeeTerritoriesURL" method="get">
                                    <p>
                                        SearchName: <input type="text" name="emid" id="">
                                        <input type="submit" value="submit" name="submit">
                                        <input type="reset" value="clear">
                                        <input type="hidden" name="service" value="AllEmployeeTerritories">    
                                </p>
                                </form>""");
                String submit = request.getParameter("submit");
                out.println(" <p><a href=\"HTML/insertEmployeeTerritories.html\">insert EmployeeTerritories</a></p>");
                String sql = "";
                if (submit == null) {
                    sql = "select*from EmployeeTerritories";
                } else {
                    int emId = Integer.parseInt(request.getParameter("emid"));
                    sql = "select*from EmployeeTerritories where EmployeeID ="+emId ;
                }
                Vector<EmployeeTerritories> vector = dao.getEmployeeTerritories(sql);
                out.println("""
                            <table border=1  style=" background-color: aqua;">
                                    <tr>
                                        <th>CustomerID</th>
                                        <th>CustomerTypeID</th>
                                        <th>update</th>
                                        <th>delete</th>
                                    </tr>""");
                for (EmployeeTerritories et : vector) {
                    out.println("""
                                <tr>
                                            <td>""" + et.getEmployeeID()+ "</td>\n"
                            + "            <td>" + et.getTerritoryID()+ "</td>\n"
                            + "            <td></td>\n"
                            + "            <td><a href=\"EmployeeTerritoriesURL?service=deleteEmployeeTerritories&eid=" + et.getEmployeeID()+ "\">delete</a></td>\n"
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
