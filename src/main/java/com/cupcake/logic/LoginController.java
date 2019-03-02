/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.UserDataMapper;
import com.cupcake.data.User;

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
        if (username == null || username.isEmpty()) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }

        User user = db.getUser(username);
        if (user.getPassword() != null){
        return password.equals(user.getPassword());
        }
        return false;
    }

    public User getUser(String username) {
        return db.getUser(username);
    }

}
