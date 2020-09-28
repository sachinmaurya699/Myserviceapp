package com.sossolution.serviceonway.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.Class.User;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Build.ID;

public class Otp_Activity extends AppCompatActivity {

    Button button1,button2;
    TextView text1;
    EditText editText2;
    TextView textView1,textView2;
    String first,second,third,fourth,five,six;
    String a,Otp;
    String nam,em,add,pho;
    String  otp1;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    ProgressBar progressBar;
    String otp;
    String ID;
    TextView textviews;
    CheckBox checkBox;
    String message;
    TextView textotp;

    //first api create ................................
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_);
        textotp=findViewById(R.id.incorrect_otp);
        textotp.setVisibility(View.GONE);
        textviews=findViewById(R.id.text_igree);
        checkBox=findViewById(R.id.checkBox1);


        //cretae spanable string..........
        String s1="I agree to terms and conditions";

        SpannableString spanable= new SpannableString(s1);
        //ForegroundColorSpan colorSpan= new ForegroundColorSpan(Color.BLUE);
        ClickableSpan clickableSpan= new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View view) {

                Intent intent= new Intent(Otp_Activity.this,Terms_Condition_Activity.class);
                startActivity(intent);

            }
        };
      // spanable.setSpan(clickableSpan,11,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanable.setSpan(clickableSpan, 11, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textviews.setText(spanable);
        textviews.setMovementMethod(LinkMovementMethod.getInstance());
        text1 = findViewById(R.id.editText_showNumber);
        editText2 = findViewById(R.id.editText_otp);
        progressBar=findViewById(R.id.progressBar_Otp);
        progressBar.setVisibility(View.GONE);


        //get mobile number (Login activity).......
         a=getIntent().getStringExtra("number");
         Log.d("number",a);
         text1.setText(a);

         //api hit resend otp generate..................
         button2=findViewById(R.id.rsend);
         button2.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View view)
            {
                progressBar.setVisibility(View.VISIBLE);
                //otp resend...
                GenerateOtp(a);
            }
        });

        button1=findViewById(R.id.button_otpsubmit);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Otp=editText2.getText().toString();

                if(Otp.isEmpty())
                {
                    editText2.setError("Enter the otp");
                    editText2.setFocusable(true);
                }
                else if(Otp.length()!=6)
                {
                    editText2.setError("Enter the six digit otp");
                    editText2.setFocusable(true);
                }
                else if(!checkBox.isChecked())
                {
                    Toast.makeText(Otp_Activity.this, "please check term  and condition", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    hitapiotpverfy(Otp,a);
                    progressBar.setVisibility(View.VISIBLE);
                }


            }
        });
    }

    private void hitapigetname(String ID)
    {

        String url="https://serviceonway.com/GetUserProfileAndroidApi?id="+ID;

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject= response.getJSONObject(0);
                    //Toast.makeText(Otp_Activity.this,response.toString(), Toast.LENGTH_SHORT).show();
                    String name=jsonObject.getString("name");
                    String email=jsonObject.getString("email");
                    String address=jsonObject.getString("address");
                    String contect=jsonObject.getString("contact");

                    SharedPreferences preferences=getSharedPreferences("All details",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("name",name);
                    editor.putString("email",email);
                    editor.putString("address",address);
                    editor.putString("contect",contect);
                    editor.apply();




                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                   // Toast.makeText(Otp_Activity.this,e.toString(), Toast.LENGTH_SHORT).show();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("error",error.toString());
                //Toast.makeText(Otp_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy (
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonArrayRequest);


    }




    private void hitapiotpverfy(String otp, final String a)
    {

       String url="https://serviceonway.com/VerifyContactWithOtpApi?otp="+otp+"&contact="+a;

       JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
               null, new Response.Listener<JSONArray>()
       {
           @Override
           public void onResponse(JSONArray response)
           {
               progressBar.setVisibility(View.GONE);

               try {
                   JSONObject jsonObject=response.getJSONObject(0);

                   //
                   if(jsonObject.getString("message").equals("error"))
                   {
                        textotp.setVisibility(View.VISIBLE);
                      // Toast.makeText(Otp_Activity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                   }
                   else
                       {
                       textotp.setVisibility(View.GONE);
                       String id = jsonObject.getString("id");
                       SharedPreferences sharedPreferences5 = getSharedPreferences("myid", MODE_PRIVATE);
                       SharedPreferences.Editor editor1 = sharedPreferences5.edit();
                       editor1.putString("unicid", id);
                       editor1.apply();

                        message = jsonObject.getString("message");


                       if (message.equals("success"))
                       {
                           //Toast.makeText(Otp_Activity.this, "success 1", Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                           SharedPreferences sharedPreferences3 = getSharedPreferences("myid", MODE_PRIVATE);
                           String s1=sharedPreferences3.getString("unicid","");
                           hitapigetname(s1);


                           if (!jsonObject.isNull("name"))
                           {
                              // Toast.makeText(Otp_Activity.this, "old user", Toast.LENGTH_SHORT).show();

                               Intent intent = new Intent(Otp_Activity.this, MainActivity.class);
                               SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                               SharedPreferences.Editor editor = sharedPreferences.edit();
                               String login = "1";
                               editor.putString("id", login);
                               editor.apply();
                               startActivity(intent);
                               finish();
                               Toast.makeText(Otp_Activity.this,"old user save", Toast.LENGTH_SHORT).show();


                               //save the phone number store the local d

                               User user = new User(Otp_Activity.this);
                               user.setPhone(a);
                               Toast.makeText(Otp_Activity.this,a, Toast.LENGTH_SHORT).show();

                               SharedPreferences sharedPreferences6 = getSharedPreferences("myvalue", MODE_PRIVATE);
                               SharedPreferences.Editor editor2 = sharedPreferences6.edit();
                               editor2.putString("id1", a);
                               editor2.apply();
                               ///Toast.makeText(Otp_Activity.this, "otp success", Toast.LENGTH_SHORT).show();
                              } else
                               {
                               SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                               SharedPreferences.Editor editor = sharedPreferences.edit();
                               String login = "1";
                               editor.putString("id", login);
                                   Toast.makeText(Otp_Activity.this,"new user save", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(Otp_Activity.this, Signup_Activity.class);
                               startActivity(intent);
                               finish();

                                   User user = new User(Otp_Activity.this);
                                   user.setPhone(a);


                               SharedPreferences sharedPreferences6 = getSharedPreferences("myvalue", MODE_PRIVATE);
                               SharedPreferences.Editor editor2 = sharedPreferences6.edit();
                               editor2.putString("id1", a);
                               editor2.apply();

                             }

                       } else {
                           Log.e("error","not verfy");
                           //Toast.makeText(Otp_Activity.this, "not verfy", Toast.LENGTH_SHORT).show();

                       }


                   }
               } catch (JSONException e)
               {
                   e.printStackTrace();
                   Log.e("error",e.toString());

                   //Toast.makeText(Otp_Activity.this,e.toString(), Toast.LENGTH_SHORT).show();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error)
           {
               Log.e("error",error.toString());

               //Toast.makeText(Otp_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();

           }
       }){

           @Override
           protected Map<String, String> getParams() throws AuthFailureError
           {
               HashMap<String,String> hashMap= new HashMap<String, String>();
               return hashMap;
           }
       };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy (
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonArrayRequest);


    }


    //otp generate code
    private void GenerateOtp(String a)
    {

        //otp resend url
        String url="https://serviceonway.com/StoreContactWithOtpApi?contact="+a;

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);

                try {

                    JSONObject jsonObject=response.getJSONObject(0);
                    String result=jsonObject.getString("message");
                    if(result.equals("success"))
                    {
                        //Toast.makeText(Otp_Activity.this, "otp send", Toast.LENGTH_SHORT).show();
                        Log.e("error","otp send");
                    }
                    else
                    {
                       // Toast.makeText(Otp_Activity.this, "otp not send", Toast.LENGTH_SHORT).show();
                        Log.e("error","otp not send");
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                    //Toast.makeText(Otp_Activity.this,e.toString()
                            //, Toast.LENGTH_SHORT).show();
                    Log.e("error",e.toString());
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //Toast.makeText(Otp_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonArrayRequest);

    }
}
