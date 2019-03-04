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
import java.util.ArrayList;
import java.util.List;
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
    private DBConnector conn;

    public UserDataMapper() {
    }

    /**
     * remove balance from user
     *
     * @param name
     * @param balance
     */
    public void removeBalance(String name, int balance) {
        int end = balance;
        try {
            String query = "SELECT balance FROM cupcake.users WHERE username=" + name + ";";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int startBalance = rs.getInt("balance");
                if (startBalance <= 0) {
                    throw new IllegalArgumentException();
                }

                end = startBalance - balance;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            String insertNewBalance = "INSERT INTO cupcake.`users` (name, balance)"
                    + " VALUES (" + name + ", ?);";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertNewBalance);
            ps.setInt(1, end);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void removeBalance(User user, int balance) {
        String name = user.getUsername();
        int end = balance;
        try {
            String query = "SELECT balance FROM cupcake.users WHERE username=" + name + ";";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int startBalance = rs.getInt("balance");
                if (startBalance <= 0) {
                    throw new IllegalArgumentException();
                }

                end = startBalance - balance;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            String insertNewBalance = "INSERT INTO cupcake.`users` (name, balance)"
                    + " VALUES (" + name + ", ?);";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertNewBalance);
            ps.setInt(1, end);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Returns a User
     *
     * @param userName
     * @return
     * @throws java.sql.SQLException
     */
    public User getUser(String userName) throws SQLException {
        User user = new User();

        conn = new DBConnector();

        String query = "SELECT * FROM cupcake.users "
                + "WHERE `name`='" + userName + "';";

        Connection connection = conn.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            /* Password */
            String pass = rs.getString("password");
            user.setPassword(pass);
            /* Balance */
            int balance = rs.getInt("balance");
            user.setBalance(balance);
            /*  E-mail */
            String e = rs.getString("email");
            user.setEmail(e);
        }
        /* Username */
        user.setUsername(userName);
        return user;
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
            ex.printStackTrace();
            Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Get invite ID.
     *
     * @return ID.
     */
    public int getInvID() {
        try {
            conn = new DBConnector();

            String query = "SELECT COUNT(ID) FROM `Cupcake`.`invoices`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int highestID = 0;

            while (rs.next()) {
                highestID = rs.getInt("COUNT(ID)") + 1;
            }
            return highestID;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    /**
     * Adds User to Database.
     *
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

    /**
     * Get all Users.
     *
     * @return List of Users.
     * @throws SQLException
     */
    public List<User> getUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        conn = new DBConnector();

        String query = "SELECT * FROM cupcake.users";

        Connection connection = conn.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String name = rs.getString("name");
            String password = rs.getString("password");
            int balance = rs.getInt("balance");
            String email = rs.getString("email");
            User user = new User(name, password, email);
            user.addBalance(balance);
            users.add(user);
        }
        return users;
    }

}
