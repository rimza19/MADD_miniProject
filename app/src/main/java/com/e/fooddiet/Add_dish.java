package com.e.fooddiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Add_dish extends AppCompatActivity {

    ImageButton okBtn , clBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        okBtn = findViewById(R.id.pick_dish_OK_button);
        clBtn = findViewById(R.id.pick_dish_cancel_button);

        //creating intent
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.e.fooddiet.Add_dish.this,FoodHome.class);
                startActivity(i);
            }
        });

        //creating intent for cancel button
        clBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(com.e.fooddiet.Add_dish.this,FoodHome.class);
                startActivity(i1);
            }
        });
    }
}