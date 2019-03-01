/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Malte
 */
public abstract class Command {

    /*
        This is called from "FrontController"
    */
    public abstract void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    public static Command from(HttpServletRequest request) {
        Command c;
        String path = request.getPathInfo().substring(1);

        /*  Switches on the paths.  */
        switch (path) {
            case "Login":
                c = new LoginCommand();
                break;
            case "Shop":
                c = new ShopCommand();
                break;
            case "CreateUser":
                c = new CreateUserCommand();
                break;
            case "InputUser":
                c = new InputUserCommand();
                break;
            case "LoginPage":
                c = new LoginPageCommand();
                break;
            /* If none of the above, then go to an error */
            default: 
                c = new UnknownCommand();
        }
        return c;
    }

}
