package com.e.fooddiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.e.fooddiet.dataBase.CalorieMaster;
import com.e.fooddiet.dataBase.DBHelper;
import com.e.fooddiet.entities.Dish;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Add_dish extends AppCompatActivity {

    ImageButton okBtn , clBtn ;
    ImageButton newDish ;
    ListView list ;
    TextView picked_dish ;
    DBHelper db ;
    ArrayList<Map<String, Object>> data;
    SimpleAdapter sAdapter;
    File fileList ;
    long selectedElementId=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        okBtn = findViewById(R.id.pick_dish_OK_button);
        clBtn = findViewById(R.id.pick_dish_cancel_button);
        newDish = findViewById(R.id.newDish_add_button) ;
        list = (ListView)findViewById(R.id.pick_dish_list_ret);
        picked_dish = (TextView)findViewById(R.id.pick_dish_picked_dish_name);

        db = new DBHelper(getApplicationContext());
        data =DBHelper.cursorToArrayList(db.getDishes());

        String[] from = new String[] {CalorieMaster.Calorie.DISH_COLUMN_DISHNAME, CalorieMaster.Calorie.DISH_COLUMN_CAL_PER_100 }; // columns names
        int[] to = new int[] { R.id.db_item_name, R.id.db_item_right_text}; // places to write (View id)

        sAdapter = new SimpleAdapter(this, data ,R.layout.database_item, from, to);
        list.setAdapter(sAdapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*selectedElementId = l;*/
                String selected = (String)list.getItemAtPosition(i);
                picked_dish.setText(selected);

            }
        });

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

        newDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add_dish.this,New_dish.class);
                startActivity(i);
            }
        });
    }
}