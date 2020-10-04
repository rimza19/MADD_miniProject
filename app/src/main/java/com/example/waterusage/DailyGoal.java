package com.example.waterusage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.waterusage.DB.DBHelper;
//import com.example.waterusage.Database.DrinkWaterDB;


public class DailyGoal extends AppCompatActivity {
    public static  TextView choosenAmountTv;
    public static TextView dailyGoal;
    public static TextView text1;
    public static TextView waterIntake;
    Button history;
    Button addWater;
    private DBHelper db;
    private Context context ;
    //TextView dailyGoal,waterIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_goal);
        db= new DBHelper(DailyGoal.this);

        dailyGoal = findViewById(R.id.EnterWeight);
        waterIntake = findViewById(R.id.waterIntake);
        text1 = findViewById(R.id.text1);

        Intent intent = getIntent();
        //String s1 = intent.getStringExtra("weight");
        int w = intent.getIntExtra("water",0);
        float goal = intent.getFloatExtra("goal",0);

        String s = String.format("%.2f", goal);

       // waterIntake.setText("Weight "+String.valueOf(w)+ " Kg");
        text1.setText(String.valueOf(s)+" Ltr.");

        //updateView();

        history = findViewById(R.id.todaylog);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });

        addWater = findViewById(R.id.add_drink_button);
        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddWaterActivity();
            }
        });

        updateView();
    }
    public void openHistoryActivity(){
        Intent intent = new Intent(this,usageHistory.class);
        startActivity(intent);
    }

    public void openAddWaterActivity(){
        Intent intent = new Intent(this,Addadrink.class);
        startActivity(intent);
    }

    private void  updateView(){
       int perValue= db.getTotalConsumedWater();
        waterIntake.setText(String.valueOf(perValue+" out of "+
               " 2000 ml"));
    }

}