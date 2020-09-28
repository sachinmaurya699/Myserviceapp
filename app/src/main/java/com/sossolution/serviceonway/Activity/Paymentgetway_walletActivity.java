package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;
import com.sossolution.serviceonway.Class.User;
import com.sossolution.serviceonway.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Paymentgetway_walletActivity extends AppCompatActivity implements PaymentResultWithDataListener
{
    String number;
    String email;
    String payment;
    RazorpayClient razorpayClient;
    String paymentId;
    String orderId;
    Checkout chekout;
    String User_id;
    String Amount;
    String Type;
    String order_key;
    String order_id;
    String signature;
    String message;
    int orderkey;
    String s1;
    String price;

    String booking_id="";
    String transaction_id="";
    String order_id1="";
    String sighnature;
    String shop_contact;
    String user_contact;
    String amount;
    String promocode_amount="";
    String mode="";
    String promocode="";

    String order_key1;
    String order;
    String Mode;
    String email1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentgetway_wallet);

        //user number........................
        SharedPreferences sharedPreferences6 = getSharedPreferences("myvalue", MODE_PRIVATE);
        number = sharedPreferences6.getString("id1", "");
        Log.d("value1",number);


        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences.getString("unicid", "");
        Log.d("user",User_id);

        SharedPreferences preferences=getSharedPreferences("My booking id",MODE_PRIVATE);
        booking_id =preferences.getString("getbookingid","");
        Log.d("booking_id",booking_id);



        SharedPreferences preferences1= getSharedPreferences("key",MODE_PRIVATE);
        order  =preferences1.getString("order_key","");
        Log.d("order_id",order+"v1");


        SharedPreferences prefere= getSharedPreferences("Email",MODE_PRIVATE);
        email1=prefere.getString("get_email","");
        Log.d("getemail",email1);


        SharedPreferences preferences3=getSharedPreferences("shop_contact",MODE_PRIVATE);
        shop_contact= preferences3.getString("shop","");
        Log.d("shop_contact",shop_contact);


        SharedPreferences preferences4= getSharedPreferences("user",MODE_PRIVATE);
        user_contact= preferences4.getString("user_contact","");
        Log.d("user_contact",user_contact);

        mode="online";

        User user= new User(Paymentgetway_walletActivity.this);

        String email=user.getEmail("userdata");
        Log.d("emial11",email+"value");



        // Checkout.preload(getApplicationContext());

         /*Intent intent = getIntent();
         price = intent.getStringExtra("price");
         Log.d("price_a",price);*/


        price="1";

        payment_gatway();


    }

    private void payment_gatway()
    {

       // Toast.makeText(this, "payment getway", Toast.LENGTH_SHORT).show();
        final Activity activity = this;
        final   Checkout  chekout = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Service on way");
            options.put("description", "My Booking payment");
            options.put("image", "https://serviceonway.com/UploadedFiles/ServiceProviderImages/logo3-plane1.png");
            options.put("currency", "INR");
            options.put("order_id",order.replace("\"", ""));
            double total = Double.parseDouble( price);
            total = total * 100;
            options.put("amount", total);
            Log.e("amount_value", total + "amount");
            options.put("prefill.email",email1);
            options.put("prefill.contact",number);
            Log.d("options", options.toString());


            // options.put("prefill", preFill);
            // Log.d("prefill", preFill.toString());
            chekout.open(activity, options);

        } catch (Exception e)
        {
            e.printStackTrace();
            Log.e("error1", e.toString());
            // Toast.makeText(this, "error"+e.toString(), Toast.LENGTH_SHORT).show();

        }






    }

    @Override
    public void onPaymentSuccess(String s, PaymentData data)
    {
        try {

            paymentId = data.getPaymentId();
            signature = data.getSignature();
            orderId = data.getOrderId();

            Log.d("result",paymentId+","+signature+","+orderId);

            String   ID=User_id;
            String  Type="user";
            String Payment_id=paymentId;
            String order_id=orderId;
            String sighnature_id=signature;
            promocode_amount="1";
            amount="1";



             Add_Wallet_Moneyapi(ID,Type,Payment_id,order_id,sighnature_id);

            //PaymentUserBookingAndroidApi_wallet(booking_id,Payment_id,order_id,sighnature_id, shop_contact,  user_contact,amount,promocode_amount,mode,promocode);


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void Add_Wallet_Moneyapi(String ID,String Type,String Payment_id,String Order_id,String Sighnature_id)
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
                   // Toast.makeText(Paymentgetway_walletActivity.this, "done", Toast.LENGTH_SHORT).show();
                    StyleableToast.makeText(Paymentgetway_walletActivity.this,"Add amount successful",R.style.exapleToast).show();


                    Intent intent= new Intent(Paymentgetway_walletActivity.this,MainActivity.class);
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
        Volley.newRequestQueue(Paymentgetway_walletActivity.this).add(request);

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData)
    {

        try {
           // Toast.makeText(this, "error"+s, Toast.LENGTH_SHORT).show();
            Log.d("error",s);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}