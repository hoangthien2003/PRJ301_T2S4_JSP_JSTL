<%-- 
    Document   : view_cart
    Created on : Jun 15, 2023, 4:20:50 PM
    Author     : hoang
--%>

<%@page import="sample.shopping.Tea"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart of Tien's Farm</title>
    </head>
    <body>
        <% 
            Cart cart = (Cart)session.getAttribute("CART");
            if (cart != null) {
                if (cart.getCart().size() > 0) {
        %>
        <h1>Your cart is here !</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Edit</th>
                    <th>Remove</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for(Tea tea : cart.getCart().values()) {
                        total += tea.getPrice() * tea.getQuantity();
                %>
                <form action="MainController" method="POST">
                   <tr>
                    <td><%= count++ %></td>
                    <td>
                        <input type="text" name="id" value="<%= tea.getId() %>" />
                    </td>
                    <td><%= tea.getName() %></td>
                    <td><%= tea.getPrice() %>$</td>
                    <td>
                        <input type="number" value="<%= tea.getQuantity()%>" min="1" name="quantity" required />
                    </td>
                    <td>
                        <input type="submit" name="action" value="Edit" />
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove" />
                    </td>
                    <td><%= tea.getPrice() * tea.getQuantity() %>$</td>
                </tr>
                </form>
                <%
                    }
                %>
            </tbody>
        </table>
        <h1>Total: <%= total %></h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="total" value="<%= total %>" />
            <input type="submit" name="action" value="Checkout" />
        </form>
        <%
                } else {
        %>
        <h1>Your cart is empty! Buy something, please</h1>
        <%
}}
        %>
        <a href="shopping.html">Add more</a>
    </body>
</html>
