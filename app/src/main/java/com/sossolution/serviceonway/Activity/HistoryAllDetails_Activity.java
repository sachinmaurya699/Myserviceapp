package com.sossolution.serviceonway.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.sossolution.serviceonway.Adapter.Include_ItemAdapter;
import com.sossolution.serviceonway.Class.Include_item;
import com.sossolution.serviceonway.Class.NotIncludeI_temAdapter;
import com.sossolution.serviceonway.Class.Notification_Chanal;
import com.sossolution.serviceonway.Class.Notinclude_item;
import com.sossolution.serviceonway.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class HistoryAllDetails_Activity extends AppCompatActivity
{
    String ID="";
    String price="";
    String shop_contect;
    String shopcontect;
    //new add
    //textview dinamic cast.......
    TextView textView_ddate,textView_dmaker,textView_dbookingid,textView_dmodel,
            textView_dvechical;
   //textview dainamic
    TextView textView_dinclude,textView_dconnect,textView_dshopname,textView_dprice;
    Button buttonaction;
    String bookingid;
    int totalPrice = 0;
    TextView total;
    TextView textprice;
    String booking="";
    Button process;
    String User="";
    String Provider="";
    String Payment="";
    TextView showprice;
    TextView textView_total,textView_price;
    TextView textpromocode,text_spromocode,textpersentage,texttotalprice,textfinalprice;
    String Code;

    String Price1="";
    String promocode_final_price="";
    Button buttonprossing;
    TextView textshopdetails;
    TextView textinclude,textincludeprice,textnotinclude;
    String connectno="";
     String EMail="";
     String Bookingid="";
     String Transactionid="";
     String Shopcontact="";
     String Usercontact="";
     String Amount="";
     String Mode="";
     String Promoammount="";
     String Promocode="";
     TextView text_promocode;
     ProgressBar progressBar;
     SwipeRefreshLayout refreshLayout;
     TextView texttotal,textprice1;
     int value1=0;
    String Email;
    String Number;
    String email1;
    String bookingId;
    //String shopcontect;
    String shopcontect1;
    Handler handler;
    Button button_view;
    String User_lat;
    String User_lang;
    String Provider_lat;
    String Provider_lang;
    String vehicle;
    String provide_new_lat;
    String provider_new_lang;
    String newuser_latitude;
    String newuser_lagitude;
    TextView textView_notinclude;
    Timer timer;
    String Service_not_include="";
    private Timer t ;
    private TimerTask task ;
    RecyclerView recyclerView_include;
    RecyclerView recyclerView_notinclude;
    List<Include_item>include_itemslist;
    List<Notinclude_item>notinclude_itemslist2;
    Include_ItemAdapter include_itemAdapter;
    NotIncludeI_temAdapter notIncludeI_temAdapter;
    String Notincludeservice="";
    ImageView back;
    TextView header;
    String model;
    Notification_Chanal notification_chanal;
    Button online,wallet_new;
    String order_key;
    String number;
    String  User_id;
    String   newprice;
    String wallet;
    String email;
    String address;
    String name;
    String Number1;
    String signupamount;
    double  Signup_lessamount;
    double FinalPay_amount;
    String  Type1;


    String booking_id="";
    String transaction_id="";
    String order_id="";
    String sighnature="";
    String shop_contact;
    String user_contact;
    String amount;
    String promocode_amount="";
    String mode="";
    String promocode="";
    String neworder_id;

    String  Type="user";

    double s1;
    double s2;
    Dialog dialog;
    TextView textmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //notification_chanal= new Notification_Chanal(this);

        setContentView(R.layout.historyfulldetail);
        //recyclerview add
        back=findViewById(R.id.back);
        online=findViewById(R.id.online);
        wallet_new=findViewById(R.id.wallet_new);
        online.setVisibility(View.GONE);
        wallet_new.setVisibility(View.GONE);
        header=findViewById(R.id.header);
        recyclerView_include=findViewById(R.id.recyclerview_first);
        header.setText("Booking Details");



      /*  getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        recyclerView_include.setHasFixedSize(true);
        recyclerView_notinclude=findViewById(R.id.recycler_second);
        recyclerView_notinclude.setHasFixedSize(true);
        recyclerView_include.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerView_notinclude.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        include_itemslist= new ArrayList<>();
        notinclude_itemslist2=new ArrayList<>();
        button_view=findViewById(R.id.view_direction);
        textView_notinclude=findViewById(R.id.text_notinclude);
        t=new Timer();
        task = new TimerTask()
        {
            @Override    public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                       GetUserBookingDetail(ID);
                        //Toast.makeText(HistoryAllDetails_Activity.this, "call", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        t.scheduleAtFixedRate(task, 0, 5000);


        button_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                String s1="https://maps.google.com/maps?saddr="+newuser_latitude+","+newuser_lagitude+"&daddr="+provide_new_lat+","+provider_new_lang;

                Log.d("newuser","null"+newuser_latitude+","+newuser_lagitude);

                Log.d("values1",s1);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(s1));
                startActivity(intent);

            }
        });

        //new handlar use

        SharedPreferences preferences=getSharedPreferences("My booking id",MODE_PRIVATE);


        bookingid =preferences.getString("getbookingid","");
        textView_dbookingid=findViewById(R.id.text_showid);
        textView_dbookingid.setText(bookingid);




        textprice=findViewById(R.id.newprice3);
        Checkout.preload(getApplicationContext());


        SharedPreferences sharedPreferences6=getSharedPreferences("myvalue",MODE_PRIVATE);
        Number = sharedPreferences6.getString("id1","");
        Log.d("number",Number);

        SharedPreferences s1= getSharedPreferences("Email",MODE_PRIVATE);
        EMail=s1.getString("myemail","");

        SharedPreferences sharedPreferences= getSharedPreferences("email",MODE_PRIVATE);
        String email=sharedPreferences.getString("email","");
        Log.d("newemail",email);

        SharedPreferences prefere= getSharedPreferences("Email",MODE_PRIVATE);
        email1=prefere.getString("myemail","");
        Log.d("email1",email1);





        ID=bookingid;
        Log.d("booking",ID);
        GetUserBookingDetail(ID);

        SharedPreferences sharedPreferences1 = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences1.getString("unicid", "");
        Log.d("user",User_id);
        Type="user";

        textinclude=findViewById(R.id.text_includeshow);
        textincludeprice=findViewById(R.id.text_includeprice);
        textView_ddate=findViewById(R.id.text_showdate);
        textView_dmaker=findViewById(R.id.textshow_make);
        textView_dbookingid=findViewById(R.id.text_showid);
        textView_dmodel=findViewById(R.id.text_showmodel);
        textView_dvechical=findViewById(R.id.text_showvechicle);
        buttonprossing=findViewById(R.id.buttonchange);
         //referesh();
        //shop details ......
        textView_dconnect=findViewById(R.id.text_shopconnectnew);
        textView_dshopname=findViewById(R.id.text_shopnamenew);
        refreshLayout=findViewById(R.id.swiperefresh_items);

    }
    private void GetUserBookingDetail(String ID)
    {
        String url="https://serviceonway.com/GetUserBookingDetailsBySpecBIdAndroidApi?bid="+ID;
        Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {

               include_itemslist= new ArrayList<>();
                Log.d("newvalue", response.toString());
                 try
                  {
                    JSONArray jsonArray = response.getJSONArray(0);
                    Log.d("getjson", jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String date = jsonObject.getString("date");
                        booking_id = jsonObject.getString("booking_id");
                        String vehicle = jsonObject.getString("vehicle");
                        newuser_latitude = jsonObject.getString("user_lat");
                        newuser_lagitude = jsonObject.getString("user_lng");
                        textView_ddate.setText(date);
                        textView_dvechical.setText(vehicle);


                        String sr = jsonObject.getString("service_include");
                        Log.d("sr_value",sr);
                         Include_item include= new Include_item();
                         include.setName(jsonObject.getString("service_include"));
                         include.setPrice(jsonObject.getString("service_include"));
                         include_itemslist.add(include);


                        Notinclude_item notinclude_item = new Notinclude_item();
                        notinclude_item.setService(jsonObject.getString("service_not_include"));
                        notinclude_itemslist2.add(notinclude_item);


                        Price1 = jsonObject.getString("price");
                        Amount = Price1;
                        //user booking api show payment amount.................
                        Log.d("Amount",Price1);
                        textprice.setText("Total"+"   "+"\u20B9"+Amount);
                        Payment = jsonObject.getString("payment");
                        User = jsonObject.getString("status");
                        Provider = jsonObject.getString("shop_status");
                        connectno = jsonObject.getString("user_contact");


                        SharedPreferences preferences= getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("user_contact",connectno);
                        editor.apply();


                        user_contact=connectno;
                        Usercontact = connectno;
                        String promocode_id = jsonObject.getString("promocode_id");
                        String promocode = jsonObject.getString("promocode");
                        Promocode = promocode;


                        String promocode_amount = jsonObject.getString("promocode_amount");
                        //textpersentage.setText(promocode_amount);
                        promocode_final_price = jsonObject.getString("promocode_final_price");
                        // texttotalprice.setText("\u20B9"+promocode_final_price);
                        // Promoammount=promocode_final_price;


                        if (User.equals("0") || Provider.equals("0"))
                        {
                            buttonprossing.setText("Cancel");
                            buttonprossing.setBackgroundColor(Color.RED);

                        } else if (Provider.equals("1") && Payment.equals("0")) {
                            buttonprossing.setText("Processing");
                            buttonprossing.setBackgroundColor(Color.GREEN);
                            // Toast.makeText(HistoryAllDetails_Activity.this, "processing", Toast.LENGTH_SHORT).show();


                        } else if (Provider.equals("2") && Payment.equals("0"))
                        {
                            buttonprossing.setText("Executive on the way");
                            //notification();
                            buttonprossing.setBackgroundColor(Color.BLUE);
                            //Toast.makeText(HistoryAllDetails_Activity.this, "execute on the way", Toast.LENGTH_SHORT).show();


                        } else if (Provider.equals("3") && Payment.equals("0"))
                        {
                            //hitapishowhistory(ID);
                            //Toast.makeText(HistoryAllDetails_Activity.this, "payment ", Toast.LENGTH_SHORT).show();
                            buttonprossing.setText("Payment");

                            buttonprossing.setBackgroundColor(Color.GREEN);

                            buttonprossing.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    buttonprossing.setVisibility(View.GONE);
                                     online.setVisibility(View.VISIBLE);

                                     online.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View view)
                                         {



                                              String pc="1";
                                              int value = Integer.parseInt(pc);
                                              int total = value * 100;
                                             newprice = String.valueOf(total);


                                              walletcheck_blance(User_id,Type);

                                              getorder_id(Type,User_id,newprice);



                                           //  check_bonsewallet(name,Number1,email,address,User_id);

                                         }
                                     });
                                     wallet_new.setVisibility(View.VISIBLE);


                                     wallet_new.setOnClickListener(new View.OnClickListener()
                                     {

                                         @Override
                                         public void onClick(View view)
                                         {
                                             buttonprossing.setVisibility(View.GONE);

                                              SharedPreferences sharedPreferences7=getSharedPreferences("myvalue",MODE_PRIVATE);
                                              Number1=sharedPreferences7.getString("contect","");

                                              SharedPreferences sharedPreferences1=getSharedPreferences("username",MODE_PRIVATE);
                                              name=sharedPreferences1.getString("id1","");

                                              SharedPreferences preferences= getSharedPreferences("Email",MODE_PRIVATE);
                                              email=preferences.getString("myemail","");

                                              SharedPreferences sharedPreferences3= getSharedPreferences("profile_Activity",MODE_PRIVATE);
                                              address=sharedPreferences3.getString("getaddress","");

                                              SharedPreferences sharedPreferences2 = getSharedPreferences("myid", MODE_PRIVATE);
                                              User_id = sharedPreferences2.getString("unicid", "");


                                              SharedPreferences preferences1=getSharedPreferences("My booking id",MODE_PRIVATE);
                                              bookingid =preferences1.getString("getbookingid","");


                                               String type2="user";
                                               String newprice1="1";
                                               String pc="1";


                                              int value = Integer.parseInt(pc);
                                              int total = value * 100;
                                              String newprice4 = String.valueOf(total);


                                              chekwallet_Blance(User_id,Type);

                                             // getorder_id(type2,User_id,newprice4);

                                             //check_bonsewallet(name,Number1,email,address,User_id);




                                         }

                                     });

                                         }
                                     });



                        } else if (Provider.equals("3") && Payment.equals("2")) {

                            buttonprossing.setText("Cash Process");
                            buttonprossing.setBackgroundColor(Color.GREEN);

                        } else if (Provider.equals("3") && Payment.equals("1")) {
                            // hitapishowhistory(ID);
                            buttonprossing.setText("Booking complete");
                            buttonprossing.setBackgroundColor(Color.GREEN);
                        } else
                            {
                            buttonprossing.setText("Success");

                            }

                        //data add show krna ....................


                        String[] s1 = sr.split(",");


                        String bookingdata = "";
                        String[] bookingdata1;
                        String service_name = "";
                        String service_price = "";
                        String s2 = "";
                        String myprice = "";

                        //first for loop..............
                        for (int j = 0; j < s1.length; j++)
                        {
                            bookingdata1 = s1[j].split("=");
                            Log.d("bookingdata1", bookingdata.toString());

                            service_name += bookingdata1[0] + ",";
                            service_price += bookingdata1[1] + ",";
                            Log.d("service", service_price.toString());

                        }

                        //second sinerio...........................
                        String[] s3 = service_name.split(",");
                        String[] s4 = service_price.split(",");

                        //second for loop.............................
                        for (int m = 0; m < s3.length; m++)
                        {
                            s2 += s3[m] + "\n";

                        }
                        Log.d("values2", s2.toString());

                        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                        //  textinclude.setText(s2+"\n");

                        //third for loop...............

                        for (int n = 0; n < s4.length; n++)
                        {
                            myprice += "\u20B9" + s4[n] + "\n";
                        }
                        Log.d("price", myprice.toString());
                        String maker = jsonObject.getString("maker");
                        textView_dmaker.setText(maker);
                        Log.d("makar", maker.toString());
                        model = jsonObject.getString("model");
                        Log.d("model",model);


                        if(model.startsWith("Splendor"))
                        {
                            textView_dmodel.setText(model);

                        }
                        else
                        {
                            textView_dmodel.setText(model);

                        }
                        textView_dmaker.setText(maker);

                    }

                    //not include adapter.........................
                     notIncludeI_temAdapter= new NotIncludeI_temAdapter(getApplicationContext(), (ArrayList<Notinclude_item>) notinclude_itemslist2);
                     recyclerView_notinclude.setAdapter(notIncludeI_temAdapter);


                     //include adapter......................
                     include_itemAdapter= new Include_ItemAdapter(getApplicationContext(), (ArrayList<Include_item>) include_itemslist);
                     recyclerView_include.setAdapter(include_itemAdapter);

                      notinclude_itemslist2= new ArrayList<>();
                      JSONArray jsonArray1 = response.getJSONArray(1);
                    //Toast.makeText(HistoryAllDetails_Activity.this,jsonArray1.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("jsonarray", jsonArray1.toString());


                    for (int j = 0; j < jsonArray1.length(); j++)
                    {

                        // Toast.makeText(HistoryAllDetails_Activity.this,jsonArray1.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                        Log.d("getdata", jsonObject1.toString());
                        String shop = jsonObject1.getString("shop_name");
                        provide_new_lat = jsonObject1.getString("latitude");
                        Log.d("lat", "p" + provide_new_lat);
                        provider_new_lang = jsonObject1.getString("longitude");
                        Log.d("lat", "p" + provider_new_lang);
                        textView_dshopname.setText(shop);

                        shopcontect1 = jsonObject1.getString("contact");
                        SharedPreferences preferences=getSharedPreferences("shop_contact",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("shop",shopcontect1);
                        editor.apply();

                        shop_contact=shopcontect1;
                        textView_dconnect.setText(shopcontect1);
                        Log.e("error", shopcontect1);

                    }


                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d("error", e.toString());
                }



            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.e("error",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);

    }



    private void getorder_id(String Type,String User_id,String Amount)
    {

        String url = "https://serviceonway.com/RazorpayApiKey?type="+Type+"&id="+User_id+"&amount="+Amount;

        Log.d("getorder_id", url.toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                Toast.makeText(HistoryAllDetails_Activity.this,response.toString(), Toast.LENGTH_SHORT).show();

                Log.d("##responce", response.toString());
                String replace = response.toString().replace("[", "");
                String replace1 = replace.replace("]", "");
                List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
                Log.d("list", myList.toString());
                Log.d("list", "" + myList.size());
                String key = myList.get(0);
                Log.d("k", key);
                order_key = myList.get(1);
                Toast.makeText(HistoryAllDetails_Activity.this,order_key, Toast.LENGTH_SHORT).show();

                if(order_key!=null)
                {

                    Toast.makeText(HistoryAllDetails_Activity.this, "online payment", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HistoryAllDetails_Activity.this,Paymentgetway_Activity.class);

                     Toast.makeText(HistoryAllDetails_Activity.this, "order hai", Toast.LENGTH_SHORT).show();

                     SharedPreferences preferences= getSharedPreferences("key",MODE_PRIVATE);
                     SharedPreferences.Editor editor=preferences.edit();
                     editor.putString("order_key",order_key);
                     editor.apply();


                     startActivity(intent);
                }
                else
                {
                    Toast.makeText(HistoryAllDetails_Activity.this, "not order id", Toast.LENGTH_SHORT).show();
                }
                Log.d("order_key",order_key);
                String key1 = myList.get(2);
                Log.d("key1", key1.toString());

                try {
                    JSONArray jsonarray = response.getJSONArray(2);
                    JSONObject jsonObject = jsonarray.getJSONObject(0);
                    Log.d("object", jsonObject.toString());
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String contact = jsonObject.getString("contact");
                    String email = jsonObject.getString("email");
                    Log.d("###name", name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //yhi problem hai


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error", error.toString());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("Content-Type", "application/json; charset=utf-8");
                // Toast.makeText(Maker_Activity.this,hashMap.toString(), Toast.LENGTH_SHORT).show();
                Log.d("maker", hashMap.toString());
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);



    }

    private void PaymentUserBookingAndroidApi_wallet(String Booking_id,String Transaction_id,String Order_id,String sighnature_id,String Shop_contact,String User_contact,String Amount,String Promo_amount,String Mode,String Promo_code)
    {

        String url="https://serviceonway.com/PaymentUserBookingAndroidApiByBId?bid="+Booking_id+"&tdid="+Transaction_id+"&oid="+Order_id+"&sg="+sighnature_id+"&s_contact="+Shop_contact+"&u_contact="+User_contact+"&amount="+Amount+"&promo_amount="+Promo_amount+"&mode="+Mode+"&promo_code="+Promo_code;
        Log.d("PaymentUserBooking_url",url.toString());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {


                Toast.makeText(HistoryAllDetails_Activity.this, "payment user booking final ", Toast.LENGTH_SHORT).show();
                 Log.d("responce222",response.toString());

                try {
                    String message=response.getString("message");
                    if(message.equals("success"))
                    {
                        StyleableToast.makeText(HistoryAllDetails_Activity.this,"payment successfull",R.style.exapleToast).show();

                       // Toast.makeText(HistoryAllDetails_Activity.this, "payment successfull", Toast.LENGTH_SHORT).show();

                    }
                    else if(message.equals("invoice mail error"))
                    {
                        StyleableToast.makeText(HistoryAllDetails_Activity.this,"payment successfull",R.style.exapleToast).show();
                        // Toast.makeText(HistoryAllDetails_Activity.this, " payment successfull", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(HistoryAllDetails_Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                       // Log.d("")
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("responce_error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy (
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonObjectRequest);


    }

    private void walletcheck_blance(String ID,String Type)
    {

      String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+ID+"&type="+Type;
         Log.d("onlineurl",url);

        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Toast.makeText(HistoryAllDetails_Activity.this, "check api", Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
                Log.d("onresponce",response.toString());

                try {
                    JSONObject jsonObject= response.getJSONObject("map");
                    wallet =jsonObject.getString("wallet");
                    double s1=Double.parseDouble(wallet);
                    double s2=Double.parseDouble(Amount);
                    //show_amount.setText("\u20B9"+wallet);
                    Log.d("wallet",wallet);
                    Log.d("object",jsonObject.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("user");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("id");
                        signupamount=jsonObject1.getString("signup_amount");
                        double  signup_amount =Double.parseDouble(signupamount);

                        if(signup_amount>100)
                        {

                            Signup_lessamount=signup_amount-100;

                            Toast.makeText(HistoryAllDetails_Activity.this,String.valueOf(Signup_lessamount), Toast.LENGTH_SHORT).show();
                            Toast.makeText(HistoryAllDetails_Activity.this, "100 greater then", Toast.LENGTH_SHORT).show();
                            Log.d("amount",String.valueOf(Signup_lessamount));

                            /* double newamount= Double.parseDouble(Amount);
                             FinalPay_amount=newamount-100;*/

                            Log.d("mypayment",String.valueOf(FinalPay_amount));

                           // Intent intent= new Intent(HistoryAllDetails_Activity.this,Paymentgetway_Activity.class);
                           // startActivity(intent);



                        }
                        else
                        {
                            Toast.makeText(HistoryAllDetails_Activity.this, "100 less then ", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("error",signupamount);
                        // textView.setText("\u20B9"+signupamount);
                        String transaction_id=jsonObject1.getString("transaction_id");
                        String name=jsonObject1.getString("name");
                        String contact=jsonObject1.getString("contact");
                        Log.d("object",jsonObject1.toString());

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);


    }

    private void check_bonsewallet(String Name,String number,String Email,String Address,String a)
    {

        String url="https://serviceonway.com/InsertUserSignupAmountApi?name="+Name+"&contact="+number+"&email="+Email+"&address="+Address+"&id="+a;
        Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("newresponce",response.toString());
                        try
                        {

                            JSONObject jsonObject= response.getJSONObject(0);
                            String result=jsonObject.getString("message");
                            Toast.makeText(HistoryAllDetails_Activity.this, "chal", Toast.LENGTH_SHORT).show();
                            Toast.makeText(HistoryAllDetails_Activity.this, "second api", Toast.LENGTH_SHORT).show();
                            if(result.equals("success"))
                            {

                                Toast.makeText(HistoryAllDetails_Activity.this, "success", Toast.LENGTH_SHORT).show();
                               /* Intent intent= new Intent(Signup_Activity.this, MainActivity.class);
                                startActivity(intent);
                                progressBar.setVisibility(View.GONE);
                                finish();*/


                            }
                            else
                            {
                                Log.e("error","signup not successfull");
                                Toast.makeText(HistoryAllDetails_Activity.this,"signup not successfull", Toast.LENGTH_SHORT).show();
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

    private void chekwallet_Blance(String ID,String Type)
    {


        String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+ID+"&type="+Type;

        Log.d("urlcheck",url);

        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Toast.makeText(HistoryAllDetails_Activity.this, "checkwallet vali api chali", Toast.LENGTH_SHORT).show();

                Log.d("onresponce",response.toString());
                 newprice="1";

                try {

                     JSONObject jsonObject= response.getJSONObject("map");
                     wallet =jsonObject.getString("wallet");
                      s1=Double.parseDouble(wallet);
                      s2=Double.parseDouble(newprice);

                     //if condition.............

                     if(s1<s2)
                     {
                         Toast.makeText(HistoryAllDetails_Activity.this, "if", Toast.LENGTH_SHORT).show();

                         dialog = new Dialog(HistoryAllDetails_Activity.this);

                         // dialog_button=findViewById(R.id.okbutton);
                         dialog.setContentView(R.layout.customdialog);
                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                             dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));

                         }
                         dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                         dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                         dialog.setCancelable(false);
                         Button cancel = dialog.findViewById(R.id.cancel_button);
                         Button add = dialog.findViewById(R.id.addbutton);
                         TextView message = dialog.findViewById(R.id.message);
                         message.setText("1");
                         //dialog_button1=dialog.findViewById(R.id.okbutton);
                         add.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {


                                     /* int value = Integer.parseInt(Amount);
                                         int total = value * 100;
                                         newprice = String.valueOf(total);
                                         Log.d("price", newprice);*/

                                 String newprice1 = "1";
                                 int value = Integer.parseInt(newprice1);
                                 int total = value * 100;
                                 String newprice5 = String.valueOf(total);




                                         /*String newprice1="1";
                                         double v1=Double.parseDouble(newprice1);
                                         double total = v1 * 100;
                                         String  newprice2 = String.valueOf(total);
                                         Log.d("price", newprice2);*/


                                 //getorder_id(Type, User_id,newprice);

                                 wallet_add(Type, User_id, newprice5);

                                 // Toast.makeText(HistoryAllDetails_Activity.this, "1", Toast.LENGTH_SHORT).show();
                                 //Intent intent= new Intent(MainActivity.this,MainActivity.class);
                                 //startActivity(intent);
                             }
                         });
                         cancel.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {

                                 dialog.dismiss();

                                 //Toast.makeText(HistoryAllDetails_Activity.this, "2", Toast.LENGTH_SHORT).show();
                                 //Intent intent= new Intent(MainActivity.this,MainActivity.class);
                                 //startActivity(intent);


                             }

                         });
                         dialog.show();

                     }

                     else {
                         Toast.makeText(HistoryAllDetails_Activity.this, "else", Toast.LENGTH_SHORT).show();

                         String Payment_id="";
                         String order_newid="";
                         String sighnature_id="";
                         String newamount ="1";
                         String newpromocode_amount="1";
                         String new_mode="wallet";

                         PaymentUserBookingAndroidApi_wallet(booking_id,Payment_id,order_newid,sighnature_id, shop_contact,  user_contact,newamount,newpromocode_amount,new_mode,promocode);

                         Log.d("wallet", wallet);
                         Log.d("object", jsonObject.toString());


                         JSONArray jsonArray = jsonObject.getJSONArray("user");

                         for (int i = 0; i < jsonArray.length(); i++)
                         {
                             JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                             String id = jsonObject1.getString("id");
                             signupamount = jsonObject1.getString("signup_amount");


                             double signup_amount = Double.parseDouble(signupamount);

                             if (signup_amount > 100)
                             {

                                 Signup_lessamount = signup_amount - 100;
                                 Toast.makeText(HistoryAllDetails_Activity.this, String.valueOf(Signup_lessamount), Toast.LENGTH_SHORT).show();
                                 Toast.makeText(HistoryAllDetails_Activity.this, "100 greater then", Toast.LENGTH_SHORT).show();
                                 Log.d("amount", String.valueOf(Signup_lessamount));


                                 //double myamount = Double.parseDouble(Price1) - 100;
                               /* String my_amount=String.valueOf(myamount);
                                 Log.d("price", String.valueOf(myamount));*/

                                 String my_amount = "1";

                                 mode = "wallet";
                                 promocode_amount="1";

                             }

                             Log.d("error", signupamount);
                             // textView.setText("\u20B9"+signupamount);
                             String transaction_id = jsonObject1.getString("transaction_id");
                             String name = jsonObject1.getString("name");
                             String contact = jsonObject1.getString("contact");
                             Log.d("object", jsonObject1.toString());




                             //Add_Wallet_Moneyapi(ID,Type,Payment_id,order_id,sighnature_id);

                         }
                         // payment vali api..........*/

                     }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);

    }

    private void wallet_add(String Type, String User_id, String Amount)
    {

        String url = "https://serviceonway.com/RazorpayApiKey?type="+Type+"&id="+User_id+"&amount="+Amount;

        Log.d("url1", url.toString());
        Toast.makeText(HistoryAllDetails_Activity.this, "order hit", Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                Toast.makeText(HistoryAllDetails_Activity.this, "onResponse aa gya", Toast.LENGTH_SHORT).show();

                Log.d("##responce", response.toString());
                String replace = response.toString().replace("[", "");
                String replace1 = replace.replace("]", "");
                List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
                Log.d("list", myList.toString());
                Log.d("list", "" + myList.size());
                String key = myList.get(0);
                Log.d("k", key);
                order_key = myList.get(1);
                Log.d("order",order_key);
                Toast.makeText(HistoryAllDetails_Activity.this,order_key, Toast.LENGTH_SHORT).show();

                if(order_key!=null)
                {
                    //ye order id get kr rha hu;
                   // startpayment();

                    Intent intent= new Intent(HistoryAllDetails_Activity.this,Paymentgetway_modewalletActivity.class);
                    startActivity(intent);

                    Toast.makeText(HistoryAllDetails_Activity.this, "startpayment", Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences= getSharedPreferences("order",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("order_key",order_key);
                    editor.apply();

                }

                SharedPreferences preferences= getSharedPreferences("order",MODE_PRIVATE);
                 neworder_id=preferences.getString("order_key","");
                 Log.d("order_id",neworder_id);
                Log.d("order_key",order_key);
                String key1 = myList.get(2);
                Log.d("key1", key1.toString());

                try {
                    JSONArray jsonarray = response.getJSONArray(2);
                    JSONObject jsonObject = jsonarray.getJSONObject(0);
                    Log.d("object", jsonObject.toString());
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String contact = jsonObject.getString("contact");
                    String email = jsonObject.getString("email");
                    Log.d("###name", name);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
                //yhi problem hai


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error1", error.toString());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("Content-Type", "application/json; charset=utf-8");
                // Toast.makeText(Maker_Activity.this,hashMap.toString(), Toast.LENGTH_SHORT).show();
                Log.d("maker", hashMap.toString());
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);


    }



  /*  @Override
    public void onPaymentSuccess(String razorpayPaymentID, PaymentData data)
    {

        Mode="wallet";
        Log.e("error",Mode+"mode");
        Transactionid=razorpayPaymentID;
        transaction_id=razorpayPaymentID;
        Log.e("error",Transactionid+"transaction");

        Log.e("error",Shopcontact+"shopcontect");
        // Transactionid=razorpayPaymentID;

        String promoammount="";
        String promocodeamount="1";
        String promocode="0";
        String bookingid1=bookingId;
        String userconnect=connectno;
        String shopconnect= shopcontect1;
        String amount="1";

        try {

           String paymentId = data.getPaymentId();
           String   signature = data.getSignature();
           String  orderId = data.getOrderId();

            Log.d("result",paymentId+","+signature+","+orderId);

            String   ID=User_id;
            String  Type="user";

            String Payment_id =paymentId;
            String order_id =orderId;
            String sighnature_id =signature;

            promocode_amount="1";
            amount="1";

            Add_Wallet_Moneyapi(ID,Type,Payment_id,order_id,sighnature_id);

            //check vali api set reduse 300-100
            walletcheck_blance(ID,Type);

            //amount -100=?
            //finaly payment ...........
            PaymentUserBookingAndroidApi_wallet(booking_id,Payment_id,order_id,sighnature_id, shop_contact,  user_contact,amount,promocode_amount,mode,promocode);

            // chekwallet_Blance(ID,Type);






            //  conformapihit();

        }catch (Exception e)
        {
            e.printStackTrace();
        }




       // Add_Wallet_Moneyapi();



    }*/

    /*@Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }*/



  /*  private void Add_Wallet_Moneyapi(String ID,String Type,String Payment_id,String Order_id,String Sighnature_id)
    {



        String url="https://serviceonway.com/Add_Wallet_Money_Android_Api?id="+ID+"&type="+Type+"&pid="+Payment_id+"&oid="+Order_id+"&sg="+Sighnature_id;

        Log.d("url",url);

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("responce",response);

                if(response.contains("done"))
                {
                    Toast.makeText(HistoryAllDetails_Activity.this, "done", Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(HistoryAllDetails_Activity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.d("responce_error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(request);






    }*/

    //payment error show..................


}
