<%-- 
    Document   : insertOrderDetail
    Created on : Oct 14, 2024, 3:35:44 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
    ResultSet rsOrd=(ResultSet)request.getAttribute("rsOrd");
    ResultSet rsPro=(ResultSet)request.getAttribute("rsPro");
    %>
    <body>
        <form action="OrderDetailURL" method="post">
            <input type="hidden" name="service" value="insertOrderDetail">
            <table>
                <tr>
                    <td><label for="OrderID">OrderID</label></td>
                    <td>
                        <select name="OrderID" id="OrderID">
                            <%while(rsOrd.next()){%>
                            <option value="<%=rsOrd.getInt(1)%>"><%=rsOrd.getInt(1)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="ProductID">ProductID</label></td>
                    <td>
                        <select name="ProductID" id="ProductID">
                            <%while(rsPro.next()){%>
                            <option value="<%=rsPro.getInt(1)%>"><%=rsPro.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="UnitPrice">UnitPrice</label></td>
                    <td><input type="text" name="UnitPrice" id="UnitPrice"></td>
                </tr>
                <tr>
                    <td><label for="Quantity">Quantity</label></td>
                    <td><input type="text" name="Quantity" id="Quantity"></td>
                </tr>
                <tr>
                    <td><label for="Discount">Discount</label></td>
                    <td><input type="text" name="Discount" id="Discount"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertProduct" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
