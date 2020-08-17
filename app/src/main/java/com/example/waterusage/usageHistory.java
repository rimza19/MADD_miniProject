package com.example.waterusage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class usageHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_history);
        final ListView list = findViewById(R.id.History);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("250 ml\n8:16 A.M");
        arrayList.add("250 ml\n9:35 A.M");
        arrayList.add("300 ml\n10:20 A.M");
        arrayList.add("1000 ml\n13:40 P.M");
        arrayList.add("100 ml\n15.34 P.M");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
    }
}