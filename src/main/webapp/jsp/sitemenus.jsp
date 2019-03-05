        
<%@page import="com.cupcake.logic.UserController"%>
<%@page import="com.cupcake.data.User"%>
<%
    User user = (User) session.getAttribute("user");
    UserController uc = new UserController();
    if (user != null && uc.isValid(user.getUsername(), user.getPassword())) {
%>

<form method="post" action="/Cupcake/Controller?command=Login">
    <input type="hidden" name="origin" value="logout">
    <input type="submit" value="Log Out"/>
</form>

<%
} else {
%>
<a href="jsp/LoginPage.jsp">Login</a>
<a href="jsp/registration.jsp">Create User</a>
<%
    }


%>

