/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employees;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.util.Vector;
import model.DAOEmployees;
import java.sql.ResultSet;

/**
 *
 * @author dvdung
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeURL"})
public class EmployeeController extends HttpServlet {

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
            DAOEmployees dao = new DAOEmployees();
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service.equals("deleteEmployee")) {
                dao.deleteEmployees(Integer.parseInt(request.getParameter("eid")));
                response.sendRedirect("EmployeeURL?service=AllEmployee");
            }
            if(service.equals("loginEmployee")){
                String submit= request.getParameter("submit");
                if(submit==null){
                    request.getRequestDispatcher("/view/loginEmployee.jsp").forward(request, response);
                }else{
                    Employees emp=dao.login(request.getParameter("username"),Integer.parseInt(request.getParameter("password")));
                    if(emp==null){
                        request.setAttribute("error", "login failed");
                        request.getRequestDispatcher("/view/loginEmployee.jsp").forward(request, response);
                    }else{
                        session.setAttribute("Employee", emp);
                        response.sendRedirect("admin");
                    }
                }
            }
            if(service.equals("logoutEmployee")){
                session.invalidate();
                response.sendRedirect("EmployeeURL?service=AllEmployee");
            }
            if (service.equals("insertEmployee")) {
                String submit= request.getParameter("submit");
                if(submit==null){
                    ResultSet rsEm= dao.getData("SELECT  [EmployeeID] ,[LastName] FROM Employees"); 
                    request.setAttribute("rsEm", rsEm);
                    request.getRequestDispatcher("/view/insertEmployee.jsp").forward(request, response);
                }
                if(submit!=null){
                String LastName=request.getParameter("LastName");
                String FirstName=request.getParameter("FirstName");
                String Title=request.getParameter("Title");
                String TitleOfCourtesy=request.getParameter("TitleOfCourtesy");
                String BirthDate=request.getParameter("BirthDate");
                String HireDate=request.getParameter("HireDate");
                String Address=request.getParameter("Address");
                String City=request.getParameter("City");
                String Region=request.getParameter("Region");
                String PostalCode=request.getParameter("PostalCode");
                String Country=request.getParameter("Country");
                String HomePhone=request.getParameter("HomePhone");
                String Extension=request.getParameter("Extension");
                String Photo=request.getParameter("Photo");
                String Notes=request.getParameter("Notes");
                String ReportsTo=request.getParameter("ReportsTo");
                String PhotoPath=request.getParameter("PhotoPath");
                //convert
                byte[] PhotO = Photo.getBytes();
                int ReportsTO =Integer.parseInt(ReportsTo);
                Employees em=new Employees(0, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, PhotO, Notes, ReportsTO, PhotoPath);
                dao.insertEmployees(em);
                response.sendRedirect("EmployeeURL?service=AllEmployee");
                }
            }
            if (service.equals("updateEmployee")) {
                String submit= request.getParameter("submit");
                if(submit==null){
                    int eid=Integer.parseInt(request.getParameter("eid"));
                    String sql="SELECT  * FROM Employees where EmployeeID="+eid;
                    ResultSet rsEm= dao.getData("SELECT  [EmployeeID] ,[LastName] FROM Employees"); 
                    Vector<Employees> vector= dao.getEmployees(sql);
                    request.setAttribute("vector", vector);
                    request.setAttribute("rsEm", rsEm);
                    request.getRequestDispatcher("/view/updateEmployee.jsp").forward(request, response);
                }
                if(submit!=null){
                String EmployeeID=request.getParameter("EmployeeID");
                String LastName=request.getParameter("LastName");
                String FirstName=request.getParameter("FirstName");
                String Title=request.getParameter("Title");
                String TitleOfCourtesy=request.getParameter("TitleOfCourtesy");
                String BirthDate=request.getParameter("BirthDate");
                String HireDate=request.getParameter("HireDate");
                String Address=request.getParameter("Address");
                String City=request.getParameter("City");
                String Region=request.getParameter("Region");
                String PostalCode=request.getParameter("PostalCode");
                String Country=request.getParameter("Country");
                String HomePhone=request.getParameter("HomePhone");
                String Extension=request.getParameter("Extension");
                String Photo=request.getParameter("Photo");
                String Notes=request.getParameter("Notes");
                String ReportsTo=request.getParameter("ReportsTo");
                String PhotoPath=request.getParameter("PhotoPath");
                //convert
                int EmployeeId=Integer.parseInt(EmployeeID);
                byte[] PhotO = Photo.getBytes();
                int ReportsTO =Integer.parseInt(ReportsTo);
                Employees em=new Employees(EmployeeId, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, PhotO, Notes, ReportsTO, PhotoPath);
                dao.updateEmployee(em);
                response.sendRedirect("EmployeeURL?service=AllEmployee");
                }
            }
            if (service.equals("AllEmployee")) {
                String submit = request.getParameter("submit");
                String sql = "";
                if (submit == null) {
                    sql = "select*from Employees";
                } else {
                    String ename = request.getParameter("ename");
                    sql = "select*from Employees where LastName like '" + ename + "'";
                }
                Vector<Employees> vector = dao.getEmployees(sql);
                RequestDispatcher dis= request.getRequestDispatcher("/view/listEmployee.jsp");
                request.setAttribute("listEmployee", vector);
                request.setAttribute("titleTable", "list of employee");
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
