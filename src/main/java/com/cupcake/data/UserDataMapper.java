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
            String insertNewBalance = "START TRANSACTION;"
                    + "INSERT INTO cupcake.`users` (name, balance)"
                    + " VALUES (" + name + ", ?);"
                    + "COMMIT;";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertNewBalance);
            ps.setInt(1, end);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            try {
                String rollBack = "ROLLBACK;";
                PreparedStatement ps = conn.getConnection().prepareStatement(rollBack);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     *
     * @param user
     * @param balance
     */
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
            String insertNewBalance = "START TRANSACTION;"
                    + "INSERT INTO cupcake.`users` (name, balance)"
                    + " VALUES (" + name + ", ?);"
                    + "COMMIT;";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertNewBalance);
            ps.setInt(1, end);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            try {
                String rollBack = "ROLLBACK;";
                PreparedStatement ps = conn.getConnection().prepareStatement(rollBack);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
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
    public void addBalance(String name, int money) {
        try {
            conn = new DBConnector();

            String insertBalance = "UPDATE `cupcake`.`users` SET balance = balance "
                    + "+ ? WHERE name = '?';";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertBalance);
            String balance = String.valueOf(money);
            ps.setString(1, balance);
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

            int highestID = 1;

            while (rs.next()) {
                highestID = rs.getInt("COUNT(ID)") + 1;
            }
            return highestID;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 1;
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
                String insertUser = "START TRANSACTION;"
                        + "INSERT INTO `cupcake`.`users`\n"
                        + "(`name`,\n"
                        + "`password`,\n"
                        + "`balance`,\n"
                        + "`email`)\n"
                        + "VALUES\n"
                        + "(?,\n"
                        + "?,\n"
                        + "0,\n"
                        + "?);"
                        + "COMMIT;";
                PreparedStatement ps = conn.getConnection().prepareStatement(insertUser);
                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    String rollBack = "ROLLBACK;";
                    PreparedStatement ps = conn.getConnection().prepareStatement(rollBack);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
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
     * Used by getInvoices.
     *
     * @param user
     * @return
     * @throws SQLException
     */
    public List<Integer> getInvoiceIDs(User user) throws SQLException {
        List<Integer> ints = new ArrayList<>();
        String username = user.getUsername();
        conn = new DBConnector();

        String query = "SELECT id FROM invoices WHERE `name`='" + username + "';";

        Connection connection = conn.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            ints.add(rs.getInt("id"));
        }
        return ints;
    }

    /**
     * Used by getInvoices.
     *
     * @param number
     * @return
     * @throws SQLException
     */
    public ShoppingCart getOrders(int number) throws SQLException {
        List<LineItem> items = new ArrayList<>();
        conn = new DBConnector();
        CupcakeDataMapper db = new CupcakeDataMapper();

        String query = "SELECT * FROM cupcake.ordertails "
                + "WHERE id = " + number + ";";

        Connection connection = conn.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String tname = rs.getString("tname");
            Top top = new Top(tname, db.getTopPrice(tname));
            String bname = rs.getString("bname");
            Bottom bot = new Bottom(bname, db.getBottomPrice(bname));
            int qty = rs.getInt("qty");
            Cupcake cake = new Cupcake(top, bot);
            LineItem item = new LineItem(cake);
            items.add(item);
        }
        
        ShoppingCart cart = new ShoppingCart();
        cart.setLineItems(items);
        cart.setDate(getInvoiceDate(number));
        return cart;
        
    }
    
    public String getInvoiceDate (int id) throws SQLException {
        conn = new DBConnector();
        String query = "SELECT `date` FROM invoices WHERE id = "+id+";";
        
        Connection connection = conn.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String date = "";
        while(rs.next()){
            date = rs.getString("date");
        }
        return date;
    }
    
    public List<ShoppingCart> getInvoices(User user) throws SQLException{
        List<ShoppingCart> invoices = new ArrayList<>();
        for(Integer id: getInvoiceIDs(user)){
            invoices.add(getOrders(id));
        }
        return invoices;
    }

    /**
     * Adds an invoice to the database TO DO - Doesn't really work. Look at
     * notes.
     *
     * @param name
     * @param id
     * @param quantity
     * @param tname
     * @param bname
     */
    public void addInvoice(User user) {
        try {
            conn = new DBConnector();

            int id = getInvID();
            String name = user.getUsername();
            String insertBalance = "INSERT INTO `cupcake`.`invoices` (`name`, id) VALUES (?, ?);";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertBalance);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();

            ShoppingCart cart = user.getCart();
            for (LineItem item : cart.getLineItems()) {
                addOrder(item, id);
            }
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

    public void addOrder(LineItem item, int id) throws SQLException {
        conn = new DBConnector();

        Cupcake cake = item.getCupcake();
        Top top = cake.getTop();
        Bottom bot = cake.getBottom();

        String tname = top.getName();
        String bname = bot.getName();
        int qty = item.getQuantity();

        String query = "INSERT INTO `cupcake`.`ordertails` (id, tname, bname, qty)"
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, tname);
        ps.setString(3, bname);
        ps.setInt(4, qty);
        ps.executeUpdate();
    }

    public void setBalance(User user, int userbalance) {
        try {
            String username = user.getUsername();
            String balance = String.valueOf(userbalance);
            conn = new DBConnector();

            String query = "START TRANSACTION;"
                    + "UPDATE users SET balance = ? WHERE name = ?;"
                    + "COMMIT;";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, balance);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(UserDataMapper.class.getName()).log(Level.SEVERE, null, ex);
            try {
                String rollBack = "ROLLBACK;";
                PreparedStatement ps = conn.getConnection().prepareStatement(rollBack);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
