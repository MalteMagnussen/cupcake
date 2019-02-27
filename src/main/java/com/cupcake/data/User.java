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
public class User {
    /**
     * Basic User Object Class
     */
    private String name;
    private int balance;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.balance = 0;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }
    
    
}
