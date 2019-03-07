<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cupcake.logic.UserController"%>
<%@page import="com.cupcake.data.User"%>
<!DOCTYPE html>
<html lang = "en">
    <head>

        <title>Cupcake</title>

        <base href="${pageContext.request.contextPath}/" />

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link type="text/css" rel="stylesheet" href="css/css.css">

    </head>
    <body>
        <img src="https://i.imgur.com/ZMGDK82.png" 
             alt="Cupcake Banner" style="float: top; align-items: center;" >


        <%
            String errormessage = (String) session.getAttribute("errormessage");
            if (errormessage != null && !errormessage.isEmpty()) {
                out.println("");
            }

            User user = (User) session.getAttribute("user");
            UserController uc = new UserController();
            if (user != null && uc.isValid(user.getUsername(), user.getPassword())) {
                if ("user".equals(user.getRole())) {

        %>
        <form method="post" action="/Cupcake/Controller?command=Login">
            <input type="hidden" name="origin" value="logout">
            <input type="submit" value="Log Out"/>
        </form>

        <a href="jsp/invoices.jsp">Invoices</a>

        <a href="jsp/Shop.jsp">Shop</a>

        <%        } else if ("admin".equals(user.getRole())) {
        %>
        <form method="post" action="/Cupcake/Controller?command=Login">
            <input type="hidden" name="origin" value="logout">
            <input type="submit" value="Log Out"/>
        </form>

        <a href="jsp/admininvoices.jsp">Invoices</a>

        <a href="jsp/Shop.jsp">Shop</a>

        <%
            }
        } else {
        %>
        <div style="">
        <a href="jsp/LoginPage.jsp">Login</a>
        <a href="jsp/registration.jsp">Create User</a>
        </div>
        <%
            }


        %>

