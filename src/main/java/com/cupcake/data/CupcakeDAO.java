/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.List;

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
        int price = db.getTopPrice(name);
        Top top = new Top(name, price);
        return top;
    }
    
    public Bottom getBottom(String name){
        int price = db.getBottomPrice(name);
        Bottom bot = new Bottom(name, price);
        return bot;
    }
    
    public Cupcake makeCupcake(String topName, String bottomName){
        Top top = getTop(topName);
        Bottom bottom = getBottom(bottomName);
        return new Cupcake(top, bottom);
    }
}
