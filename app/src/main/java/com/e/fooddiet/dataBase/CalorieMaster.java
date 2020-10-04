package com.e.fooddiet.dataBase;

import android.provider.BaseColumns;

public class CalorieMaster {

    public CalorieMaster() {
    }

    public static class Calorie implements BaseColumns {

        public static final String DAYS_TABLE = "days";
        public static final String DISH_TABLE = "dishes";
        public static final String DAYS_DISHES_TABLE = "days_dishes";
        public static final String WEIGHT_TABLE = "weight";
        public static final String ACCOUNT_TABLE2 = "Account2" ;

        public static final String COLUMN_ID = "_ID";
        public static final String DAYS_COLUMN_DATE = "date";
        public static final String DAYS_COLUMN_RECORD = "record";
        public static final String DISH_COLUMN_NAME = "dish";
        public static final String DISH_COLUMN_CALORIES_PER_100_GM = "calories";
        public static final String DAYS_DISH_COLUMN_WEIGHT = "weight";


        //weight table columns
        public static final String WEIGHT_COLUMN_GOAL_WEIGHT = "Goal_weight";
        public static final String WEIGHT_COLUMN_CURRENT_WEIGHT = "Current weight";

        //account table columns
        //registration and profile
        public static final String ACC_COLUMN_EMAIL_reg = "Email_reg";
        public static final String  ACC_COLUMN_DOB_reg = "Dob_reg";
        public static final String  ACC_COLUMN_GENDER_reg = "Gender_reg";
        public static final String  ACC_COLUMN_ACTIVE_reg = "Active_reg" ;
        public static final String  ACC_COLUMN_HEIGHT_reg = "Height_reg" ;
        public static final String  ACC_COLUMN_WEIGHT_reg = "Weight_reg" ;
        public static final String  ACC_COLUMN_GWEIGHT_reg = "GWeight_reg" ;

        //dish table columns
        public static final String DISH_COLUMN_ID = "ID";
        public static final String DISH_COLUMN_DISHNAME = "Dish_name" ;
        public static final String DISH_COLUMN_CAL_PER_100 = "Calories_per_100g" ;


        public static final int MAX_RECORD_WIDTH = 700;
    }
}
