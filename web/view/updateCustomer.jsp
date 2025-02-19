<%-- 
    Document   : updateCustomer
    Created on : Oct 25, 2024, 9:34:41 AM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector,entity.Customers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%Vector<Customers> vector=(Vector<Customers>)request.getAttribute("vector");
      Customers cus=vector.get(0);
    %>
    <body>
         <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="updateCustomer">
            <table>
                <tr>
                    <td><label for="CustomerID">CustomerID</label></td>
                    <td><input type="text" name="CustomerID" id="CustomerID" value="<%=cus.getCustomerID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" value="<%=cus.getCompanyName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactName">ContactName</label></td>
                    <td><input type="text" name="ContactName" id="ContactName" value="<%=cus.getContactName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactTitle">ContactTitle</label></td>
                    <td><input type="text" name="ContactTitle" id="ContactTitle" value="<%=cus.getContactTitle()%>"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address" value="<%=cus.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City" value="<%=cus.getCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region" value="<%=cus.getRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode" value="<%=cus.getPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country" value="<%=cus.getCountry()%>"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone" value="<%=cus.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="Fax">Fax</label></td>
                    <td><input type="text" name="Fax" id="Fax" value="<%=cus.getFax()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateCustomer" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
