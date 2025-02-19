<%-- 
    Document   : MyJSP
    Created on : Oct 4, 2024, 1:18:39 PM
    Author     : dvdung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>script: code java :servlet
            <% float x=100.4f;
            out.println("<h2> value x="+x+"</h2>");
            %>
        <p> expression: in ra gia tri cac bieu thuc
        <h2> value x*6 is <%=x*6%></h2>
        <%for (int i = 10; i < 200; i+=10) {%>
        <hr width="<%=i%>">
        <%}%>
        <p>declared: khai bao global variable
            <%! int global=100; %>
            <%! String getvalue(){
                return "value is " +global;
            }%>
        <h2><%=getvalue()%></h2>
    </body>
</html>
