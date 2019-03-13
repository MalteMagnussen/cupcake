/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCart Object Class
 * @author 
 */
public class ShoppingCart {

    /**
     * The shopping cart holds Line Items which has information of which cupcake
     * (bottom and topping) and the quantity of cupcakes. 
     */
    private List<LineItem> lineItems;
    private int invoiceid;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    /**
     * Constructor.
     */
    public ShoppingCart() {
        this.lineItems = new ArrayList<>();
    }

    public boolean isEmpty() {
        return lineItems == null || lineItems.isEmpty();
    }

    /**
     * Add line item
     *
     * @param lineItem
     */
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    /**
     *
     * @return
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * Get invoice ID
     *
     * @return int
     */
    public int getInvoiceid() {
        return invoiceid;
    }

    /**
     * Set invoice ID
     *
     * @param invoiceid
     */
    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    /**
     * Customized toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        String print = "";
        for (LineItem item: this.lineItems){
            print += item.toString();
        }
        return "Shopping Cart: " + print;
    }

    public void setLineItems(List<LineItem> items) {
        this.lineItems = items;
    }

}
