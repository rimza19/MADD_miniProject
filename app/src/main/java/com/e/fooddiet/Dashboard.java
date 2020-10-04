package com.e.fooddiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button register ;
    Button dishes , exercise , water , profile , statistic , exit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        register = findViewById(R.id.from_menu_to_register);
        dishes = findViewById(R.id.from_menu_to_dishes);
        exercise = findViewById(R.id.from_menu_to_exercises) ;
        water = findViewById(R.id.from_menu_to_water) ;
        statistic = findViewById(R.id.from_menu_to_statistic);
        exit = findViewById(R.id.exit);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,MainActivity.class);
                startActivity(i);
            }
        });

        dishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,FoodHome.class);
                startActivity(i);
            }
        });

    }

    public void onExitButton(final View view) {
        finish();
    }
}