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
import static javax.swing.text.html.HTML.Attribute.ID;

/**
 *
 * @author Malte
 */
class CupcakeDataMapper {

    private final DBConnector conn = null;

    /**
     * Get cupcakes from Database
     * Make a GET TOP
     * Make a GET BOTTOM
     */
    
    /**
     * SELECT * FROM cupcake.toppings;
     * tname STRING
     * Price INT
     */
    
    /**
     * SELECT * FROM cupcake.bottom;
     * bname STRING
     * Price INT
     */
    public void addTopping(String name, int price)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();
        
        String insertTopping = "INSERT INTO `cupcake`.`toppings` (tname, price) "
                + "VALUES (?, " + price +");";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertTopping);
        ps.setString(1, name);
        ps.executeUpdate();
    }
    
    public void addBottom(String name, int price)
            throws SQLException, DataException{
        DBConnector conn = new DBConnector();
        
        String insertBottom = "INSERT INTO `cupcake`.`bottom` (bname, price) "
                + "VALUES (?, " + price +");";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertBottom);
        ps.setString(1, name);
        ps.executeUpdate();
    }
    
    public List<String> getToppings() throws DataException{
        try {
            DBConnector conn = new DBConnector();
            
            String query = "SELECT * FROM `Cupcake`.`toppings`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            List<String> toppings = new ArrayList<>();
            String name = "";
            String price = "";
            
            while (rs.next()){
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
    
    public List<String> getBottom() throws DataException{
        try {
            DBConnector conn = new DBConnector();
            
            String query = "SELECT * FROM `Cupcake`.`bottom`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            List<String> bottoms = new ArrayList<>();
            String name = "";
            String price = "";
            
            while (rs.next()){
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
    
    
}
