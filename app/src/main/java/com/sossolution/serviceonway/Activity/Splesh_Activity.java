package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.sossolution.serviceonway.R;
import com.sossolution.serviceonway.Class.User;

public class Splesh_Activity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIME_OUT=3000;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_);
        FirebaseApp.initializeApp(this);

      //  (Splesh_Activity.this).getSupportActionBar().hide();

          User user = new User(Splesh_Activity.this);
          new Handler().postDelayed(new Runnable()
          {
            @Override
            public void run()
            {

                if(user.getPhone()!="")
                {
                    Intent intent= new Intent(Splesh_Activity.this, MainActivity.class);
                    intent.putExtra("userdata",user.getPhone());
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Intent intent= new Intent(Splesh_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();

                }

            }
        }, SPLASH_SCREEN_TIME_OUT);


    }

  }
