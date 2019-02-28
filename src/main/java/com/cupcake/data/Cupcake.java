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
public class Cupcake {
    private String top;
    private String bottom;
    private int topPrice;
    private int bottomPrice;

    public Cupcake(String top, String bottom, int topPrice, int bottomPrice) {
        this.top = top;
        this.bottom = bottom;
        this.topPrice = topPrice;
        this.bottomPrice = bottomPrice;
    }

    public String getTop() {
        return top;
    }

    public String getBottom() {
        return bottom;
    }

    public int getTopPrice() {
        return topPrice;
    }

    public int getBottomPrice() {
        return bottomPrice;
    }
    
    
    
}
