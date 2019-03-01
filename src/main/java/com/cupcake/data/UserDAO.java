/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malte
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
     * Returns a User Object when given the username.
     *
     * @param username
     * @return User.
     */
    public User getUser(String username) {
        if (username != null || !username.isEmpty()) {
            try {
                return db.getUser(username);
            } catch (DataException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    /**
     * Make a User.
     * @param username
     * @param password
     * @param email 
     */
    public void makeUser(String username, String password, String email){
        if (username != null || !username.isEmpty()
                || password != null || !password.isEmpty() 
                || email != null || !email.isEmpty()){
        try {
            db.addUser(username, password, email);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }} else {
            // Something to indicate that it went wrong. TO DO
        }
    }

}
