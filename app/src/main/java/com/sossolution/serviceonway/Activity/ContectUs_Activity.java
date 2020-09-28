package com.sossolution.serviceonway.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContectUs_Activity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    EditText editText;
    String Message;
    private static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    String Number;
    EditText editTextnumber, editTextmessage;
    String number;
    TextView User_name;
    TextView User_contact;
    TextView User_email;
    TextView User_message;
    Button send_button,phone_call_button,email_button;
    String name,contect,email,message;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    Dialog dialog;
    ImageView back;
    TextView header;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contect_us_);
        back=findViewById(R.id.back);
        header=findViewById(R.id.header);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        header.setText("Contect Us From");
       /* getSupportActionBar().setTitle("CONTACT US FROM");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);

        User_name=findViewById(R.id.user_name);
        User_contact=findViewById(R.id.user_contact);
        User_email=findViewById(R.id.user_email);
        User_message=findViewById(R.id.user_message);
        send_button=findViewById(R.id.send_button);
      //  phone_call_button=findViewById(R.id.phone_contact);
        /*phone_call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {



            }
        });*/
      //  email_button=findViewById(R.id.button_email);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                name=User_name.getText().toString();
                contect=User_contact.getText().toString();
                email=User_email.getText().toString();
                message=User_message.getText().toString();

                if(name.isEmpty())
                {
                    User_name.setError("Enter the name");
                    User_name.setFocusable(true);
                }else if(contect.isEmpty())
                {
                    User_contact.setError("Enter the contect");
                    User_contact.setFocusable(true);
                }else if(email.isEmpty())
                {
                    User_email.setError("Enter the email");
                    User_email.setFocusable(true);
                }else if(message.isEmpty())
                {
                    User_email.setError("Enter the message");
                    User_email.setFocusable(true);
                }
                else
                {
                    sendapi(name,contect,email,message);
                }

            }
        });
       // getpermission();

        /*email_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String s2 = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "sahabsatish@gmail.com", null));
                intent.putExtra(Intent.EXTRA_TEXT, s2);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));


            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            Intent intent= new Intent();

        }
        return super.onOptionsItemSelected(item);
    }

    private void sendapi(String Name, String Contact, String Email, String Message)
    {

     String url="https://serviceonway.com/ContactUsForm?name="+Name+"&contact="+Contact+"&email="+Email+"&message="+Message;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    String s1=response.getString("message");
                    if(s1.equals("success"))
                    {
                       dialog1();
                    }
                    else
                    {
                        Toast.makeText(ContectUs_Activity.this,"form not submit", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ContectUs_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(ContectUs_Activity.this).add(jsonObjectRequest);



    }

    private void dialog1() {
        Toast.makeText(ContectUs_Activity.this,"form submit", Toast.LENGTH_SHORT).show();
        dialog = new Dialog(new ContextThemeWrapper(ContectUs_Activity.this, R.style.AlertDialogDanger));
        // Setting Dialog Titdialog.setMessage(hop");
        // Setting Icon to Dialog
       // String  text="NEAR YOU FILL THE FORM OUR EXECUTIVE WILL CONNECT YOU SOON click here";


        // dialog.setText(spanable);
        // dialog.setMovementMethod(LinkMovementMethod.getInstance());

        dialog.setTitle("your have  form submitted ");

       // dialog.setIcon(R.drawable.ic_check_circle_black_24dp);
        dialog.show();



    }


}
