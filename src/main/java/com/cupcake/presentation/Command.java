/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        
        String origin = request.getParameter("command");
        
        Map<String, Command> commands = new HashMap<>();
                
        commands.put("LoginPage", new LoginPageCommand());
        commands.put("Login", new LoginCommand());
        commands.put("Shop", new ShopCommand());
        commands.put("Product", new ProductDispatcher());
        // commands.put("CreateUser", new CreateUserCommand());
        commands.put("InputUser", new InputUserCommand());
        
        c = commands.getOrDefault(origin, new UnknownCommand());
        
        return c;
        
//        Command c;
//        String path = request.getPathInfo().substring(1);
//
//        /*  Switches on the paths.  */
//        switch (path) {
//            case "LoginPage":
//                c = new LoginPageCommand();
//                break;
//            case "Login":
//                c = new LoginCommand();
//                break;
//            case "Shop":
//                c = new ShopCommand();
//                break;
//            case "Product":
//                c = new ProductDispatcher();
//                break;
//            case "CreateUser":
//                c = new CreateUserCommand();
//                break;
//            case "InputUser":
//                c = new InputUserCommand();
//                break;
//            
//            /* If none of the above, then go to an error */
//            default: 
//                c = new UnknownCommand();
//        }
//        return c;
    }

}
