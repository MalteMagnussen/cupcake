/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.UserDataMapper;
import com.cupcake.data.User;
import com.mysql.cj.util.StringUtils;

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
        if (StringUtils.isNullOrEmpty(username)) {
            return false;
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return false;
        }

        User user = db.getUser(username);
        
        if (StringUtils.isNullOrEmpty(user.getPassword())) {
            return false;
        } else if (user.getPassword().isEmpty()) {
            return false;
        } else if (StringUtils.isNullOrEmpty(password)){
            return false;
        } 
        return password.equals(user.getPassword());
    }

    public User getUser(String username) {
        return db.getUser(username);
    }

}
