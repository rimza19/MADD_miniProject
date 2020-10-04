package com.e.fooddiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.e.fooddiet.dataBase.CalorieMaster;
import com.e.fooddiet.dataBase.DBHelper;
import com.e.fooddiet.utils.MyDate;

import java.util.ArrayList;
import java.util.Map;

public class Pick_dish extends AppCompatActivity {


    private DBHelper db;
    private Map<String, Object> map;
    private ArrayList<Map<String, Object>> data;
    private SimpleAdapter sAdapter;
    private long selectedElementId = -1;
    ListView listView;
    public static MyDate date;
    private static final int SET_WEIGHT = 1;
    private static Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_dish);

        db = new DBHelper(context);
        data = DBHelper.cursorToArrayList(db.getDishes()) ;

        /*data = DBHelper.cursorToArrayList(DBHelper.getDishes()) ;*/

        String[] from = new String[]{CalorieMaster.Calorie.DISH_COLUMN_NAME, CalorieMaster.Calorie.DISH_COLUMN_CALORIES_PER_100_GM};
        int[] to = new int[]{R.id.db_item_name, R.id.db_item_right_text};

        sAdapter = new SimpleAdapter(this, data, R.layout.database_item, from, to);
        listView = findViewById(R.id.pick_dish_list);
       /* listView.setAdapter(sAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) findViewById(R.id.pick_dish_picked_dish_name)).setText(((TextView) view.findViewById(R.id.db_item_name)).getText().toString());
                selectedElementId = id;
               *//* onSetPickedDishWeight(view);*//*
            }
        });*/

        findViewById(R.id.pick_dish_OK_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedElementId < 0) {
                    finish();
                    return;
                }
                String pickedDishName = (String) data.get((int) selectedElementId).get(CalorieMaster.Calorie.DISH_COLUMN_NAME);
                int pickedWeight = Integer.parseInt(((EditText) findViewById(R.id.pick_dish_picked_dish_weight)).getText().toString());
                Cursor cursor = db.getDayDish(date, pickedDishName);
                if (cursor == null)
                    db.addDayDish(date, pickedDishName, pickedWeight);
                else
                    db.updateDayDish(date, pickedDishName, pickedWeight +
                            cursor.getInt(cursor.getColumnIndex(CalorieMaster.Calorie.DAYS_DISH_COLUMN_WEIGHT)));
                finish();
            }
        });
        findViewById(R.id.pick_dish_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

  /*  public void onSetPickedDishWeight(View view) {
        showDialog(SET_WEIGHT); }*/


    /*protected Dialog onCreateDialog(int id){

        if (id == SET_WEIGHT) {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle(getString(R.string.enter_dish_weight));
            dialog.setContentView(R.layout.set_weight_dialog);
            Button okButton = dialog.findViewById(R.id.set_weight_dialog_OK_button);
            Button cancelButton = dialog.findViewById(R.id.set_weight_dialog_cancel_button);
            final NumberPicker np1 = dialog.findViewById(R.id.set_weight_dialog_number_1);
            final NumberPicker np2 = dialog.findViewById(R.id.set_weight_dialog_number_2);
            final NumberPicker np3 = dialog.findViewById(R.id.set_weight_dialog_number_3);
            final NumberPicker np4 = dialog.findViewById(R.id.set_weight_dialog_number_4);
            np1.setMaxValue(9);
            np1.setMinValue(0);
            np2.setMaxValue(9);
            np2.setMinValue(0);
            np3.setMaxValue(9);
            np3.setMinValue(0);
            np4.setMaxValue(9);
            np4.setMinValue(0);
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer weight = np1.getValue() * 1000 + np2.getValue() * 100 + np3.getValue() * 10 + np4.getValue();
                    ((EditText) findViewById(R.id.pick_dish_picked_dish_weight)).setText(weight.toString());
                    dialog.dismiss();
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            return dialog;
        }
        return super.CreateDialog(id);
    }*/


}