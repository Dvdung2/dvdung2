<%-- 
    Document   : insertCategory
    Created on : Oct 14, 2024, 2:08:59 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CategoriesURL" method="post">
            <input type="hidden" name="service" value="insertCategories">
            <table>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td><input type="text" name="CategoryID" id="CategoryID" ></td>
                </tr>
                <tr>
                    <td><label for="CategoryName">CategoryName</label></td>
                    <td><input type="text" name="CategoryName" id="CategoryName"></td>
                </tr>
                <tr>
                    <td><label for="Description">Description</label></td>
                    <td><input type="text" name="Description" id="Description"></td>
                </tr>
                <tr>
                    <td><label for="Picture">Picture</label></td>
                    <td><input type="text" name="Picture" id="Picture"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertProduct" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
