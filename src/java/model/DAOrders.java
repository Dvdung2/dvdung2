/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/**
 *
 * @author dvdung
 */
public class DAOrders extends DBConnect {

    public int insertOrders(Orders ord) {
        Timestamp st;
        int n = 0;
        Date date = null;
        String sql = "INSERT INTO Orders (CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipName());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOrders.class.getName()).log(Level.SEVERE, sql, e);
        }
        return n;
    }

    public int updateOrders(Orders ord) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders]  SET [CustomerID] = ?  ,[EmployeeID] = ?   ,[OrderDate] = ?   ,[RequiredDate] = ?   ,[ShippedDate] = ?\n"
                + "      ,[ShipVia] = ?    ,[Freight] = ?    ,[ShipName] = ?   ,[ShipAddress] = ?    ,[ShipCity] = ?\n"
                + "      ,[ShipRegion] = ?    ,[ShipPostalCode] = ?   ,[ShipCountry] = ?\n"
                + " WHERE OrderID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipName());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            pre.setInt(14, ord.getOrderID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOrders.class.getName()).log(Level.SEVERE, sql, e);
        }
        return n;
    }

    public void changeDiscontinue(int oid, int value) {

        String sql = "update Orders set Status=" + value + " Where OrderID="+oid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int deleteOrders(int oid) {
        int n = 0;
        String checkSQL="select*from [Order Details] where OrderID=" + oid;
        try {
            ResultSet rs=conn.createStatement().executeQuery(checkSQL);
            if(rs.next()){
                changeDiscontinue(oid, 0);
                return n;
            }
            String sql = "delete from Orders WHERE OrderID=" + oid;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int orderID = rs.getInt("OrderID");
                String customerID = rs.getString("CustomerID");
                int employeeID = rs.getInt("EmployeeID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                double freight = rs.getDouble("Freight");
                String shipName = rs.getString("ShipName");
                String shipAddress = rs.getString("ShipAddress");
                String shipCity = rs.getString("ShipCity");
                String shipRegion = rs.getString("ShipRegion");
                String shipPostalCode = rs.getString("ShipPostalCode");
                String shipCountry = rs.getString("ShipCountry");
                boolean status = (rs.getInt("Status") == 1 ? true : false);
                Orders ord = new Orders(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry, status);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    

    public static void main(String[] args) {
        DAOrders dao = new DAOrders();
//        Vector<Orders> vector = dao.getOrders("select*from Orders");
//
//        for (Orders employees : vector) {
//            System.out.println("success");
//            System.out.println(employees);
//        }
        int n = dao.deleteOrders(11080);
        if (n > 0) {
            System.out.println("inserted");
        }
    }
}
