<%-- 
    Document   : listCustomer
    Created on : Oct 10, 2024, 4:44:17 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Customers> vector=(Vector<Customers>)request.getAttribute("listCustomer");
            String titleTable=(String)request.getAttribute("titleTable");
            Customers customer=(Customers)session.getAttribute("customer");
        %>
         <h1 align="right">
             <%if(customer==null){%>
            <a href="CustomerURL?service=loginCustomer" >login</a>
            <%}else{%>
            <%="welcome boy "+customer.getContactName()%>
            <a href="CustomerURL?service=logoutCustomer" >log out</a>
            <%}%>
        </h1>
        <form action="CustomerURL" method="get">
            <input type="hidden" name="service" value="AllCustomer">
            <p>
                Search Name: <input type="text" name="cname" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p style="background-color: aquamarine; text-decoration: none"><a href="CustomerURL?service=insertCustomer">Insert Customer</a></p>
        <table border=1 style="color: blue;">
            <tr>
                <th>CustomerID</th>
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
                <th>Status</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <tr>
            <%for (Customers cus : vector) {%>
            <td><%=cus.getCustomerID()%></td>
            <td><%=cus.getCompanyName()%></td>
            <td><%=cus.getContactName()%></td>
            <td><%=cus.getContactTitle()%></td>
            <td><%=cus.getAddress()%></td>
            <td><%=cus.getCity()%></td>
            <td><%=cus.getRegion()%></td>
            <td><%=cus.getPostalCode()%></td>
            <td><%=cus.getCountry()%></td>
            <td><%=cus.getPhone()%></td>
            <td><%=cus.getFax()%></td>
            <td><%=(cus.isStatus() == true ? "Continue" : "Discontinue")%></td>
            <td><a href="CustomerURL?service=updateCustomer&cid=<%=cus.getCustomerID()%>">update</a></td>
            <td><a href="CustomerURL?service=deleteCustomer&cid=<%=cus.getCustomerID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
