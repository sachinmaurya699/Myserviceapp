package com.sossolution.serviceonway.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sossolution.serviceonway.Interface.CustomItem2;

import com.sossolution.serviceonway.Class.Newmodel;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;


class MakeAdapter extends RecyclerView.Adapter<MakeAdapter.Makeviewholder>
{
    private Context context2;
    private ArrayList<Newmodel> datalistmake;
    private CustomItem2 customItem2;


    public MakeAdapter(Context context2, ArrayList<Newmodel> datalistmake, CustomItem2 customItem2) {
        this.context2 = context2;
        this.datalistmake = datalistmake;
        this.customItem2 = customItem2;
    }

    @NonNull
    @Override
    public MakeAdapter.Makeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MakeAdapter.Makeviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakeAdapter.Makeviewholder holder, int position) {


        Newmodel newmodel= datalistmake.get(position);
        String text=newmodel.getModel();
        holder.textView.setText(text);

        holder.bind(datalistmake.get(position),customItem2);

    }

    @Override
    public int getItemCount() {
        return datalistmake.size();
    }

    public class Makeviewholder extends RecyclerView.ViewHolder{

        TextView textView;
        public Makeviewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.textviewmodel);
        }

        public void bind(final Newmodel newmodel, final CustomItem2 customItem2) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    customItem2.onItem(newmodel);
                }
            });
        }
    }
}
