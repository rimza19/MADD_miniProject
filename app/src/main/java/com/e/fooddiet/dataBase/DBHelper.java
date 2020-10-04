package com.e.fooddiet.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.e.fooddiet.entities.Account;
import com.e.fooddiet.entities.Day;
import com.e.fooddiet.entities.Dish;
import com.e.fooddiet.utils.MyDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "CalorieCounter.db";
    public static int dishSortId = 0; // will allow to keep the sorting we need for dishes when updating the screen
    private static Context context ;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*db.execSQL("CREATE TABLE if not exists " + CalorieMaster.Calorie.DAYS_TABLE + " ( " + CalorieMaster.Calorie.COLUMN_ID +
                " INTEGER primary key , " + CalorieMaster.Calorie.DISH_COLUMN_NAME + " text UNIQUE," +
                CalorieMaster.Calorie.DISH_COLUMN_CALORIES_PER_100_GM + " integer not null" + ");"); // create table dishes
*/
        db.execSQL("create table if not exists " + CalorieMaster.Calorie.DAYS_DISHES_TABLE + " ( " + CalorieMaster.Calorie.DAYS_COLUMN_DATE +
                " integer, " + CalorieMaster.Calorie.DISH_COLUMN_NAME + " text, " + CalorieMaster.Calorie.DAYS_DISH_COLUMN_WEIGHT +
                " integer default 100," + "FOREIGN KEY(" + CalorieMaster.Calorie.DAYS_COLUMN_DATE + ") REFERENCES " +
                CalorieMaster.Calorie.DAYS_TABLE + "(" + CalorieMaster.Calorie.DAYS_COLUMN_DATE + "), FOREIGN KEY(" +
                CalorieMaster.Calorie.DISH_COLUMN_NAME + ") REFERENCES " + CalorieMaster.Calorie.DISH_TABLE + "(" +
                CalorieMaster.Calorie.DISH_COLUMN_NAME + ") " + ");");//create table days_dishes

        db.execSQL("create table if not exists " + CalorieMaster.Calorie.DAYS_TABLE + " ( " + CalorieMaster.Calorie.DAYS_COLUMN_DATE +
                " integer primary key, " + CalorieMaster.Calorie.DAYS_COLUMN_RECORD + " varchar(" +
                CalorieMaster.Calorie.MAX_RECORD_WIDTH + ") default ''" + ");");//create table days

        db.execSQL("CREATE TABLE if not exists " + CalorieMaster.Calorie.WEIGHT_TABLE + " ( " + CalorieMaster.Calorie._ID +
                " INTEGER primary key autoincrement, " + CalorieMaster.Calorie.WEIGHT_COLUMN_CURRENT_WEIGHT +
                "INTEGER not null," + CalorieMaster.Calorie.WEIGHT_COLUMN_GOAL_WEIGHT + "integer not null" + ");" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if( i1 > i){

            sqLiteDatabase.execSQL("CREATE TABLE if not exists " + CalorieMaster.Calorie.ACCOUNT_TABLE2 + " ( "  + CalorieMaster.Calorie._ID +
                    " INTEGER primary key autoincrement, " + CalorieMaster.Calorie.ACC_COLUMN_EMAIL_reg +
                    " INTEGER not null, " + CalorieMaster.Calorie.ACC_COLUMN_DOB_reg + " INTEGER, " + CalorieMaster.Calorie.ACC_COLUMN_GENDER_reg +
                    " TEXT , " + CalorieMaster.Calorie.ACC_COLUMN_ACTIVE_reg + " TEXT, " + CalorieMaster.Calorie.ACC_COLUMN_HEIGHT_reg +
                    " INTEGER not null , " + CalorieMaster.Calorie.ACC_COLUMN_GWEIGHT_reg + " INTEGER not null, " +
                    CalorieMaster.Calorie.ACC_COLUMN_WEIGHT_reg + " INTEGER not null );" );

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ CalorieMaster.Calorie.DISH_TABLE +" ( " + CalorieMaster.Calorie.DISH_COLUMN_ID +
                    " INTEGER primary key autoincrement, " + CalorieMaster.Calorie.DISH_COLUMN_DISHNAME +
                    " text UNIQUE not null, " + CalorieMaster.Calorie.DISH_COLUMN_CAL_PER_100 + " TEXT not null );");


        }

    }

    public boolean createAcc(Account acc){
        boolean result = true ;

            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues() ;

            cv.put(CalorieMaster.Calorie.ACC_COLUMN_EMAIL_reg , acc.getEmail());
            cv.put(CalorieMaster.Calorie.ACC_COLUMN_DOB_reg , acc.getDob());
            cv.put(CalorieMaster.Calorie.ACC_COLUMN_GENDER_reg , acc.getGender());
            cv.put(CalorieMaster.Calorie.ACC_COLUMN_ACTIVE_reg , acc.getStatus());
            cv.put(CalorieMaster.Calorie.ACC_COLUMN_HEIGHT_reg , acc.getHeight());
            cv.put(CalorieMaster.Calorie.ACC_COLUMN_WEIGHT_reg , acc.getWeight());
            cv.put(CalorieMaster.Calorie.ACC_COLUMN_GWEIGHT_reg , acc.getGoal_weight());

            return db.insert(CalorieMaster.Calorie.ACCOUNT_TABLE2,null,cv)>0 ;

    }

    public boolean createDish(Dish dish){
        boolean result = true ;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CalorieMaster.Calorie.DISH_COLUMN_DISHNAME , dish.getName());
        cv.put(CalorieMaster.Calorie.DISH_COLUMN_CAL_PER_100 , dish.getCaloriesPer100Gm());

        return db.insert(CalorieMaster.Calorie.DISH_TABLE,null,cv)> 0 ;
    }

    public List readDishDetails(){
         SQLiteDatabase db = getReadableDatabase();
          String[] projection ={
                  CalorieMaster.Calorie.DISH_COLUMN_ID,
                  CalorieMaster.Calorie.DISH_COLUMN_DISHNAME ,
                  CalorieMaster.Calorie.DISH_COLUMN_CAL_PER_100
          };

          Cursor cursor = db.query(CalorieMaster.Calorie.DISH_TABLE,projection,null,null,
                  null,null,null) ;

          List dishName = new ArrayList<>();
          List calorie = new ArrayList<>();

          while (cursor.moveToNext()){
              String dish_name = cursor.getString(cursor.getColumnIndexOrThrow(CalorieMaster.Calorie.DISH_COLUMN_DISHNAME));
              String cal = cursor.getString(cursor.getColumnIndexOrThrow(CalorieMaster.Calorie.DISH_COLUMN_CAL_PER_100));
              dishName.add(dish_name);
              calorie.add(cal);
          }

          cursor.close();
          return dishName;

    }



    public Cursor getDishes() {

        SQLiteDatabase mDB = getReadableDatabase();

        switch (dishSortId) {
            case 1:
                return mDB.query(CalorieMaster.Calorie.DISH_TABLE, null, null,
                        null, null, null, CalorieMaster.Calorie.DISH_COLUMN_DISHNAME);
            case 2:
                return mDB.query(CalorieMaster.Calorie.DISH_TABLE, null, null,
                        null, null, null, CalorieMaster.Calorie.DISH_COLUMN_CAL_PER_100);
        }
        return mDB.query(CalorieMaster.Calorie.DISH_TABLE, null, null,
                null, null, null, null);
    }

    public Dish getDish(String name) {

        SQLiteDatabase mDB = getReadableDatabase();

        Cursor cursor = mDB.query(CalorieMaster.Calorie.DISH_TABLE, null,
                CalorieMaster.Calorie.DISH_COLUMN_NAME + "='" + name + "'", null, null,
                null, null);

        if (!cursor.moveToFirst())
            return null;
        else
            return new Dish(cursor.getString(cursor.getColumnIndex(CalorieMaster.Calorie.DISH_COLUMN_NAME)),
                    cursor.getInt(cursor.getColumnIndex(CalorieMaster.Calorie.DISH_COLUMN_CALORIES_PER_100_GM)));
    }

    public long addDish(String name, int calories) {

        SQLiteDatabase mDB = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CalorieMaster.Calorie.DISH_COLUMN_NAME, name);
        cv.put(CalorieMaster.Calorie.DISH_COLUMN_CALORIES_PER_100_GM, calories);

        long newRowID = mDB.insert(CalorieMaster.Calorie.DISH_TABLE, null, cv);

        return newRowID ;
    }

    public long addWeight(int cweight, int gweight) {

        SQLiteDatabase mDB = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CalorieMaster.Calorie.WEIGHT_COLUMN_CURRENT_WEIGHT, cweight);
        cv.put(CalorieMaster.Calorie.WEIGHT_COLUMN_GOAL_WEIGHT , gweight);

        long newRowID = mDB.insert(CalorieMaster.Calorie.WEIGHT_TABLE, null, cv);

        return newRowID ;
    }

    public void updateWeight(int cweight, int gweight) {

        SQLiteDatabase mDB = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CalorieMaster.Calorie.WEIGHT_COLUMN_GOAL_WEIGHT , gweight);
        cv.put(CalorieMaster.Calorie.WEIGHT_COLUMN_CURRENT_WEIGHT , cweight);

        /*String selection = CalorieMaster.Calorie.WEIGHT_COLUMN_GOAL_WEIGHT + " LIKE ?" ;
        int[] selectionArgs = { cweight } ;*/


        mDB.update(CalorieMaster.Calorie.WEIGHT_TABLE, cv, CalorieMaster.Calorie.WEIGHT_COLUMN_GOAL_WEIGHT +
                "='" + gweight + "'", null);
    }


    public void updateDish(String name, int calories) {

        SQLiteDatabase mDB = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CalorieMaster.Calorie.DISH_COLUMN_NAME, name);
        cv.put(CalorieMaster.Calorie.DISH_COLUMN_CALORIES_PER_100_GM, calories);

        mDB.update(CalorieMaster.Calorie.DISH_TABLE, cv, CalorieMaster.Calorie.DISH_COLUMN_NAME +
                "='" + name + "'", null);
    }

    public void deleteDish(String name) {
        SQLiteDatabase mDB = getReadableDatabase();

        String selection = CalorieMaster.Calorie.DISH_COLUMN_NAME + " LIKE ? " ;

        String[] selectionArgs = { name } ;

        mDB.delete(CalorieMaster.Calorie.DAYS_DISHES_TABLE,selection,selectionArgs) ;

        mDB.delete(CalorieMaster.Calorie.DISH_TABLE, CalorieMaster.Calorie.DISH_COLUMN_NAME + "='" +
                name + "'", null);
        /*mDB.delete(CalorieMaster.Calorie.DAYS_DISHES_TABLE, CalorieMaster.Calorie.DISH_COLUMN_NAME + "='" +
                name + "'", null);*/
    }

    public Cursor getDaysDishesData() {

        SQLiteDatabase mDB = getReadableDatabase();

        return mDB.query(CalorieMaster.Calorie.DAYS_DISHES_TABLE, null,
                null, null, null, null, null);
    }

    /* check */
    public Cursor getAllDayDishes(MyDate date) {

        SQLiteDatabase mDB = getReadableDatabase();

        Cursor cursor = mDB.query(CalorieMaster.Calorie.DAYS_DISHES_TABLE, null,
                CalorieMaster.Calorie.DAYS_COLUMN_DATE +
                "=" + date.getTime(), null, null, null, null);

        if (cursor.moveToFirst())
            return cursor;
        else
            return null;
    }

    public Cursor getDayDish(MyDate date, String dishName) {

        SQLiteDatabase mDB = getReadableDatabase();

        Cursor cursor = mDB.query(CalorieMaster.Calorie.DAYS_DISHES_TABLE, null,
                CalorieMaster.Calorie.DAYS_COLUMN_DATE + " = " + date.getTime() + " AND " +
                        CalorieMaster.Calorie.DISH_COLUMN_NAME + " = '" + dishName + "'", null, null,
                null, null);

        if (cursor.moveToFirst())
            return cursor;
        else
            return null;
    }

    public void updateDayDish(MyDate date, String dishName, int weight) {

        SQLiteDatabase mDB = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put( CalorieMaster.Calorie.DAYS_COLUMN_DATE, date.getTime());
        cv.put( CalorieMaster.Calorie.DISH_COLUMN_NAME, dishName);
        cv.put( CalorieMaster.Calorie.DAYS_DISH_COLUMN_WEIGHT, weight);
        mDB.update( CalorieMaster.Calorie.DAYS_DISHES_TABLE, cv,  CalorieMaster.Calorie.DAYS_COLUMN_DATE + " = " +
                date.getTime() + " AND " +  CalorieMaster.Calorie.DISH_COLUMN_NAME + " = '" + dishName + "'", null);

    }

    public void addDayDish(MyDate date, String dishName, int weight) {

        SQLiteDatabase mDB = getWritableDatabase();

        if (weight == 0)
            return;
        Cursor cursor = getDayDish(date, dishName);
        if (cursor == null) {
            ContentValues cv = new ContentValues();
            cv.put( CalorieMaster.Calorie.DAYS_COLUMN_DATE, date.getTime());
            cv.put( CalorieMaster.Calorie.DISH_COLUMN_NAME, dishName);
            cv.put( CalorieMaster.Calorie.DAYS_DISH_COLUMN_WEIGHT, weight);
            mDB.insert( CalorieMaster.Calorie.DAYS_DISHES_TABLE, null, cv);
        } else {
            updateDayDish(date, dishName, weight);
        }
    }

    public void deleteDayDish(MyDate date, String dishName) {
        SQLiteDatabase mDB = getReadableDatabase();

        mDB.delete(CalorieMaster.Calorie.DAYS_DISHES_TABLE, CalorieMaster.Calorie.DAYS_COLUMN_DATE + " = " +
                date.getTime() + " AND " + CalorieMaster.Calorie.DISH_COLUMN_NAME + " = '" + dishName + "'", null);
    }

    public void deleteDayDishes(MyDate date) {

        SQLiteDatabase mDB = getReadableDatabase();

        mDB.delete(CalorieMaster.Calorie.DAYS_DISHES_TABLE, CalorieMaster.Calorie.DAYS_COLUMN_DATE + " = " +
                date.getTime(), null);
    }

    public Cursor getDaysRecords() {

        SQLiteDatabase mDB = getReadableDatabase();

        return mDB.query(CalorieMaster.Calorie.DAYS_TABLE, null, null, null, null,
                null, null);
    }

    public Cursor getDayRecord(MyDate date) {

        SQLiteDatabase mDB = getReadableDatabase();

        Cursor cursor = mDB.query(CalorieMaster.Calorie.DAYS_TABLE, null, CalorieMaster.Calorie.DAYS_COLUMN_DATE +
                "=" + date.getTime(), null, null, null, null);

        if (!cursor.moveToFirst())
            return null;
        else
            return cursor;
    }

    public void updateDayRecord(MyDate date, String record) {

        SQLiteDatabase mDB = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CalorieMaster.Calorie.DAYS_COLUMN_DATE, date.getTime());
        cv.put(CalorieMaster.Calorie.DAYS_COLUMN_RECORD, record);
        mDB.update(CalorieMaster.Calorie.DAYS_TABLE, cv, CalorieMaster.Calorie.DAYS_COLUMN_DATE + " = " +
                date.getTime(), null);
    }

    public void addDayRecord(MyDate date, String record) {

        SQLiteDatabase mDB = getWritableDatabase() ;

        ContentValues cv = new ContentValues();
        cv.put(CalorieMaster.Calorie.DAYS_COLUMN_DATE, date.getTime());
        cv.put(CalorieMaster.Calorie.DAYS_COLUMN_RECORD, record);
        mDB.insert(CalorieMaster.Calorie.DAYS_TABLE, null, cv);
    }

    public void deleteDayRecord(MyDate date) {

        SQLiteDatabase mDB = getReadableDatabase();

        mDB.delete(CalorieMaster.Calorie.DAYS_TABLE, CalorieMaster.Calorie.DAYS_COLUMN_DATE + " = " +
                date.getTime(), null);
    }


    public Day getDay(MyDate date) {

        Day day = new Day(date);

        if (getDayRecord(date) == null)
            return day;

        int cc = getDayRecord(date).getColumnIndex(CalorieMaster.Calorie.DAYS_COLUMN_RECORD);

        String rec = getDayRecord(date).getString(cc);
        day.setRecord(rec);
        Cursor cursor = getAllDayDishes(date);
        if (cursor != null) {
            do {
                day.addDish(getDish(cursor.getString(cursor.getColumnIndex(CalorieMaster.Calorie.DISH_COLUMN_NAME))),
                        cursor.getInt(cursor.getColumnIndex(CalorieMaster.Calorie.DAYS_DISH_COLUMN_WEIGHT)));
            } while (cursor.moveToNext());
        }

        return day;
    }

    public void saveDay(Day day) {
        MyDate date = day.getDate();
        Cursor cursor;
        cursor = getDayRecord(date);
        if (cursor == null)
            addDayRecord(date, day.getRecord());
        else
            updateDayRecord(date, day.getRecord());
        cursor = getAllDayDishes(date);
        if (cursor == null)
            for (Map.Entry<Dish, Integer> dish : day.getDishes().entrySet())
                addDayDish(date, dish.getKey().getName(), dish.getValue());
        else {
            deleteDayDishes(date);
            for (Map.Entry<Dish, Integer> dish : day.getDishes().entrySet())
                addDayDish(date, dish.getKey().getName(), dish.getValue());
        }

    }

    public static ArrayList<Map<String, Object>> cursorToArrayList(Cursor cursor) {
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Map<String, Object> map = new HashMap<>();
                for (String colName : cursor.getColumnNames()) {
                    map.put(colName, cursor.getString(cursor.getColumnIndex(colName)));
                }
                data.add(map);
            } while (cursor.moveToNext());
        }
        return data;
    }


}
