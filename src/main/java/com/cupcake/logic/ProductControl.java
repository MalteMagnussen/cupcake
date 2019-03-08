/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.Cupcake;
import com.cupcake.data.CupcakeDataMapper;
import com.cupcake.data.LineItem;
import com.cupcake.data.ShoppingCart;
import com.cupcake.data.User;
import com.cupcake.data.UserDataMapper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *
 * @author
 */
public class ProductControl extends Command {

    /* 
        Takes Requests from Shop Command. 
        
            - Takes query of add balance and adds that to SQL.
                Not done in ShopCommand yet.
    
            - Takes query of invoice and adds that to SQL and clears shoppingcart.
                Not done in ShopCommand yet.
    
        Forwards back to Shop afterwards with new shit in session.
        
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Pulling the user out of Session */
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /* Switch on Origin. So class knows what method to run. */
        String origin = (String) request.getParameter("origin");
        if (origin != null) {
            switch (origin) {
                case "addProduct":
                    cupcakeToCart(request, user);
                    break;
                case "add balance":
                    addBalance(request, user);
                    break;
                case "checkout":
                    checkout(user, request);
                    break;
                case "removeitem":
                    removeitem(user, request);
                    break;
                case "admininvoice":
                    admininvoice(request, session, response);
                    break;
                case "cart":
                    cart(request, session, response);
                    break;
            }
        }

        /* Put finished User back on Session */
        session.setAttribute("user", user);
        // Send user back to shop
        RequestDispatcher rd = request.getRequestDispatcher("jsp/Shop.jsp");
        rd.forward(request, response);
    }

    private void admininvoice(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        UserDataMapper db = new UserDataMapper();
        String date = (String) request.getParameter("date");
        String username = (String) request.getParameter("user");
        List<ShoppingCart> carts = null;
        try {
            carts = db.getInvoices(db.getUser(username));
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (ShoppingCart cart : carts) {
            if (date.equals(cart.getDate())) {
                session.setAttribute("cart", cart);
            }
        }

        // Send admin on to the invoice
        RequestDispatcher rd = request.getRequestDispatcher("jsp/invoice.jsp");
        rd.forward(request, response);
    }

    private void cart(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        String date = (String) request.getParameter("date");
        List<ShoppingCart> carts = (List<ShoppingCart>) session.getAttribute("carts");

        for (ShoppingCart cart : carts) {
            if (date.equals(cart.getDate())) {
                session.setAttribute("cart", cart);
            }
        }

        // Send user on to the invoice
        RequestDispatcher rd = request.getRequestDispatcher("jsp/invoice.jsp");
        rd.forward(request, response);
    }

    private void removeitem(User user, HttpServletRequest request) {
        /* Get the cart */
        ShoppingCart cart = user.getCart();
        /* Get the parameter from the request */
        String cakename = (String) request.getParameter("cake");
        /* put cart into a List */
        List<LineItem> items = cart.getLineItems();
        /* For each item in the list */
        for (int i = 0; i < items.size(); i++) {
            String tname = items.get(i).getCupcake().getTop().getName();
            String bname = items.get(i).getCupcake().getBottom().getName();
            /* Remove the one that matches the one from the parameter */
            String itemname = bname + tname;
            if (itemname.equals(cakename)) {
                items.remove(i);
            }
        }
        cart.setLineItems(items);
        user.setCart(cart);
    }

    private void checkout(User user, HttpServletRequest request) {
        int userbalance = user.getBalance();
        int cartPrice = user.getTotalPrice();
        /* If user does NOT have enough money for the purchase */
        if (userbalance < cartPrice) {
            // Send errormessage to User
            String errormessage = "Not enough money on your balance "
                    + "for this purchase";
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", errormessage);
        } else {
            /* If user DOES have enough money. */
            UserDataMapper db = new UserDataMapper();
            /* Removes the money from the Balance of the User */
            user.addBalance(-cartPrice);
            /* Adds cart as an invoice in the SQL */
            db.addInvoice(user);
            // Set message to send to the user
            String errormessage = "We have received your order. "
                    + "Here is the total: " + user.getTotalPrice();
            /* Makes a new empty shoppingcart and adds that to user
            effectively resetting the cart. */
            ShoppingCart emptyCart = new ShoppingCart();
            user.setCart(emptyCart);
            // Send message to the user
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", errormessage);
        }
    }

    private void addBalance(HttpServletRequest request, User user) throws NumberFormatException {
        String amount = (String) request.getParameter("amount");
        int money = Integer.parseInt(amount);
        user.addBalance(money);
        UserDataMapper DB = new UserDataMapper();
        DB.setBalance(user, user.getBalance());
    }

    private void cupcakeToCart(HttpServletRequest request, User user) throws NumberFormatException {

        /*  TO DO
               - Check if Cupcake already is in cart.
               - If it is, just increase its LineItems Quantity with given amount.
        
            Get info about the cupcake */
        String topName = (String) request.getParameter("top");
        String botName = (String) request.getParameter("bottom");

        /* Create cupcake */
        // TO DO - Protect this from nullpointers
        CupcakeDataMapper db = new CupcakeDataMapper();
        Cupcake cupcake = db.makeCupcake(topName, botName);

        /* Make LineItem */
        // TO DO - Protect: If quantity field is empty you get a 
        // java.lang.NumberFormatException: For input string: ""
        LineItem lineitem = new LineItem(cupcake);
        int qty = (int) Integer.parseInt(
                (String) request.getParameter("qty")
        );
        lineitem.addQuantity(qty);

        /* Get cart so we can add the cupcake to it */
        ShoppingCart cart = new ShoppingCart();
        ShoppingCart usercart = null;

        boolean cupcakeincart = false;

        if (user.getCart() != null) {
            usercart = user.getCart();
            // If cupcake exists in Cart.
            for (LineItem item : usercart.getLineItems()) {
                if (item.equals(lineitem)) {
                    item.addQuantity(lineitem.getQuantity());
                    cupcakeincart = true;
                }
            }
            // If cupcake exists in Cart end.
        }

        if (usercart != null && usercart.getLineItems() != null) {
            List<LineItem> items = usercart.getLineItems();
            for (LineItem item : items) {
                cart.addLineItem(item);
            }
        }

        // Adds item to the cart.
        if (cupcakeincart == false) {
            cart.addLineItem(lineitem);
        }

        /* Put cart back on User */
        user.setCart(cart);

    }

}
