package com.e.fooddiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FoodHome extends AppCompatActivity {

    ImageButton btnNext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_home);

        btnNext = findViewById(R.id.dish_add_button);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //explicit intent
                Intent i = new Intent(com.e.fooddiet.FoodHome.this,Add_dish.class);
                startActivity(i);
            }
        });
    }
}