/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customers;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author dvdung
 */
public class DAOCustomers extends DBConnect {
    
    public Customers Login(String username, String password){
        Customers customer=null;
        String sql="select*from Customers where CustomerID=? and ContactName=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,username );
            pre.setString(2,password );
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                customer=new Customers(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
                        ,rs.getString(9),rs.getString(10),rs.getString(11));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public int insertCustomers(Customers cus) {
        int n = 0;
        String sql = "INSERT INTO Customers (CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomers(Customers cus) {
        int n = 0;
        String sql = "UPDATE Customers SET CompanyName = ?, ContactName = ?, ContactTitle = ?, Address = ?, City = ?, Region = ?, PostalCode = ?, Country = ?, Phone = ?, Fax = ? "
                + "WHERE CustomerID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCompanyName());
            pre.setString(2, cus.getContactName());
            pre.setString(3, cus.getContactTitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPostalCode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getCustomerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public void changeDiscontinue(String cid, int value){
       
       String sql="update Customers set Status=" +value+" Where CustomerID='"+ cid+"'";
               try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }

    public int deleteCustomers(String cid) {
        int n = 0;
        String checkSQL = "select*from [Orders] where CustomerID ='"+ cid+"'";
        try {
            ResultSet rs=conn.createStatement().executeQuery(checkSQL);
            if(rs.next()){
                changeDiscontinue(cid, 0);
                return n;
            }
            String sql = "DELETE FROM Customers WHERE CustomerID ='"+ cid+"'";
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }


    public Vector<Customers> getCustomers(String sql) {
        Vector<Customers> vector = new Vector<Customers>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
               String customerID = rs.getString("CustomerID");
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
                boolean Status = (rs.getInt("Status") == 1 ? true : false);
                Customers cus = new Customers(customerID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, Status);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
        int n= dao.insertCustomers(new Customers("ggf6h", "dfg", "fggddf", "dfgfdg", "bnm", "nbm", "bm", "bnm", "bnm", "nbm", "nbm"));
         if (n > 0) {
            System.out.println("inserted");
        }

    }
}
