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
public class Cupcake {

    /**
     * Basic object class.
     */
    private final Top top;
    private final Bottom bottom;
    private final int totalPrice;

    /**
     * Constructor of Cupcake Object.
     *
     * @param top
     * @param bottom
     */
    public Cupcake(Top top, Bottom bottom) {
        this.top = top;
        this.bottom = bottom;
        this.totalPrice = top.getPrice() + bottom.getPrice();
    }

    /**
     * Get top object
     *
     * @return Top object
     */
    public Top getTop() {
        return top;
    }

    /**
     * Get bottom object
     *
     * @return Bottom object
     */
    public Bottom getBottom() {
        return bottom;
    }

    /**
     * Get price of Cupcake
     *
     * @return int
     */
    public int getPrice() {
        return totalPrice;
    }

    /**
     * To String
     *
     * @return String
     */
    @Override
    public String toString() {
        return bottom.getName() + " bottom with " + top.getName()
                + " as topping, with price = " + totalPrice + " $";
    }

}
