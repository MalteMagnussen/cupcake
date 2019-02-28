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
class CupcakeDataMapper {

    /**
     * Insert a top into the SQL database.
     *
     * @param name
     * @param price
     * @throws SQLException
     * @throws DataException
     */
    public void addTopping(String name, int price)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();

        String insertTopping = "INSERT INTO `cupcake`.`topping` (tname, price) "
                + "VALUES (?, " + price + ");";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertTopping);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    /**
     * Insert a bottom into the SQL database.
     *
     * @param name
     * @param price
     * @throws SQLException
     * @throws DataException
     */
    public void addBottom(String name, int price)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();

        String insertBottom = "INSERT INTO `cupcake`.`bottom` (bname, price) "
                + "VALUES (?, " + price + ");";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertBottom);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    /**
     * Returns a list of Strings in the order "name" followed by its "price".
     *
     * @return
     * @throws DataException
     */
    public List<String> getToppings() throws DataException {
        try {
            DBConnector conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`topping`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<String> toppings = new ArrayList<>();
            String name = "";
            String price = "";

            while (rs.next()) {
                name = rs.getString("tname");
                toppings.add(name);
                price = "" + rs.getInt("price");
                toppings.add(price);

                return null;
            }
            return toppings;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Returns a list of Strings in the order "name" followed by its "price".
     *
     * @return
     * @throws DataException
     */
    public List<String> getBottom() throws DataException {
        try {
            DBConnector conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`bottom`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<String> bottoms = new ArrayList<>();
            String name = "";
            String price = "";

            while (rs.next()) {
                name = rs.getString("tname");
                bottoms.add(name);
                price = "" + rs.getInt("price");
                bottoms.add(price);

                return null;
            }
            return bottoms;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public int getTopPrice(String name) throws DataException {
        try {
            DBConnector conn = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`topping` "
                    + "WHERE `cupcake`.`topping`.`tname` = '" + name + "';";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int price = 0;

            while (rs.next()) {
                price = rs.getInt("price");
            }
            return price;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
    
    /**
     * Gets a price for a bottom piece of Cupcake.
     * @param name name of the bottom piece.
     * @return int - Price
     * @throws DataException 
     */
    public int getBottomPrice(String name) throws DataException {
        try {
            DBConnector conn = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`bottom` "
                    + "WHERE `cupcake`.`bottom`.`bname` = '" + name + "';";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int price = 0;

            while (rs.next()) {
                price = rs.getInt("price");
            }
            return price;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
}
