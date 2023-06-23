<%-- 
    Document   : shopping
    Created on : Jun 15, 2023, 3:36:01 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Beautiful Tien</title>
    </head>
    <body>
        <h1>Welcome to my store</h1>
        <form action="MainController" method="POST">
            <select name="cmbTea">
                <option value="T01-Peach Tea-25">Peach Tea-25$</option>
                <option value="T02-Lychee Tea-25">Lychee Tea-25$</option>
                <option value="T03-Soursop Tea-30">Soursop Tea-30$</option>
                <option value="T04-Mangosteen Tea-30">Mangosteen Tea-30$</option>
            </select>
            <select name="cmbQuantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="10">10</option>
            </select>
            <input type="submit" name="action" value="Add" />
            <input type="submit" name="action" value="View" />
        </form>
        ${requestScope.MESSAGE}
        ${requestScope.ERROR}
    </body>
</html>
