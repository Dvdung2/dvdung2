/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Region;
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
public class DAORegion extends DBConnect {

    public int insertRegion(Region re) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Region]([RegionID],[RegionDescription])\n" +
"     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, re.getRegionID());
            pre.setString(2, re.getRegionDescription());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateRegion(Region re) {
        int n = 0;
        String sql = "UPDATE Region SET RegionDescription = ? WHERE RegionID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, re.getRegionID());
            pre.setString(2, re.getRegionDescription());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteRegion(int rid) {
        int n = 0;
        String sql = "DELETE FROM Region WHERE RegionID = "+rid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Region> getRegion(String sql) {
        Vector<Region> vector = new Vector<Region>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int regionID = rs.getInt("RegionID");
                String regionDescription = rs.getString("RegionDescription");
                Region re = new Region(regionID, regionDescription);
                vector.add(re);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAORegion dao= new DAORegion();
        int n=dao.insertRegion(new Region(5, "ret"));
        if(n>0){
            System.out.println("dfs");
        }
    }
}
