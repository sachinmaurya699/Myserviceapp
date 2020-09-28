package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.razorpay.Checkout;
import com.sossolution.serviceonway.Adapter.Includeadapter;
import com.sossolution.serviceonway.Adapter.NotdataAdapter;
import com.sossolution.serviceonway.Class.Notification_Chanal;
import com.sossolution.serviceonway.Class.Notservice;
import com.sossolution.serviceonway.Class.Service;
import com.sossolution.serviceonway.Class.Servicename;
import com.sossolution.serviceonway.Fragment.History_Fragment;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopDetails_Activity extends AppCompatActivity
{

    TextView textView,textViewshop,textdata,total;
    Button button;
    RatingBar ratingBar;
    NotdataAdapter notdataAdapter;
    Includeadapter includeadapter;
    RecyclerView recyclerView,recyclerView1;
    List<Servicename> datalistinclude1;
    List<Notservice> datalistnotservice;
    String Id1;
    String Service1;
    String vechical;
    ImageView imageView;
    Context mcontext;
    String skk,spp,sll, providerid;
    double mSubTotal ;
    String provider, Newservice,User_id,Provider_id;
    String Shop_name,User_Name,User_Contact,User_opt_contact;
    String User_address,User_lat,User_lng,Vehicle;
    String Marker,Model,Fuel;
    String  Service_include="";
    String  Service_not_include="";
    String Price;
    String Address="";
    String Lat1,Lang1;
    String s1;
    String include1;
    String ServiceInclude="";
    AlertDialog.Builder alertDialog;
    String includeapiservie="";
    EditText editText;
    // Button apply;
    TextView textbarcode,textgrandtotal,textpersentage,totalprice;
    String finalammout;
    String deduct1;
    TextView price;
    EditText promocodenew;
    //all value store
    String  Promodecode="";
    String  Promocode_id="";
    String  Promocodepersentage="";
    String  Promocode_final_price="";
    //all value call
    String  Promodecode1;
    String  Promocode_id1;
    String  Promocode_ammount;
    String Sm="";
    String promocode_price;
    TextView textnewprocode;
    Button buttonapply;
    Button nextbutton;
    TextView showpromocodetext;
    TextView textpromocode_applystatic;;
    TextView text_totalpaybal;
    EditText textedit;
    TextView textpromocode,showpromocode;
    String shopimage,shopname,service,provider11,servicevalue,reating;
    String  CHECK="";
    String provide_new;
    String shop_new;
    String service_new_inclde;
    String notinclude;

    //new value
    String USER_ID;
    String PROVIDER_ID;
    String SHOP_NAME;
    String USER_NAME;
    String USER_CONNECT;
    String USER_SECOND_CONNECT;
    String USER_address;
    String USER_USERLATITUDE;
    String USER_USERLANGITUDE;
    String USER_VECHICAL;
    String USER_MAKER;
    String USER_MODEL="";
    String USER_FUEL;
    String USER_SERVICE_INCLUDE;
    String USER_SERVICE_NOT_INCLUDE;
    String USER_PRICE;
    String USER_PROMOCODE_FINAL_PRICE;
    String USER_PROMOCODE_AMOUNT;
    String USER_PROMOCODE;
    String USER_PROMOCODE_ID;
    String img;
    String Code;
    TextView text_grandtotal;
    TextView  text_procode1;
    String includeapiservie1="";
    String vechicle;
    String address;
    String  address1;
    String  address5;
    String shop_n;
    String shop_title;
    String Img;
    ImageView back;
    TextView header;
    Uri imageUri;
    String not_includevalue="";
    String  item1;
    String getmodel;
    String not_include;
    ProgressBar progressBar;

    //new add
    Notification_Chanal notificationChanal;
    //..............oncreate method..............
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdeatilsnew);
        back=findViewById(R.id.back);
        //cast notification chanal
        notificationChanal= new Notification_Chanal(this);
        
        progressBar=findViewById(R.id.progressBar_new);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Shops Details");

        //          all data..........................

        // Log.d("s1value",s1.toString());
        imageView=findViewById(R.id.imageView13);
        SharedPreferences mPrefs1 = getSharedPreferences("MyAllgetdetails1", MODE_PRIVATE);
        shopimage =mPrefs1.getString("getimage","");
        Log.d("shopimage",shopimage);

       // Toast.makeText(ShopDetails_Activity.this,shopimage.toString(), Toast.LENGTH_SHORT).show();
        if(shopimage.contains("none"))
        {

            Glide.with(this)
                    .load("https://serviceonway.com/UploadedFiles/ServiceProviderImages/logo3-plane1.png")
                    .into(imageView);

        }
        else
            {
           // Toast.makeText(ShopDetails_Activity.this, "not none", Toast.LENGTH_SHORT).show();
            String images1[]=shopimage.split(",");
            img=images1[0];
          }

       // Log.d("s1value",img);
        shopname=mPrefs1.getString("getshopname","");
        servicevalue=mPrefs1.getString("getservice","");
        provider11=mPrefs1.getString("getproviderid","");
        Log.d("provider",provider11.toString());
      //  Toast.makeText(ShopDetails_Activity.this,provider11.toString(), Toast.LENGTH_SHORT).show();
        Id1= provider11;
        Log.d("providerdata","valu1"+provider11);
        reating=mPrefs1.getString("getreating","");
        ratingBar=findViewById(R.id.text_nratingbar);

        Log.d("image","shop details"+shopimage);
        //textgrandtotal=findViewById(R.id.text_totalpaybal);
       // textgrandtotal.setVisibility(View.GONE);
       // text_grandtotal.setVisibility(View.GONE);
        totalprice =findViewById(R.id.text_price);
        price=findViewById(R.id.Tprice);
        recyclerView=findViewById(R.id.recyclerview_includeshow);
        recyclerView1=findViewById(R.id.recyclerviewnotinclude1);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        nextbutton=findViewById(R.id.button_next);
        mSubTotal=0;
        //new shop cast
        textViewshop=findViewById(R.id.text_nshop);

        //imageview cast................

        Picasso.get()
                .load("https://serviceonway.com/UploadedFiles/ServiceProviderImages/"+img)
                .resize(900,300)
                .into(imageView);
        textViewshop.setText(shopname);
        //textViewshop.setText(shop_n);

        datalistinclude1=new ArrayList<Servicename>();
        Collections.reverse(datalistinclude1);
        datalistnotservice=new ArrayList<Notservice>();
        Collections.reverse(datalistnotservice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        SharedPreferences mPrefs2 = getSharedPreferences("map", MODE_PRIVATE);
        CHECK = mPrefs2.getString("mp", "");
        CHECK = CHECK.replaceAll(",$", "");
        Service1 = CHECK;



        Log.d("Service1",Service1 );
        SharedPreferences pref = getSharedPreferences("vec",Context.MODE_PRIVATE);
        item1=pref.getString("car","");

        Log.d("car", item1);

        if(item1.equals("car"))
        {
            vechical="service_provider_car_service";

        }
        else
        {
            vechical="service_provider_bike_service";
           // Toast.makeText(this, "bike", Toast.LENGTH_SHORT).show();

        }
       hitapi(Id1,Service1,vechical);

        textView=findViewById(R.id.text);
        //button=findViewById(R.id.conform);

        SharedPreferences preferences3 = getSharedPreferences("model item", MODE_PRIVATE);
        getmodel=preferences3.getString("model","");

        Log.d("getmodel",getmodel);
        if(getmodel.contains("Splendor+"))
        {
            USER_MODEL="Splendor%2B";
        }
        else
        {
            USER_MODEL=getmodel;
            Log.d("user_m",USER_MODEL);
        }

        /*String not_include  = not_includevalue;
        notinclude=not_include;
        if(notinclude.contains("&"))
        {
            Toast.makeText(ShopDetails_Activity.this,"get&", Toast.LENGTH_SHORT).show();
        }
        Log.d("getuser","Service_include"+includeapiservie1.toString());
        Log.d("getuser"," notinclude"+  notinclude.toString());*/




        nextbutton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(ShopDetails_Activity.this, R.style.AlertDialogDanger));
                // Setting Dialog Title
                alertDialog.setTitle("Success");
                // Setting Dialog Message
                alertDialog.setMessage("Your booking has been Sent for confirmation. once get confirmed, You will receive a message shortly");
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_check_circle_black_24dp);
                alertDialog.setPositiveButton("View",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent intent= new Intent(ShopDetails_Activity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });

                alertDialog.show();

                notification();
                //create notification............................

              //  notification();



                //startPayment();
                //............................................All details................................//
                SharedPreferences sharedPreferences=getSharedPreferences("myid",MODE_PRIVATE);
                User_id =sharedPreferences.getString("unicid","");
                Log.d("getuser","User_id"+User_id.toString());



                //profile_activity
                SharedPreferences preferences=getSharedPreferences("All details",MODE_PRIVATE);
                String contect=preferences.getString("contect","");
                String name=preferences.getString("name","");





                SharedPreferences sharedPreferences3 = getSharedPreferences("vec", MODE_PRIVATE);
                Vehicle=sharedPreferences3.getString("car","");
                Log.d("getuser","Vehicle"+Vehicle.toString());
                SharedPreferences mPrefs5 = getSharedPreferences("makeitem", MODE_PRIVATE);
                Marker=mPrefs5.getString("bike","");
                Log.d("getuser","getuser"+Marker.toString());

                SharedPreferences mPrefs1 = getSharedPreferences("model item", MODE_PRIVATE);
                Model=mPrefs1.getString("model","");

                Log.d("getuser"," Model"+Model.toString());


                SharedPreferences mPrefs2 = getSharedPreferences("Oilitem", MODE_PRIVATE);
                Fuel=mPrefs2.getString("Oil","");

                String includeitem=includeapiservie;

                Log.d("include",includeitem);
                includeapiservie1=includeitem;

                //change
                 notinclude=Service_not_include.replace("&","%26");

                Log.d("getuser","Service_include"+includeapiservie1.toString());
                Log.d("getuser"," notinclude"+  notinclude.toString());
                Price=String.valueOf(mSubTotal);

                Log.d("getuser"," Price"+Price.toString());




                //1
                SharedPreferences getuser_id=getSharedPreferences("myid",MODE_PRIVATE);
                String use_id =getuser_id.getString("unicid","");
                Log.d("getbooking id","v1"+s1);

                //2
               /* SharedPreferences mPrefs3 = getSharedPreferences("MyAllgetdetails", MODE_PRIVATE);
                provide_new=mPrefs3.getString("getproviderid","");
                Provider_id=provide_new;
                Log.d("getuser"," Provider_id"+Provider_id.toString());
               // shop_new=mPrefs3.getString("getshopname","");
                Shop_name= shopname;
                Log.d("getuser","Shop_name"+Shop_name.toString());*/


                //5555555
                SharedPreferences mPrefs4=getSharedPreferences("MyAllgetdetails1",MODE_PRIVATE);
                provide_new=mPrefs4.getString("getproviderid","");
                Provider_id=provide_new;
                Log.d("getuser"," Provider_id"+Provider_id.toString());
                shop_new=mPrefs4.getString("getshopname","");
                Log.d("getuser","Shop_name"+shop_new.toString());



               // SharedPreferences preferences1= getSharedPreferences("MyAllgetdetails1",MODE_PRIVATE);
                SharedPreferences preferences1=getSharedPreferences("location",MODE_PRIVATE);

                String latitude1=preferences1.getString("getlatitude","");
                String logitude1=preferences1.getString("getlongitude","");
                User_lat=latitude1;
                User_lng=logitude1;
                Log.d("getuser"," User_lng"+User_lat.toString());
                Log.d("getuser"," User_lat"+User_lng.toString());




             /*   Log.d("getuser"," Provider_id"+Provider_id.toString());
                shop_new=mPrefs3.getString("getshopname","");
                Shop_name=shop_new;
                Log.d("getuser","Shop_name"+Shop_name.toString());*/



                //3
                SharedPreferences sharedPreferences1=getSharedPreferences("address",MODE_PRIVATE);
                address5=sharedPreferences1.getString("place","");
                USER_address=address5;
                Log.d("user_address", address5);

                SharedPreferences preferences4= getSharedPreferences("profile_Activity",MODE_PRIVATE);
                address1=preferences4.getString("getaddress","");
                Log.d("address",address1.toString());
                User_Name=name;
                Log.d("getuser"," User_Name"+User_Name);
                User_Contact=contect;
                Log.d("getuser","User_Contact"+User_Contact);

                Log.d("getuser"," User_address"+User_address);
                User_opt_contact=contect;
                Log.d("getuser"," User_opt_contact"+User_opt_contact);



               /* SharedPreferences shared=getSharedPreferences("MyAllgetdetails",MODE_PRIVATE);
                String latitude1=shared.getString("getlatitude","");
                String logitude1=shared.getString("getlongitude","");
                User_lat=latitude1;
                User_lng=logitude1;
                Log.d("getuser"," User_lng"+User_lat.toString());
                Log.d("getuser"," User_lat"+User_lng.toString());*/


                SharedPreferences preferences8 = getSharedPreferences("makeitem", MODE_PRIVATE);
                String maker=preferences8 .getString("bike","");

                SharedPreferences  preferences2 = getSharedPreferences("vec", MODE_PRIVATE);
                 vechicle= preferences2.getString("car","");
                 Log.d(" vechicle", vechicle);

                /*//home fragment
                SharedPreferences pref = getSharedPreferences("car",Context.MODE_PRIVATE);
                vechicle =pref.getString("car","");*/

              /*  SharedPreferences preferences3 = getSharedPreferences("model item", MODE_PRIVATE);
                 getmodel=preferences3.getString("model","");
                 Log.d("getmodel",getmodel);
                 if(getmodel.equals("Splendor+"))
                 {
                     //USER_MODEL="Splendor"+"\u002B";
                     Toast.makeText(ShopDetails_Activity.this, "splendor", Toast.LENGTH_SHORT).show();
                 }*/


                SharedPreferences mPref = getSharedPreferences("Oilitem", MODE_PRIVATE);
                String fuel =mPref.getString("Oil","");

                SharedPreferences  mPrefs = getSharedPreferences("data", MODE_PRIVATE);
                String promocode_id=mPrefs.getString("get_id","");
                Log.d("getuser","  promocode_id"+promocode_id.toString());
                String promocodevalue=mPrefs.getString("get_promocode","");
                Log.d("getpromocode_value"," promocodevalue"+promocodevalue.toString());
                String promocodepersent = mPrefs.getString("get_persentage","");
                Log.d("getpromocode_value","promocodepersent"+promocodepersent.toString());
                String  promocodefinalprice=mPrefs.getString("get_finalprice","");
                Log.d("getpromocode_value","  promocodefinalprice"+ promocodefinalprice.toString());




                USER_ID =use_id;
                //String USER_ID="31";
               // Toast.makeText(ShopDetails_Activity.this,USER_ID, Toast.LENGTH_SHORT).show();
                Log.d("first","v1"+USER_ID);
                PROVIDER_ID = provide_new;
               // Toast.makeText(ShopDetails_Activity.this,PROVIDER_ID, Toast.LENGTH_SHORT).show();
                Log.d("first",PROVIDER_ID);
                SHOP_NAME= shop_new;
              ///  Toast.makeText(ShopDetails_Activity.this,SHOP_NAME, Toast.LENGTH_SHORT).show();
                Log.d("first",SHOP_NAME);
                USER_NAME=name;
              //  Toast.makeText(ShopDetails_Activity.this,USER_NAME, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_NAME);
                USER_CONNECT=contect;
              //  Toast.makeText(ShopDetails_Activity.this,USER_CONNECT, Toast.LENGTH_SHORT).show();
                Log.d("first", USER_CONNECT);
                USER_SECOND_CONNECT=contect;
              //  Toast.makeText(ShopDetails_Activity.this,USER_SECOND_CONNECT, Toast.LENGTH_SHORT).show();
                Log.d("first",  USER_SECOND_CONNECT);
               // USER_address=address;
               // Toast.makeText(ShopDetails_Activity.this, USER_address, Toast.LENGTH_SHORT).show();

                USER_USERLATITUDE=latitude1;
              //  Toast.makeText(ShopDetails_Activity.this, USER_USERLATITUDE, Toast.LENGTH_SHORT).show();
                Log.d("first", USER_USERLATITUDE);
                USER_USERLANGITUDE=logitude1;
              //  Toast.makeText(ShopDetails_Activity.this,  USER_USERLANGITUDE, Toast.LENGTH_SHORT).show();
                Log.d("first", USER_USERLANGITUDE);
                USER_VECHICAL=vechicle;
              //  Toast.makeText(ShopDetails_Activity.this,  USER_VECHICAL, Toast.LENGTH_SHORT).show();
                Log.d("first", USER_VECHICAL);
                USER_MAKER=maker;
              //  Toast.makeText(ShopDetails_Activity.this,USER_MAKER, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_MAKER);
               // USER_MODEL="\u002B";

               // Toast.makeText(ShopDetails_Activity.this, USER_MODEL, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_MODEL);
                USER_FUEL=fuel ;
                //Toast.makeText(ShopDetails_Activity.this,USER_FUEL, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_FUEL);
                USER_SERVICE_INCLUDE= includeapiservie1;
               // Toast.makeText(ShopDetails_Activity.this,USER_SERVICE_INCLUDE, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_SERVICE_INCLUDE.toString());
                USER_SERVICE_NOT_INCLUDE=  notinclude;
                Log.d("not_include",USER_SERVICE_NOT_INCLUDE.toString());
               // Toast.makeText(ShopDetails_Activity.this, USER_SERVICE_NOT_INCLUDE, Toast.LENGTH_SHORT).show();
                USER_PRICE= String.valueOf(mSubTotal);
                totalprice.setText(USER_PRICE);
                Log.d("first",USER_PRICE);
               // Toast.makeText(ShopDetails_Activity.this, USER_PRICE, Toast.LENGTH_SHORT).show();
                //USER_PROMOCODE_ID=promocode_id;
                USER_PROMOCODE_ID="";
               // Toast.makeText(ShopDetails_Activity.this, USER_PROMOCODE_ID, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_PROMOCODE_ID);
               // USER_PROMOCODE=promocodevalue;
                USER_PROMOCODE="";
               // Toast.makeText(ShopDetails_Activity.this, USER_PROMOCODE, Toast.LENGTH_SHORT).show();
                Log.d("first", USER_PROMOCODE);
                //USER_PROMOCODE_AMOUNT=promocodepersent;
                USER_PROMOCODE_AMOUNT="";
               // Toast.makeText(ShopDetails_Activity.this,USER_PROMOCODE_AMOUNT, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_PROMOCODE_AMOUNT);
               // USER_PROMOCODE_FINAL_PRICE=promocodefinalprice;
                USER_PROMOCODE_FINAL_PRICE="";
               // Toast.makeText(ShopDetails_Activity.this,USER_PROMOCODE_FINAL_PRICE, Toast.LENGTH_SHORT).show();
                Log.d("first",USER_PROMOCODE_FINAL_PRICE);



                hitBookignapi(USER_ID,PROVIDER_ID,SHOP_NAME,USER_NAME,USER_SECOND_CONNECT,USER_CONNECT,USER_address,USER_USERLATITUDE,USER_USERLANGITUDE,USER_VECHICAL,USER_MAKER,USER_MODEL,USER_FUEL,USER_SERVICE_INCLUDE,USER_SERVICE_NOT_INCLUDE,USER_PRICE,USER_PROMOCODE_ID,USER_PROMOCODE,USER_PROMOCODE_AMOUNT,USER_PROMOCODE_FINAL_PRICE);


            }
        });
    }

    private void notification()
    {

        String message="Your booking has been Sent for confirmation. once get confirmed, You will receive a message shortly";
        String title="Serviceonway";

        notificationChanal.notify(message,title);


    }


    public void hitBookignapi(String User_Id,String Provider_Id,String Shop_Name,String User_Name,String User_Connect,String User_Opt_Contact,String User_Address,String User_Lat,String User_Lang,String Vechical,String Maker,String Model,String Fuel,String Service_Include,String Service_Not_Include,String Price,String Promocode_Id,String Promocode,String Promocode_Amount,String Promocode_Final_Price)
    {
        
       String url ="https://serviceonway.com/BookingServiceAndroidApi?user_id="+User_Id+"&provider_id="+Provider_Id+"&shop_name="+Shop_Name+"&user_name="+User_Name+"&user_contact="+User_Connect+"&user_opt_contact="+User_Opt_Contact+"&user_address="+User_Address+"&user_lat="+User_Lat+"&user_lng="+User_Lang+"&vehicle="+Vechical+"&maker="+Maker+"&model="+Model+"&fuel="+Fuel+"&service_include="+Service_Include+"&service_not_include="+Service_Not_Include+"&price="+Price+"&promocode_id="+Promocode_Id+"&promocode="+Promocode+"&promocode_amount="+Promocode_Amount+"&promocode_final_price="+Promocode_Final_Price;

        //String url ="https://serviceonway.com/BookingServiceAndroidApi?user_id=\"+User_Id+\"&provider_id=\"+Provider_Id+\"&shop_name=\"+Shop_Name+\"&user_name=\"+User_Name+\"&user_contact=\"+User_Connect+\"&user_opt_contact=\"+User_Opt_Contact+\"&user_address=\"+User_Address+\"&user_lat=\"+User_Lat+\"&user_lng=\"+User_Lang+\"&vehicle=\"+Vechical+\"&maker=\"+Maker+\"&model=\"+Model+\"&fuel=\"+Fuel+\"&service_include=\"+Service_Include+\"&service_not_include=\"+Service_Not_Include+\"&price=\"+Price+\"&promocode_id=\"+Promocode_Id+\"&promocode=\"+Promocode+\"&promocode_amount=\"+Promocode_Amount+\"&promocode_final_price=\"+Promocode_Final_Price";
        Log.d("savedata","v22"+url);
      //  Toast.makeText(this,url, Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,url,null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try {

                            JSONObject jsonObject=response.getJSONObject(0);
                            String id=jsonObject.getString("id");
                            SharedPreferences sharedPreferences=getSharedPreferences("Allvalue",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("useId",USER_ID);
                            editor.putString("Provider_Id",PROVIDER_ID);
                            editor.putString("Shop_Name",SHOP_NAME);
                            editor.putString("User_Name",USER_NAME);
                            editor.putString("User_Connect",USER_SECOND_CONNECT);
                            editor.putString("USER_CONNECT",USER_CONNECT);
                            editor.putString("User_Address",User_Address);
                            editor.putString("useId",USER_USERLATITUDE);
                            editor.putString("USER_USERLANGITUDE",USER_USERLANGITUDE);
                            editor.putString("USER_VECHICAL",USER_VECHICAL);
                            editor.putString("USER_MAKER",USER_MAKER);
                            editor.putString("USER_MODEL",USER_MODEL);
                            editor.putString("USER_FUEL",USER_FUEL);
                            editor.putString("USER_SERVICE_INCLUDE",USER_SERVICE_INCLUDE);
                            editor.putString("USER_SERVICE_NOT_INCLUDE",USER_SERVICE_NOT_INCLUDE);
                            Log.d("user",USER_SERVICE_NOT_INCLUDE);
                            editor.putString("USER_PRICE",USER_PRICE);
                            editor.putString("USER_PROMOCODE_ID",USER_PROMOCODE_ID);
                            editor.putString("USER_PROMOCODE",USER_PROMOCODE);
                            editor.putString("USER_PROMOCODE_AMOUNT",USER_PROMOCODE_AMOUNT);
                            editor.putString("USER_PROMOCODE_FINAL_PRICE",USER_PROMOCODE_FINAL_PRICE);
                            editor.apply();


                            //Toast.makeText(ShopDetails_Activity.this,"getid"+id, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                            Log.e("error",e.toString());
                        }

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
              //  Toast.makeText(ShopDetails_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("error",error.toString());
                Log.d( "Error: ","v1" + error
                        + "\nStatus Code " + error.networkResponse.statusCode
                        + "\nCause " + error.getCause()
                        + "\nnetworkResponse " + error.networkResponse.data.toString()
                        + "\nmessage" + error.getMessage());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<>();
                //hashMap.put ("Content-Type","application/json;charset=utf-8");
               // Log.d("hasmap","h1"+hashMap.toString());
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(ShopDetails_Activity.this).add(jsonArrayRequest);

    }

    private void promocode(String Code,String Price)
    {

        String  procode="https://serviceonway.com/userpromocodeandroidapi?code="+Code+"&price="+Price;

        JsonObjectRequest objectRequest= new JsonObjectRequest(Request.Method.POST,
                procode, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONObject object= response.getJSONObject("data");
                    String promocode=object.getString("code");
                    String id=object.getString("id");
                    String deduct_type=object.getString("type");
                    String deduct_type_amount=object.getString("deduct");
                    String deduct1=object.getString("deduct_amount");
                    String amount1=object.getString("final_amount");

                    //sharedprefrence..........

                    SharedPreferences sharedPreferences1=getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences1.edit();
                    editor.putString("get_promocode",promocode);
                    editor.putString("get_id",id);



                    //check value show ..............................
                    if(deduct_type.equals("PERCENT"))
                    {
                        editor.putString("get_persentage", deduct_type_amount+" Prc");
                    }
                    else
                    {
                        editor.putString("get_persentage", deduct_type_amount);
                    }


                    //editor.putString("get_persentage", deduct1);

                    editor.putString("get_finalprice",amount1);
                    editor.apply();


                    // textgrandtotal.setVisibility(View.VISIBLE);
                 //   totalprice.setText("\u20B9"+amount1);

                    totalprice.setVisibility(View.VISIBLE);
                    textpersentage.setVisibility(View.VISIBLE);
                    textpersentage.setText(deduct1);
                    //textbarcode.setVisibility(View.VISIBLE);

                   // textbarcode.setText(promocode);
                   // showpromocode.setText(promocode);
                  //  textbarcode.setText(promocode);
                    textedit.setText(promocode);

                    // Toast.makeText(ShopDetails_Activity.this,object.toString(), Toast.LENGTH_SHORT).show();


                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.e("error",e.toString());

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("error",error.toString());
            }
        });
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        objectRequest.setShouldCache(false);
        Volley.newRequestQueue(ShopDetails_Activity.this).add(objectRequest);

    }


    private void hitapi(String Id1, String Serviceid, String vechical)
    {

        String url="https://www.serviceonway.com/GetSelectedShopDetails?id="+Id1+"&service="+Serviceid+"&vehicle="+vechical;


        Log.d("v2",url);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,url,
                null,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                Log.d("responcevalue",response.toString());

                String Notincludeservice="";

                //null value show one index data show
                if (response.toString().contains("null"))
                {
                    //Toast.makeText(ShopDetails_Activity.this, "if blog call", Toast.LENGTH_SHORT).show();

                    try {

                        JSONArray jsonArray2 = response.getJSONArray(1);
                        for (int j = 0; j < jsonArray2.length(); j++)
                        {
                            Log.d("json value", "show value" + jsonArray2.length());
                            JSONObject includeservice = jsonArray2.getJSONObject(j);
                            Servicename servicename = new Servicename();
                            servicename.setName(includeservice.getString("service"));
                          //  String service_name=includeservice.getString("service");
                            servicename.setPrice(includeservice.getString("price"));
                            datalistinclude1.add(servicename);
                            //include servcie...............
                            includeapiservie += servicename.getName() + "=" + servicename.getPrice();
                            Log.d("service",includeapiservie);

                            Log.d("includevalue", "include" + includeapiservie);
                            // Service_include+=includeapiservie;
                        }

                        includeadapter = new Includeadapter(getApplicationContext(),
                                (ArrayList<Servicename>) datalistinclude1);
                        //fix bug.......
                        mSubTotal = includeadapter.grandTotal();
                        Log.d("value1","v1"+String.valueOf(mSubTotal));
                        //price.setText("\u20B9" + mSubTotal);
                        totalprice.setText("Total"+"   "+"\u20B9" + mSubTotal);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.d("error", e.toString());
                    }

                    recyclerView.setAdapter(includeadapter);
                    recyclerView1.setAdapter(notdataAdapter);

                    //if condition show data

                } else
                    {

                   // Toast.makeText(ShopDetails_Activity.this, "else value call", Toast.LENGTH_SHORT).show();
                    try {
                        JSONArray jsonArray = response.getJSONArray(0);
                        Log.d("json value", "show value" + jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            Log.d("elsebolck", "elsevalue" + jsonArray.toString());
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Notservice service = new Notservice();
                            service.setService(jsonObject.getString("service"));
                            //String service_one=jsonObject.getString("service");
                           // Log.d("service",service_one);
                            datalistnotservice.add(service);
                            Notincludeservice += service.getService() + ",";

                        }
                         Service_not_include += Notincludeservice;
                          not_includevalue=Service_not_include.replaceAll(",$","");

                        Log.d("Notincludeservice",Notincludeservice);
                        notdataAdapter = new NotdataAdapter(getApplicationContext(),
                                (ArrayList<Notservice>) datalistnotservice);

                        //two condition
                        JSONArray jsonArray2 = response.getJSONArray(1);
                        for (int j = 0; j < jsonArray2.length(); j++)
                        {
                            JSONObject jsonObject = jsonArray2.getJSONObject(j);
                            Servicename servicename = new Servicename();
                            servicename.setName(jsonObject.getString("service"));
                            servicename.setPrice(jsonObject.getString("price"));
                            includeapiservie += servicename.getName() + "=" + servicename.getPrice() + ",";
                            datalistinclude1.add(servicename);
                            //Toast.makeText(ShopDetails_Activity.this,includeapiservie+"includevalue", Toast.LENGTH_SHORT).show();
                        }

                        includeadapter = new Includeadapter(getApplicationContext(),
                                (ArrayList<Servicename>) datalistinclude1);
                        mSubTotal = includeadapter.grandTotal();
                        totalprice.setText("\u20B9"+mSubTotal);

                        Log.d("valueerror","v12"+ String.valueOf(mSubTotal));
                        // total.setText("Total= " + "\u20B9" + mSubTotal);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.d("error", e.toString());
                    }

                    recyclerView.setAdapter(includeadapter);
                    //Service_include=includeadapter.toString();
                    recyclerView1.setAdapter(notdataAdapter);
                    //Service_not_include=notdataAdapter.toString();

                }
                //else bolog finished.................

            }
        },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.e("error",error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                // hashMap.put ("Content-Type", "application/json; charset=utf-8");
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(ShopDetails_Activity.this).add(jsonArrayRequest);

    }

}
