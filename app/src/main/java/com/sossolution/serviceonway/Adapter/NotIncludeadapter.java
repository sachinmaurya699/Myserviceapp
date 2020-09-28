package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Notservice;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;

public class NotIncludeadapter extends RecyclerView.Adapter<NotIncludeadapter.Notviewholder>
{
    private Context context1;
    private ArrayList<Notservice> datalistnot;

    public NotIncludeadapter(Context context, ArrayList<Notservice> datalistnot)
    {
        this.context1=context;
        this.datalistnot=  datalistnot;
    }

    @NonNull
    @Override
    public Notviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notinclude,parent);

        return new Notviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotIncludeadapter.Notviewholder holder, int position)
    {
       Notservice notservice=datalistnot.get(position);
       String s1=notservice.getService();
       holder.textView.setText(s1);


    }

    @Override
    public int getItemCount() {
        return datalistnot.size();
    }

    public class Notviewholder extends RecyclerView.ViewHolder{

        TextView textView;

        public Notviewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.service11);

        }
    }
}
