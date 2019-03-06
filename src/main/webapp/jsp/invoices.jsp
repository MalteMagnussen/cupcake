<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<%

    User user = (User) session.getAttribute("user");

    out.println("<h1> Invoices for " + user.getUsername() + "</h1>");

    
%>


<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>