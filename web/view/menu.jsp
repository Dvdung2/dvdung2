<%-- 
    Document   : menu
    Created on : Nov 1, 2024, 1:36:28 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product,entity.Suppliers,entity.Customers,java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                    Vector<Product> vector=(Vector<Product>)request.getAttribute("listProduct");
                    String titleTable=(String)request.getAttribute("titleTable");
                    ResultSet rsCat= (ResultSet)request.getAttribute("rsCat");
                    ResultSet rsSup= (ResultSet)request.getAttribute("rsSup");
        %>
        <div style="width: 15%">
            <div style="width: 10%">
                <table border=1>
                    <caption style="font-size: 130%">List Categories</caption>
                    <%while(rsCat.next()) {%>
                    <tr>
                        <td><a style="display: flex; flex-wrap: wrap" href="ProductURL?service=listAllProducts&cid=<%=rsCat.getInt(1)%>"><%=rsCat.getString(2)%></a></td>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div style="width: 10%">
                <table border=1>
                    <caption style="font-size: 130%">List suppliers</caption>
                    <%while(rsSup.next()) {%>
                    <tr>
                        <td><a style="display: flex; flex-wrap: wrap" href="ProductURL?service=listAllProducts&sid=<%=rsSup.getInt(1)%>"><%=rsSup.getString(2)%></a></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </body>
</html>
