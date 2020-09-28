package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.sossolution.serviceonway.R;

public class Fuel_Activity extends AppCompatActivity {

    TextView textView1,textView2;
    TabLayout tabLayout1;
    Button petrolbutton,dieselbutton;
    ImageView back;
    TextView header;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Select Fuel");
        //getSupportActionBar().setTitle("Select Fuel");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //textView1=findViewById(R.id.first_desial1);
        petrolbutton=findViewById(R.id.button7);

        dieselbutton=findViewById(R.id.button8);
        tabLayout1=findViewById(R.id.tabthree);

        //first activity fatch data



        SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
        String s1=mPrefs.getString("bike","");
        tabLayout1.getTabAt(0).setText(s1);
       // tabLayout1.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));



        //two activity data fetch

        SharedPreferences mPrefs1 = getSharedPreferences("model item", MODE_PRIVATE);
        String s2=mPrefs1.getString("model","");
        tabLayout1.getTabAt(1).setText(s2);




       // textView2=findViewById(R.id.second_petrol1);
        dieselbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //tabLayout1.getTabAt(2).setText("diesel");
                String s1="Diesel";
                SharedPreferences mPrefs = getSharedPreferences("Oilitem", MODE_PRIVATE);
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("Oil",s1);
                editor.apply();
                Intent intent= new Intent(Fuel_Activity.this,Service_Activity.class);
                startActivity(intent);


            }
        });
        petrolbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // tabLayout1.getTabAt(2).setText("Petrol");
                String s2="Petrol";
                SharedPreferences mPrefs = getSharedPreferences("Oilitem", MODE_PRIVATE);
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("Oil",s2);
                editor.apply();

                Intent intent= new Intent(Fuel_Activity.this,Service_Activity.class);
                startActivity(intent);
            }
        });
    }
}
