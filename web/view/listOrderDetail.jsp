<%-- 
    Document   : list
    Created on : Oct 14, 2024, 3:35:08 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.OrderDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Vector<OrderDetails> vector= (Vector<OrderDetails>)request.getAttribute("listOrderDetail");
        %>
        <form action="OrderDetailURL" method="get">
            <input type="hidden" name="service" value="listAllOrderDetail">
            <p>
                SearchName: <input type="text" name="order" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="OrderDetailURL?service=insertOrderDetail">insert OrderDetail</a></p>
        <table border=1  style=" background-color: aqua;">
            <tr>

                <th>OrderID</th>
                <th>ProductID</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Discount</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <tr>
            <%for (OrderDetails ord : vector) {%>
            <td><%=ord.getOrderID()%></td>
            <td><%=ord.getProductID()%></td>
            <td><%=ord.getUnitPrice()%></td>
            <td><%=ord.getQuantity()%></td>
            <td><%=ord.getDiscount()%></td>
            <td><a href="OrderDetailURL?service=updateOrderDetail&oid=<%=ord.getOrderID()%>&pid=<%=ord.getProductID()%>">update</a></td>
            <td><a href="OrderDetailURL?service=deleteOrderDetail&oid=<%=ord.getOrderID()%>&pid=<%=ord.getProductID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
