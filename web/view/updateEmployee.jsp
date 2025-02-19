<%-- 
    Document   : updateEmployee
    Created on : Oct 25, 2024, 9:34:31 AM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector,entity.Employees" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
    ResultSet rsEm=(ResultSet)request.getAttribute("rsEm");
    Vector<Employees> vector=(Vector<Employees>)request.getAttribute("vector");
      Employees em=vector.get(0);
    %>
    <body>
        <form action="EmployeeURL" method="post">
            <input type="hidden" name="service" value="updateEmployee">
            <table>
                <tr>
                    <td><label for="EmployeeID">EmployeeID</label></td>
                    <td><input type="text" name="EmployeeID" id="EmployeeID" readonly value="<%=em.getEmployeeID()%>"></td>
                </tr>
                <tr>
                    <td><label for="LastName">LastName</label></td>
                    <td><input type="text" name="LastName" id="LastName" value="<%=em.getLastName()%>"></td>
                </tr>
                <tr>
                    <td><label for="FirstName">FirstName</label></td>
                    <td><input type="text" name="FirstName" id="FirstName" value="<%=em.getFirstName()%>"></td>
                </tr>
                <tr>
                    <td><label for="Title">Title</label></td>
                    <td><input type="text" name="Title" id="Title" value="<%=em.getTitle()%>" ></td>
                </tr>
                <tr>
                    <td><label for="TitleOfCourtesy">TitleOfCourtesy</label></td>
                    <td><input type="text" name="TitleOfCourtesy" id="TitleOfCourtesy" value="<%=em.getTitleOfCourtesy()%>"></td>
                </tr>
                <tr>
                    <td><label for="BirthDate">BirthDate</label></td>
                    <td><input type="text" name="BirthDate" id="BirthDate" value="<%=em.getBirthDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="HireDate">HireDate</label></td>
                    <td><input type="text" name="HireDate" id="HireDate" value="<%=em.getHireDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address" value="<%=em.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City" value="<%=em.getCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region" value="<%=em.getRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode" value="<%=em.getPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country" value="<%=em.getCountry()%>"></td>
                </tr>
                <tr>
                    <td><label for="HomePhone">HomePhone</label></td>
                    <td><input type="text" name="HomePhone" id="HomePhone" value="<%=em.getHomePhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="Extension">Extension</label></td>
                    <td><input type="text" name="Extension" id="Extension" value="<%=em.getExtension()%>"></td>
                </tr>
                <tr>
                    <td><label for="Photo">Photo</label></td>
                    <td><input type="text" name="Photo" id="Photo" value="<%=em.getPhoto()%>" ></td>
                </tr>
                <tr>
                    <td><label for="Notes">Notes</label></td>
                    <td><input type="text" name="Notes" id="Notes" value="<%=em.getNotes()%>"></td>
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
                    <td><input type="text" name="PhotoPath" id="PhotoPath" value="<%=em.getPhotoPath()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateEmployee" name="submit"></td>
                    <td><input type="reset" name="clear"</td>
                </tr>
            </table>
    </body>
</html>

