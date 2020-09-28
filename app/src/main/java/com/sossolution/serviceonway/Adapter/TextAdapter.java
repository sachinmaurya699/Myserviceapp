package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sossolution.serviceonway.R;

public class TextAdapter extends BaseAdapter{

    Context context;
    String [] text;
    int []image;

    public TextAdapter(Context context, String[] text, int[] image)
    {
        this.context = context;
        this.text = text;
        this.image = image;
    }

    @Override
    public int getCount() {
        return text.length;
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

      //  view= LayoutInflater.from(context).inflate(R.layout.about,null);
        TextView country = view.findViewById(R.id.textViewlist);
        ImageView icon = view.findViewById(R.id.imageViewlist);
        country.setText(text[i]);
        icon.setImageResource(image[i]);

        return view;
    }
}
