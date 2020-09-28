package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sossolution.serviceonway.R;

public class Refer_earn_Activity extends AppCompatActivity {

    TextView text_firsttext;
    TextView text_secontext;
    Button button_send;
    ImageView back;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn_);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Refer and Earn");
      //  getSupportActionBar().setTitle("Refer and Earn");

        text_firsttext=findViewById(R.id.text_firsttext);
       // text_secontext=findViewById(R.id.text_secondtext);
        button_send=findViewById(R.id.send_button);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"ServiceOnWay");
                String message="Download Serviceonway - India's No 1 Car & Bike Service";
                String shareBodyText = "https://play.google.com/store/apps/details?id=com.sossolution.serviceapp";

              //  https://play.google.com/store/apps/details?id=com.sossolution.serviceonway
                intent.putExtra(Intent.EXTRA_TEXT,message+","+shareBodyText);
                startActivity(Intent.createChooser(intent,"choose one"));

            }
        });




    }
}
