/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.User;
import com.cupcake.data.UserDataMapper;
import com.cupcake.logic.LoginController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mikkel
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Get Parameters from the URL. (From the HTTP request) */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDataMapper db = new UserDataMapper();
        LoginController c = new LoginController(db);
        
        boolean valid = c.isValid(username, password);
        User user = c.getUser(username);

        HttpSession session = request.getSession();
        
        if (valid) {
            session.setAttribute("user", user);
            
            /* session.setAttribute("cart", new ShoppingCart()); */
            /* Shoppingcart is on User now */
            
            User u = (User) session.getAttribute("user");
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (valid) {
                out.println("<h1>Username=" + username + "</h1>");
            } else {
                out.println("<h1> INVALID </h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
