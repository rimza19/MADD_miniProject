package com.e.fooddiet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.fooddiet.dataBase.DBHelper;
import com.e.fooddiet.entities.Dish;

public class New_dish extends AppCompatActivity {

    EditText dish_name , calorie ;
    Button add ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);

        dish_name = findViewById(R.id.et_dish_name);
        calorie = findViewById(R.id.et_calorie_per_100);
        add = findViewById(R.id.btn_add_food_new) ;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd_onclick(view);
            }
        });

    }

    public void btnAdd_onclick(View view){
        try{
            DBHelper db = new DBHelper(getApplicationContext());
            Dish dish = new Dish();

            dish.setName(dish_name.getText().toString());
            String cal = calorie.getText().toString();
            dish.setCalories(Integer.parseInt(cal));

            if(db.createDish(dish)){
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Inserted successfully",Toast.LENGTH_SHORT);
                toast.show();
            }

            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()) ;
                builder.setTitle(R.string.error);
                builder.setMessage("Can not insert food data");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }) ;
                builder.show();
            }

        }catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()) ;
            builder.setTitle(R.string.error);
            builder.setMessage(e.getMessage());
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }) ;
            builder.show();
        }
    }
}