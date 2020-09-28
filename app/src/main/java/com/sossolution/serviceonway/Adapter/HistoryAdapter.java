package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Activity.HistoryAllDetails_Activity;
import com.sossolution.serviceonway.Activity.History_Activity;
import com.sossolution.serviceonway.Class.HistoryAllDetails;
import com.sossolution.serviceonway.Class.Historyitem;
import com.sossolution.serviceonway.Interface.Clickbutton;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Historyviewholder>
{

    Context context;
    ArrayList<Historyitem>datahistory;
    private Clickbutton click;
    Historyitem historyitem;


    public HistoryAdapter(Context context,ArrayList<Historyitem> datahistory,Clickbutton click)
    {
        this.context=context;
        this.datahistory=datahistory;
        this.click=click;

    }

    @NonNull
    @Override
    public Historyviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history,parent,false);
        context = parent.getContext();
        return new Historyviewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.Historyviewholder holder, int position)
    {

        Historyitem historyitem= datahistory.get(position);
        String date=historyitem.getDate();
        String amount=historyitem.getAmount();
        String id=historyitem.getId();
        String buttonname1 =historyitem.getButtonname();
        holder.textView_Date.setText("Date"+"-"+date);
        //new task
        String includeservice =historyitem.getInclude();
        String number=includeservice.replaceAll("[0-9]","");
        Log.d("number",number.toString());
        //String number=includeservice.replaceAll("[0-9]","");
        String []data= number.split("=");
        String incs=data[0];
       // String incs1 ="";
       /* String incs ="";

        for (int j=0; j<data.length; j++)
        {
            incs+= data[j]+" ";


           }*/
       /* incs1+=incs.split("=");
        String []data2= incs.split("=");

        for (int i=0; i<data2.length-1; i++)
        {
            incs1 += data2[i].replaceAll("[0-9]","") + ",";
        }
*/
        //holder.textView_address.setText("Include_Service"+"\n"+incs2);
        holder.button.setText(buttonname1);
        holder.textView_amount.setText("Amount"+"-"+"\u20B9"+" "+amount);
        holder.textView_Id.setText("Id"+"-"+id);
        holder.textView_include.setText("Include-Service"+"-"+incs);
        Log.d("include",incs.toString());

        holder.bind(datahistory.get(position),click);

       }

   /* @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);

    }
    public void insert(int position, Historyitem historyitem)
    {
        datahistory.add(position, historyitem);
        notifyItemInserted(position);
    }*/
   /* public void remove(Historyitem historyitem) {
        int position = datahistory.indexOf(historyitem);
        datahistory.remove(position);
        notifyItemRemoved(position);
    }*/

    @Override
    public int getItemCount()

    {
        return datahistory.size();
    }

    public class Historyviewholder  extends RecyclerView.ViewHolder
    {

        TextView textView_Date,textView_include,textView_amount,
        textView_Id;
        Button button;

        public Historyviewholder(@NonNull View itemView)
        {
            super(itemView);
            //textView_address=itemView.findViewById(R.id.includehistory);
            textView_Date=itemView.findViewById(R.id.text_historydate);
            textView_amount=itemView.findViewById(R.id.text_historyammount);
            textView_Id=itemView.findViewById(R.id.text_historyid);
            button=itemView.findViewById(R.id.button_historybutton);
            textView_include=itemView.findViewById(R.id.text_historyInclude);

        }

        public void bind(Historyitem historyitem, Clickbutton click)
        {
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    click.senddata(historyitem);


                }
            });
        }
    }
}
