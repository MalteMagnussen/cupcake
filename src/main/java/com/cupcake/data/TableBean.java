/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Malte
 */
@Entity
@ManagedBean(name = "itembean")
@SessionScoped
public class TableBean implements Serializable {

    @Id
    private LineItem item;

    public TableBean(LineItem item) {
        this.item = item;
    }
    
    public int getTotalPrice() {
        return item.getCupcake().getPrice() * item.getQuantity();
    }

    public int getQuantity() {
        return item.getQuantity();
    }

    public String getBottom() {
        return item.getCupcake().getBottom().getName();
    }

    public String getTop() {
        return item.getCupcake().getTop().getName();
    }

    public int getPriceForOne() {
        return item.getCupcake().getPrice();
    }

}


