package controller;

import entity.Territories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOTerritories;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "TerritoriesController", urlPatterns = {"/TerritoriesURL"})
public class TerritoriesController extends HttpServlet {

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
        DAOTerritories dao = new DAOTerritories();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteTerritories")) {
                dao.deleteTerritories(Integer.parseInt(request.getParameter("tid")));
                response.sendRedirect("TerritoriesURL?service=AllTerritories");
            }
            if (service.equals("insertTerritories")) {
                //getdata
                String TerritoryID = request.getParameter("TerritoryID");
                String TerritoryDescription = request.getParameter("TerritoryDescription");
                String RegionID = request.getParameter("RegionID");

                //check data
                //convert
                int RegionId = Integer.parseInt(RegionID);
                Territories te = new Territories(TerritoryID, TerritoryDescription, RegionId);
                int n = dao.insertTerritories(te);
                response.sendRedirect("TerritoriesURL?service=AllTerritories");
            }
            if (service.equals("AllTerritories")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet TerritoriesController</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("""
                            <form action="TerritoriesURL" method="get">
                                    <p>
                                        SearchName: <input type="text" name="tname" id="">
                                        <input type="submit" value="submit" name="submit">
                                        <input type="reset" value="clear">
                                        <input type="hidden" name="service" value="AllTerritories">    
                                </p>
                                </form>""");
                String submit = request.getParameter("submit");
                out.println(" <p><a href=\"HTML/insertTerritories.html\">insert Territories</a></p>");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Territories";
                } else {
                    String tname = request.getParameter("tname");
                    sql = "select*from Territories where TerritoryDescription like '%" + tname + "%'";
                }
                Vector<Territories> vector = dao.getTerritories(sql);
                out.println("""
                            <table border=1  style=" background-color: aqua;">
                                    <tr>
                                        <th>TerritoryID</th>
                                        <th>TerritoryDescription</th>
                                        <th>RegionID</th>
                                        <th>update</th>
                                        <th>delete</th>
                                    </tr>""");
                for (Territories te : vector) {
                    out.println("""
                                <tr>
                                            <td>""" + te.getTerritoryID()+ "</td>\n"
                            + "            <td>" + te.getTerritoryDescription()+ "</td>\n"
                            + "            <td>" + te.getRegionID()+ "</td>\n"
                            + "            <td></td>\n"
                            + "            <td><a href=\"TerritoriesURL?service=deleteTerritories&tid=" + te.getTerritoryID()+ "\">delete</a></td>\n"
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
