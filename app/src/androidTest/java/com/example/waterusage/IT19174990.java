package com.example.waterusage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IT19174990 {
    private static MainActivity mainActivity;
    private float volume;

    @BeforeClass
    //called only once before every thing
    public static void initMain(){
        //initalize the variable
        mainActivity = new MainActivity();
    }

    @Before
    //called every time before each test
    public void setup(){
        volume = 0.00f;
    }

    @Test
    //test methods
    public void dailyGoal_isCorrect(){
        volume = mainActivity.setGoal(60);
        assertEquals(3.18,volume,0.01);
    }

    @After
    public void clear(){
        volume = 0.0f;
    }

    @AfterClass
    public static void clearAll(){
        mainActivity = null;
    }
}
