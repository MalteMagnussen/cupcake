/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.UserDataMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class InputUserCommand extends Command {

    /**
     * Adds the User to the SQL database.
     * This Method is called from CreateUserCommand.
     * This Method sends the user to LoginPageCommand.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Get the username, email and password from the URL Parameters.*/
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        /* Instance of the relevant DataMapper */
        UserDataMapper db = new UserDataMapper();

        /* Insert the User into the SQL Database */
        db.addUser(username, password, email);

        /* Welcome User! */
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("Welcome " + username);

            out.print("<a href='LoginPage'>  Buy Cupcakes  </a>");
        }

    }

}
