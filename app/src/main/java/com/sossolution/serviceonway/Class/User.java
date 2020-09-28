package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class User{

    //sharedprefrance
    private String Phone;
    private String Name;
    private String Email;
    private String Address;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;


    public User(Context context)
    {
        this.context=context;
        //factory method
        sharedPreferences=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }
    public String getName()
    {
        Name=sharedPreferences.getString("userdata","");
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
        sharedPreferences.edit().putString("userdata","").apply();
    }

    public String getEmail(String email)
    {
        Email=sharedPreferences.getString("userdata","");

        return Email;
    }

    public void setEmail(String email)
    {
        this.Email = Email;
        sharedPreferences.edit().putString("userdata",email).apply();
    }

    public String getAddress()
    {
        Address=sharedPreferences.getString("userdata","");
        return Address;
    }

    public void setAddress(String address)
    {
        this.Address = address;
        sharedPreferences.edit().putString("userdata",address).apply();
    }


    public void removeUser()
    {
        sharedPreferences.edit().remove("userdata").apply();

        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

    }

    public void SaveDatils(String name, String email, String address)
    {

        Toast.makeText(context,name+"save name", Toast.LENGTH_SHORT).show();
        Toast.makeText(context,email+"save email", Toast.LENGTH_SHORT).show();
        Toast.makeText(context,address+"save address", Toast.LENGTH_SHORT).show();
        sharedPreferences.edit().putString("userdata",Name).apply();
        sharedPreferences.edit().putString("userdata",Email).apply();
        sharedPreferences.edit().putString("userdata",Address).apply();

    }
    //gettter method.....
    public String getPhone()
    {
       Phone=sharedPreferences.getString("userdata","");

        return Phone;
    }
    //setter method......
    public void setPhone(String Phone)
    {
        this.Phone = Phone;
        sharedPreferences.edit().putString("userdata",Phone).apply();

    }


}
