/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

/**
 *
 * @author 
 */
public class Bottom {

    private final String name;
    private final int price;

    /**
     * Constructor.
     * @param name
     * @param price 
     */
    public Bottom(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Get Name of the Bottom
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get the price
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     * To String
     * @return String
     */
    @Override
    public String toString() {
        return "Bottom{" + "name=" + name + ", price=" + price + '}';
    }

}
