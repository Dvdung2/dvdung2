<%-- 
    Document   : showCart
    Created on : Oct 22, 2024, 4:14:53 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Cart" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Cart> vector=(Vector<Cart>)request.getAttribute("vectorCart");
            double sum= (double)request.getAttribute("sum");
        %>
        <p><a href="client">continue Shopping</a></p>
        <table border=1  style="color: blue; ">
            <caption><%="Cart table"%></caption>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>Quantity</th>
                <th>UnitPrice</th>
                <th>Discount</th>
                <th>subTotal</th>
                <th>remove</th>
            </tr>
            <%for (Cart cart : vector) {%>
            <tr>
                <td><%=cart.getProductID()%></td>
                <td><%=cart.getProductName()%></td>
                <td><a href="CartURL?service=decrease&pid=<%=cart.getProductID()%>">-</a><input type="text" name="Quantity" id="Quantity" value="<%=cart.getQuantity()%>"><a href="CartURL?service=increase&pid=<%=cart.getProductID()%>">+</a></td>
                <td><%=cart.getUnitPrice()%></td>
                <td><%=cart.getDiscount() %></td>
                <td><%=(cart.getQuantity()*cart.getUnitPrice())-cart.getDiscount()*(cart.getQuantity()*cart.getUnitPrice())%></td>
                <td><a href="CartURL?service=removeCart&pid=<%=cart.getProductID()%>">remove</a></td>
            </tr>
            <%}%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>Total</td>
                <td><%=sum%></td>
                <td><a href="CartURL?service=removeAllCart">removeAll</a></td>
            </tr>
        </table>
    </body>
</html>

