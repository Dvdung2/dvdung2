<%-- 
    Document   : updateCategory
    Created on : Oct 31, 2024, 4:52:54 PM
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
         <%
         Vector<Categories> vector=(Vector<Categories>)request.getAttribute("vector");
         Categories cat=vector.get(0);
         %>
    <body>
        <form action="CategoriesURL" method="post">
            <input type="hidden" name="service" value="updateCategories">
            <table>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td><input type="text" name="CategoryID" id="CategoryID" value="<%=cat.getCategoryID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CategoryName">CategoryName</label></td>
                    <td><input type="text" name="CategoryName" id="CategoryName" value="<%=cat.getCategoryName()%>"></td>
                </tr>
                <tr>
                    <td><label for="Description">Description</label></td>
                    <td><input type="text" name="Description" id="Description" value="<%=cat.getDescription()%>"></td>
                </tr>
                <tr>
                    <td><label for="Picture">Picture</label></td>
                    <td><input type="text" name="Picture" id="Picture" value="<%=cat.getPicture()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateCategory" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
