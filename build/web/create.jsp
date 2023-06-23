<%-- 
    Document   : create
    Created on : Jun 8, 2023, 3:33:28 PM
    Author     : hoang
--%>

<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Create new user:
        <form action="MainController" method="POST">
            User ID<input type="text" name="userID" required="" />${requestScope.USER_ERROR.userIDError}<br/>
            FullName<input type="text" name="fullName" required="" />${requestScope.USER_ERROR.fullNameError}<br/>
            Role ID<input type="text" name="roleID" value="US" readonly="" /><br/>
            Password<input type="password" name="password" required="" /><br/>
            Confirm<input type="password" name="confirm" required="" />${requestScope.USER_ERROR.confirmError}<br/>
            <input type="submit" name="action" value="Create" />
            <input type="reset" value="Reset" />
            ${requestScope.ERROR}
        </form>
    </body>
</html>
