/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
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
public class DAOOrderDetails extends DBConnect{
    public int insertOrderDetails(OrderDetails ord) {
        int n = 0;
        String sql = "INSERT INTO [Order Details] (OrderID, ProductID, UnitPrice, Quantity, Discount) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getOrderID());
            pre.setInt(2, ord.getProductID());
            pre.setDouble(3, ord.getUnitPrice());
            pre.setInt(4, ord.getQuantity());
            pre.setDouble(5, ord.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateOrderDetails(OrderDetails ord) {
        int n = 0;
        String sql = "UPDATE [Order Details] SET UnitPrice = ?, Quantity = ?, Discount = ? WHERE OrderID = ? AND ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, ord.getUnitPrice());
            pre.setInt(2, ord.getQuantity());
            pre.setDouble(3, ord.getDiscount());
            pre.setInt(4, ord.getOrderID());
            pre.setInt(5, ord.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteOrderDetails(int oid, int pid) {
        int n = 0;
        String sql = "DELETE FROM [Order Details] WHERE OrderID = "+oid+" AND ProductID = "+pid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<OrderDetails> getOrderDetails(String sql) {
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int orderID = rs.getInt("OrderID");
                int productID = rs.getInt("ProductID");
                Double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");
                Double discount = rs.getDouble("Discount");
                OrderDetails ord = new OrderDetails(orderID, productID, unitPrice, quantity, discount);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOOrderDetails dao= new DAOOrderDetails();
        dao.updateOrderDetails(new OrderDetails(10249	, 51, 42.9, 40, 0.0));
    }
}
