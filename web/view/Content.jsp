<%-- 
    Document   : listProduct
    Created on : Oct 4, 2024, 2:00:59 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product,entity.Suppliers,entity.Customers,java.sql.ResultSet,java.util.HashMap" %>
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
                    HashMap<Integer, String> hsSup =(HashMap<Integer, String>)request.getAttribute("hsSup");
        %>
        <div>
            <div style="justify-content: center; display: flex; gap: 10%">
                <p style="background-color: aquamarine;"><a style="text-decoration: none" href="CartURL?service=showCart">show cart</a></p>
                <form action="client" method="get">
                    <p>
                        SearchName: <input type="text" name="pname" id="">
                        <input type="submit" value="submit" name="submit">
                        <input type="reset" value="clear">
                    </p>
                </form>
            </div>
            <form action="client" method="get">
                <p>
                    <label for="sort">Sort by Unit price: </label>
                    <input type="radio" name="sort" value="ASC" >Ascending
                    <input type="radio" name="sort" value="DESC" >Decensding
                    <input type="submit" value="submit" name="submit">
                </p>
            </form>
            <div style="display: flex">
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
                            <th>add2Cart</th>
                        </tr>
                        <%for (Product product : vector) {%>
                        <tr>
                            <td><%=product.getProductID()%></td>
                            <td><%=product.getProductName()%></td>
                            <td><%=hsSup.get(product.getSupplierID())%></td>
                            <td><%=product.getCategoryID()%></td>
                            <td><%=product.getQuantityPerUnit()%></td>
                            <td><%=product.getUnitPrice()%></td>
                            <td><%=product.getUnitsInStock() %></td>
                            <td><%=product.getUnitsOnOrder()%></td>
                            <td><%=product.getReorderLevel()%></td>
                            <td><a href="CartURL?service=add2Cart&pid=<%=product.getProductID()%>">add2Cart</a></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
