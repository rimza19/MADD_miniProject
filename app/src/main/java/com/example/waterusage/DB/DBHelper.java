package com.example.waterusage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database_name";
    public static final String TABLE_NAME = "table_name";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable ="CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, volume INTEGER)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
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

    public ArrayList getConsumedWater(){
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
}
