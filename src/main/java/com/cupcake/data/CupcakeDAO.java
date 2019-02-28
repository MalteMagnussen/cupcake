/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

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
    
    private DataAccessor db;

    public CupcakeDAO(DataAccessor db) {
        this.db = db;
    }
    
    
}
