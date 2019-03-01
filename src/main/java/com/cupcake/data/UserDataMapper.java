/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class UserDataMapper {

    /**
     * Contains classes that handle SQL directly. Try to keep them as simple as
     * possible. Remember to refactor into a new method if something becomes too
     * big.
     *
     */
    private DBConnector conn = null;

    /**
     * Returns a User TO DO - Email???
     *
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        if (userName != null) {
            try {
                 conn = new DBConnector();

                String query = "SELECT * FROM cupcake.users "
                        + "WHERE `name`='" + userName + "';";

                Connection connection = conn.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String password = rs.getString("password");
                    int balance = rs.getInt("balance");
                    String email = rs.getString("email");
                    User user = new User(userName, password, email);
                    user.addBalance(balance);
                    return user;
                }
                return null;
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }

    /**
     * Add money to the users account.
     *
     * @param name
     * @param balance
     */
    public void addBalance(String name, int balance) {
        try {
            conn = new DBConnector();
            
            String insertBalance = "INSERT INTO cupcake.`users` (balance) "
                    + "VALUES (?) WHERE cupcake.`users`.`name` = (?);";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertBalance);
            ps.setInt(1, balance);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds User to Database.
     * @param name - Name of the user
     * @param password - Users password
     * @param email - Users email
     */
    public void addUser(String name, String password, String email) {
        if (name != null || password != null || email != null) {
            try {
                conn = new DBConnector();
                String insertUser = "INSERT INTO `cupcake`.`users`\n"
                        + "(`name`,\n"
                        + "`password`,\n"
                        + "`balance`,\n"
                        + "`email`)\n"
                        + "VALUES\n"
                        + "(?,\n"
                        + "?,\n"
                        + "0,\n"
                        + "?);";
                PreparedStatement ps = conn.getConnection().prepareStatement(insertUser);
                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Add e-mail
     *
     * @param name
     * @param email
     */
    public void addEmail(String name, String email) {
        try {
            conn = new DBConnector();
            
            String insertBalance = "INSERT INTO cupcake.`users` (email) "
                    + "VALUES (?) WHERE cupcake.`users`.`name` = (?);";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertBalance);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
