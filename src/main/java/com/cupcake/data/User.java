/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.List;
import java.util.Objects;

/**
 * User Object Class
 * @author 
 */
public class User {

    private String username;
    private int balance;
    private String password;
    private String email;
    private ShoppingCart cart;
    private String role;

    /**
     * User Constructor.
     */
    public User() {
    }

    /** 
     * Total price of the users cart.
     * @return price of the cart.
     */
    public int getTotalPrice() {
        int totalprice = 0;
        cart = getCart();

        if (cart != null && !cart.isEmpty()) {
            List<LineItem> items = cart.getLineItems();

            for (LineItem item : items) {
                totalprice = totalprice
                        + item.getQuantity() * item.getCupcake().getPrice();
            }
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
    
    public void setRole(){
        if ("admin".equals(username)) {
            this.role = "admin";
        } else {
            this.role = "user";
        }
    }

    public String getRole() {
        return role;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    

    
}

