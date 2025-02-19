/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerDemoGraphics;
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
public class DAOCustomerDemoGraphics extends DBConnect{
    public int insertCustomGraphics(CustomerDemoGraphics cdg) {
        int n = 0;
         String sql = "INSERT INTO CustomerDemographics (CustomerTypeID, CustomerDesc) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cdg.getCustomerTypeID());
            pre.setString(2, cdg.getCustomerDesc());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemoGraphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomGraphics(CustomerDemoGraphics cdg) {
        int n = 0;
         String sql = "UPDATE CustomerDemographics SET CustomerDesc = ? WHERE CustomerTypeID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cdg.getCustomerDesc());
            pre.setString(2, cdg.getCustomerTypeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemoGraphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCustomGraphics(String cid) {
        int n = 0;
        String sql = "delete from CustomerDemographics WHERE CustomerTypeID llike '%" + cid+"%'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemoGraphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<CustomerDemoGraphics> getCustomGraphics(String sql) {
        Vector<CustomerDemoGraphics> vector = new Vector<CustomerDemoGraphics>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                String CustomerDesc = rs.getString("CustomerDesc");
                String CustomerTypeID = rs.getString("CustomerTypeID");
                CustomerDemoGraphics cdg= new CustomerDemoGraphics(CustomerTypeID, CustomerDesc);
                vector.add(cdg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemoGraphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
