package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Payment;
import com.sossolution.serviceonway.Adapter.HistoryAdapter;
import com.sossolution.serviceonway.Adapter.Wallet_historyadapter;
import com.sossolution.serviceonway.Class.HistoryAllDetails;
import com.sossolution.serviceonway.Class.Historyitem;
import com.sossolution.serviceonway.Class.User;
import com.sossolution.serviceonway.Class.Wallet_history;
import com.sossolution.serviceonway.Interface.Clickbutton;
import com.sossolution.serviceonway.Interface.Myinterfacehistary;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History_Activity extends AppCompatActivity implements Clickbutton{

    RecyclerView recyclerView;
    Wallet_historyadapter wallet_historyadapter;
    List<Wallet_history> walletlist;
    LinearLayoutManager layoutManager;
    ImageView back;
    TextView header;
    String  User_id;
    String Type;
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history_);
         dialog= new ProgressDialog(History_Activity.this);


        back=findViewById(R.id.back);
        //dialog = new Dialog(this);
        header=findViewById(R.id.header);
        header.setText("History wallet Details");
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading.....");
        dialog.show();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(History_Activity.this,LinearLayoutManager.VERTICAL,true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        walletlist= new ArrayList<>();
        Collections.reverse(walletlist);

        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences.getString("unicid", "");
        Log.d("user",User_id);
        Type="user";

        historyapihit(User_id,Type);

    }

    private void historyapihit(String ID, String Type)
    {

        String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+ID+"&type="+Type;
        Log.d("url",url);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                dialog.dismiss();
                Log.d("onresponce",response.toString());

                try {
                    JSONObject jsonObject= response.getJSONObject("map");
                    String wallet =jsonObject.getString("wallet");
                    Log.d("wallet",wallet);
                    JSONArray jsonArray=jsonObject.getJSONArray("user");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        Wallet_history wallet_history= new Wallet_history();
                        wallet_history.setTransation_id(jsonObject1.getString("transaction_id"));
                        wallet_history.setDate(jsonObject1.getString("date"));
                        //  wallet_history.setAmount(jsonObject1.getString("amount"));
                        walletlist.add(wallet_history);
                        Log.d("object",jsonObject1.toString());

                    }
                    wallet_historyadapter= new Wallet_historyadapter(History_Activity.this,walletlist, new Myinterfacehistary() {
                        @Override
                        public void newitem(Wallet_history wallet_history)
                        {
                           /* Intent intent = new Intent(History_Activity.this, HistoryAllDetails_Activity.class);
                            startActivity(intent);
                        */}
                    });
                    Log.d("value",wallet_historyadapter.toString());
                    recyclerView.setAdapter(wallet_historyadapter);
                    wallet_historyadapter.notifyDataSetChanged();

                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d("error1",e.toString());
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
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(false);
        Volley.newRequestQueue(History_Activity.this).add(jsonObjectRequest);


    }


    @Override
    public void senddata(Historyitem historyitem) {

    }

    @Override
    public void sendid(String historyitem) {

    }
}
