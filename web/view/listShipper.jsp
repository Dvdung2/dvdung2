<%-- 
    Document   : listShipper
    Created on : Oct 14, 2024, 4:52:57 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Shippers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Vector<Shippers> vector=(Vector<Shippers>)request.getAttribute("listShipper");
        %>
        <form action="ShipperURL" method="get">
            <input type="hidden" name="service" value="AllShipper">
            <p>
                SearchName: <input type="text" name="cname" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="ShipperURL?service=insertShipper">insert Shipper</a></p>
        <table border=1  style=" background-color: aqua;">
            <tr>
                <th>ShipperID</th>
                <th>CompanyName</th>
                <th>Phone</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <tr>
                <%for(Shippers sh:vector){%>
                <td><%=sh.getShipperID()%></td>
                <td><%=sh.getCompanyName()%></td>
                <td><%=sh.getPhone()%></td>
                <td></td>
                <td><a href="ShipperURL?service=deleteShipper&sid=<%=sh.getShipperID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
