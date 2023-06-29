<%-- 
    Document   : admin
    Created on : May 29, 2023, 4:48:16 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Admin World! </h1>
        Welcome: ${sessionScope.LOGIN_USER.userID}

        <form action="MainController">
            Search<input type="text" name="search" value="${param.search}"/>
            <input type="submit" name="action" value="Search" />
        </form>

        <a href="MainController?action=Logout">Logout</a>
        <c:url var="logoutLink" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>
        <a href="${logoutLink}">Logout JSTL</a>

        <c:if test="${requestScope.LIST_USER != null}">
            <c:if test="${not empty requestScope.LIST_USER}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th>Update</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${requestScope.LIST_USER}" varStatus="counter">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="userID" value="${user.userID}" readonly="" />
                                </td>
                                <td>
                                    <input type="text" name="fullName" value="${user.fullName}" required="" />
                                </td>
                                <td>
                                    <input type="text" name="roleID" value="${user.roleID}" required="" />
                                </td>
                                <td>${user.password}</td>
                                <td>
                                    <input type="submit" name="action" value="Update" />
                                    <input type="hidden" name="search" value="${param.search}"
                                </td>
                                <td>
                                    <c:url var="removeLink" value="MainController">
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="userID" value="${user.userID}"></c:param>
                                        <c:param name="search" value="${param.search}"></c:param>
                                    </c:url>
                                    <a href="${removeLink}">Remove</a>
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </c:if>
        ${requestScope.ERROR}
    </body>
</html>
