package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Wallet_history;
import com.sossolution.serviceonway.Interface.Myinterfacehistary;
import com.sossolution.serviceonway.R;

import java.util.List;

public class Wallet_historyadapter extends RecyclerView.Adapter<Wallet_historyadapter.Myviewholder>{

    private Context context;
    private  List<Wallet_history> walletlist;
    private Myinterfacehistary myinterfacehistary;


    public Wallet_historyadapter(Context context, List<Wallet_history> walletlist,Myinterfacehistary myinterfacehistary)
    {
        this.context=context;
        this.walletlist=walletlist;
        this.myinterfacehistary=myinterfacehistary;

    }
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.wallet_history,parent,false);

        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position)
    {
        Wallet_history wallet_history=walletlist.get(position);
        String date=wallet_history.getDate();
        String transsaction_id=wallet_history.getTransation_id();
        String amount=wallet_history.getAmount();

        holder.transaction_id.setText("TRANSACTION ID-"+transsaction_id);
       // holder.amount.setText("Amount"+"-"+"\u20B9"+" "+amount);
        holder.date.setText("DATE-"+date);

        //data click
        holder.bind(walletlist.get(position),myinterfacehistary);


    }

    @Override
    public int getItemCount()
    {
        return walletlist.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{

        TextView transaction_id;
        TextView date;
        TextView amount;


        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);
            transaction_id=itemView.findViewById(R.id.text_historyInclude);
            date=itemView.findViewById(R.id.text_historydate);
            amount=itemView.findViewById(R.id.text_historyammount);

        }
        public void bind(Wallet_history wallet_history,Myinterfacehistary myinterfacehistary)
        {
            myinterfacehistary.newitem(wallet_history);
        }

    }
}
