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
public class Top {

    private final String name;
    private final int price;

    /**
     * Constructor
     *
     * @param name String
     * @param price int
     */
    public Top(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     * To String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Top{" + "name=" + name + ", price=" + price + '}';
    }

}
