package com.example.waterusage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DefaultMain extends AppCompatActivity {
    Button history;
    Button addWater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_main);
        history = findViewById(R.id.btnHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });

        addWater = findViewById(R.id.btnAddVolume);
        addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddWaterActivity();
            }
        });
    }
    public void openHistoryActivity(){
        Intent intent = new Intent(this,usageHistory.class);
        startActivity(intent);
    }

    public void openAddWaterActivity(){
        Intent intent = new Intent(this,Addadrink.class);
        startActivity(intent);
    }
}