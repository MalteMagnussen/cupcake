<%@page import="java.util.ArrayList"%>
<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>


<%
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

%>

<table border="3" width="2" cellspacing="2" cellpadding="2" id="invoice" class="display">
    <thead>
        <tr>
            <th>Bottom</th>
            <th>Topping</th>
            <th>Price_for_one</th>
            <th>Quantity</th>
            <th>Total_Price</th>
        </tr>
    </thead>
    <tbody>
        <%            for (LineItem item : cart.getLineItems()) {
                out.println("<tr>");

                out.println("<td>" + item.getBottom() + "</td>");
                out.println("<td>" + item.getTop() + "</td>");
                out.println("<td>" + item.getPriceForOne() + "$</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>" + item.getTotalPrice() + "$</td>");

                out.println("</tr>");
            }
        %>
    </tbody>
</table>
    

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>