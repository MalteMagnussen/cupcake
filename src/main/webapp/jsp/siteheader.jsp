<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cupcake.logic.UserController"%>
<%@page import="com.cupcake.data.User"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Cupcake</title>

        <base href="${pageContext.request.contextPath}/" />

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link type="text/css" rel="stylesheet" href="css/css.css">

    </head>
    <body>

        <%
            User user = (User) session.getAttribute("user");
            UserController uc = new UserController();
            if (user != null && uc.isValid(user.getUsername(), user.getPassword())) {
                switch (user.getRole()) {
                    case USER:
        %>
        <form method="post" action="/Cupcake/Controller?command=Login">
            <input type="hidden" name="origin" value="logout">
            <input type="submit" value="Log Out"/>
        </form>

        <a href="jsp/invoices.jsp">Invoices</a>

        <a href="jsp/Shop.jsp">Shop</a>

        <%
                break;
            case ADMIN:
        %>
        <form method="post" action="/Cupcake/Controller?command=Login">
            <input type="hidden" name="origin" value="logout">
            <input type="submit" value="Log Out"/>
        </form>

        <a href="jsp/admininvoices.jsp">Invoices</a>

        <a href="jsp/Shop.jsp">Shop</a>

        <%
                    break;
            }
        } else {
        %>
        <a href="jsp/LoginPage.jsp">Login</a>
        <a href="jsp/registration.jsp">Create User</a>
        <%
            }


        %>

