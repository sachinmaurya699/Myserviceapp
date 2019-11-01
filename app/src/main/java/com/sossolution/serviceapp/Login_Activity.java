package com.sossolution.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
       textView=findViewById(R.id.text_Bick);
       // editText=findViewById(R.id.editText_Mobilenumber);
        button=findViewById(R.id.button_login);
        imageView=findViewById(R.id.image_Login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(Login_Activity.this,Otp_Activity.class);
                startActivity(intent);
            }
        });



    }
}
