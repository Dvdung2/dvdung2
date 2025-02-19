/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
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
public class DAOCustomerCustomerDemo extends DBConnect{
    public int insertCustomDemo(CustomerCustomerDemo ccd) {
        int n = 0;
        String sql = "INSERT INTO CustomerCustomerDemo (CustomerID, CustomerTypeID) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ccd.getCustomerID());
            stmt.setString(2, ccd.getCustomerTypeID());
            n = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomDemo(CustomerCustomerDemo ccd) {
        int n = 0;
        String sql = "UPDATE CustomerCustomerDemo SET CustomerTypeID = ? WHERE CustomerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ccd.getCustomerID());
            pre.setString(2, ccd.getCustomerTypeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCustomDemo(int cid) {
        int n = 0;
        String sql = "delete from CustomerCustomerDemo WHERE CustomerID=" + cid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<CustomerCustomerDemo> getCustomerDemo(String sql) {
        Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                String customerID = rs.getString("CustomerID");
                String customerTypeID = rs.getString("CustomerTypeID");
                CustomerCustomerDemo ccd= new CustomerCustomerDemo(customerID, customerTypeID);
                vector.add(ccd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
