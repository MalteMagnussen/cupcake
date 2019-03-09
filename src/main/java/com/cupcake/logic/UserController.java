/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.logic;

import com.cupcake.data.UserDataMapper;
import com.cupcake.data.User;
import java.sql.SQLException;

/**
 *
 * @author Mikkel
 */
public class UserController {

    public UserController() {

    }

    /**
     * Checks whether or not User exists in Database.
     * 
     * @param username Name of the User we're validating.
     * @param password Password of the User we're validating.
     * @return boolean Returns True if User exists in the Database.
     * @throws SQLException
     */
    public boolean isValid(String username, String password) throws SQLException {
        User user = getUser(username);
        if (password == null) {
            return false;
        }
        if (user.getPassword() == null) {
            return false;
        }
        return password.equals(user.getPassword());
    }

    /**
     * Pull User out of Database. 
     *
     * @param username Name of the User we want to get from the SQL database.
     * @return User object.
     * @throws SQLException
     */
    public User getUser(String username) throws SQLException {
        UserDataMapper db = new UserDataMapper();
        return db.getUser(username);
    }

}
