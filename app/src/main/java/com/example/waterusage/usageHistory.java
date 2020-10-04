package com.example.waterusage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waterusage.DB.DBHelper;
import com.example.waterusage.Database.DrinkWaterDB;
import com.example.waterusage.model.DateLog;

import java.util.ArrayList;
import java.util.List;

public class usageHistory extends AppCompatActivity {

    /*private DateLog dateLog;
    private TextView waterLog,dateTv;
    static ListView listView;
    static List<DateLog> values ;
    static ArrayAdapter<DateLog> adapter;*/

    ListView listView;

    private DBHelper db;
    private ArrayList arraylist;
    private ArrayAdapter arrayAdapter;
   // private static DrinkWaterDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_history);

        listView = findViewById(R.id.log_list);

        db = new DBHelper(usageHistory.this);


    }


}