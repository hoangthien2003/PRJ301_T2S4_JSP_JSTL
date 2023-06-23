<%-- 
    Document   : checkout
    Created on : Jun 16, 2023, 2:57:50 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
    </head>
    <body>
        <%
            String error = (String)request.getAttribute("ERROR");
            if (error == null) {
                String message = (String)request.getAttribute("MESSAGE");
        %>      
                <h1><%= message %></h1>
                <a href="MainController?action=ShoppingPage">Buy more</a>
        <%
            } 
            else {
        %>
                <h1><%= error %></h1>
        <%
            }
        %>
        
    </body>
</html>
