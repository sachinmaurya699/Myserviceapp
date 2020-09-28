package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Servicename;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;

public class Includeadapter extends RecyclerView.Adapter<Includeadapter.Includeviewholder>{

    private Context context1;
    private ArrayList<Servicename> datalistinclude1;


    public Includeadapter(Context context, ArrayList<Servicename> datalistinclude)
    {
        this.context1=context;
        this.datalistinclude1=datalistinclude;

    }
    @NonNull
    @Override
    public Includeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.include,parent,false);
        return new Includeviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Includeviewholder holder, int position)
    {
         String totalPrice = "";

         //privese work......
         Servicename servicename=datalistinclude1.get(position);
         String shopname=servicename.getName();
         String price=servicename.getPrice();

         holder.textView1.setText(shopname);
         holder.textView2.setText("\u20B9"+price);
       // Toast.makeText(context1,shopname.toString()+price.toString()+"include data", Toast.LENGTH_SHORT).show();
         grandTotal();


    }
    public int grandTotal()
    {
        int totalPrice = 0;

        for (int i = 0; i < datalistinclude1.size(); i++)
        {
            totalPrice += Double.parseDouble(datalistinclude1.get(i).getPrice());
        }
        return totalPrice;
    }

    @Override
    public int getItemCount()
    {
        return datalistinclude1.size();
    }

    public class Includeviewholder  extends RecyclerView.ViewHolder{

        TextView textView1,textView2;

        public Includeviewholder(@NonNull View itemView)
        {
            super(itemView);
            textView1=itemView.findViewById(R.id.servicename);
            textView2=itemView.findViewById(R.id.textprice);

        }
    }
}
