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
    private String username;
    private int balance;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }
    
    public void addBalance(int amount){
        this.balance = this.balance + amount;
    }
    
    public String getEmail(){
        return email;
    }
    
    
    
}
