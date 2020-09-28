package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.sossolution.serviceonway.R;

public class Vehicle_Activity extends AppCompatActivity {

    Button submit;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    ImageButton imageButton1,imageButton2;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    Switch aSwitch;
    ImageView back;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vech_);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Select vehicle Type");
       // getSupportActionBar().setTitle("Select vehicle Type");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aSwitch=findViewById(R.id.simpleSwitch);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    aSwitch.setText("Emergency");
                    String s1="1";
                    SharedPreferences mPrefs = getSharedPreferences("Em", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("switch",s1);
                    editor.apply();
                    //To change the text near to switch
                    Log.d("You are :", "Checked");
                   }
                else {
                    aSwitch.setText("Emergency");  //To change the text near to switch
                    Log.d("You are :", " Not Checked");
                    String s2="0";
                    SharedPreferences mPrefs = getSharedPreferences("Em", MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("switch",s2);
                    editor.apply();
                    }


            }
        });
        radioGroup = findViewById(R.id.radioGroup1111);
        radioGroup.clearCheck();
        imageButton1=findViewById(R.id.checkBox_car);
        imageButton2=findViewById(R.id.checkBox_bike);

            clrearmethod();


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1="bike";
                Intent intent = new Intent(Vehicle_Activity.this, Maker_Activity.class);
               /* intent.putExtra("EXTRA_SESSION_ID",s1);*/
                startActivity(intent);

                clrearmethod();
                //shareprefrance
                mPrefs = getSharedPreferences("vec", MODE_PRIVATE);
                editor = mPrefs.edit();
                editor.putString("car",s1);
                editor.apply();



            }

        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s2="car";
                clrearmethod();
                Intent intent1 = new Intent(Vehicle_Activity.this, Maker_Activity.class);
              //  intent1.putExtra("EXTRA_SESSION_ID",s2);
                startActivity(intent1);
                //sharedprefrance
                mPrefs= getSharedPreferences("vec", MODE_PRIVATE);
                editor = mPrefs.edit();
                editor.putString("car",s2);
                editor.apply();

            }
        });



    }


    private void clrearmethod()
    {
       // editor.remove("car");
        // will delete key key_name3
        SharedPreferences mPrefs = getSharedPreferences("Oilitem", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("Oil","");
        editor.apply();


    }

}
