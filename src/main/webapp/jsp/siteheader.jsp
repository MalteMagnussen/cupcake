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
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link type="text/css" rel="stylesheet" href="css/cssone.css">
        <link href="floating-labels.css" rel="stylesheet">
        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/floating-labels/">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <!-- Jquery -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <!-- Popper -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <!-- Bootstrap -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <!-- Datatables -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    </head>
    <body>
        <!-- Navigation -->
        <div id ="everything">

            <img src="https://i.imgur.com/ZMGDK82.png" 
                 alt="Cupcake Banner" style="float: top; margin: auto; display: table;" >

            <div id="sitemenus" >
                <%
                    String errormessage = (String) session.getAttribute("errormessage");
                    if (errormessage != null && !errormessage.isEmpty()) {
                        out.println("MESSAGE: " + errormessage);
                    }

                    session.removeAttribute("errormessage");

                    User user = (User) session.getAttribute("user");
                    UserController uc = new UserController();
                    if (user != null && uc.isValid(user.getUsername(), user.getPassword())) {
                        if ("user".equals(user.getRole())) {

                %>
                <form method="post" action="/Cupcake/Controller?command=Login">
                    <input type="hidden" name="origin" value="logout">
                    <input type="submit" value="Log Out"/>
                </form>

                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">Cupcake</a>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="jsp/invoices.jsp"></span>Invoices</a></li><br>
                            <li><a href="jsp/Shop.jsp"></span>Shop</a></li>
                        </ul>
                    </div>
                </nav>

                <%        } else if ("admin".equals(user.getRole())) {
                %>

                <form method="post" action="/Cupcake/Controller?command=Login">
                    <input type="hidden" name="origin" value="logout">
                    <input type="submit" value="Log Out"/>
                </form>

                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">Cupcake</a>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="jsp/admininvoices.jsp"></span>Invoices</a></li><br>
                            <li><a href="jsp/Shop.jsp"></span>Shop</a></li>
                        </ul>
                    </div>
                </nav>

                <%
                    }
                } else {
                %>
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">Cupcake</a>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="jsp/LoginPage.jsp"></span>Login</a></li><br>
                            <li><a href="jsp/registration.jsp"></span>Create User</a></li>
                        </ul>
                    </div>
                </nav>
                <%
                    }


                %>
            </div>


