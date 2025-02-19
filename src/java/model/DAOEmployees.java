/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.logging.Logger;
import entity.Employees;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dvdung
 */
public class DAOEmployees extends DBConnect {
    public Employees login(String username, int password){
        Employees emp=null;
        String sql="select*from Employees where LastName=? and EmployeeID=?";
        try{
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, username);
        pre.setInt(2, password);
        ResultSet rs= pre.executeQuery();
        if(rs.next()){
            int employeeID = rs.getInt("EmployeeID");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String title = rs.getString("Title");
                String titleOfCourtesy = rs.getString("TitleOfCourtesy");
                String birthDate = rs.getString("BirthDate");
                String hireDate = rs.getString("HireDate");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String region = rs.getString("Region");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                String homePhone = rs.getString("HomePhone");
                String extension = rs.getString("Extension");
                byte[] photo = rs.getBytes("Photo");
                String notes = rs.getString("Notes");
                int reportsTo = rs.getInt("ReportsTo");
                String photoPath = rs.getString("PhotoPath");
                Boolean status = (rs.getInt("Status") == 1 ? true : false);
                emp = new Employees(employeeID, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, photo, notes, reportsTo, photoPath, status);
        }
        
        }catch (SQLException e) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, e);
        }
        return emp;
    }

    public int insertEmployees(Employees em) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]    ([LastName]  ,[FirstName]  ,[Title]  ,[TitleOfCourtesy]  ,[BirthDate]   ,[HireDate]\n"
                + "           ,[Address]  ,[City]    ,[Region]   ,[PostalCode]    ,[Country]  ,[HomePhone]    ,[Extension]  \n"
                + "		   ,[Photo]     ,[Notes]   ,[ReportsTo]  ,[PhotoPath])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, em.getLastName());
            pre.setString(2, em.getFirstName());
            pre.setString(3, em.getTitle());
            pre.setString(4, em.getTitleOfCourtesy());
            pre.setString(5, em.getBirthDate());
            pre.setString(6, em.getHireDate());
            pre.setString(7, em.getAddress());
            pre.setString(8, em.getCity());
            pre.setString(9, em.getRegion());
            pre.setString(10, em.getPostalCode());
            pre.setString(11, em.getCountry());
            pre.setString(12, em.getHomePhone());
            pre.setString(13, em.getExtension());
            pre.setBytes(14, em.getPhoto());
            pre.setString(15, em.getNotes());
            pre.setInt(16, em.getReportsTo());
            pre.setString(17, em.getPhotoPath());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, e);
        }
        return n;
    }

    public int updateEmployee(Employees em) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [LastName] = ?  ,[FirstName] = ? ,[Title] = ? ,[TitleOfCourtesy] = ? ,[BirthDate] = ?\n"
                + "      ,[HireDate] = ?  ,[Address] = ?  ,[City] = ? ,[Region] = ? ,[PostalCode] = ? ,[Country] = ?\n"
                + "      ,[HomePhone] = ?   ,[Extension] = ? ,[Photo] = ? ,[Notes] = ? ,[ReportsTo] = ? ,[PhotoPath] = ?\n"
                + " WHERE EmployeeID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, em.getLastName());
            pre.setString(2, em.getFirstName());
            pre.setString(3, em.getTitle());
            pre.setString(4, em.getTitleOfCourtesy());
            pre.setString(5, em.getBirthDate());
            pre.setString(6, em.getHireDate());
            pre.setString(7, em.getAddress());
            pre.setString(8, em.getCity());
            pre.setString(9, em.getRegion());
            pre.setString(10, em.getPostalCode());
            pre.setString(11, em.getCountry());
            pre.setString(12, em.getHomePhone());
            pre.setString(13, em.getExtension());
            pre.setBytes(14, em.getPhoto());
            pre.setString(15, em.getNotes());
            pre.setInt(16, em.getReportsTo());
            pre.setString(17, em.getPhotoPath());
            pre.setInt(18, em.getEmployeeID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, e);
        }
        return n;
    }

    public int deleteEmployees(int eid) {
        int n = 0;
        String checkSQL = "select*from [Orders] where EmployeeID=" + eid;
        String checkSQL2 = "select*from [EmployeeTerritories] where EmployeeID=" + eid;
        try {
            ResultSet rs = conn.createStatement().executeQuery(checkSQL);
            ResultSet rs2 = conn.createStatement().executeQuery(checkSQL2);
            if (rs.next()) {
                changeDiscontinue(eid, 0);
                return n;
            }
            else if (rs2.next()) {
                changeDiscontinue(eid, 0);
                return n;
            }
            String sql = "delete from Employees WHERE EmployeeID=" + eid;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void changeDiscontinue(int eid, int value) {
        String sql = "update Employees set Status=" + value + " Where EmployeeID=" + eid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Vector<Employees> getEmployees(String sql) {
        Vector<Employees> vector = new Vector<Employees>();
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String title = rs.getString("Title");
                String titleOfCourtesy = rs.getString("TitleOfCourtesy");
                String birthDate = rs.getString("BirthDate");
                String hireDate = rs.getString("HireDate");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String region = rs.getString("Region");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                String homePhone = rs.getString("HomePhone");
                String extension = rs.getString("Extension");
                byte[] photo = rs.getBytes("Photo");
                String notes = rs.getString("Notes");
                int reportsTo = rs.getInt("ReportsTo");
                String photoPath = rs.getString("PhotoPath");
                Boolean status = (rs.getInt("Status") == 1 ? true : false);
                Employees em = new Employees(employeeID, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, photo, notes, reportsTo, photoPath, status);
                vector.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    

    public static void main(String[] args) {
        DAOEmployees dao = new DAOEmployees();
        int n = dao.updateEmployee(new Employees(9, "fdg", "fdgfd", "fdg", "FDg", "03/12/2012", "03/12/2012 00:00:00.0", "er", "wer", "sdfsdf", "Sdf", "SDf", "Sdf", "SDf", null, "asd", 1, "dsfsf"));
        if (n > 0) {
            System.out.println("inserted");
        }
//        Vector<Employees> vector = dao.getEmployees("select*from Employees");
//
//        for (Employees employees : vector) {
//            System.out.println("success");
//            System.out.println(employees);
//        }
    }

}
