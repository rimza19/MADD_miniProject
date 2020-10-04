package com.e.fooddiet.IT19064000;
import static org.junit.Assert.*;

import com.e.fooddiet.Add_dish;
import com.e.fooddiet.MainActivity;
import com.e.fooddiet.entities.Account;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IT19064000 {
    public static MainActivity mainActivity ;
    public static Account acc ;
    private static String welcome , email ;
    @BeforeClass
    public static void initMain(){
        mainActivity = new MainActivity();
    }
    @Before
    public void setUp(){
         acc = new Account();
    }
    @Test
    public void UnitTest_1(){
        acc.setEmail("rimzrafeek19@gmail.com");
        assertEquals("Inserted Successfully rimzarafeek19@gmail.com", acc.getWelcome());
    }
    @After
    public void clear(){
        acc.setEmail(null);
    }
    @AfterClass
    public static void eraseObject(){
        mainActivity = null ;
    }
}
