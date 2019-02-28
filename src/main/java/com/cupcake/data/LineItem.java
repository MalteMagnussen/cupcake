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
class LineItem {

    /**
     * When a cupcake is added, a new Line Item is created and added to the
     * cart. If the user order the same cake twice we can add yet another
     * LineItem (or the quantity of an already existing Line Item can be
     * incremented).
     */
    private int invoice_id;
    private int quantity;
    private Cupcake cupcake;

    public LineItem(Cupcake cupcake) {
        this.cupcake = cupcake;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
