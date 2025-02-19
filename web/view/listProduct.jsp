<%-- 
    Document   : listProduct
    Created on : Oct 4, 2024, 2:00:59 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product,entity.Suppliers,entity.Customers,java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Product> vector=(Vector<Product>)request.getAttribute("listProduct");
            String titleTable=(String)request.getAttribute("titleTable");
            ResultSet rsCat= (ResultSet)request.getAttribute("rsCat");
            ResultSet rsSup= (ResultSet)request.getAttribute("rsSup");
        %>
        <div style="justify-content: center; display: flex; gap: 10%">
            <p style="background-color: aquamarine;"><a style="text-decoration: none" href="ProductURL?service=insertProduct">Insert Product</a></p>
            <p style="background-color: aquamarine;"><a style="text-decoration: none" href="CartURL?service=showCart">show cart</a></p>
            <form action="ProductURL" method="get">
                <input type="hidden" name="service" value="listAllProducts">
                <p>
                    SearchName: <input type="text" name="pname" id="">
                    <input type="submit" value="submit" name="submit">
                    <input type="reset" value="clear">
                </p>
            </form>
        </div>
        <form action="ProductURL" method="get">
            <input type="hidden" name="service" value="listAllProducts">
            <p>
            <label for="sort">Sort by Unit price: </label>
                <input type="radio" name="sort" value="ASC" >Ascending
                <input type="radio" name="sort" value="DESC" >Decensding
            <input type="submit" value="submit" name="submit">
            </p>
        </form>
    <div style="display: flex">
        <div style="width: 15%">
            <div style="width: 10%">
                <table border=1>
                    <caption style="font-size: 130%">List Categories</caption>
                    <%while(rsCat.next()) {%>
                    <tr>
                        <td><a style="display: flex; flex-wrap: wrap" href="ProductURL?service=listAllProducts&cid=<%=rsCat.getInt(1)%>"><%=rsCat.getString(2)%></a></td>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div style="width: 10%">
                <table border=1>
                    <caption style="font-size: 130%">List suppliers</caption>
                    <%while(rsSup.next()) {%>
                    <tr>
                        <td><a style="display: flex; flex-wrap: wrap" href="ProductURL?service=listAllProducts&sid=<%=rsSup.getInt(1)%>"><%=rsSup.getString(2)%></a></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
        <div style="width: 60%">
            <table border=1  style="color: black; text-align: center; ">
                <caption style="font-size: 130%; text-align: left"><%=titleTable%></caption>
                <tr>
                    <th>ProductID</th>
                    <th>ProductName</th>
                    <th>SupplierID</th>
                    <th>CategoryID</th>
                    <th>QuantityPerUnit</th>
                    <th>UnitPrice</th>
                    <th>UnitsInStock</th>
                    <th>UnitsOnOrder</th>
                    <th>ReorderLevel</th>
                    <th>Discontinued</th>
                    <th>update</th>
                    <th>delete</th>
                    <th>add2Cart</th>
                </tr>
                <%for (Product product : vector) {%>
                <tr>
                    <td><%=product.getProductID()%></td>
                    <td><%=product.getProductName()%></td>
                    <td><%=product.getSupplierID()%></td>
                    <td><%=product.getCategoryID()%></td>
                    <td><%=product.getQuantityPerUnit()%></td>
                    <td><%=product.getUnitPrice()%></td>
                    <td><%=product.getUnitsInStock() %></td>
                    <td><%=product.getUnitsOnOrder()%></td>
                    <td><%=product.getReorderLevel()%></td>
                    <td><%=(product.isDiscontinued() == true ? "Continue" : "Discontinue")%></td>
                    <td><a href="ProductURL?service=updateProduct&pid=<%=product.getProductID()%>">update</a></td>
                    <td><a href="ProductURL?service=deleteProduct&pid=<%=product.getProductID()%>">delete</a></td>
                    <td><a href="CartURL?service=add2Cart&pid=<%=product.getProductID()%>">add2Cart</a></td>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
</body>
</html>
