<%-- 
    Document   : insertShipper
    Created on : Oct 14, 2024, 4:53:06 PM
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
        <form action="ShipperURL" method="post">
            <input type="hidden" name="service" value="insertShipper">
            <table>
                <tr>
                    <td><label for="ShipperID">ShipperID</label></td>
                    <td><input type="text" name="ShipperID" id="ShipperID" ></td>
                </tr>
                 <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" ></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertShipper" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
