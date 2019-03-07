<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cupcake.logic.UserController"%>
<%@page import="com.cupcake.data.User"%>
<!DOCTYPE html>
<html lang = "en">
    <head>

        <title>Cupcake</title>

        <base href="${pageContext.request.contextPath}/" />
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link type="text/css" rel="stylesheet" href="css/cssone.css">
        


    </head>
    <body>
        <!-- Navigation -->
        <div id ="everything">

            <img src="https://i.imgur.com/ZMGDK82.png" 
                 alt="Cupcake Banner" style="float: top; align-items: center;" >

            <div id="sitemenus" >
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
                
                    <a href="jsp/LoginPage.jsp">Login</a>
                    <a href="jsp/registration.jsp">Create User</a>

                <%
                    }


                %>
            </div>


