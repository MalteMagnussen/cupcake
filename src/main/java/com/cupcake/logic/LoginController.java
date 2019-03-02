/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.UserDataMapper;
import com.cupcake.data.User;
import com.mysql.cj.util.StringUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikkel
 */
public class LoginController {

    public LoginController() {
      
    }

    public boolean isValid(String username, String password) throws SQLException {
        UserDataMapper db = new UserDataMapper();
        User user = db.getUser(username);
        if(password == null) return false;
        return password.equals(user.getPassword());
    }

    public User getUser(String username) throws SQLException {
        UserDataMapper db = new UserDataMapper();
        return db.getUser(username);
    }

}
