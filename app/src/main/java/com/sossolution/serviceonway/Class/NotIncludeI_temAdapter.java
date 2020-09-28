package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Adapter.Include_ItemAdapter;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;
import java.util.List;

public class NotIncludeI_temAdapter extends RecyclerView.Adapter<NotIncludeI_temAdapter.My_newViewholder>
{
    private  ArrayList<Notinclude_item>notinclude_itemslist2;;
    private Context context;
    String s1;

    public NotIncludeI_temAdapter(Context context, ArrayList<Notinclude_item> notinclude_itemslist2)
    {
        this.context= context;
        this.notinclude_itemslist2=notinclude_itemslist2;
    }

    @NonNull
    @Override
    public My_newViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(context).inflate(R.layout.notinclude_item,parent,false);

        return new My_newViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull My_newViewholder holder, int position)
    {
        Notinclude_item notinclude_item=notinclude_itemslist2.get(position);
        String name=notinclude_item.getService();
        Log.d("myvalue","v1"+name);

        String []s5=name.split(",");
        String s6="";
        for(int m=0;m<s5.length;m++)
        {
            s6+=s5[m]+"\n";

        }
       // textView_notinclude.setText();*/
        holder.text_name.setText(s6+"\n");

    }

    @Override
    public int getItemCount()
    {
        return notinclude_itemslist2.size() ;
    }

    public class My_newViewholder extends RecyclerView.ViewHolder
    {
        TextView text_name;
        public My_newViewholder(@NonNull View itemView)
        {

            super(itemView);
            text_name=itemView.findViewById(R.id.service_item);
        }
    }
}
