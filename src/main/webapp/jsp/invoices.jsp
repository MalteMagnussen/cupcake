<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<%

    User user = (User) session.getAttribute("user");

    out.println("<h1> Invoices for " + user.getUsername() + "</h1>");
    List<ShoppingCart> carts;
    carts = (List<ShoppingCart>) session.getAttribute("carts");
    if (carts != null) {
        for (ShoppingCart cart : carts) {
            String date = cart.getDate();
            out.println("<form method=\"post\" action=\"/Cupcake/Controller?command=Product\">\n"
                    + "            <input type=\"hidden\" name=\"origin\" value=\"cart\">\n"
                    + "            <input type=\"hidden\" name=\"date\" value=\"" + date + "\">\n"
                    + "            <input type=\"submit\" value=\"Invoice of: " + date + "\"/>\n"
                    + "        </form>");

        }
    } else {
        UserDataMapper db = new UserDataMapper();

        carts = db.getInvoices(user);

        session.setAttribute("carts", carts);

        for (ShoppingCart cart : carts) {
            String date = cart.getDate();
            out.println("<form method=\"post\" action=\"/Cupcake/Controller?command=Product\">\n"
                    + "            <input type=\"hidden\" name=\"origin\" value=\"cart\">\n"
                    + "            <input type=\"hidden\" name=\"date\" value=\"" + date + "\">\n"
                    + "            <input type=\"submit\" value=\"Invoice of: " + date + "\"/>\n"
                    + "        </form>");

        }
    }
%>


<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>