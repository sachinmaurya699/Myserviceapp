package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
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
import com.google.android.material.tabs.TabLayout;
import com.sossolution.serviceonway.Interface.CustomItem2;
import com.sossolution.serviceonway.Interface.CustomItemClick;
import com.sossolution.serviceonway.Class.Newmodel;
import com.sossolution.serviceonway.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model_Activity extends AppCompatActivity implements CustomItem2
{
    TextView textView;
    CustomItemClick click;
    Fragment frag1;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    MakeAdapter makeAdapter;
    List<Newmodel> datalistmake;
    GridLayout gridLayout;
    String VEH;
    String Honda;
    String make;
    TabLayout tabLayout;
    String qty;
    ProgressBar progressBar;
    String p1;
    String s1;
    ImageView back;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_newdesign);
        back=findViewById(R.id.back);
        header=findViewById(R.id.header);
        header.setText("Select Model");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
       // getSupportActionBar().setTitle("Select Model");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar=findViewById(R.id.progressBar_model);
        progressBar.setVisibility(View.VISIBLE);
//        recyclerview
        recyclerView=findViewById(R.id.recyclerviewmodemodel);
        recyclerView.setHasFixedSize(true);


         tabLayout=findViewById(R.id.tabtwo);
        // String value = getIntent().getExtras().getString("value");
       // tabLayout.getTabAt(0).setText("bmw");
        //TabLayout.Tab firstTab = tabLayout.newTab();
        //firstTab.setText("tab");
        //;
        SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
         s1=mPrefs.getString("bike","");
        Log.d("maker",s1);


        tabLayout.getTabAt(0).setText(s1);
        datalistmake=new ArrayList<>();

        //one activity to data fech

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2,GridLayoutManager.VERTICAL,false));
        // gridLayout = findViewById(R.id.gridview1);

        SharedPreferences mPrefs1 = getSharedPreferences("vec", MODE_PRIVATE);
        VEH=mPrefs1.getString("car","");
         Log.d("vec","vac"+p1);

        //home fragment
      /*  SharedPreferences pref = getSharedPreferences("car",Context.MODE_PRIVATE);
        VEH =pref.getString("car","");
       Log.d("car","car"+p1);*/

        //VEH=p1;
        //VEH=p2;
        Honda=s1;
        hitapimodel(VEH,Honda);
        //second adapter data



    }


    private void hitapimodel(final String VEH, String Honda)
    {
        String url="https://serviceonway.com/Get_All_Model_Details?veh="+VEH+"&maker="+Honda;
         Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String model=jsonObject.getString("model");
                        datalistmake.add(new Newmodel(model));
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.d("error",e.toString());

                    }
                }
                makeAdapter=new MakeAdapter(getApplicationContext(),
                        (ArrayList<Newmodel>) datalistmake, new CustomItem2() {
                    @Override
                    public void onItem(Newmodel newmodel)
                    {
                        SharedPreferences mPrefs = getSharedPreferences("model item", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("model", newmodel.getModel());
                        editor.apply();

                        if(!VEH.equals("bike"))
                        {
                            Intent intent= new Intent(Model_Activity.this, Fuel_Activity.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Intent intent= new Intent(Model_Activity.this,Service_Activity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
                recyclerView.setAdapter(makeAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                hashMap.put ("Content-Type", "application/json; charset=utf-8");
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(Model_Activity.this).add(jsonArrayRequest);


    }

    @Override
    public void onItem(Newmodel newmodel)
    {

    }

}
