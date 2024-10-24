/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import entity.EmployeeTerritories;
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
public class DAOEmployeeTerritories extends DBConnect {
     public int insertEmployeeTerritories(EmployeeTerritories et) {
        int n = 0;
        String sql = "INSERT INTO EmployeeTerritories (EmployeeID, TerritoryID) VALUES (?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, et.getEmployeeID());
            pre.setString(2, et.getTerritoryID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateEmployeeTerritories(EmployeeTerritories et) {
        int n = 0;
        String sql = "UPDATE EmployeeTerritories SET TerritoryID = ? WHERE EmployeeID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, et.getTerritoryID());
            pre.setInt(2, et.getEmployeeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteEmployeeTerritories(int eid) {
        int n = 0;
        String sql = "DELETE FROM EmployeeTerritories WHERE EmployeeID = "+eid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<EmployeeTerritories> getEmployeeTerritories(String sql) {
        Vector<EmployeeTerritories> vector = new Vector<EmployeeTerritories>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                 int employeeID = rs.getInt("EmployeeID");
                String territoryID = rs.getString("TerritoryID");
                EmployeeTerritories et = new EmployeeTerritories(employeeID, territoryID);
                vector.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
