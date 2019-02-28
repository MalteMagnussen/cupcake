/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Malte
 */
public class CupcakeDataMapperTest {
    
    public CupcakeDataMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addTopping method, of class CupcakeDataMapper.
     */
    @org.junit.Test
    public void testAddTopping() throws Exception {
        System.out.println("addTopping");
        String name = "";
        int price = 0;
        CupcakeDataMapper instance = new CupcakeDataMapper();
        instance.addTopping(name, price);
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBottom method, of class CupcakeDataMapper.
     */
    @org.junit.Test
    public void testAddBottom() throws Exception {
        System.out.println("addBottom");
        String name = "";
        int price = 0;
        CupcakeDataMapper instance = new CupcakeDataMapper();
        instance.addBottom(name, price);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToppings method, of class CupcakeDataMapper.
     */
    @org.junit.Test
    public void testGetToppings() throws Exception {
        System.out.println("getToppings");
        CupcakeDataMapper instance = new CupcakeDataMapper();
        List<String> expResult = null;
        List<String> result = instance.getToppings();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBottom method, of class CupcakeDataMapper.
     */
    @org.junit.Test
    public void testGetBottom() throws Exception {
        System.out.println("getBottom");
        CupcakeDataMapper instance = new CupcakeDataMapper();
        List<String> expResult = null;
        List<String> result = instance.getBottom();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTopPrice method, of class CupcakeDataMapper.
     */
    @org.junit.Test
    public void testGetTopPrice() throws Exception {
        System.out.println("getTopPrice");
        String name = "Blueberry";
        CupcakeDataMapper instance = new CupcakeDataMapper();
        int expResult = 5;
        int result = instance.getTopPrice(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBottomPrice method, of class CupcakeDataMapper.
     */
    @org.junit.Test
    public void testGetBottomPrice() throws Exception {
        System.out.println("getBottomPrice");
        String name = "Chocolate";
        CupcakeDataMapper instance = new CupcakeDataMapper();
        int expResult = 5;
        int result = instance.getBottomPrice(name);
        assertEquals(expResult, result);
    }
    
}
