<%-- 
    Document   : listCategory
    Created on : Oct 14, 2024, 2:08:43 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Categories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Vector<Categories> vector=(Vector<Categories>)request.getAttribute("vector");
        %>
        <form action="CategoriesURL" method="get">
            <input type="hidden" name="service" value="listAllCategories">  
            <p>
                SearchName: <input type="text" name="cname" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="CategoriesURL?service=insertCategories">insert Categories</a></p>
        <table border=1  style=" background-color: aqua;">
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Description</th>
                <th>Picture</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <tr>
            <%for (Categories cat : vector) {%>
            <td><%=cat.getCategoryID()%></td>
            <td><%=cat.getCategoryName()%></td>
            <td><%=cat.getDescription()%></td>
            <td><%=cat.getPicture()%></td>
            <td><a href="CategoriesURL?service=updateCategories&cid=<%=cat.getCategoryID()%>">update</a></td>
            <td><a href="CategoriesURL?service=deleteCategories&cid=<%=cat.getCategoryID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
