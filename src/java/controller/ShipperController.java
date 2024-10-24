package controller;

import entity.Shippers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOShippers;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "ShipperController", urlPatterns = {"/ShipperURL"})
public class ShipperController extends HttpServlet {

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
        DAOShippers dao = new DAOShippers();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteShipper")) {
                dao.deleteShippers(Integer.parseInt(request.getParameter("sid")));
                response.sendRedirect("ShipperURL?service=AllShipper");
            }
            if (service.equals("insertShipper")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/view/insertShipper.jsp").forward(request, response);
                }
                //getdata
                if (submit != null) {
                    String ShipperID = request.getParameter("ShipperID");
                    String CompanyName = request.getParameter("CompanyName");
                    String Phone = request.getParameter("Phone");
                    //check data
                    //convert
                    int ShipperId = Integer.parseInt(ShipperID);
                    Shippers sh = new Shippers(ShipperId, CompanyName, Phone);
                    int n = dao.insertShippers(sh);
                    response.sendRedirect("ShipperURL?service=AllShipper");
                }
            }
            if (service.equals("AllShipper")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Shippers";
                } else {
                    String cname = request.getParameter("cname");
                    sql = "select*from Shippers where CompanyName like '" + cname + "'";
                }
                Vector<Shippers> vector = dao.getShippers(sql);
                request.setAttribute("listShipper", vector);
                request.getRequestDispatcher("/view/listShipper.jsp").forward(request, response);
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
