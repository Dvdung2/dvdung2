<%-- 
    Document   : updateOrder
    Created on : Oct 28, 2024, 2:41:12 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,entity.Orders,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <%
      ResultSet rsCus= (ResultSet)request.getAttribute("rsCus");
      ResultSet rsEm= (ResultSet)request.getAttribute("rsEm");
      ResultSet rsSh= (ResultSet)request.getAttribute("rsSh");
      Vector<Orders> vector=(Vector<Orders>)request.getAttribute("vector");
      Orders ord=vector.get(0);
    %>
    <body>
        <form action="OrderURL" method="post">
            <input type="hidden" name="service" value="updateOrder"> 
            <table>
                <tr>
                    <td><label for="OrderID">OrderID</label></td>
                    <td><input type="text" name="OrderID" id="OrderID" readonly value="<%=ord.getOrderID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CustomerID">CustomerID</label></td>
                <td>
                    <select name="CustomerID" id="CustomerID">
                        <%while(rsCus.next()){%>
                        <option value="<%=rsCus.getString(1)%>"><%=rsCus.getString(2)%></option>
                        <%}%>
                    </select>
                </td>
                </tr>
                <tr>
                    <td><label for="EmployeeID">EmployeeID</label></td>
                <td>
                    <select name="EmployeeID" id="EmployeeID">
                        <%while(rsEm.next()){%>
                        <option value="<%=rsEm.getInt(1)%>"><%=rsEm.getString(2)%></option>
                        <%}%>
                    </select>
                </td>
                </tr>
                <tr>
                    <td><label for="OrderDate">OrderDate</label></td>
                    <td><input type="text" name="OrderDate" id="OrderDate" value="<%=ord.getOrderDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="RequiredDate">RequiredDate</label></td>
                    <td><input type="text" name="RequiredDate" id="RequiredDate" value="<%=ord.getRequiredDate()%>" ></td>
                </tr>
                <tr>
                    <td><label for="ShippedDate">ShippedDate</label></td>
                    <td><input type="text" name="ShippedDate" id="ShippedDate" value="<%=ord.getShippedDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipVia">ShipVia</label></td>
                <td>
                    <select name="ShipVia" id="ShipVia">
                        <%while(rsSh.next()){%>
                        <option value="<%=rsSh.getInt(1)%>"><%=rsSh.getString(2)%></option>
                        <%}%>
                    </select>
                </td>
                </tr>
                <tr>
                    <td><label for="Freight">Freight</label></td>
                    <td><input type="text" name="Freight" id="Freight" value="<%=ord.getFreight()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipName">ShipName</label></td>
                    <td><input type="text" name="ShipName" id="ShipName" value="<%=ord.getShipName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipAddress">ShipAddress</label></td>
                    <td><input type="text" name="ShipAddress" id="ShipAddress" value="<%=ord.getShipAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipCity">ShipCity</label></td>
                    <td><input type="text" name="ShipCity" id="ShipCity" value="<%=ord.getShipCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipRegion">ShipRegion</label></td>
                    <td><input type="text" name="ShipRegion" id="ShipRegion" value="<%=ord.getShipRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipPostalCode">ShipPostalCode</label></td>
                    <td><input type="text" name="ShipPostalCode" id="ShipPostalCode" value="<%=ord.getShipPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipCountry">ShipCountry</label></td>
                    <td><input type="text" name="ShipCountry" id="ShipCountry" value="<%=ord.getShipCountry()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateOrder" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
        </form>
    </body>
</html>
