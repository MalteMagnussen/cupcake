/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

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
            default:
                errorMessage(request, response);
                break;
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    private void errorMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /* If User is not in Database send him back to LoginPage */
        request.setAttribute("errormessage", "User not registered");
        RequestDispatcher rd = request.getRequestDispatcher("Controller?command=LoginPage");
        rd.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (valid) {

            try {
                HttpSession session = request.getSession();
                /* Pull user out of SQL */
                User user = (User) c.getUser(username);

                /* Put user on session */
                session.setAttribute("user", user);
                /* Forward to Shop */
                response.sendRedirect("jsp/Shop.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

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
