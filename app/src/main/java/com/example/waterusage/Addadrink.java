package com.example.waterusage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Addadrink extends AppCompatActivity {

    Button otherDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadrink);

        otherDrink = findViewById(R.id.other_size_button);
        otherDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
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
}