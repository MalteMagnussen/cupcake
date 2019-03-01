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
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String userName = "test";
        UserDataMapper instance = new UserDataMapper();
        User expResult = new User("test", "test", "test@gmail.com");
        User result = instance.getUser(userName);
        assertEquals(expResult.toString(), result.toString());
    }

}
