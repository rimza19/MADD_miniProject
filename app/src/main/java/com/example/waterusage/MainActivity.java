package com.example.waterusage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText enWeight;
    Button btnDefault;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView result;
    public static float res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enWeight = (EditText) findViewById(R.id.enWeight);
        radioGroup = (RadioGroup) findViewById(R.id.activity_level);
        btnDefault = (Button) findViewById(R.id.btnCalculate);
        result = (TextView) findViewById(R.id.result);

        addListenerOnButton();
        /*btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });*/

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){

                    case R.id.radio_activity_1:
                        // do operations specific to this selection
                        res = 12;
                        break;
                    case R.id.radio_activity_2:
                        // do operations specific to this selection
                        res = 24;
                        break;
                    case R.id.radio_activity_3:
                        // do operations specific to this selection
                        res = 36;
                        break;
                }
            }
        });

    }

   /* public void openNewActivity(){
        Intent intent = new Intent(this,DailyGoal.class);
        startActivity(intent);
    }*/

    public void addListenerOnButton() {

        btnDefault.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

             /*   // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();
            */
                String s1=enWeight.getText().toString();
                //result.setText(s1);

                int weight=Integer.parseInt(enWeight.getText().toString());
                float water=Float.parseFloat(enWeight.getText().toString());

                int s = weight;

                // Daily water requirement = body weight * 2/3 + water according to the activity level  (lb)
                float goalPounds = water * (float)1.47 + res;
                float goalLtrs = goalPounds * (float)0.02835;


                result.setText(String.valueOf(s));

                Intent intent = new Intent(getApplicationContext(),DailyGoal.class);
                //intent.putExtra("weight",s1);
                intent.putExtra("water",(int)s);
                intent.putExtra("goal",(float)goalLtrs);
                startActivity(intent);
            }
        });

    }




}