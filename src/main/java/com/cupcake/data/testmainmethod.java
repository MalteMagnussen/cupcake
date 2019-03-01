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
public class testmainmethod {
    public static void main(String[] args) throws DataException {
        CupcakeDataMapper db = new CupcakeDataMapper();
        Cupcake testOrder = new Cupcake(db.getTops().get(0), db.getBottoms().get(0));
        System.out.println("Here is your order: " + testOrder.toString());
        //System.out.println("Price: "+db.getTopPrice("Blue Cheese"));
    }
}
