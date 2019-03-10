<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>
    <div class="row">
        <div class="col-sm-6">
        <%
            // List of All invoices in the system.
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

    </div>
    <div class="col-sm-6">

        <%
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart != null && !cart.isEmpty()) {
        %>
        <!--  Below is the Script for Sorting.   -->
        <script>
            $(document).ready(function () {
                $('#invoice').DataTable();
            });
        </script>

        <!--  Below is the Table for an Invoice  -->
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


        <%
            }
        %>
    </div>
</div>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>