package com.example.waterusage.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DrinkWaterDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "drinkHistory.db";
    public static final int DATABASE_VERSION = 1;

    public static final String Date_TABLE_NAME = "dateTable";
    public static final String TIME_TABLE_NAME = "TimeTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME_ID = "_id";
    public static final String COLUMN_WATER_NEED = "WaterNeed";
    public static final String COLUMN_WATER_DRUNK="drunkWater";
    public static final String COLUMN_WATER_DRUNK_ONCE="drunkWaterOnce";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_TIME_DATE="date";
    public static final String COLUMN_TIME="time";
    public static final String COLUMN_TYP="containerTyp";

    private static final String CREATE_DATE_TABLE = "create table " +Date_TABLE_NAME
            + " (" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WATER_NEED + " integer, "
            + COLUMN_WATER_DRUNK + " integer, "
            + COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP " +
            ");";

    private static final String CREATE_TIME_TABLE = "create table " +TIME_TABLE_NAME
            + " (" + COLUMN_TIME_ID + " integer primary key autoincrement, "
            + COLUMN_WATER_DRUNK_ONCE + " integer, "
            + COLUMN_TYP + " TEXT, "
            + COLUMN_TIME_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP , "
            + COLUMN_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public  DrinkWaterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATE_TABLE);
        db.execSQL(CREATE_TIME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
