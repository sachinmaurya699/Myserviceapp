package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.sossolution.serviceonway.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Signup_Activity extends AppCompatActivity
{


    TextView textView1,textView3;
    EditText editText1,editText2,editText4;
    Button button;
    ImageButton imageButton;
    String Name,Email,Phone,Address,Id;
    String a,b;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String number;
    ProgressBar progressBar;
    String  User_id;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);
        textView1=findViewById(R.id.sign_up);
        progressBar =findViewById(R.id.progressBar_signup);
        progressBar.setVisibility(View.GONE);

        //sharedpreference mobile number
          SharedPreferences sharedPreferences6=getSharedPreferences("myvalue",MODE_PRIVATE);
          number = sharedPreferences6.getString("id1","");
          Log.d("newid",number);
          textView3=findViewById(R.id.phone);
          textView3.setText(number);


       //
        SharedPreferences sharedPreferences1= getSharedPreferences("myid",MODE_PRIVATE);
        a = sharedPreferences1.getString("unicid","");
        Log.d("signupid",a);
        Toast.makeText(this,a+"userid", Toast.LENGTH_SHORT).show();



        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences.getString("unicid", "");
        Log.d("userid",User_id);



              b=getIntent().getStringExtra("value1");

             editText1=findViewById(R.id.name);
             editText2=findViewById(R.id.email);
             editText4=findViewById(R.id.address);
             button=findViewById(R.id.next);
             preferences=getSharedPreferences("my",MODE_PRIVATE);

             //button click
             button.setOnClickListener(new View.OnClickListener()
             {
            @Override
            public void onClick(View view) {

                Name=editText1.getText().toString();
                Email=editText2.getText().toString();

              SharedPreferences preferences= getSharedPreferences("Email",MODE_PRIVATE);
              SharedPreferences.Editor editor=preferences.edit();
              editor.putString("email",Email);
              editor.apply();

                Address=editText4.getText().toString();

               if(Name.isEmpty())
               {
                   editText1.setError("Enter the name");
                   editText1.setFocusable(true);
               }
               else if(Email.isEmpty())
               {
                   editText2.setError("Enter the Email");
                   editText2.setFocusable(true);
               }
               else if(Address.isEmpty())
               {
                   editText4.setError("Enter the address");
                   editText4.setFocusable(true);
               }
              else
               {
                   UpdateUserDetail(Name,number,Email,Address,User_id);
                   updatewallet(Name,number,Email,Address,User_id);
                   progressBar.setVisibility(View.VISIBLE);
               }

            }
        });


    }

    private void updatewallet(String Name,String number,String Email,String Address ,String a)
    {

        String url="https://serviceonway.com/InsertUserSignupAmountApi?name="+Name+"&contact="+number+"&email="+Email+"&address="+Address+"&id="+a;
        Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                          Log.d("responce",response.toString());
                        try
                        {

                            JSONObject jsonObject= response.getJSONObject(0);
                            String result=jsonObject.getString("message");
                            Toast.makeText(Signup_Activity.this, "chal", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Signup_Activity.this, "second api", Toast.LENGTH_SHORT).show();
                            if(result.equals("success"))
                            {

                                Toast.makeText(Signup_Activity.this, "success", Toast.LENGTH_SHORT).show();
                               /* Intent intent= new Intent(Signup_Activity.this, MainActivity.class);
                                startActivity(intent);
                                progressBar.setVisibility(View.GONE);
                                finish();*/


                            }
                            else
                            {
                                Log.e("error","signup not successfull");
                                Toast.makeText(Signup_Activity.this,"signup not successfull", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                //Toast.makeText(Signup_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<String, String>();
                return super.getParams();
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonArrayRequest);
    }

    //new signup user.................

    private void UpdateUserDetail(String Name,String number,String Email,String Address,String user)
    {

        String url="https://serviceonway.com/UpdateUserDetailsWithContactApi?name="+Name+"&contact="+number+"&email="+Email+"&address="+Address+"&id="+user;
        Log.d("url2",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            JSONObject jsonObject= response.getJSONObject(0);
                            String result=jsonObject.getString("message");

                            if(result.equals("success"))
                            {
                                Intent intent= new Intent(Signup_Activity.this, MainActivity.class);
                                startActivity(intent);
                                progressBar.setVisibility(View.GONE);
                                finish();


                            }
                            else
                            {
                                Log.e("error","signup not successfull");
                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                //Toast.makeText(Signup_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<String, String>();
                return super.getParams();
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
