<%-- 
    Document   : updateOrderDetail
    Created on : Oct 15, 2024, 3:25:58 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector,entity.OrderDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
       Vector<OrderDetails> vector=(Vector<OrderDetails>)request.getAttribute("vector");
       OrderDetails ord=vector.get(0);
       ResultSet rsOrd=(ResultSet)request.getAttribute("rsOrd");
       ResultSet rsPro=(ResultSet)request.getAttribute("rsPro");
    %>
    <body>
        <form action="OrderDetailURL" method="post">
            <input type="hidden" name="service" value="updateOrderDetail">
            <table>
                <tr>
                    <td><label for="OrderID">OrderID</label></td>
                    <td><input type="text" name="OrderID" id="OrderID" value="<%=ord.getOrderID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="ProductID">ProductID</label></td>
                   <td><input type="text" name="ProductID" id="ProductID" value="<%=ord.getProductID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="UnitPrice">UnitPrice</label></td>
                    <td><input type="text" name="UnitPrice" id="UnitPrice" value="<%=ord.getUnitPrice()%>"></td>
                </tr>
                <tr>
                    <td><label for="Quantity">Quantity</label></td>
                    <td><input type="text" name="Quantity" id="Quantity" value="<%=ord.getQuantity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Discount">Discount</label></td>
                    <td><input type="text" name="Discount" id="Discount" value="<%=ord.getDiscount()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateOrderDetail" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
        </form>
    </body>
</html>
