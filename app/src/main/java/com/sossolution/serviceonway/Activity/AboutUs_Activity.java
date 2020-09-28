package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sossolution.serviceonway.Adapter.TextAdapter;
import com.sossolution.serviceonway.R;

public class AboutUs_Activity extends AppCompatActivity
{

    public static String FACEBOOK_URL = "https://www.facebook.com/Serviceonway1";
    public static String FACEBOOK_PAGE_ID = "Serviceonway1";

    ImageView image_fb,image_instagram,image_termcondition,image_lock;
    TextView text_fb,text_instagram,text_youtube,text_lock;
    Context context;
    PackageManager packageManager;
    ImageView back;
    TextView header;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_);
     //   getSupportActionBar().setTitle("About Us");

        //cast image
        image_fb=findViewById(R.id.imageView_fb);
        image_instagram=findViewById(R.id.imageView_instagram);
        image_termcondition=findViewById(R.id.imageView_termcondition);
        image_lock=findViewById(R.id.imageView_lock);

        //new
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        header=findViewById(R.id.header);
        header.setText("About Us");

        //cast text
        text_fb=findViewById(R.id.textView_fb);
        text_fb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String YourPageURL = "https://www.facebook.com/Serviceonway1";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(YourPageURL));
                startActivity(intent);

            }
        });
        text_instagram=findViewById(R.id.textView_instagram);
        text_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String YourPageURL = "https://www.instagram.com/serviceonway/";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(YourPageURL));
                startActivity(intent);

            }
        });
        text_youtube=findViewById(R.id.textView_youtube);
        text_youtube.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String url="https://twitter.com/serviceonway";
                Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);

            }
        });
        text_lock=findViewById(R.id.textView_lock);
        text_lock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent  intent= new Intent(AboutUs_Activity.this,Terms_Condition_Activity.class);
                startActivity(intent);
            }
        });
    }
}
