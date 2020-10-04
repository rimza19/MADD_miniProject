package com.example.waterusage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database_name";
    public static final String TABLE_NAME = "table_name";
    private static final String TABLE_WATER_LOG = "water_log";
    private static final String KEY_ID = "id";
    private static final String VOLUME = "volume";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable ="CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY, volume INTEGER)";

        String CREATE_TABLE_LOG = "CREATE TABLE " + TABLE_WATER_LOG + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VOLUME + " INTEGER"+ ")";

        db.execSQL(createTable);
        db.execSQL(CREATE_TABLE_LOG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_LOG);
        onCreate(db);
    }

    public boolean addWaterVolume(int volume){
        //get writable database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("waterVolume",volume);

        sqLiteDatabase.insert(TABLE_NAME,null,values);
        return true;
    }

    public ArrayList getConsumedWaterToday(){
        //get readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();

        //create cursor to select all
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("volume")));
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }

    public int getTotalConsumedWater(){
        int totalConsumed=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select volume from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
                int w= cursor.getInt(cursor.getColumnIndex("volume"));
                totalConsumed = totalConsumed+ w;
            cursor.moveToNext();
        }
        return totalConsumed;
    }

    public ArrayList<HashMap<Integer,Integer>> GetTodayConsumption(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<Integer,Integer>> waterLog = new ArrayList<>();
        String query = "SELECT id, volume FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<Integer,Integer> w = new HashMap<>();
            w.put(001,cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            w.put(002,cursor.getInt(cursor.getColumnIndex(VOLUME)));

            waterLog.add(w);
        }
        return  waterLog;
    }

    public void insertToWaterLog(int volume){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(VOLUME, volume);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_WATER_LOG,null, cValues);
        db.close();
    }
}
