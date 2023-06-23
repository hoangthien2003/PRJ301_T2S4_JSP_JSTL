<%-- 
    Document   : login
    Created on : May 29, 2023, 4:40:36 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        Input your information:
        <form action="MainController" method="post">
            User ID<input type="text" name="userID" required /><br/>
            Password<input type="password" name="password" required /><br/>
            <input type="submit" name="action" value="Login" />
            <input type="reset" value="Reset" />
        </form>
        <a href="MainController?action=CreatePage">Create User</a><br/>
        <a href="MainController?action=ShoppingPage">Tien's Farm</a>
        ${requestScope.ERROR}
    </body>
</html>
