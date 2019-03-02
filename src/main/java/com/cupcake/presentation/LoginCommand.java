/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.User;
import com.cupcake.data.UserDataMapper;
import com.cupcake.logic.LoginController;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mikkel
 */
public class LoginCommand extends Command {

    /**
     * LoginCommand. Starts the Session.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Get Parameters from the URL. (From the HTTP request) */
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        LoginController c = new LoginController();

        boolean valid = false;

        if (!StringUtils.isNullOrEmpty(password)
                && !StringUtils.isNullOrEmpty(username)) {
            try {
                valid = c.isValid(username, password);
            } catch (SQLException ex) {
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        HttpSession session = request.getSession();

        if (valid) {
            try {
                User user = (User) c.getUser(username);
                session.setAttribute("user", user);
            } catch (SQLException ex) {
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /* If User is in Database send him on to the Shop */
        if (valid) {
            RequestDispatcher rd = request.getRequestDispatcher("Shop");
            rd.forward(request, response);
        } else {
            // If User is not in Database send him back to this site
            RequestDispatcher rd = request.getRequestDispatcher("LoginPage");
            rd.forward(request, response);

        }

    }
}
