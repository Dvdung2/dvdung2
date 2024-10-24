/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dvdung
 */
public class DAOSuppliers extends DBConnect{
    public int insertSuppliers(Suppliers su) {
        int n = 0;
        String sql = "INSERT INTO Suppliers (CompanyName, ContactName, ContactTitle, "
                + "Address, City, Region, PostalCode, Country, Phone, Fax, HomePage)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, su.getCompanyName());
            pre.setString(2, su.getContactName());
            pre.setString(3, su.getContactTitle());
            pre.setString(4, su.getAddress());
            pre.setString(5, su.getCity());
            pre.setString(6, su.getRegion());
            pre.setString(7, su.getPostalCode());
            pre.setString(8, su.getCountry());
            pre.setString(9, su.getPhone());
            pre.setString(10, su.getFax());
            pre.setString(11, su.getHomePage());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSuppliers(Suppliers su) {
        int n = 0;
        String sql = "UPDATE Suppliers SET CompanyName=?, ContactName=?, ContactTitle=?"
                + ", Address=?, City=?, Region=?, PostalCode=?, Country=?"
                + ", Phone=?, Fax=?, HomePage=? WHERE SupplierID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, su.getCompanyName());
            pre.setString(2, su.getContactName());
            pre.setString(3, su.getContactTitle());
            pre.setString(4, su.getAddress());
            pre.setString(5, su.getCity());
            pre.setString(6, su.getRegion());
            pre.setString(7, su.getPostalCode());
            pre.setString(8, su.getCountry());
            pre.setString(9, su.getPhone());
            pre.setString(10, su.getFax());
            pre.setString(11, su.getHomePage());
            pre.setInt(12, su.getSupplierID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteSuppliers(int sid) {
        int n = 0;
        String sql = "DELETE FROM Suppliers WHERE SupplierID="+sid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Suppliers> getSuppliers(String sql) {
        Vector<Suppliers> vector = new Vector<Suppliers>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String contactName = rs.getString("ContactName");
                String contactTitle = rs.getString("ContactTitle");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String region = rs.getString("Region");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                String phone = rs.getString("Phone");
                String fax = rs.getString("Fax");
                String homePage = rs.getString("HomePage");
                Suppliers su = new Suppliers(supplierID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homePage);
                vector.add(su);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOSuppliers dao= new  DAOSuppliers();
        dao.deleteSuppliers(31);
    }
}
