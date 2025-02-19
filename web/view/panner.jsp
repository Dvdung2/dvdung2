<%-- 
    Document   : panner
    Created on : Nov 1, 2024, 9:30:25 AM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customers customer=(Customers)session.getAttribute("customer");
        %>
        <div>
             <h1 align="right">
             <%if(customer==null){%>
            <%}else{%>
            <%="welcome "+customer.getContactName()%>
            <%}%>
        </div>
        <div style="display: flex; justify-content: center; gap: 10%; font-size: 150%">
            <%if(customer==null){%>
            <a href="CustomerURL?service=loginCustomer" >login</a>
            <a href="CustomerURL?service=insertCustomer" >Register</a>
            <%}else{%>
            <a href="CustomerURL?service=logoutCustomer" >log out</a>
            <%}%>
        </div>
    </body>
</html>
