/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
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
public class DAOCategories extends DBConnect {

    public int insertCategories(Categories cat) {
        int n = 0;
        String sql = "INSERT INTO Categories (CategoryName, Description, Picture) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cat.getCategoryName());
            stmt.setString(2, cat.getDescription());
            stmt.setBytes(3, cat.getPicture());
            n = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCategories(Categories cat) {
        int n = 0;
        String sql = "UPDATE Categories SET CategoryName = ?, "
                + "Description = ?, Picture = ? WHERE CategoryID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cat.getCategoryName());
            pre.setString(2, cat.getDescription());
            pre.setBytes(3, cat.getPicture());
            pre.setInt(4, cat.getCategoryID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCategories(int cid) {
        int n = 0;
        updateCatIDInProducts(cid);
        String sql = "delete from Categories WHERE CategoryID=" + cid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateCatIDInProducts(int cid) {
        String sql = "update Products\n"
                + "set CategoryID=null\n"
                + "where CategoryID=" + cid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Categories> getCategories(String sql) {
        Vector<Categories> vector = new Vector<Categories>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int id = rs.getInt("CategoryID");
                String name = rs.getString("CategoryName");
                String description = rs.getString("Description");
                byte[] picture = rs.getBytes("Picture");
                Categories cat= new Categories(id, name, description, picture);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCategories dao= new DAOCategories();
        int n= dao.deleteCategories(9);
    }
}
