/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Shippers;
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
public class DAOShippers extends DBConnect {

    public int insertShippers(Shippers sh) {
        int n = 0;
        String sql = "INSERT INTO Shippers (CompanyName, Phone) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sh.getCompanyName());
            pre.setString(2, sh.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateShippers(Shippers sh) {
        int n = 0;
        String sql = "UPDATE Shippers SET CompanyName = ?, Phone = ? WHERE ShipperID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sh.getCompanyName());
            pre.setString(2, sh.getPhone());
            pre.setInt(3, sh.getShipperID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteShippers(int sid) {
        int n = 0;
        String sql = "DELETE FROM Shippers WHERE ShipperID = "+sid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Shippers> getShippers(String sql) {
        Vector<Shippers> vector = new Vector<Shippers>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                 int shipperID = rs.getInt("ShipperID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                Shippers sh = new Shippers(shipperID, companyName, phone);
                vector.add(sh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
