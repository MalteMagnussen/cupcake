<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>


<%
    User user = (User) session.getAttribute("user");
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    
    for (LineItem item: cart.getLineItems()){
        out.println("<p>"+item.toString()+"</p>");
    }
%>


<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>