/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class UserDAO {

    /**
     * User Data Access Object. Takes Data from a Database Connector Class and
     * makes it into instances of Objects of class User. Also takes Users and
     * breaks them down and inputs them into the database using methods from a
     * Database Connector Class.
     */
    private UserDataMapper db;

    public UserDAO(UserDataMapper db) {
        this.db = db;
    }

    /**
     * Returns a user Object when given the username.
     * @param username
     * @return User.
     */
    public User getUser(String username) {
        // Get a list of info about the user.
        List<String> userinfo = null;
        try {
            userinfo = db.getUser(username);
        } catch (DataException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (userinfo != null) {
            // Extract the info from the List in the right order.
            username = userinfo.get(0);
            String password = userinfo.get(1);
            int balance = Integer.getInteger(userinfo.get(2));
            String email = userinfo.get(3);

            // Create an instance of the User object.
            User user = new User(username, password, email);

            // Add the balance to the Object.
            user.addBalance(balance);

            // Return the user instance.
            return user;
        }
        return null;
    }

}
