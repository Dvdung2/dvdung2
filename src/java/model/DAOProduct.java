/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.util.Vector;


/**
 *
 * @author dvdung
 */
public class DAOProduct extends DBConnect {

    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES('" + pro.getProductName() + "'," + pro.getSupplierID() + "," + pro.getCategoryID() + " ,'" + pro.getQuantityPerUnit() + "', " + pro.getUnitPrice() + "," + pro.getUnitsInStock() + "," + pro.getUnitsOnOrder() + "," + pro.getReorderLevel() + "," + (pro.isDiscontinued() == true ? 1 : 0) + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        // ?:field: start Of ?= 1 : First(1): ProductName ...
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for?
            /*
             private int ProductID;
	private String ProductName;
	private int SupplierID,CategoryID;
	private String QuantityPerUnit;
	private Double UnitPrice;
        private int UnitsInStock,UnitsOnOrder,ReorderLevel;
	private boolean Discontinued;
             */
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ? ,[SupplierID] = ? ,[CategoryID] = ? ,[QuantityPerUnit] = ?\n"
                + "      ,[UnitPrice] = ? ,[UnitsInStock] = ?  ,[UnitsOnOrder] = ? ,[ReorderLevel] = ? ,[Discontinued] = ?\n"
                + " WHERE ProductID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            pre.setInt(10, pro.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
   public void changeDiscontinue(int pid, int value){
       
       String sql="update Products set Discontinued=" +value+" Where ProductId="+pid;
               try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
    public int deleteProduct(int pid) {
        int n = 0;
        String checkSQL="select*from [Order Details] where ProductID=" + pid;
        try {
            ResultSet rs=conn.createStatement().executeQuery(checkSQL);
            if(rs.next()){
                changeDiscontinue(pid, 0);
                return n;
            }
            String sql = "delete from Products WHERE ProductID=" + pid;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    // xóa product dính khóa ngoại đặt discontinue về false
    

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt("SupplierID"), CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                Double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7), UnitsOnOrder = rs.getInt(8), ReorderLevel = rs.getInt(9);
                boolean Discontinued = (rs.getInt(10) == 1 ? true : false);
                Product pro = new Product(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vector<Product> getProducts(String sql){
        Vector<Product> vector= new Vector<Product>();
        ResultSet rs = null;
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(fieldname/index=1)
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt("SupplierID"), CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                Double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7), UnitsOnOrder = rs.getInt(8), ReorderLevel = rs.getInt(9);
                boolean Discontinued = (rs.getInt(10) == 1 ? true : false);
                Product pro = new Product(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                vector.add(pro);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        //int n= dao.insertProduct(new Product(2, "ProductName", 1, 1, "QuantityPerUnit", 100.0, 20, 10, 1, true));
        //int n= dao.addProduct(new Product(99, "ProductName", 1, 1, "QuantityPerUnit", 100.0, 20, 10, 1, true));
       // int n= dao.updateProduct(new Product(1, " NEW ProductName", 1, 1, "QuantityPerUnit", 100.0, 20, 10, 1, true));
        int n = dao.deleteProduct(79);
        //int n = dao.deleteProduct(5);
        if (n > 0) {
            System.out.println("inserted");
        }

       //dao.displayAll("select*from Products");
//       Vector<Product> vector=dao.getProducts("select*from Products");
//        for (Product product : vector) {
//            System.out.println(product);
//        }
    }
}
