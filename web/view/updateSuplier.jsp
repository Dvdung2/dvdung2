<%-- 
    Document   : updateSuplier
    Created on : Oct 31, 2024, 4:05:09 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Suppliers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Vector<Suppliers> vector=(Vector<Suppliers>)request.getAttribute("vector");
        Suppliers sup=vector.get(0);
    %>
    <body>
        <form action="SupplierURL" method="post">
            <input type="hidden" name="service" value="updateSupplier">
            <table>
                <tr>
                    <td><label for="SupplierID">SupplierID</label></td>
                    <td><input type="text" name="SupplierID" id="SupplierID" value="<%=sup.getSupplierID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" value="<%=sup.getCompanyName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactName">ContactName</label></td>
                    <td><input type="text" name="ContactName" id="ContactName" value="<%=sup.getContactName()%>" ></td>
                </tr>
                <tr>
                    <td><label for="ContactTitle">ContactTitle</label></td>
                    <td><input type="text" name="ContactTitle" id="ContactTitle" value="<%=sup.getContactTitle()%>"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address" value="<%=sup.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City" value="<%=sup.getCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region" value="<%=sup.getRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode" value="<%=sup.getPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country" value="<%=sup.getCountry()%>"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone" value="<%=sup.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="Fax">Fax</label></td>
                    <td><input type="text" name="Fax" id="Fax" value="<%=sup.getFax()%>"></td>
                </tr>
                <tr>
                    <td><label for="HomePage">HomePage</label></td>
                    <td><input type="text" name="HomePage" id="HomePage" value="<%=sup.getHomePage()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateSupplier" name="submit"></td>
                    <td><input type="reset" name="clear" ></td>
                </tr>
            </table>
    </body>
</html>
