<%-- 
    Document   : user
    Created on : May 29, 2023, 4:44:04 PM
    Author     : hoang
--%>

<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User's information:</h1>
    
        User ID: ${sessionScope.LOGIN_USER.userID}<br/>
        Full Name: ${sessionScope.LOGIN_USER.fullName}<br/>
        Role ID: ${sessionScope.LOGIN_USER.roleID}<br/>
        Password: ${sessionScope.LOGIN_USER.password}<br/>
    </body>
</html>
