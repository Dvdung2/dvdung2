<%-- 
    Document   : admin
    Created on : Nov 1, 2024, 3:02:50 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Employees" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Employees emp=(Employees)session.getAttribute("Employee");
        %>
        <div>
        <h1 align="right">
             <%if(emp==null){%>
            <%}else{%>
            <%="welcome boy "+emp.getLastName()%>
            <%}%>
        </h1>
        </div>
        <div style="display: flex; justify-content: center; gap: 10%; font-size: 150%">
            <%if(emp==null){%>
            <a href="EmployeeURL?service=loginEmployee" >login</a>
            <%}else{%>
            <a href="EmployeeURL?service=logoutEmployee" >log out</a>
            <%}%>
        </div>
        <p><a href="CustomerURL?service=AllCustomer">Customer Manager</a>
            <p><a href="ProductURL?service=listAllProducts">Product Manager</a>
    </body>
</html>
