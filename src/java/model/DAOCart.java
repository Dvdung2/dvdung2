/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dvdung
 */
public class DAOCart extends DBConnect{
    public Cart getCart(int pid){
        Cart cart=null;
        String sql="select ProductID,ProductName,UnitPrice from Products where ProductID="+pid;
        try {
            ResultSet rs=conn.createStatement().executeQuery(sql);
            if(rs.next()){
                cart=new Cart(pid, rs.getString(2), 0, rs.getDouble(3), 0);
            }
        }catch (SQLException e) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, e);
        }
        return cart;
    }
    
    
}
