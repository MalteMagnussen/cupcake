/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class LoginPageCommand extends Command {

    /**
     * Static HTML site that allows you to log in to CupCake. You input your
     * Username and Password. Then it sends that on to LoginCommand class and
     * the "Login" URL along with the parameters.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.print("<!DOCTYPE html>\n"
                    + "<!--\n"
                    + "To change this license header, choose License Headers in Project Properties.\n"
                    + "To change this template file, choose Tools | Templates\n"
                    + "and open the template in the editor.\n"
                    + "-->\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <title>Login</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + " <p> Login: \n</p> \n"
                    + "        <form method=\"post\" action=\"Login\">\n"
                    + "            Name:        <input type=\"text\" name=\"username\"/><br/>\n"
                    + "            Password:    <input type=\"text\" name=\"password\"/><br/>\n"
                    + "            <input type=\"submit\" value=\"Login\"/>\n"
                    + "        </form>\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "");
        }

    }

}
