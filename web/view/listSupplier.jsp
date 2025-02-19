<%-- 
    Document   : listSuplier
    Created on : Oct 14, 2024, 2:57:42 PM
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
    <body>
        <%
         Vector<Suppliers> vector=(Vector<Suppliers>)request.getAttribute("listSupplier");
        %>
        <form action="SupplierURL" method="get">
            <input type="hidden" name="service" value="AllSupplier"> 
            <p>
                SearchName: <input type="text" name="cname" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="SupplierURL?service=insertSupplier">insert Supplier</a></p>
        <table border=1  style=" background-color: aqua;">
            <tr>
                <th>SupplierID</th>
                <th>CompanyName</th>
                <th>ContactName</th>
                <th>ContactTitle</th>
                <th>Address</th>
                <th>City</th>
                <th>Region</th>
                <th>PostalCode</th>
                <th>Country</th>
                <th>Phone</th>
                <th>Fax</th>
                <th>HomePage</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <tr>
                <%for (Suppliers sup : vector) {%>
                <td><%=sup.getSupplierID()%></td>
                <td><%=sup.getCompanyName()%></td>
                <td><%=sup.getContactName()%></td>
                <td><%=sup.getContactTitle()%></td>
                <td><%=sup.getAddress()%></td>
                <td><%=sup.getCity()%></td>
                <td><%=sup.getRegion()%></td>
                <td><%=sup.getPostalCode()%></td>
                <td><%=sup.getCountry()%></td>
                <td><%=sup.getPhone()%></td>
                <td><%=sup.getFax()%></td>
                <td><%=sup.getHomePage()%></td>
                <td><a href="SupplierURL?service=updateSupplier&sid=<%=sup.getSupplierID()%>">update</a></td>
                <td><a href="SupplierURL?service=deleteSupplier&sid=<%=sup.getSupplierID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
