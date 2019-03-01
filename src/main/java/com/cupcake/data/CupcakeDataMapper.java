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
 * @author Nikolaj
 */
class CupcakeDataMapper {

    /**
     * Insert a top into the SQL database. Dont use.
     *
     * @param name
     * @param price
     * @throws SQLException
     * @throws DataException
     */
    public void addTopping(String name, int price) {
        try {
            DBConnector conn = new DBConnector();
            
            String insertTopping = "INSERT INTO `cupcake`.`topping` (tname, price) "
                    + "VALUES (?, " + price + ");";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertTopping);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeDataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Insert a bottom into the SQL database. Dont use.
     *
     * @param name
     * @param price
     * @throws SQLException
     * @throws DataException
     */
    public void addBottom(String name, int price) {
        try {
            DBConnector conn = new DBConnector();
            
            String insertBottom = "INSERT INTO `cupcake`.`bottom` (bname, price) "
                    + "VALUES (?, " + price + ");";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertBottom);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeDataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a list of Tops.
     *
     * @return
     * @throws DataException
     */
    public List<Top> getTops() {
        try {
            DBConnector conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`topping`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Top> toppings = new ArrayList<>();
            String name = "";
            int price = 0;

            while (rs.next()) {
                name = rs.getString("tname");
                price = rs.getInt("Price");
                Top top = new Top(name, price);
                toppings.add(top);
            }
            return toppings;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Returns a list of Bottoms.
     *
     * @return List of Bottoms.
     */
    public List<Bottom> getBottoms(){
        try {
            DBConnector conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`bottom`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Bottom> bottoms = new ArrayList<>();
            String name = "";
            int price = 0;

            while (rs.next()) {
                name = rs.getString("bname");
                price = rs.getInt("Price");
                Bottom bottom = new Bottom(name, price);
                bottoms.add(bottom);
            }
            return bottoms;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Gets a price for a bottom piece of Cupcake.
     *
     * @param name name of the bottom piece.
     * @return int - Price
     */
    public int getTopPrice(String name) {
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
     *
     * @param name name of the bottom piece.
     * @return int - Price
     */
    public int getBottomPrice(String name) {
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
