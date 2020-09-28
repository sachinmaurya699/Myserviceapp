package com.sossolution.serviceonway.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sossolution.serviceonway.Adapter.ServiceAdapter;
import com.sossolution.serviceonway.Interface.CustomItemService;
import com.sossolution.serviceonway.Interface.MyCustomInterface;
import com.sossolution.serviceonway.R;
import com.sossolution.serviceonway.Class.Service;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sossolution.serviceonway.Activity.Map2_Activity.LOCATION_SETTINGS_REQUEST;

public class Service_Activity extends AppCompatActivity implements CustomItemService, MyCustomInterface
{

    RecyclerView recyclerView;
    CustomItemService customItemService;
    GridLayoutManager gridLayoutManager;
    ServiceAdapter serviceAdapter;
    List<Service> datalistservice;
    GridLayout gridLayout;
    String Car,Maker,Model,Fuel;
    Button buttonselect,btndeselect;
    TextView textView;
    ProgressBar progressBar;
    Context context;
    List<Integer> idsar;
    String item="";
    String p1;
    String s1;
    String s2;
    ImageView back;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicenew);

        buttonselect=findViewById(R.id.nexts);
        progressBar=findViewById(R.id.progressBar_service);
        progressBar.setVisibility(View.VISIBLE);
        mapfind();
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();

            }
        });
        header=findViewById(R.id.header);
        header.setText("Select Service");


         idsar = new ArrayList<Integer>();

         buttonselect.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Service_Activity.this,Map2_Activity.class);
                startActivity(intent);

            }
        });
        buttonselect.setVisibility(View.GONE);
        recyclerView=findViewById(R.id.recyclerviewservice);
        recyclerView.setHasFixedSize(true);
        datalistservice=new ArrayList<>();

        recyclerView.setLayoutManager(new
                GridLayoutManager(Service_Activity.this, 2,GridLayoutManager.VERTICAL,false));

        //vechicle vechle activity
        SharedPreferences mPrefs1 = getSharedPreferences("vec", MODE_PRIVATE);
         p1 =mPrefs1.getString("car","");


        Car=p1;
        //Car=p2;


        SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
         s1=mPrefs.getString("bike","");
         Log.d("makritem",s1.toString());

        Maker=s1;
        Log.d("today2",s1.toString());

        SharedPreferences mPrefs2 = getSharedPreferences("model item", MODE_PRIVATE);
        s2=mPrefs2.getString("model","");
        Model=s2;
        Log.d("value",s2.toString());
        if(s2.contains("Splendor+"))
        {
            Model="Splendor%2B";
        }
       // Model=s2;

        SharedPreferences mPref = getSharedPreferences("Oilitem", MODE_PRIVATE);
        String sp=mPref.getString("Oil","");
        Fuel=sp;
        Log.d("today1",Fuel.toString());

        hitapiService(Car,Maker,Model,Fuel);

    }

    private void mapfind()
    {

        LocationRequest mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);

        Log.d("mlocation",mLocationRequest.toString());

        LocationSettingsRequest.Builder settingsBuilder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        Log.d("settingsBuilder",settingsBuilder.toString());
        settingsBuilder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                .checkLocationSettings(settingsBuilder.build());

        Log.d("newlocation",result.toString());



        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>()
        {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task)
            {
                try {
                    LocationSettingsResponse response =
                            task.getResult(ApiException.class);
                } catch (ApiException ex) {
                    switch (ex.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException =
                                        (ResolvableApiException) ex;
                                resolvableApiException
                                        .startResolutionForResult(Service_Activity.this,
                                                LOCATION_SETTINGS_REQUEST);
                            } catch (IntentSender.SendIntentException e) {

                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                            break;
                    }
                }
            }
        });



    }

    private void hitapiService(String Car,String Maker,String Model,String Fuel)
    {

        String url="https://serviceonway.com/Get_All_Service_Details?veh="+Car+"&maker="+Maker+"&model="+Model+"&fuel="+Fuel;
          Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("responce",response.toString());
                progressBar.setVisibility(View.GONE);

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        String Id=jsonObject.getString("id");
                        String Maker=jsonObject.getString("maker");
                        String Model=jsonObject.getString("model");
                        String Servicess=jsonObject.getString("service");
                        String Price=jsonObject.getString("charges"+"");
                        String Logo=jsonObject.getString("logo");

                        datalistservice.add(new Service(Logo,Price,Servicess,Id));

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.e("error",e.toString());
                        }

                }
                serviceAdapter=new ServiceAdapter(getApplicationContext(),
                        (ArrayList<Service>) datalistservice, new CustomItemService() {
                    @Override
                    public void onItemClickService(Service service1)
                    {

                    }

                    @Override
                    public void onItemCheck(String string)
                    {

                        String s1 = string;
                        if (s1.length() > 0)
                        {
                            buttonselect.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            buttonselect.setVisibility(View.GONE);

                        }




                        SharedPreferences mPrefs = getSharedPreferences("map", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("mp",s1);
                        editor.apply();

                    }

                    @Override
                    public void onItemUncheck(String string) {
                      }
                });
                recyclerView.setAdapter(serviceAdapter);


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
        Volley.newRequestQueue(Service_Activity.this).add(jsonArrayRequest);

    }

    @Override
    public void onItemClickService(Service service1) {

      }

    @Override
    public void onItemCheck(String string) {

    }

    @Override
    public void onItemUncheck(String string)
    {

    }

    @Override
    public void sendData(String str) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
               // recived_message.setText(str);

            }
        });

    }
}
