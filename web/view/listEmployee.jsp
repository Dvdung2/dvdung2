<%-- 
    Document   : listEmployee
    Created on : Oct 11, 2024, 1:44:24 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Employees" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Vector<Employees> vector= (Vector<Employees>)request.getAttribute("listEmployee");
        String titleTable= (String)request.getAttribute("titleTable");
        Employees emp=(Employees)session.getAttribute("Employee");
        %>
        <h1 align="right">
             <%if(emp==null){%>
            <a href="EmployeeURL?service=loginEmployee" >login</a>
            <%}else{%>
            <%="welcome boy "+emp.getLastName()%>
            <a href="EmployeeURL?service=logoutEmployee" >log out</a>
            <%}%>
        </h1>
        <form action="EmployeeURL" method="get">
            <input type="hidden" name="service" value="AllEmployee">
            <p>
                Search Name: <input type="text" name="ename" id="">
                <input type="submit" value="submit" name="submit">
                <input type="reset" value="clear">
            </p>
        </form>
        <p><a href="EmployeeURL?service=insertEmployee">Insert Employee</a></p>
        <table border=1 >
            <caption><%=titleTable%></caption>
            <tr>
                <th>EmployeeID</th>
                <th>LastName</th>
                <th>FirstName</th>
                <th>Title</th>
                <th>TitleOfCourtesy</th>
                <th>BirthDate</th>
                <th>HireDate</th>
                <th>Address</th>
                <th>City</th>
                <th>Region</th>
                <th>PostalCode</th>
                <th>Country</th>
                <th>HomePhone</th>
                <th>Extension</th>
                <th>Photo</th>
                <th>Notes</th>
                <th>ReportsTo</th>
                <th>PhotoPath</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Employees em : vector) {%>
            <tr>
                <td><%=em.getEmployeeID()%></td>
                <td><%=em.getLastName()%></td>
                <td><%=em.getFirstName()%></td>
                <td><%=em.getTitle()%></td>
                <td><%=em.getTitleOfCourtesy()%></td>
                <td><%=em.getBirthDate()%></td>
                <td><%=em.getHireDate()%></td>
                <td><%=em.getAddress()%></td>
                <td><%=em.getCity()%></td>
                <td><%=em.getRegion()%></td>
                <td><%=em.getPostalCode()%></td>
                <td><%=em.getCountry()%></td>
                <td><%=em.getHomePhone()%></td>
                <td><%=em.getExtension()%></td>
                <td><%=em.getPhoto()%></td>
                <td><%=em.getNotes()%></td>
                <td><%=em.getReportsTo()%></td>
                <td><%=em.getPhotoPath()%></td>
                <td><%=(em.isStatus() == true ? "Continue" : "Discontinue")%></td>
                <td><a href="EmployeeURL?service=updateEmployee&eid=<%=em.getEmployeeID()%>">update</a></td>
                <td><a href="EmployeeURL?service=deleteEmployee&eid=<%=em.getEmployeeID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
