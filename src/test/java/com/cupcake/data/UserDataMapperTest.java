/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

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
public class UserDataMapperTest {
    
    public UserDataMapperTest() {
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
     * Test of getUser method, of class UserDataMapper.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String userName = "";
        UserDataMapper instance = new UserDataMapper();
        User expResult = null;
        User result = instance.getUser(userName);
        assertEquals(expResult, result);
    }

   
    
}
