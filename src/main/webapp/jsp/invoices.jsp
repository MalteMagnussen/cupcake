<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<%

    User user = (User) session.getAttribute("user");

    out.println("<h1> Invoices for " + user.getUsername() + "</h1>");

    UserDataMapper db = new UserDataMapper();
    
    List<ShoppingCart> carts = db.getInvoices();
%>


<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>