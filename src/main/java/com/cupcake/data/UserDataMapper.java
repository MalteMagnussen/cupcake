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

/**
 *
 * @author Nikolaj
 */
public class UserDataMapper {

    /**
     * Contains classes that handle SQL directly. Try to keep them as simple as
     * possible. Remember to refactor into a new method if something becomes too
     * big.
     *
     */
    private final DBConnector conn = null;

    /**
     * Returns a User TO DO - Email???
     *
     * @param userName
     * @return
     * @throws DataException
     */
    public User getUser(String userName) throws DataException {
        try {
            DBConnector conn = new DBConnector();

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
        return null;
    }

    /**
     * Add money to the users account.
     *
     * @param name
     * @param balance
     * @throws SQLException
     * @throws DataException
     */
    public void addBalance(String name, int balance)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();

        String insertBalance = "INSERT INTO cupcake.`users` (balance) "
                + "VALUES (?) WHERE cupcake.`users`.`name` = (?);";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertBalance);
        ps.setInt(1, balance);
        ps.setString(2, name);
        ps.executeUpdate();
    }

    /**
     * Adds User to Database.
     * @param name - Name of the user
     * @param password - Users password
     * @param email - Users email
     * @throws SQLException 
     */
    public void addUser(String name, String password, String email) 
            throws SQLException {
        DBConnector conn = new DBConnector();
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
    }

    /**
     * Add e-mail
     *
     * @param name
     * @param email
     * @throws SQLException
     * @throws DataException
     */
    public void addEmail(String name, String email)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();

        String insertBalance = "INSERT INTO cupcake.`users` (email) "
                + "VALUES (?) WHERE cupcake.`users`.`name` = (?);";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertBalance);
        ps.setString(1, email);
        ps.setString(2, name);
        ps.executeUpdate();
    }

}
