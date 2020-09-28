package com.sossolution.serviceonway.Fragment;



import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.Activity.AboutUs_Activity;
import com.sossolution.serviceonway.Activity.ContectUs_Activity;
import com.sossolution.serviceonway.Activity.History_Activity;
import com.sossolution.serviceonway.Activity.Login_Activity;
import com.sossolution.serviceonway.Activity.MainActivity;
import com.sossolution.serviceonway.Activity.Maker_Activity;
import com.sossolution.serviceonway.Activity.Profile_Activity;
import com.sossolution.serviceonway.Activity.Refer_earn_Activity;
import com.sossolution.serviceonway.Class.User;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class Profile_Fragment extends Fragment{

    ListView listView;
    Button button;
    String[] item = {"Profile", "My history", "Contact Us", "About Us", "Refer and Earn"
            , "Logout"};
    String ID;
    ImageView imageView;
    ProgressBar progressBar;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    Uri Image_uri;
    TextView user_name;
    SharedPreferences preferences;
    Bitmap bitmap;
   ProgressDialog dialog;
    String file="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("myid", MODE_PRIVATE);
        ID=sharedPreferences.getString("unicid","");




        hitapiimage(ID);
        View view = inflater.inflate(R.layout.profile_fragmentnew, container, false);
     //   ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //SharedPreferences preferences=getActivity().getSupportFragmentManager();
        //SharedPreferences preferences= getActivity().getSharedPreferences("username",MODE_PRIVATE);
          SharedPreferences preferences=getActivity().getSharedPreferences("username",MODE_PRIVATE);
          String s1=preferences.getString("name","");
        user_name=view.findViewById(R.id.user_name);
        user_name.setText(s1);








       // String s1= getSharedPreferences("username",MODE_PRIVATE);


       // user_name.setText();
        imageView=view.findViewById(R.id.imageViewcircle);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"),SELECT_PICTURE);
                upload();


            }
        });


        progressBar=view.findViewById(R.id.progressBar_fragment);
        progressBar.setVisibility(View.VISIBLE);
        listView = view.findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if (i == 0) {
                    Intent intent = new Intent(getActivity(), Profile_Activity.class);
                    startActivity(intent);
                } else if (i == 5)
                {

                    User user = new User(getContext());
                    user.removeUser();
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                    getActivity().finish();

                   /* SharedPreferences sharedPreferences= getActivity().getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    String login="0";
                    editor.putString("id",login);*/
                    /* *//*new User(getActivity()).removeUser();
                    getActivity().finish();*//*
                    Intent intent= new Intent(getActivity(), Logout_Activity.class);
                    startActivity(intent);*/



                } else if (i == 1) {
                    /*new User(getActivity()).removeUser();
                    getActivity().finish();*/
                    Intent intent = new Intent(getActivity(), History_Activity.class);
                    startActivity(intent);




                } else if (i == 2) {
                    /*new User(getActivity()).removeUser();
                    getActivity().finish();*/
                    Intent intent = new Intent(getActivity(), ContectUs_Activity.class);
                    startActivity(intent);




                } else if (i == 3) {
                    /*new User(getActivity()).removeUser();
                    getActivity().finish();*/
                    Intent intent = new Intent(getActivity(), AboutUs_Activity.class);
                    startActivity(intent);

                }else if(i==4)
                {
                    Intent intent = new Intent(getActivity(), Refer_earn_Activity.class);
                    startActivity(intent);
                }


            }
        });

        return view;
    }

    private void newapi(String name,String contact,String email,String address,String user_id ,String file)
    {

        String s1="https://serviceonway.com/update_User_img?name="+name+"&contact="+contact+"&email="+email+"&address="+address+"&user_id="+user_id+"&file="+file;
       Log.d("s1",s1.toString());

        StringRequest request= new StringRequest(Request.Method.POST,s1, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)

            {
                Log.d("responce",response);

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

                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(getActivity()).add(request);


    }

    private void upload()
    {






    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==SELECT_PICTURE && resultCode == RESULT_OK && data != null)
        {
            Image_uri = data.getData();

            try {
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), resultUri);
                dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Uploading, please wait...");
                dialog.show();
                 bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),Image_uri);
                //converting image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                 file = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                if(file!=null)
                {
                    String name="sk";
                    String contact="8601502970";
                    String email="sachin@123";
                    String address="gkp";
                    String user_id="31";

                    newapi(name,contact,email,address,user_id,file);

                    dialog.dismiss();
                }

                Log.d("bitmap", bitmap.toString());
                   imageView.setImageBitmap(bitmap);




            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    private void hitapiimage(String ID)
    {

        String url = "https://serviceonway.com/GetUserProfileAndroidApi?id="+ID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String image=jsonObject.getString("image");
                    Picasso.get()
                            .load("https://www.serviceonway.com/UploadedFiles/UsersImages/"+image)
                            .into(imageView);
                    //imageView.setImageResource(Integer.parseInt(("https://www.serviceonway.com/UploadedFiles/UsersImages/"+image)));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);


    }


    // method for bitmap to base64

}
