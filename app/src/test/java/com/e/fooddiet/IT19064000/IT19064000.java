package com.e.fooddiet.IT19064000;

import com.e.fooddiet.Add_dish;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IT19064000 {
    public static Add_dish add_dish ;

    @BeforeClass
    public static void initMain(){
        add_dish = new Add_dish();
    }

    @Before
    public void setUp(){

    }

    @Test
    @After

    @AfterClass
    public static void eraseObject(){
        add_dish = null ;
    }
}
