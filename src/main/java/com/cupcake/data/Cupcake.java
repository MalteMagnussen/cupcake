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
    
    /**
     * Basic object class.
     */

    private Top top;
    private Bottom bottom;

    public Cupcake(Top top, Bottom bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public Top getTop() {
        return top;
    }

    public Bottom getBottom() {
        return bottom;
    }

}
