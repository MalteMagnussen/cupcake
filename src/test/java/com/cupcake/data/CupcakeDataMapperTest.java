/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.util.ArrayList;
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
     * Test of getTopPrice method, of class CupcakeDataMapper.
     * @throws java.lang.Exception
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
     * @throws java.lang.Exception
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

    /**
     * Test of getTops method, of class CupcakeDataMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTops() throws Exception {
        System.out.println("getTops");
        CupcakeDataMapper instance = new CupcakeDataMapper();
        Top top1 = new Top("Blue cheese", 9);
        Top top2 = new Top("Blueberry", 5);
        Top top3 = new Top("Chocolate", 5);
        Top top4 = new Top("Crispy", 6);
        Top top5 = new Top("Lemon", 8);
        Top top6 = new Top("Orange", 8);
        Top top7 = new Top("Rasberry", 5);
        Top top8 = new Top("Rum/Raisin", 7);
        Top top9 = new Top("Strawberry", 6);
        List<Top> e = new ArrayList<>();
        e.add(top1);
        e.add(top2);
        e.add(top3);
        e.add(top4);
        e.add(top5);
        e.add(top6);
        e.add(top7);
        e.add(top8);
        e.add(top9);
        List<Top> result = instance.getTops();
        assertEquals(e.toString(), result.toString());
    }

    /**
     * Test of getBottoms method, of class CupcakeDataMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBottoms() throws Exception {
        System.out.println("getBottoms");
        CupcakeDataMapper instance = new CupcakeDataMapper();
        List<Bottom> e = new ArrayList<>();
        Bottom bot1 = new Bottom("Almond", 7);
        Bottom bot2 = new Bottom("Chocolate", 5);
        Bottom bot3 = new Bottom("Nutmeg", 5);
        Bottom bot4 = new Bottom("Pistacio", 6);
        Bottom bot5 = new Bottom("Vanilla", 5);
        e.add(bot1);
        e.add(bot2);
        e.add(bot3);
        e.add(bot4);
        e.add(bot5);
        List<Bottom> result = instance.getBottoms();
        assertEquals(e.toString(), result.toString());
    }

    @Test
    public void testGetTotalPrice() throws Exception {
        System.out.println("getTotalPrice");
        CupcakeDataMapper instance = new CupcakeDataMapper();
        Cupcake cup = new Cupcake(instance.getTops().get(0), instance.getBottoms().get(0));
        int expected = 16;
        int actual = cup.getPrice();
        assertEquals(expected, actual);
    }

}
