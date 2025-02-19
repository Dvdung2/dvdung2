<%-- 
    Document   : listOrder
    Created on : Oct 10, 2024, 2:25:03 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Orders> vector=(Vector<Orders>)request.getAttribute("listOrder");
            String titleTable=(String)request.getAttribute("titleTable");
        %>
        <form action="OrderURL" method="get">
            <input type="hidden" name="service" value="AllOrder">
            <p>
                SearchorderID: <input type="text" name="order" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="OrderURL?service=insertOrder">Insert Order</a></p>
        <table border=1>
            <caption><%=titleTable%></caption>
            <tr>
                <th>OrderID</th>
                <th>CustomerID</th>
                <th>EmployeeID</th>
                <th>OrderDate</th>
                <th>RequiredDate</th>
                <th>ShippedDate</th>
                <th>ShipVia</th>
                <th>Freight</th>
                <th>ShipName</th>
                <th>ShipAddress</th>
                <th>ShipCity</th>
                <th>ShipRegion</th>
                <th>ShipPostalCode</th>
                <th>ShipCountry</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Orders ord : vector) {%>
            <tr>
                <td><%=ord.getOrderID()%></td>
                <td><%=ord.getCustomerID()%></td>
                <td><%=ord.getEmployeeID()%></td>
                <td><%=ord.getOrderDate()%></td>
                <td><%=ord.getRequiredDate()%></td>
                <td><%=ord.getShippedDate()%></td>
                <td><%=ord.getShipVia()%></td>
                <td><%=ord.getFreight()%></td>
                <td><%=ord.getShipName()%></td>
                <td><%=ord.getShipAddress()%></td>
                <td><%=ord.getShipCity()%></td>
                <td><%=ord.getShipRegion()%></td>
                <td><%=ord.getShipPostalCode()%></td>
                <td><%=ord.getShipCountry()%></td>
                <td><%=(ord.isStatus() == true ? "Continue" : "Discontinue")%></td>
                <td><a href="OrderURL?service=updateOrder&oid=<%=ord.getOrderID()%>">update</a></td>
                <td><a href="OrderURL?service=deleteOrder&oid=<%=ord.getOrderID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
