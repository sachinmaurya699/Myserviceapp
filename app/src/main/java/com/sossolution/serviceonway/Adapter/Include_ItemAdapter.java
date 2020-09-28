package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Include_item;
import com.sossolution.serviceonway.Class.Notinclude_item;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;
import java.util.List;

public class Include_ItemAdapter extends RecyclerView.Adapter<Include_ItemAdapter.Myviewholder>
   {
      private ArrayList<Include_item> include_itemslist;
      private Context context;


       public Include_ItemAdapter(Context context1,ArrayList<Include_item> include_itemslist1)
       {
           this.context=context1;
           this.include_itemslist=include_itemslist1;

       }

       @NonNull
       @Override
       public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
       {
           View view= LayoutInflater.from(context).inflate(R.layout.include_item,parent,false);

           return new Myviewholder(view);
       }

       @Override
       public void onBindViewHolder(@NonNull Myviewholder holder, int position)
       {
           Include_item include_item=include_itemslist.get(position);
           String name=include_item.getName();
           String price=include_item.getPrice();
           Log.d("price",price);
           Log.d("new1","v1"+name);
           //new idea
           String[] s1 = name.split(",");


           String bookingdata = "";
           String[] bookingdata1;
           String service_name = "";
           String service_price = "";
           String s2 = "";
           String myprice = "";

           //first for loop.......................
           for (int j = 0; j < s1.length; j++)
           {
               bookingdata1 = s1[j].split("=");
               Log.d("bookingdata1", bookingdata.toString());

               service_name += bookingdata1[0] + ",";
               service_price += bookingdata1[1] + ",";
               Log.d("service", service_price.toString());

           }

           String[] s3 = service_name.split(",");
           String[] s4 = service_price.split(",");
//          second for loop...................
           for (int m = 0; m < s3.length; m++)
           {
               s2 += s3[m] + "\n";

           }
           Log.d("values2", s2.toString());
           Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
             //textinclude.setText();
           holder.text_name.setText(s2+"\n");

           for (int n = 0; n < s4.length; n++)
           {
               myprice += "\u20B9" + s4[n] + "\n";
           }
           Log.d("price", myprice.toString());
           //  textincludeprice.setText("\n"+myprice);
           holder.text_price.setText(myprice);


       }

       @Override
       public int getItemCount()
       {
           return include_itemslist.size();
       }

     public   class Myviewholder extends RecyclerView.ViewHolder
      {
          TextView text_name;
          TextView text_price;
        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);
            text_name=itemView.findViewById(R.id.service_name);
            text_price=itemView.findViewById(R.id.text_price);

        }
      }
}
