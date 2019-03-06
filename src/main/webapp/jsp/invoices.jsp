<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<h1> Invoices for: </h1>
<%
    
            User user = (User) session.getAttribute("user");
            
            out.println(user.getUsername());
            
        
%>
    

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>