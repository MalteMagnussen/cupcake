/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TO DO
 *
 * @author
 */
public class ProductDispatcher extends Command implements RequestDispatcher {

    /* 
        Takes Requests from Shop Command. 
        
            - Takes query of a cupcake and adds that shit to cart.
    
            - Takes query of add balance and adds that to SQL.
    
            - Takes query of invoice and adds that to SQL and clears shoppingcart.
    
        Forwards back to Shop Command afterwards with new shit in session.
        
    */
    
    @Override
    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 

}
