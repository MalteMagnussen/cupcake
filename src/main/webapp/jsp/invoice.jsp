<%@page import="com.cupcake.data.TableEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>


<%
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    out.println("<ol>");
    for (LineItem item: cart.getLineItems()){
        out.println("<li><p>"+item.toString()+"</p></li>");
    }
    out.println("</ol>");
    
    
    
    
%>






<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>