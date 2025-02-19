<%-- 
    Document   : insertRegion
    Created on : Oct 14, 2024, 4:25:59 PM
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
        <form action="RegionURL" method="post">
            <input type="hidden" name="service" value="insertRegion">
            <table>
                <tr>
                    <td><label for="RegionID">RegionID</label></td>
                    <td><input type="text" name="RegionID" id="RegionID" ></td>
                </tr>
                <tr>
                    <td><label for="RegionDescription">RegionDescription</label></td>
                    <td><input type="text" name="RegionDescription" id="RegionDescription"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertRegion" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
