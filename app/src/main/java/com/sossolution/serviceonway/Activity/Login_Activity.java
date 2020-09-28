package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.sossolution.serviceonway.Class.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private ImageView imageView;
    String Phone;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        textView=findViewById(R.id.text_Bick);
        editText=findViewById(R.id.edittext);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        button=findViewById(R.id.button_login);
        imageView=findViewById(R.id.image_Login);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                 Phone=editText.getText().toString();

                 //show error.........
                if(Phone.isEmpty())
                {
                    editText.setError("Enter the number");
                    editText.setFocusable(true);
                }
                  else if(!Patterns.PHONE.matcher(Phone).matches())
                {
                    editText.setError("Enter valid number");
                    editText.setFocusable(true);

                }
                  else  if(Phone.length()!=10)
                  {
                      editText.setError("Enter valid number ");
                      editText.setFocusable(true);
                  }
                  else
                  {
                      StoreContact_api(Phone);
                  }



            }
        });
    }

    //otp generate in method
    private void StoreContact_api(final String phone)
    {

     String url="https://serviceonway.com/StoreContactWithOtpApi?contact="+Phone;

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
                null,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);

                try {

                    JSONObject jsonObject=response.getJSONObject(0);
                    String result=jsonObject.getString("message");
                    if(result.equals("success"))
                    {
                       // Toast.makeText(Login_Activity.this, "otp send", Toast.LENGTH_SHORT).show();
                       /* User user= new User(Login_Activity.this);
                        user.setPhone(Phone);*/
                        Intent intent = new Intent(Login_Activity.this, Otp_Activity.class);
                        intent.putExtra("number",Phone);

                       /* SharedPreferences sharedPreferences=getSharedPreferences("Mobile",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("pho",Phone);
                        editor.apply();*/
                        //intent.putExtra("value",Phone);

                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(Login_Activity.this, "otp not send", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                   // Toast.makeText(Login_Activity.this,e.toString()
                            //, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();

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
}
