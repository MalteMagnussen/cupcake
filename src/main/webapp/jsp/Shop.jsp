<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cupcake.data.UserDataMapper"%>
<%@page import="com.cupcake.data.User"%>
<%@page import="com.cupcake.data.ShoppingCart"%>
<%@page import="com.cupcake.data.LineItem"%>
<%@page import="com.cupcake.data.CupcakeDataMapper"%>
<%@page import="java.util.List"%>
<%@page import="com.cupcake.data.Top"%>
<%@page import="com.cupcake.data.Bottom"%>
<jsp:include page='/jsp/siteheader.jsp'></jsp:include>
    <!-- Author: Malte -->
    <h1> SHOP </h1>

<%  
    /* Pulling the User out from the SQL */
    User user = (User) session.getAttribute("user");

    /* Instance of relevant DataMapper */
    CupcakeDataMapper db = new CupcakeDataMapper();

    /* Shows which user is logged in */
    out.println("<h3><center> " + user.getUsername() + " is logged in at " + displayDate() + "</center></h3>");

    /* Shows the users balance */
    out.println("<p style=\"font-size:18px\"> "
            + "Users Balance: " + user.getBalance() + "</p>");

%>

<!-- Add Balance to the User - Button -->
<p> Add Balance: </p> 
<form action="Controller?command=Product" method="post">
    <input type="hidden" name="origin" value="add balance">
    Amount:        <input type="text" name="amount"/>
    <input type="submit" value="Add Balance"/>
</form>

<% 
    /* Add balance END */
    // Dropdown menu:
    /* Pulling the tops and bottoms of the cupcakes out of SQL */
    List<Top> tops = db.getTops();
    List<Bottom> bots = db.getBottoms();

    /* Form for dropdowns BEGIN */
%>

<!-- Form start -->
<form action="Controller?command=Product" method="post">
    <!-- Hidden input: &origin=addProduct -->
    <input type="hidden" name="origin" value="addProduct">
    <!-- Table start. Table makes sure it's all organized neatly -->
    <table class="table table-striped">
        
        <!-- Headers of the table -->
        <thead>
            <tr>
                <th>Bottom</th>
                <th>Topping</th>
                <th>Quantity</th>
                <th>Select</th>
                <th></th>
            </tr>
        </thead>

        <!-- Table body start -->
        <tbody>
            <tr>
                <!-- Bottom Dropdown Menu -->
                <td><select name="bottom" id="bottomSelect">
                        <%    for (Bottom bot : bots) {
                                out.print("<option value=\"" + bot.getName()
                                        + "\">" + bot.getName() + " " + bot.getPrice() + "$</option>\n");
                            }

                            out.print("<select>\n");
                            /* Bottom Dropdown Menu end */
                            
                            /* Toppings Dropdown Menu */
                            out.print("<td><select name=\"top\" id=\"topSelect\">\n");

                            for (Top top : tops) {
                                out.print("<option value=\"" + top.getName()
                                        + "\">" + top.getName() + " " + top.getPrice() + "$</option>\n");
                            }
                        %>
                    </select>
                    <!-- Quantity field -->
                <td><input type="text" name="qty" placeholder="quantity" id="qtyInput"></td>
                <!-- Add to cart button -->
                <td><input type="submit" name="submit" value="Add to cart"></td>
            </tr>
        <!-- Table Body end -->
        </tbody>
    <!-- Table end -->    
    </table>
<!-- Form end -->
</form>
<!-- Form for downdrops end -->

<%

    /* Form for ShoppingCart START */
    ShoppingCart cart = user.getCart();

    out.println("<h2> Shopping Cart: </h2>");

    if (cart != null) {
        // prints total price of the cart
        out.print("<h3> Total Price of Cart: " + user.getTotalPrice() + "$</h3>");

        /* 
            Prints every LineItem in the cart in a list. 
            Could be improved with sorting functions and zebra stripes.
         */
        List<LineItem> items = cart.getLineItems();
        for (LineItem item : items) {
            String tname = item.getCupcake().getTop().getName();
            String bname = item.getCupcake().getBottom().getName();
            out.println("<p style=\"font-size:18px\"> "
                    + "Cupcake: " + item.toString()
                    /* Button to remove the ListItem */
                    + "<form action=\"Controller?command=Product\" method=\"post\">\n"
                    + "    <input type=\"hidden\" name=\"origin\" value=\"removeitem\">\n"
                    + "    <input type=\"hidden\" name=\"cake\" value=\"" + bname + tname + "\">\n"
                    + "    <input type=\"submit\" value=\"Remove\"/>\n"
                    + "</form> </p>"
            );
        }
    }
    /* Form for ShoppingCart END */

 /* Cart Checkout Start */
    if (cart != null && !cart.isEmpty() && user.getTotalPrice() < user.getBalance()) {
%>

<p> Checkout Entire Cart and make it an invoice: 
    <!-- Button to checkout the cart -->
<form action="Controller?command=Product" method="post">
    <input type="hidden" name="origin" value="checkout">
    <input type="submit" value="Checkout"/>
</form></p> 

<%
} else {
%>

<!-- If there is nothing in cart, 
        or the customer doesn't have enough money to checkout the cart -->
<p>
    Either cart is empty or you do not have enough money for items in Cart.
    Add more money, or remove items in Cart to be able to checkout.
</p>

<%
    }
    /* Cart Checkout End */
%>

<!-- Method that shows date if called.
        It's called in the top of the .jsp page. -->
<%!
    public String displayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Date date = Calendar.getInstance().getTime();
        return dateFormat.format(date);
    }
%>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
