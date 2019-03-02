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
    
    UserDataMapper db;

    public LoginController() {
        db = new UserDataMapper();
    }

    public boolean isValid(String username, String password) {
        try {
            User user = db.getUsertwo(username);
            return password.equals(user.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public User getUser(String username) throws SQLException {
        return db.getUser(username);
    }

}
