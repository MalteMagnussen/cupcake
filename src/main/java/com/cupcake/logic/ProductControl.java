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
 * Product Control.
 * Takes Requests from Shop Command. 
 * Handles:
 *      - adding cupcake to the cart
 *      - adding balance to account
 *      - checking out cart
 *      - removing item from cart
 *      - getting invoices
 * @author Malte
 */
public class ProductControl extends Command {

    /**
     * Main method. Contains a switch that delegates to other methods in the
     * class.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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
        if (!"admininvoice".equals(origin) && !"cart".equals(origin)) {
            // Send user back to shop
            RequestDispatcher rd = request.getRequestDispatcher("jsp/Shop.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Get a single cart from a single user. For Admin.
     *
     * @param request
     * @param session
     * @param response
     * @throws IOException
     * @throws ServletException
     */
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

        if (carts != null) {
            for (ShoppingCart cart : carts) {
                if (date.equals(cart.getDate())) {
                    session.setAttribute("cart", cart);
                }
            }
        }

        // Send admin on to the invoice
        RequestDispatcher rd = request.getRequestDispatcher("jsp/admininvoices.jsp");
        rd.forward(request, response);
    }

    /**
     * Get a single cart from a list of carts. We just match on the date. No way
     * someone makes two invoices within 1 sec.
     *
     * @param request
     * @param session
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void cart(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        String date = (String) request.getParameter("date");
        List<ShoppingCart> carts = (List<ShoppingCart>) session.getAttribute("carts");

        for (ShoppingCart cart : carts) {
            if (date.equals(cart.getDate())) {
                session.setAttribute("cart", cart);
            }
        }

        // Send user on to the invoice
        RequestDispatcher rd = request.getRequestDispatcher("jsp/invoices.jsp");
        rd.forward(request, response);
    }

    /**
     * Removes an item from the cart. Does it on Session.
     *
     * @param user Owner of the cart.
     * @param request
     */
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

    /**
     * Does a checkout of the cart. Puts cart into SQL database as an invoice.
     * Then empties the cart on the session. Also sends a message to user: "We
     * have received your order. Here is the total: "amount"$
     *
     * @param user User who you wish to checkout.
     * @param request
     */
    private void checkout(User user, HttpServletRequest request) {
        int cartPrice = user.getTotalPrice();

        UserDataMapper db = new UserDataMapper();
        /* Removes the money from the Balance of the User */
        user.addBalance(-cartPrice);
        /* Adds cart as an invoice in the SQL */
        db.addInvoice(user);
        // Set message to send to the user
        String errormessage = "We have received your order. "
                + "Here is the total: " + user.getTotalPrice() + "$";
        /* Makes a new empty shoppingcart and adds that to user
            effectively resetting the cart. */
        ShoppingCart emptyCart = new ShoppingCart();
        user.setCart(emptyCart);
        // Send message to the user
        HttpSession session = request.getSession();
        session.setAttribute("errormessage", errormessage);
//        }
    }

    /**
     * Put some money on the users account.
     *
     * @param request
     * @param user User who receives the money.
     * @throws NumberFormatException
     */
    private void addBalance(HttpServletRequest request, User user) throws NumberFormatException {
        /* Pull the amount of money out of the URL */
        String amount = (String) request.getParameter("amount");
        if (amount != null && !amount.isEmpty()) {
            int money = Integer.parseInt(amount);
            /* Add it to the user on session */
            user.addBalance(money);
            /* Add it to the SQL database aswell */
            UserDataMapper DB = new UserDataMapper();
            DB.setBalance(user, user.getBalance());
        } else {
            String errormessage = "Wrong input in Add Balance field. Try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", errormessage);
        }
    }

    /**
     * Put the cupcake into the cart. Call this method to pull cupcake from URL
     * and add it to the cart on session.
     *
     * @param request
     * @param user User who owns the cart.
     * @throws NumberFormatException
     */
    private void cupcakeToCart(HttpServletRequest request, User user) throws NumberFormatException {

        /* Get info about the cupcake */
        String topName = (String) request.getParameter("top");
        String botName = (String) request.getParameter("bottom");

        /* Create cupcake */
        CupcakeDataMapper db = new CupcakeDataMapper();
        Cupcake cupcake = db.makeCupcake(topName, botName);

        /* Make LineItem */
        LineItem lineitem = new LineItem(cupcake);
        try {
            int qty = (int) Integer.parseInt(
                    (String) request.getParameter("qty")
            );
            lineitem.addQuantity(qty);
        } catch (NumberFormatException e) {
            String errormessage = "Wrong input in Quantity field. Try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", errormessage);
            return;
        }

        /* Get cart so we can add the cupcake to it */
        ShoppingCart cart = new ShoppingCart();

        boolean cupcakeincart = false;

        if (user.getCart() != null) {
            cart = user.getCart();
        }

        // If cupcake exists in Cart that means we just have to add the quantity.
        for (LineItem item : cart.getLineItems()) {
            if (item.equals(lineitem)) {
                item.addQuantity(lineitem.getQuantity());
                cupcakeincart = true;
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
