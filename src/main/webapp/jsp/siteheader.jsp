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
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
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


