/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import com.cupcake.data.User;
import com.cupcake.data.UserDataMapper;
import com.cupcake.logic.UserController;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
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
     * LoginCommand. Starts the Session. Forwards to Shop if Login is correct.
     * Sends you back to LoginPage with an error message (TODO errormessage) if
     * login is invalid.
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

        UserController c = new UserController();

        boolean valid = false;

        /* Check if User exists in the SQL database */
        if (!StringUtils.isNullOrEmpty(password)
                && !StringUtils.isNullOrEmpty(username)) {
            try {
                valid = c.isValid(username, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        HttpSession session = request.getSession();

        /* If User is in Database send him on to the Shop */
        if (valid) {
            try {
                /* Pull user out of SQL */
                User user = (User) c.getUser(username);
                /* Put user on session */
                session.setAttribute("user", user);
                /* Forward to Shop */
                response.sendRedirect("Shop");
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            /* If User is not in Database send him back to LoginPage */
            request.setAttribute("errormessage", "User not registered");
            RequestDispatcher rd = request.getRequestDispatcher("LoginPage");
            rd.forward(request, response);
        }

    }
}
