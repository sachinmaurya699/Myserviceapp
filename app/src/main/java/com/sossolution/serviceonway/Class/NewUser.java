package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.content.SharedPreferences;

public class NewUser{


    String name;
    String phone_no;
    String email;
    String address;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public NewUser(String name, String phone_no, String email, String address) {
        this.name = name;
        this.phone_no = phone_no;
        this.email= email;
        this.address = address;
    }

    public NewUser()
    {

    }

    public String getname(String string) {

        name=sharedPreferences.getString("user","");
        return name;

    }

    public void setname(String name) {
        this.name = name;
        sharedPreferences.edit().putString("user","").apply();

    }

    public String getPhone_no() {

        phone_no=sharedPreferences.getString("user","");
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
        sharedPreferences.edit().putString("user","").apply();
    }

    public String getemail(String string) {

        email=sharedPreferences.getString("user","");
        return email;
    }

    public void setemail(String email)
    {
        this.email = email;
        sharedPreferences.edit().putString("user","").apply();
    }

    public String getaddress(String string)
    {
        address=sharedPreferences.getString("","");
        return address;

    }

    public void setaddress(String address)
    {
        this.address = address;
        sharedPreferences.edit().putString("user","").apply();
    }
    public  void savedatil()
    {
        sharedPreferences.edit().putString("user",name).apply();
        sharedPreferences.edit().putString("user",phone_no).apply();
        sharedPreferences.edit().putString("user",email).apply();
        sharedPreferences.edit().putString("user",address).apply();
    }


}
