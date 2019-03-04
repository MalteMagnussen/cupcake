<%-- 
    Document   : CreateUser
    Created on : Mar 4, 2019, 2:38:12 PM
    Author     : Malte
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Create User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form method="post" action="/Cupcake/Controller?command=InputUser">
            Name:        <input type="text" name="username"/><br/>
            Password:    <input type="text" name="password"/><br/>
            Email:       <input type="text" name="email"/><br/>
            <input type="submit" value="Create User"/>
        </form>
    </body>
</html>

