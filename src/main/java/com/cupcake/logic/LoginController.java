/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.UserDataMapper;
import com.cupcake.data.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikkel
 */
public class LoginController {

    private UserDataMapper db;

    public LoginController(UserDataMapper db) {
        this.db = db;
    }

    public boolean isValid(String username, String password) {
        try {
            if (username == null || username.isEmpty()) {
                return false;
            }
            if (password == null || password.isEmpty()) {
                return false;
            }
            
            User user = db.getUser(username);
            if (user != null) {
                return password.equals(user.getPassword());
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public User getUser(String username) throws SQLException {
        return db.getUser(username);
    }

}
