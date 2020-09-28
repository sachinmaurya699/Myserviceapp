package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class Profile_Activity extends AppCompatActivity {

    //create object
    TextView textView1,textView2;
    EditText editText1,editText2,editText3,editText4;
    String Name,Email,Number,Address,Id;
    Button button,button1;
    TextView textname,textemail,textaddress;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    SharedPreferences sharedPreferences2;
    String ID;
    TextView textnumber,header;
    String Num;
    ProgressBar progressBar;

    //new xml add all id
    ImageView image_phone ,image_profile ,image_email,image_address,back;
    TextView text_phone;
    EditText editText_name,editText_email,editText_address;
    Button button_update;
    TextView textView_profileinfo,textView_number;
    String email1;

    //new add
    private final static int ALL_PERMISSIONS_RESULT = 107;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileshow);

        //imageview cast
      //  getSupportActionBar().setTitle("Profile Details");
         image_phone=findViewById(R.id.imageview_phone);
         back=findViewById(R.id.back);
         header=findViewById(R.id.header);
         image_profile=findViewById(R.id.imageView_profile);
         image_address=findViewById(R.id.imageView_address);
         image_email=findViewById(R.id.imageView_email);
         header.setText("Profile Details");

        back.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View view)
        {
        onBackPressed();
        }
        });
         //edittext cast.....
        editText_name=findViewById(R.id.editTex_name);
        editText_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editText_name.setText("");
            }
        });
        editText_email=findViewById(R.id.editText_email);
        editText_address=findViewById(R.id.editText_address);
        //button cast
        button_update=findViewById(R.id.button_update);
        //textview cast

        textView_profileinfo=findViewById(R.id.textView_profileinfo);
        textView_number=findViewById(R.id.text_phone);
        progressBar=findViewById(R.id.progressBar_profile1);
        progressBar.setVisibility(View.VISIBLE);
        //textView_profileinfo=findViewById(R.id.textView_profileinfo);

       /* editText1=findViewById(R.id.editText_name);
        editText2=findViewById(R.id.editText_Email);
        textnumber=findViewById(R.id.editText_Number);
        editText4=findViewById(R.id.editText_Address);
        button=findViewById(R.id.buttonupdate);*/


        SharedPreferences sharedPreferences7=getSharedPreferences("myvalue",MODE_PRIVATE);
        Number=sharedPreferences7.getString("id1","");
        textView_number.setText(Number);



        /*<...................save data........>*/
        //phone number get (login activity)
         /*sharedPreferences1= getSharedPreferences("Mobile",MODE_PRIVATE);
         Number = sharedPreferences1.getString("pho",null);
         textnumber.setText(Number);*/

        //id get in otpctivity.................
        // sharedPreferences2= getSharedPreferences("myvalue",MODE_PRIVATE);
         //Id = sharedPreferences2.getString("id1",null);

        //<......... data pass signup activity................>

          //sharedPreferences= getSharedPreferences("data",MODE_PRIVATE);


        //Name=editText1.getText().toString();
        //Email=editText2.getText().toString();
       // Address=editText4.getText().toString();


        SharedPreferences sharedPreferences=getSharedPreferences("myid",MODE_PRIVATE);
        ID=sharedPreferences.getString("unicid","");
        hitapiallcontent(ID);

        button_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
               //NewUser newUser= new NewUser();
                Name= editText_name.getText().toString();

                /*SharedPreferences sharedPreferences2=getSharedPreferences("username",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences2.edit();
                editor.putString("name",Name).apply();*/

                Email= editText_email.getText().toString();

               // Toast.makeText(Profile_Activity.this,Email+"email", Toast.LENGTH_SHORT).show();


                Address= editText_address.getText().toString();
                Num=Number;
                Id=ID;
                 /*    newUser.getname(editText1.getText().toString());
                newUser.getemail(editText2.getText().toString());
                newUser.getaddress(editText4.getText().toString());*/


               // Number=editText3.getText().toString();
                hitapiupdat(Name,Num,Email,Address,Id);
                //Intent intent= new Intent(Profile_Activity.this,Profile2_Activity.class);
               /* SharedPreferences sharedPreferences2=getSharedPreferences("save",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences2.edit();
                editor.putString("name",Name);
                // editor.putString("number",s1);
                editor.putString("Email",Email);
                editor.putString("address",Address);
                editor.apply();*/
                //startActivity(intent);


               // hitapifullupdat(Name,Number,Email,Address,Id);

                Intent intent= new Intent(Profile_Activity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }


    private void hitapiallcontent(String ID)
    {

      String url="https://serviceonway.com/GetUserProfileAndroidApi?id="+ID;
      Log.d("url_profile",url);

      JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
              url, null,new Response.Listener<JSONArray>() {
          @Override
          public void onResponse(JSONArray response)
          {
              progressBar.setVisibility(View.GONE);
              try {
                  JSONObject jsonObject= response.getJSONObject(0);
                  String name=jsonObject.getString("name");
                  SharedPreferences sharedPreferences=getSharedPreferences("username",MODE_PRIVATE);
                  SharedPreferences.Editor editor=sharedPreferences.edit();
                  editor.putString("name",name);
                  editor.apply();

                  email1=jsonObject.getString("email");
                  Log.d("email",email1);

                  SharedPreferences preferences= getSharedPreferences("Email",MODE_PRIVATE);
                  SharedPreferences.Editor editor2=preferences.edit();
                  editor2.putString("myemail",email1);
                  editor2.putString("contect",Num);
                  editor2.apply();
                  //Toast.makeText(Profile_Activity.this,email1.toString()+"emialcomming", Toast.LENGTH_SHORT).show();

                  String address=jsonObject.getString("address");
                  SharedPreferences sharedPreferences3= getSharedPreferences("profile_Activity",MODE_PRIVATE);
                  SharedPreferences.Editor editor1=sharedPreferences3.edit();
                  editor1.putString("getaddress",address);
                  editor1.apply();
                  Log.d("address",address);
                  editText_name.setText(name);
                  editText_email.setText(email1);
                  editText_address.setText(address);

              } catch (JSONException e) {
                  e.printStackTrace();
              }


          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {

              Log.e("error",error.toString());
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

    private void hitapiupdat(String Name,String Number,String Email,String Address,String Id)
    {

        String url="https://serviceonway.com/UpdateUserDetailsWithContactApi?name="+Name+"&contact="+Number+"&email="+Email+"&address="+Address+"&id="+Id;
       final JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
               url, null, new Response.Listener<JSONArray>()
       {

           @Override
           public void onResponse(JSONArray response) {
               progressBar.setVisibility(View.GONE);

               try {
                   JSONObject jsonObject= response.getJSONObject(0);
                   String result=jsonObject.getString("message");
                   if(result.equals("success"))
                   {
                      /* SharedPreferences sharedPreferences3=getSharedPreferences("save",MODE_PRIVATE);
                       SharedPreferences.Editor editor=sharedPreferences3.edit();
                       String r1=sharedPreferences3.getString("name",null);
                       editText1.setText(r1);
                       String r2=sharedPreferences3.getString("Email",null);
                       editText2.setText(r2);
                       String r3=sharedPreferences3.getString("address",null);
                       editText4.setText(r3);
                       editor.apply();*/
                      }
                   else {
                       Log.e("error", "Profile not update");

                        }



               } catch (JSONException e) {
                   e.printStackTrace();
                  Log.e("error",e.toString());
                  }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

               Log.e("error",error.toString());
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
