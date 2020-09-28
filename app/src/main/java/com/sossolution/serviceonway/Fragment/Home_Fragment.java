package com.sossolution.serviceonway.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.sossolution.serviceonway.Activity.Maker_Activity;
import com.sossolution.serviceonway.Activity.Vehicle_Activity;
import com.sossolution.serviceonway.Adapter.CustomAdapter;
import com.sossolution.serviceonway.Adapter.CustomAdapter2;
import com.sossolution.serviceonway.Adapter.SliderAdapter;
import com.sossolution.serviceonway.Class.Home_bike_data;
import com.sossolution.serviceonway.Class.Home_car_data;
import com.sossolution.serviceonway.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment
{

   // private HomeAdapter homeAdapter;
   // private HomeAdapterSecond homeAdapterSecond;

    private  List<Home_car_data> datalist_carhome;
    private List<Home_bike_data>datalistsecond;
    private GridLayoutManager gridLayoutManager;

    private String Car;
    private String Bike;
    private Button button;
    ImageView imageView;
    GridView gridView,gridView2;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    //scrolling view pager create
    private static ViewPager mPager;
    private  CirclePageIndicator indicator1;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    ImageView imagenotification;


    private String [] urls ={"https://serviceonway.com/files/images/slider1.jpg","https://serviceonway.com/files/images/slider2.jpg","https://serviceonway.com/files/images/slider3.jpg"};


    private String []careservice={"Express","Dent/Scratch","Detailing","Car Posilsh",
            "Repainting","Oil Change","Complete car","Ac Service"
            };
    private String []bikeservice={"General Service","Flat Tyre","Brake Service",
            "Bike Ceramic","Accelerator Service","Locked Service","Clutch service",
            "Engine Service"};
     private int [] bike ={R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,
            R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png};
     private int [] image={R.drawable.package_car,R.drawable.package_car,R.drawable.package_car,R.drawable.package_car,
            R.drawable.package_car,R.drawable.package_car,R.drawable.package_car,R.drawable.package_car
            };

    //first method........
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.d("show","onCreateView");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.home_fragmentdesign, container, false);
        mPager = view.findViewById(R.id.imageView111);
        gridView=view.findViewById(R.id.grid_car);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                String car="car";
                preferences = getActivity().getSharedPreferences("vec",Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = preferences.edit();
                edt.putString("car", car);
                edt.apply();
                Intent intent= new Intent(getActivity(), Maker_Activity.class);
                startActivity(intent);

/*
                String car="car";
                preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
                editor=preferences.edit();
                editor.putString("car",car);
                editor.apply();*/
               /* Intent intent= new Intent(getActivity(),Vehicle_Activity.class);
                startActivity(intent);*/


            }
        });
        gridView2=view.findViewById(R.id.grid_bike);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                String bike="bike";
                preferences = getActivity().getSharedPreferences("vec",Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = preferences.edit();
                edt.putString("car", bike);
                edt.apply();
                Intent intent= new Intent(getActivity(), Maker_Activity.class);
                startActivity(intent);

            }
        });

        CustomAdapter customAdapter= new CustomAdapter(getActivity(),image,careservice);
        CustomAdapter2 customAdapter2= new CustomAdapter2(getActivity(), bike,bikeservice);
        gridView2.setAdapter(customAdapter2);
        gridView.setAdapter(customAdapter);

        //horizo ntal recyclerview

        indicator1=view.findViewById(R.id.indicator);
        imageView=view.findViewById(R.id.imageservice);

      //((MainActivity)getActivity()).getSupportActionBar().hide();

        init();
        button=view.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("show","onClick");
                Intent intent = new Intent(getActivity(),Vehicle_Activity.class);
                startActivity(intent);
                //Intent intent = new Intent(getActivity(),hit_);


            }
        });
        Car="car_package";
        Bike="bike_package";
        //hitapi_car(Car);
       // hitapi_bike(Bike);
        datalist_carhome= new ArrayList<Home_car_data>();
        datalistsecond= new ArrayList<>();
        return view;
    }
    private void init()
    {

        Log.d("show","init");

      mPager.setAdapter(new SliderAdapter(getActivity(),urls));
      indicator1.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator1.setRadius(5* density);
        NUM_PAGES = urls.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable()
        {
            public void run() {
                if (currentPage == NUM_PAGES)
                {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        // Pager listener over indicator

        indicator1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("show","onPageScrolled");
                currentPage = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

   /* private void hitapi_bike(String Bike)
    {

        Log.d("show","hitapi_bike");
        String url="https://serviceonway.com/get_home_services?table="+Bike;

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                Log.d("showresponce",response.toString());
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        Home_bike_data home= new Home_bike_data();
                        home.setName(jsonObject.getString("name"));
                        home.setImage(jsonObject.getString("images"));
                        home.setPrice(jsonObject.getString("price"));
                        datalistsecond.add(home);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.e("error",e.toString());

                    }
                }
                homeAdapterSecond= new HomeAdapterSecond(getContext(), (ArrayList<Home_bike_data>)datalistsecond);
               // recyclerView2.setAdapter(homeAdapterSecond);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error",error.toString());


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                Log.d("show","getParams");
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

    }*/

    /*private void hitapi_car(String Car)
   {

       Log.d("show","hitapi_car");
       String url="https://serviceonway.com/get_home_services?table="+Car;

       JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
               null, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response)
           {

             for(int i=0;i<response.length();i++)
             {
                 try {
                     JSONObject jsonObject=response.getJSONObject(i);
                      String name=jsonObject.getString("name");
                     String image=jsonObject.getString("images");
                     String price=jsonObject.getString("price");
                     datalist_carhome.add(new Home_car_data(name,image,price));
                 } catch (JSONException e)
                 {
                     e.printStackTrace();
                     Log.e("error",e.toString());
                      }
             }
             homeAdapter= new HomeAdapter(getContext(), (ArrayList<Home_car_data>) datalist_carhome);
             //recyclerView.setAdapter(homeAdapter);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error)
           {
               Log.d("show","onErrorResponse");

           }
       }){

           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               HashMap<String,String> hashMap= new HashMap<String, String>();
               return hashMap;

           }
       };
       jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
               DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
               2,
               DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       jsonArrayRequest.setShouldCache(false);
       Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

   }*/
}
