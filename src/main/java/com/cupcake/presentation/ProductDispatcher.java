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
        
            - Takes query of a cupcake and adds that shit to cart.
"http://localhost:8084/Cupcake/Product?origin=addProduct&bottom=Almond&top=Blue+cheese&qty=12&submit=Add+to+cart"
    
            - Takes query of add balance and adds that to SQL.
    
            - Takes query of invoice and adds that to SQL and clears shoppingcart.
    
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
        RequestDispatcher rd = request.getRequestDispatcher("Shop");
        rd.forward(request, response);
    }

    private void cupcakeToCart(HttpServletRequest request, User user, HttpSession session) throws NumberFormatException {
        /* If user wants to add a cupcake to cart */
        String origin = (String) request.getParameter("origin");
        if (origin == null) {

        } else {
            /* Get info about the cupcake */
            String topName = (String) request.getParameter("top");
            String botName = (String) request.getParameter("bottom");

            /* Create cupcake */
            CupcakeDataMapper db = new CupcakeDataMapper();
            Cupcake cupcake = db.makeCupcake(topName, botName);

            /* Make LineItem */
            LineItem lineitem = new LineItem(cupcake);
            int qty = (int) Integer.parseInt((String) request.getParameter("qty"));
            lineitem.addQuantity(qty);

            /* Get cart so we can add the cupcake to it */
            ShoppingCart cart = user.getCart();
            cart.addLineItem(lineitem);

            /* Put cart back on User */
            user.setCart(cart);

            /* Put finished User back on Session */
            session.setAttribute("user", user);
        }
    }

}
