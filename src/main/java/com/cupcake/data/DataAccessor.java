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
 * @author 
 */
public class DataAccessor {
    /**
     * Contains classes that handle SQL directly.
     * Try to keep them as simple as possible.
     * Remember to refactor into a new method if something becomes too big.
     *
     */
        private final DBConnector conn = null;

    public List<String> getUser(String name) throws DataException{
        try {
            DBConnector conn = new DBConnector();
            
            String query = "SELECT * FROM `Cupcake`.`Users` "
                    + "WHERE `Cupcake`.`Users`.`name` = " + userName + ";";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            List<String> userData = new ArrayList<>();
            String userName = "";
            String password = "";
            String balance = "";
            
            while (rs.next()){
                userName = rs.getString("name");
                if(userName.isEmty() || userName.equals("")){
                userName = rs.getString("name");
                userData.add(userName);
                password = rs.getString("password");
//                password = password.replaceAll(password, "*");
                userData.add(password);
                balance = "" + rs.getInt("balance");
                userData.add(balance);
                }
                return null;
            }
            return userData;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    
    //    // EXAMPLE START
//    public void addInstructions(String instructions, String ID)
//            throws SQLException, DataException {
//        DBConnector conn = new DBConnector();
//        String insertRecipe = "INSERT INTO `recipes`.`instructions` "
//                + "(idinstructions,instructions) "
//                + "VALUES ( ?, ?);";
//        PreparedStatement ps = conn.getConnection().prepareStatement(insertRecipe);
//        ps.setString(1, ID);
//        ps.setString(2, instructions);
//        ps.executeUpdate();
//    }
//    // EXAMPLE
//    public void addIngredients(String ingredients, String ID)
//            throws SQLException, DataException {
//        DBConnector conn = new DBConnector();
//        int id = Integer.getInteger(ID);
//        String[] split = ingredients.split(", ");
//
//        for (String str : split) {
//            String insertIngredients = "INSERT INTO `recipes`.`ingredients` "
//                    + "(idingredients, ingredient) "
//                    + "VALUES ( ?,?);";
//            PreparedStatement ps = conn.getConnection().prepareStatement(insertIngredients);
//            ps.setString(id, str);
//            ps.executeUpdate();
//        }
//    }
//    // EXAMPLE END
}
