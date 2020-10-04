package com.e.fooddiet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.e.fooddiet.dataBase.DBHelper;
import com.e.fooddiet.entities.Account;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView  mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button btnNext ;

    RadioGroup radioGroup ;
    RadioButton radioButton ;

    private EditText email , height , weight , gweight ;
    private TextView date ;
    private Spinner status ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = findViewById(R.id.buttonNext);

        mDisplayDate = (TextView) findViewById(R.id.datePicker);
        email = findViewById(R.id.editTextEmailAddress) ;
        status = findViewById(R.id.spinnerActive);
        height = findViewById(R.id.editTextHeight);
        weight = findViewById(R.id.editTextWeight);
        gweight = findViewById(R.id.editTextGoal) ;
        radioGroup = findViewById(R.id.radioGroupGender);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonNext_onClick(view) ;
               /* //explicit intent
                Intent i = new Intent(com.e.fooddiet.MainActivity.this,FoodHome.class);
                startActivity(i);*/
            }
        });
    }

    public void buttonNext_onClick(View view){
        try {

            DBHelper db = new DBHelper(getApplicationContext());
            Account acc = new Account();

            acc.setEmail(email.getText().toString());
            acc.setDob(mDisplayDate.getText().toString());

            RadioButton checkedBtn = findViewById(radioGroup.getCheckedRadioButtonId() );
            acc.setGender(checkedBtn.getText().toString());

            acc.setStatus(status.getSelectedItem().toString());
            /*acc.setStatus(checkedStts.getTe);*/
            acc.setHeight(height.getText().toString());
            acc.setWeight(weight.getText().toString());
            acc.setGoal_weight(gweight.getText().toString());
            acc.setWelcome("Inserted Successfully " + email.getText().toString());

            if(db.createAcc(acc)){

                Intent i = new Intent(com.e.fooddiet.MainActivity.this,Dashboard.class);
                startActivity(i);
                Toast toast = Toast.makeText(getApplicationContext(),"!! " +acc.getWelcome()
                        ,Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()) ;
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.cant);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }) ;
                builder.show();
            }

        } catch (Exception e) {
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