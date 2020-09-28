package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Storedata;
import com.sossolution.serviceonway.Interface.CustomItemClickListener;
import com.sossolution.serviceonway.Class.Newmodel;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;

public class Towdapter extends RecyclerView.Adapter<Towdapter.Towviewholder>
{

    private Context context2;
    private ArrayList<Newmodel> datalisttwo;
    private CustomItemClickListener listener1;
    //interface model
   //private RecyclerviewSelect recyclerviewSelect;

    public Towdapter(Context context, ArrayList<Newmodel> datalisttwo1,CustomItemClickListener listener)
    {
        context2=context;
        datalisttwo=datalisttwo1;
        listener1=listener;

    }

    @NonNull
    @Override
    public Towviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        final Towviewholder towviewholder= new Towviewholder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener1.onItemClick(view,towviewholder.getPosition());
            }
        });
        return new Towviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Towviewholder holder, int position)
    {
        Newmodel newmodel= datalisttwo.get(position);
        //get text
        String text1=newmodel.getModel();
        //set text
        holder.textView.setText(text1);

       //image store
       // final Context context = holder.imageVie.getContext(); //<----- Add this line
        //RssItem item = rssItems.get(i);
       // final Storedata storedata = datalisttwo.get(position);

       /* String image = storedata.getImage();
        Picasso.get()
                .load("https://serviceonway.com/UploadedFiles/Logos/" + image)
                .fit()
                .into(holder.imageView);
        final String text = storedata.getText();
        holder.textView.setText(text);*/



       /*holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

             //  String s3 = text1;
             //  Toast.makeText(context2,s3+"value", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent("custom-message1");
               //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
              // intent.putExtra("quantity1",s3);

               // intent.putExtra("item",ItemName);
               LocalBroadcastManager.getInstance(context2).sendBroadcast(intent);
             //  Toast.makeText(context2,text1, Toast.LENGTH_SHORT).show();
               ((First_Activity) view.getContext()).onClickCalled("texthii");
           }
       });*/
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/



    }

    @Override
    public int getItemCount()
    {
        return datalisttwo.size();
    }



    public class Towviewholder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout linearLayout;

        public Towviewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textviewmodel);
            //linearLayout = itemView.findViewById(R.id.linearlayout2);
        }

    }
    public interface Cityadapter
    {
        void oncityclick(Storedata storedata);
    }
}
