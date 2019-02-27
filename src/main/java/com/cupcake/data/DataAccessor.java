/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
    // EXAMPLE START
    public void addInstructions(String instructions, String ID)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();
        String insertRecipe = "INSERT INTO `recipes`.`instructions` "
                + "(idinstructions,instructions) "
                + "VALUES ( ?, ?);";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertRecipe);
        ps.setString(1, ID);
        ps.setString(2, instructions);
        ps.executeUpdate();
    }
    // EXAMPLE
    public void addIngredients(String ingredients, String ID)
            throws SQLException, DataException {
        DBConnector conn = new DBConnector();
        int id = Integer.getInteger(ID);
        String[] split = ingredients.split(", ");

        for (String str : split) {
            String insertIngredients = "INSERT INTO `recipes`.`ingredients` "
                    + "(idingredients, ingredient) "
                    + "VALUES ( ?,?);";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertIngredients);
            ps.setString(id, str);
            ps.executeUpdate();
        }
    }
    // EXAMPLE END
}
