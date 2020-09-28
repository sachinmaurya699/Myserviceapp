package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Storedata;
import com.sossolution.serviceonway.Interface.CustomItemClick;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.Towviewholder>
{

    private Context context1;
    private ArrayList<Storedata> datalisttwo;

    private CustomItemClick click1;


    public SecondAdapter(Context context1, ArrayList<Storedata> datalisttwo,CustomItemClick click)
    {
        this.context1=context1;
        this.datalisttwo=datalisttwo;
        this.click1=click;

    }

    @NonNull
    @Override
    public Towviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new Towviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Towviewholder holder, int position)
    {

        Storedata storedata=datalisttwo.get(position);
        final Context context = holder.imageView.getContext();
        //<----- Add this line
        //show image
        String image = storedata.getImage();
        Picasso.get()
                .load("https://serviceonway.com/UploadedFiles/Logos/"+image)
                .fit()
                .into(holder.imageView);
        final String text = storedata.getText();
        holder.textView.setText(text);

       holder.bind(datalisttwo.get(position),click1);


    }

    @Override
    public int getItemCount()
    {
        return datalisttwo.size();
    }

    public class Towviewholder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public Towviewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.textviewc);
            imageView=itemView.findViewById(R.id.imagec);
           // linearLayout = itemView.findViewById(R.id.linearlayout1);
        }

        public void bind(final Storedata storedata1, final CustomItemClick click1)
        {

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //click1.onItemClick1(storedata1);
                    click1.onItemClick1(storedata1);
                }
            });

        }
    }
}
