/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Territories;
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
public class DAOTerritories extends DBConnect {

    public int insertTerritories(Territories te) {
        int n = 0;
        String sql = "INSERT INTO Territories (TerritoryID, TerritoryDescription, RegionID) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, te.getTerritoryID());
            pre.setString(2, te.getTerritoryDescription().trim());
            pre.setInt(3, te.getRegionID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateTerritories(Territories te) {
        int n = 0;
        String sql = "UPDATE Territories SET TerritoryDescription = ?, RegionID = ? WHERE TerritoryID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, te.getTerritoryDescription().trim());
            pre.setInt(2, te.getRegionID());
            pre.setString(3, te.getTerritoryID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteTerritories(int tid) {
        int n = 0;
        String sql = "DELETE FROM Territories WHERE TerritoryID = " + tid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Territories> getTerritories(String sql) {
        Vector<Territories> vector = new Vector<Territories>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                String territoryID = rs.getString("TerritoryID");
                String territoryDescription = rs.getString("TerritoryDescription").trim();
                int regionID = rs.getInt("RegionID");
                Territories te = new Territories(territoryID, territoryDescription, regionID);
                vector.add(te);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
