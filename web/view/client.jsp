<%-- 
    Document   : index
    Created on : Nov 5, 2024, 7:35:28 AM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product,entity.Suppliers,entity.Customers,java.sql.ResultSet,java.util.HashMap" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <jsp:include page="panner.jsp"></jsp:include>
        <div style="display: flex">
        <jsp:include page="menu.jsp"></jsp:include>
        <jsp:include page="Content.jsp"></jsp:include>
    </body>
</html>
