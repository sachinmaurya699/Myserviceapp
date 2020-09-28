package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sossolution.serviceonway.R;

public class CustomAdapter extends BaseAdapter{

   private Context context;
   private   String[] carservice;
   private int [] image;
   private LayoutInflater inflater;


    public CustomAdapter(Context context, int[] image,String[] carservice)
    {
        this.context = context;
        this.image = image;
        this.carservice=carservice;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return image.length;
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
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        view = inflater.inflate(R.layout.bike, viewGroup, false);
        TextView country = view.findViewById(R.id.grid_text);
        ImageView icon = view.findViewById(R.id.grid_image);
        country.setText(carservice[i]);
        icon.setImageResource(image[i]);
        return view;

    }
}
