<%-- 
    Document   : listRegion
    Created on : Oct 14, 2024, 4:25:50 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Region" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Vector<Region> vector=(Vector<Region>)request.getAttribute("listRegion");
        %>
        <form action="RegionURL" method="get">
            <input type="hidden" name="service" value="AllRegion">
            <p>
                SearchName: <input type="text" name="region" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="RegionURL?service=insertRegion">insert Region</a></p>
        <table border=1  style=" background-color: aqua;">
            <tr>
                <th>RegionID</th>
                <th>RegionDescription</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <tr>
            <%for(Region re: vector ){%>
            <td><%=re.getRegionID()%></td>
            <td><%=re.getRegionDescription()%></td>
            <td></td>
            <td><a href="RegionURL?service=deleteRegion&rid=<%=re.getRegionID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
