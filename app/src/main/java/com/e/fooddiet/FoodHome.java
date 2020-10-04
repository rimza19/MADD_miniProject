package com.e.fooddiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.e.fooddiet.dataBase.CalorieMaster;
import com.e.fooddiet.dataBase.DBHelper;
import com.e.fooddiet.entities.Account;
import com.e.fooddiet.entities.Day;
import com.e.fooddiet.utils.MyDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class FoodHome extends AppCompatActivity {

    ImageButton btnNext, left, right;
    TextView setdate, setWeekday;
    TextView tvGoal, tvConsumed, tvNet;
    EditText cWeight , gWeight ;
    private static final String TAG = "FoodHome";

    Day viewDay; // viewed day
    DBHelper db;
    SimpleAdapter dishesAdapter;
    ArrayList<Map<String, Object>> dishesData;
    Account acc ;

    private static final int DATE_DIALOG = 1;
    private static final int VIEW_DISHES_DIALOG = 2;
    private TextView  mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_home);

        btnNext = findViewById(R.id.dish_add_button);
        setdate = findViewById(R.id.diary_set_date);
        setWeekday = findViewById(R.id.diary_weekday);
        left = findViewById(R.id.diary_left_arrow);
        right = findViewById(R.id.diary_right_arrow);
        tvGoal = findViewById(R.id.textViewGoalCal);
        tvConsumed = findViewById(R.id.textViewConsumed);
        tvNet = findViewById(R.id.textViewNet);
        cWeight = findViewById(R.id.textViewCurrentWeight);
        gWeight = findViewById(R.id.textViewGoalWeight);

        acc = new Account();

        cWeight.setText(acc.getWeight());
        gWeight.setText(acc.getGoal_weight());

        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FoodHome.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };



        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeViewDay(viewDay.getPreviousDay(db).getDate());
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewDay(viewDay.getNextDay(db).getDate());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //explicit intent
                Intent i = new Intent(com.e.fooddiet.FoodHome.this, Add_dish.class);
                startActivity(i);

               /* Pick_dish.date = viewDay.getDate();*/
            }
        });
    }

    private void changeViewDay(MyDate date) {
        saveViewDay();
        setViewDay(date);
    }

    private void saveViewDay()//закончить
    {
        /* viewDay.setRecord(((EditText) findViewById(R.id.diary_record)).getText().toString());*/
        for (Map<String, Object> dish : dishesData)
            viewDay.addDish(db.getDish((String) dish.get(CalorieMaster.Calorie.DISH_COLUMN_NAME)),
                    Integer.parseInt((String) dish.get(CalorieMaster.Calorie.DAYS_DISH_COLUMN_WEIGHT)));
        db.saveDay(viewDay);
    }

    private void setViewDay(MyDate date) {
        setViewDay(Day.getDayByDate(db, date));
    }

    private void setViewDay(Day day) {
        viewDay = day;
        if (dishesData != null) {
            dishesData.clear();
            dishesData.addAll(DBHelper.cursorToArrayList(db.getAllDayDishes(viewDay.getDate())));
            dishesAdapter.notifyDataSetChanged();
        }

        setWeekday.setText(Day.getDayOfWeekByDate(day.getDate()));
        setdate.setText(Day.format.format(day.getDate()));
        /*((EditText) findViewById(R.id.diary_record)).setText(day.getRecord());*/
        updateCaloriesRow();
    }

    /*protected void onRestart() {
        dishesData.clear();
        dishesData.addAll(db.cursorToArrayList(db.getAllDayDishes(viewDay.getDate())));
        dishesAdapter.notifyDataSetChanged();
        updateCaloriesRow();
        super.onRestart();
    }*/

    private void updateCaloriesRow() {
        Integer receivedCalories = viewDay.getReceivedCalories();
        /* Integer spentCalories = viewDay.getSpentCalories(profile.getWeight());*/
        tvGoal.setText(receivedCalories.toString());
        tvConsumed.setText(receivedCalories.toString());
        tvNet.setText(((Integer) (1200 - receivedCalories)).toString());

        tvGoal.requestLayout();
        tvConsumed.requestLayout();
        tvNet.requestLayout();
    }

   /* @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb;
        switch (id) {
            case DATE_DIALOG:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(viewDay.getDate());
                return new DatePickerDialog(this, myCallBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            case VIEW_DISHES_DIALOG:
                adb = new AlertDialog.Builder(this);
                adb.setTitle(getString(R.string.eaten_dishes));
                adb.setAdapter(dishesAdapter, null);
                return adb.create();

        }
        return super.onCreateDialog(id);
    }*/
}
