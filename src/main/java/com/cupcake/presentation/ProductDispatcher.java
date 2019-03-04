/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.Cupcake;
import com.cupcake.data.CupcakeDataMapper;
import com.cupcake.data.LineItem;
import com.cupcake.data.ShoppingCart;
import com.cupcake.data.User;
import java.io.IOException;
import java.util.List;
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
public class ProductDispatcher extends Command {

    /* 
        Takes Requests from Shop Command. 
        
            - Takes query of add balance and adds that to SQL.
                Not done in ShopCommand yet.
    
            - Takes query of invoice and adds that to SQL and clears shoppingcart.
                Not done in ShopCommand yet.
    
        Forwards back to Shop Command afterwards with new shit in session.
        
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Pulling the user out of Session */
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // If user wants to add a cupcake to cart.
        cupcakeToCart(request, user, session);

        // Send user back to shop
        RequestDispatcher rd = request.getRequestDispatcher("jsp/Shop.jsp");
        rd.forward(request, response);
    }

    private void cupcakeToCart(HttpServletRequest request, User user, HttpSession session) throws NumberFormatException {

        /*  TO DO
                Check if Cupcake already is in cart.
                If it is, just increase its LineItems Quantity with given amount.
         */
 /* If user wants to add a cupcake to cart */
        String origin = (String) request.getParameter("origin");
        if (origin == null) {

        } else {
            /* Get info about the cupcake */
            String topName = (String) request.getParameter("top");
            String botName = (String) request.getParameter("bottom");

            /* Create cupcake */
            // Protect from null
            CupcakeDataMapper db = new CupcakeDataMapper();
            Cupcake cupcake = db.makeCupcake(topName, botName);

            /* Make LineItem */
            // If quantity field is empty you get a 
            // java.lang.NumberFormatException: For input string: ""
            // Protect from null
            LineItem lineitem = new LineItem(cupcake);
            int qty = (int) Integer.parseInt(
                    (String) request.getParameter("qty")
            );
            lineitem.addQuantity(qty);

            /* Get cart so we can add the cupcake to it */
            ShoppingCart cart = new ShoppingCart();
            ShoppingCart usercart = null;
            if (user.getCart() != null) {
                usercart = user.getCart();
            }
            if (usercart != null && usercart.getLineItems() != null) {
                List<LineItem> items = usercart.getLineItems();
                for (LineItem item : items) {
                    cart.addLineItem(item);
                }
            }

            // Adds item to the cart.
            cart.addLineItem(lineitem);

            /* Put cart back on User */
            user.setCart(cart);

            /* Put finished User back on Session */
            session.setAttribute("user", user);
        }
    }

}
