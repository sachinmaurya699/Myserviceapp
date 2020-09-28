package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sossolution.serviceonway.R;

public class CustomAdapter2 extends BaseAdapter {

    Context context;
    String [] bikeservice;
    int []bike;
    LayoutInflater inflater;

    public CustomAdapter2(Context context,int [] bike,String [] bikeservice)
    {
        this.context=context;
        this.bikeservice=bikeservice;
        this.bike=bike;
        inflater=(LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return bikeservice.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.bike, viewGroup, false);
        TextView country = view.findViewById(R.id.grid_text);
        ImageView icon = view.findViewById(R.id.grid_image);
        country.setText(bikeservice[i]);
        icon.setImageResource(bike[i]);
        return view;
    }

}
