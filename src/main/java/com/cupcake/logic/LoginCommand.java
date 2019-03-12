/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.User;
import com.cupcake.data.UserDataMapper;
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
 * @author
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

        String origin = (String) request.getParameter("origin");

        switch (origin) {
            case "login": {
                login(request, response);
                break;
            }
            case "registration": {
                registration(request, response);
                break;
            }
            case "logout": {
                logout(request, response);
                break;
            }
        }
    }

    /**
     * Logout. Pulls user out of session. Pulls his balance out of session. Puts
     * balance into SQL. Resets the Session. Forwards User to the Main Page.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Pull user out of session */
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /* Reset Session */
        session.invalidate();
        /* Send back to main page */
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    /**
     * Login method. Validates the user info with the database. Then pulls the
     * user out of database if he's valid. Then places him on the session if
     * he's valid. Then forwards him to shop if he's valid.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /* Get Parameters from the URL. (From the HTTP request) */
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        /* Check if User exists in the SQL database */
        if (!StringUtils.isNullOrEmpty(password)
                && !StringUtils.isNullOrEmpty(username)) {

            try {
                /* check if user is valid */
                User user = new UserDataMapper().getUser(username);
                if (password.equals(user.getPassword())) {
                    HttpSession session = request.getSession();
                    /* Put user on session */
                    session.setAttribute("user", user);
                    /* Forward to Shop */
                    RequestDispatcher rd = request.getRequestDispatcher("jsp/Shop.jsp");
                    rd.forward(request, response);

                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            /* If User is not in Database send him back to LoginPage */
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", "User not registered");
            RequestDispatcher rd = request.getRequestDispatcher("jsp/LoginPage.jsp");
            rd.forward(request, response);

        }

    }

    /**
     * Makes a new User in the SQL database. Forwards to the LoginPage.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Get the username, email and password from the URL Parameters.*/
        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        /* Instance of the relevant DataMapper */
        UserDataMapper db = new UserDataMapper();
        /* Insert the User into the SQL Database */
        db.addUser(username, password, email);
        /* Forward User! */
        RequestDispatcher rd = request.getRequestDispatcher("jsp/LoginPage.jsp");
        rd.forward(request, response);
    }
}
