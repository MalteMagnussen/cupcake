/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.List;

/**
 *
 * @author
 */
public class User {

    /**
     * Basic User Object Class
     */
    private String username;
    private int balance;
    private String password;
    private String email;
    private ShoppingCart cart;

    public User() {
    }

    public int getTotalPrice() {
        int totalprice = 0;
        cart = getCart();

        List<LineItem> items = cart.getLineItems();

        for (LineItem item : items) {
            totalprice = totalprice
                    + item.getQuantity() * item.getCupcake().getPrice();
        }

        return totalprice;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public void addBalance(int amount) {
        this.balance = this.balance + amount;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", balance=" + balance + ", password=" + password + ", email=" + email + '}';
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
