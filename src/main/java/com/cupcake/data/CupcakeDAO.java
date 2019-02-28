/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malte
 */
public class CupcakeDAO {
    /**
     * A data mapper class with methods to create 
     * top and buttom objects from the database. 
     * E.g.
     *   List<Buttom> buttoms getAllButtoms);
     */
    
    private CupcakeDataMapper db;

    public CupcakeDAO(CupcakeDataMapper db) {
        this.db = db;
    }
    
    public Top getTop(String name){
        try {
            int price = db.getTopPrice(name);
            Top top = new Top(name, price);
            return top;
        } catch (DataException ex) {
            Logger.getLogger(CupcakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Bottom getBottom(String name){
        try {
            int price = db.getBottomPrice(name);
            Bottom bot = new Bottom(name, price);
            return bot;
        } catch (DataException ex) {
            Logger.getLogger(CupcakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Cupcake makeCupcake(String topName, String bottomName){
        Top top = getTop(topName);
        Bottom bottom = getBottom(bottomName);
        return new Cupcake(top, bottom);
    }
}
