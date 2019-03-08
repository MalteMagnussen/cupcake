<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<%

    out.println("<h1> All invoices </h1>");
    UserDataMapper db = new UserDataMapper();
    List<User> users = db.getUsers();
    for (User user : users) {
        String username = user.getUsername();
        out.println("<br>");
        out.println("<h5> Invoices for: " + username + "</h5>");
        List<ShoppingCart> carts = db.getInvoices(user);
        for (ShoppingCart cart : carts) {
            String date = cart.getDate();
            out.println("<form method=\"post\" action=\"/Cupcake/Controller?command=Product\">\n"
                    + "            <input type=\"hidden\" name=\"origin\" value=\"admininvoice\">\n"
                    + "            <input type=\"hidden\" name=\"date\" value=\"" + date + "\">\n"
                    + "            <input type=\"hidden\" name=\"user\" value=\"" + username + "\">\n"
                    + "            <input type=\"submit\" value=\"Invoice of: " + date + "\"/>\n"
                    + "        </form>");
        }
    }
%>


<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>