package com.example.waterusage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.waterusage.DB.DBHelper;
import com.example.waterusage.Database.DrinkWaterDB;
import com.example.waterusage.model.DateLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class usageHistory extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waterdetails);
        DBHelper db = new DBHelper(this);
        ArrayList<HashMap<Integer, Integer>> userList = db.GetTodayConsumption();
        ListView lv = (ListView) findViewById(R.id.log_list);
        /*ListAdapter adapter = new SimpleAdapter(usageHistory.this, userList, R.layout.row_item, new int[]{"id","volume"}, new int[]{R.id.textViewId, R.id.textViewVolume});
        lv.setAdapter(adapter);*/

        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(usageHistory.this,DailyGoal.class);
                startActivity(intent);
            }
        });
    }


}