package com.example.waterusage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.waterusage.DB.DBHelper;
import com.example.waterusage.Database.DrinkWaterDB;
import com.example.waterusage.model.DateHandler;

public class Addadrink extends AppCompatActivity {
    private Context context;
    private final String GLASS="glass";
    private final String BOTTLE="bottle";
    private int glassSize=250;
    private int bottleSize=1000;

    //private DrinkWaterDB db;
    DBHelper db;

    Button otherDrink,cancel, glassButton,bottleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadrink);

        otherDrink = findViewById(R.id.other_size_button);
        cancel = findViewById(R.id.cancel_button);
        bottleButton= findViewById(R.id.water_bottle_button);
        glassButton= findViewById(R.id.water_glass_button);

        db = new DBHelper(Addadrink.this);

        otherDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        glassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGlass();
            }
        });

        bottleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBottle();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //;dismiss()
            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Addadrink.this);
        alertDialog.setTitle("Select the volume");

        String[] volumes = {"100 ml", "300 ml", "400 ml", "1.5 l"};
        int checkedItem = 1; // cow
        alertDialog.setSingleChoiceItems(volumes, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user checked an item
            }
        });

        // add OK and Cancel buttons
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
            }
        });
        alertDialog.setNegativeButton("Cancel", null);

    // create and show the alert dialog
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    private void addGlass(){
        db.addWaterVolume(glassSize);

        Toast.makeText(getApplicationContext(),glassSize+" ml Added",Toast.LENGTH_LONG).show();
        /*int perBefore= db.getConsumedPercentage();
        db.createTimeLog(glassSize,GLASS, DateHandler.getCurrentDate(),DateHandler.getCurrentTime());
        db.updateConsumedWaterForTodayDateLog(glassSize);
        updateView(perBefore);*/


    }

    private void addBottle(){
        db.addWaterVolume(bottleSize);
        Toast.makeText(getApplicationContext(),bottleSize+" ml Added",Toast.LENGTH_LONG).show();
        /*int perBefore= db.getConsumedPercentage();
        db.createTimeLog(bottleSize,BOTTLE,DateHandler.getCurrentDate(),DateHandler.getCurrentTime());
        db.updateConsumedWaterForTodayDateLog(bottleSize);
        updateView(perBefore);*/

    }

    private void updateView(int perBefore) {
        /*int perValue= db.getConsumedPercentage();
        DailyGoal.waterIntake.setText(perValue);
       MainActivity.circleProgress.setProgress(perValue);
        MainActivity.choosenAmountTv.setText(String.valueOf(db.geConsumedWaterForToadyDateLog()+" out of "+
                PrefsHelper.getWaterNeedPrefs(context)+" ml"));*/
    }


}