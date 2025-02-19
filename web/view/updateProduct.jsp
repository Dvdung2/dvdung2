<%-- 
    Document   : insertProduct
    Created on : Oct 5, 2024, 1:23:51 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector,entity.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%Vector<Product> vector=(Vector<Product>)request.getAttribute("vector");
    Product product=vector.get(0);
      ResultSet rsSup= (ResultSet)request.getAttribute("rsSup");
      ResultSet rsCat= (ResultSet)request.getAttribute("rsCat");
    %>
    <body>
        <form action="ProductURL" method="post">
            <input type="hidden" name="service" value="updateProduct">
            <table>
                <tr>
                    <td><label for="ProductID">ProductID</label></td>
                    <td><input type="text" name="ProductID" id="ProductID" readonly value="<%=product.getProductID()%>"></td>
                </tr>
                <tr>
                    <td><label for="ProductName">ProductName</label></td>
                    <td><input type="text" name="ProductName" id="ProductName" required value="<%=product.getProductName()%>"></td>
                </tr>
                <tr>
                    <td><label for="SupplierID">SupplierID</label></td>
                    <td>
                        <select name="SupplierID" id="SupplierID">
                            <%while(rsSup.next()){%>
                            <option value="<%=rsSup.getInt(1)%>"><%=rsSup.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td>
                        <select name="CategoryID" id="CategoryID">
                            <%while(rsCat.next()){%>
                            <option value="<%=rsCat.getInt(1)%>"><%=rsCat.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="QuantityPerUnit">QuantityPerUnit</label></td>
                    <td><input type="text" name="QuantityPerUnit" id="QuantityPerUnit" value="<%=product.getQuantityPerUnit()%>"></td>
                </tr>
                <tr>
                    <td><label for="UnitPrice">UnitPrice</label></td>
                    <td><input type="text" name="UnitPrice" id="UnitPrice" value="<%=product.getUnitPrice()%>"></td>
                </tr>
                <tr>
                    <td><label for="UnitsInStock">UnitsInStock</label></td>
                    <td><input type="text" name="UnitsInStock" id="UnitsInStock" value="<%=product.getUnitsInStock()%>"></td>
                </tr>
                <tr>
                    <td><label for="UnitsOnOrder">UnitsOnOrder</label></td>
                    <td><input type="text" name="UnitsOnOrder" id="UnitsOnOrder" value="<%=product.getUnitsOnOrder()%>"></td>
                </tr>
                <tr>
                    <td><label for="ReorderLevel">ReorderLevel</label></td>
                    <td><input type="text" name="ReorderLevel" id="ReorderLevel" value="<%=product.getReorderLevel()%>"></td>
                </tr>
                <tr>
                    <td><label for="Discontinued">Discontinued</label></td>
                    <td>
                        <input type="radio" name="Discontinued" value="0" <%=(product.isDiscontinued() == true ? "checked" : "")%>>Discontinued
                        <input type="radio" name="Discontinued" value="1" <%=(product.isDiscontinued() == false ? "checked" : "")%>>Continued
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertProduct" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
        </form>
    </body>
</html>
