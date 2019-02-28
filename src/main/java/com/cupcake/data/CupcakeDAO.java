/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malte
 */
public class CupcakeDAO {

    /**
     * A data mapper class with methods to create top and buttom objects from
     * the database. E.g. List<Buttom> buttoms getAllButtoms);
     */
    private CupcakeDataMapper db;

    public CupcakeDAO(CupcakeDataMapper db) {
        this.db = db;
    }

    /**
     * Get a top from name.
     * @param name
     * @return Top
     */
    public Top getTop(String name) {
        try {
            int price = db.getTopPrice(name);
            Top top = new Top(name, price);
            return top;
        } catch (DataException ex) {
            Logger.getLogger(CupcakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Get a bottom from name.
     * @param name
     * @return Bottom
     */
    public Bottom getBottom(String name) {
        try {
            int price = db.getBottomPrice(name);
            Bottom bot = new Bottom(name, price);
            return bot;
        } catch (DataException ex) {
            Logger.getLogger(CupcakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Makes a Cupcake from a top and bottom.
     * @param topName
     * @param bottomName
     * @return Cupcake
     */
    public Cupcake makeCupcake(String topName, String bottomName) {
        Top top = getTop(topName);
        Bottom bottom = getBottom(bottomName);
        return new Cupcake(top, bottom);
    }
}
