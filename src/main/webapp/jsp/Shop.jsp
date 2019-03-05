<%@page import="com.cupcake.data.User"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.CupcakeDataMapper"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.Top"%>
<%@page import="com.cupcake.data.Bottom"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

    <h1> SHOP </h1>

<%  /* Pulling the User out from the SQL */
    User user = (User) session.getAttribute("user");

    /* Instance of relevant DataMapper */
    CupcakeDataMapper db = new CupcakeDataMapper();

    /* Shows which user is logged in */
    out.println("<h1> " + user.getUsername() + " is logged in.</h1>");

    /* Shows the users balance */
    out.println("<p style=\"font-size:18px\"> "
            + "Users Balance: " + user.getBalance() + "</p>");

    // Dropdown menu:
    /* Pulling the tops and bottoms of the cupcakes out of SQL */
    List<Top> tops = db.getTops();
    List<Bottom> bots = db.getBottoms();

    /* Form for dropdowns BEGIN */
%>
<form action="Controller?command=Product" method="post">
    <input type="hidden" name="origin" value="addProduct">
    <table class="table table-striped">
        <thead><tr><th>Bottom</th><th>Topping</th><th>Quantity</th><th>Select</th><th></th></tr></thead>
        <tbody>
            <tr>
                <td><select name="bottom" id="bottomSelect">
                        <%    for (Bottom bot : bots) {
                                out.print("<option value=\"" + bot.getName()
                                        + "\">" + bot.getName() + "</option>\n");
                            }

                            out.print("<select>\n");
                            out.print("<td><select name=\"top\" id=\"topSelect\">\n");

                            for (Top top : tops) {
                                out.print("<option value=\"" + top.getName()
                                        + "\">" + top.getName() + "</option>\n");
                            }
                        %>
                    </select>
                <td><input type="text" name="qty" placeholder="quantity" id="qtyInput"></td>
                <td><input type="submit" name="submit" value="Add to cart"></td><td><span id="errorContainer"></span></td>
            </tr>
        </tbody>
    </table>
</form>
<%
    /* Form for dropdows END */
    // ShoppingCart:
    /* Form for ShoppingCart START */
    ShoppingCart cart = user.getCart();

    out.println("<h2> ShoppingCart: </h2>");
    
    // prints total price of the cart
    if (cart != null){
        out.print("<h3> Total Price of Cart: "+ user.getTotalPrice()+"$</h3>");
    }
    
    if (cart == null) {

    } else {
        /* Prints every LineItem in the cart in a list. */
        List<LineItem> items = cart.getLineItems();
        for (LineItem item : items) {
            out.println("<p style=\"font-size:18px\"> "
                    + "Cupcake: " + item.toString() + "</p>");
        }
    }
    /* Form for ShoppingCart END */


%>
<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
