/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class UserDAO {

    /**
     * User Data Access Object. Takes Data from a Database Connector Class and
     * makes it into instances of Objects of class User. Also takes Users and
     * breaks them down and inputs them into the database using methods from a
     * Database Connector Class.
     */

    private DataAccessor db;

    public UserDAO(DataAccessor db) {
        this.db = db;
    }

    public User getUser(String username, String password, String email) {

        // Get a list of info about the user.
        List<String> userinfo = null;
        try {
            userinfo = db.getUser(username);
        } catch (DataException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (userinfo != null) {
            // Extract the info from the List in the right order.
            username = userinfo.get(0);
            password = userinfo.get(1);
            int balance = Integer.getInteger(userinfo.get(2));
            email = userinfo.get(3);

            // Create an instance of the User object.
            User user = new User(username, password, email);

            // Add the balance to the Object.
            user.addBalance(balance);

            // Return the user instance.
            return user;
        }
        return null;
    }
//
//    // EXAMPLE
//    public Recipe recipe(String name) {
//        try {
//            int ID = db.getID(name);
//            RecipeDAO rec = new RecipeDAO();
//            Recipe recipe = rec.getRecipe(name);
//            recipe.addImages(db.getImages(ID));
//            recipe.addIngredients(db.getIngredients(ID));
//            recipe.addInstructions(db.getInstructions(ID));
//            recipe.addRating(db.getRatings(ID));
//            return recipe;
//        } catch (DataException e) {
//            System.out.println("ERROR: " + e);
//        }
//        return null;
//    }
//
//    // EXAMPLE
//    public List<Recipe> recipes() {
//        try {
//            int numberofids = db.getnumberofids();
//            List<String> ids = new ArrayList<>();
//            for (int i = 1; i <= numberofids; i++) {
//                String name = db.getName(i);
//                ids.add(name);
//            }
//
//            List<Recipe> recipes = new ArrayList<>();
//            for (String str : ids) {
//                recipes.add(recipe(str));
//            }
//            return recipes;
//        } catch (DataException ex) {
//            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    // EXAMPLE END
}
