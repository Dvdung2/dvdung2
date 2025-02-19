<%-- 
    Document   : insertEmployee
    Created on : Oct 11, 2024, 1:44:07 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
    ResultSet rsEm=(ResultSet)request.getAttribute("rsEm");
    %>
    <body>
        <form action="EmployeeURL" method="post">
            <input type="hidden" name="service" value="insertEmployee">
            <table>
                <tr>
                    <td><label for="EmployeeID">EmployeeID</label></td>
                    <td><input type="text" name="EmployeeID" id="EmployeeID" readonly></td>
                </tr>
                <tr>
                    <td><label for="LastName">LastName</label></td>
                    <td><input type="text" name="LastName" id="LastName"></td>
                </tr>
                <tr>
                    <td><label for="FirstName">FirstName</label></td>
                    <td><input type="text" name="FirstName" id="FirstName"></td>
                </tr>
                <tr>
                    <td><label for="Title">Title</label></td>
                    <td><input type="text" name="Title" id="Title"></td>
                </tr>
                <tr>
                    <td><label for="TitleOfCourtesy">TitleOfCourtesy</label></td>
                    <td><input type="text" name="TitleOfCourtesy" id="TitleOfCourtesy" ></td>
                </tr>
                <tr>
                    <td><label for="BirthDate">BirthDate</label></td>
                    <td><input type="text" name="BirthDate" id="BirthDate"></td>
                </tr>
                <tr>
                    <td><label for="HireDate">HireDate</label></td>
                    <td><input type="text" name="HireDate" id="HireDate"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City" ></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country"></td>
                </tr>
                <tr>
                    <td><label for="HomePhone">HomePhone</label></td>
                    <td><input type="text" name="HomePhone" id="HomePhone" ></td>
                </tr>
                <tr>
                    <td><label for="Extension">Extension</label></td>
                    <td><input type="text" name="Extension" id="Extension"></td>
                </tr>
                <tr>
                    <td><label for="Photo">Photo</label></td>
                    <td><input type="text" name="Photo" id="Photo"></td>
                </tr>
                <tr>
                    <td><label for="Notes">Notes</label></td>
                    <td><input type="text" name="Notes" id="Notes"></td>
                </tr>
                <tr>
                    <td><label for="ReportsTo">ReportsTo</label></td>
                    <td>
                        <select name="ReportsTo" id="ReportsTo">
                            <%while(rsEm.next()){%>
                            <option value="<%=rsEm.getInt(1)%>"><%=rsEm.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="PhotoPath">PhotoPath</label></td>
                    <td><input type="text" name="PhotoPath" id="PhotoPath"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertEmployee" name="submit"></td>
                    <td><input type="reset" name="clear"</td>
                </tr>
            </table>
    </body>
</html>
