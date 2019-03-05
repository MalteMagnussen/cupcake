/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.Objects;

/**
 *
 * @author
 */
public class Cupcake {

    /*
     
        Basic object class.
      
     */
    private final Top top;
    private final Bottom bottom;
    private final int totalPrice;

    public Cupcake(Top top, Bottom bottom) {
        this.top = top;
        this.bottom = bottom;
        this.totalPrice = top.getPrice() + bottom.getPrice();
    }

    public Top getTop() {
        return top;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public int getPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return bottom.getName() + " bottom with " + top.getName()
                + " as topping. Price for one = " + totalPrice + " $";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cupcake other = (Cupcake) obj;
        if (!Objects.equals(this.top, other.top)) {
            return false;
        }
        if (!Objects.equals(this.bottom, other.bottom)) {
            return false;
        }
        return true;
    }

}
