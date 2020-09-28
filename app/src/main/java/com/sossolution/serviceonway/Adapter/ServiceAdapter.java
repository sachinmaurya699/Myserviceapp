package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Interface.CustomItemService;
import com.sossolution.serviceonway.R;
import com.sossolution.serviceonway.Class.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.Serviceviewholder>
{

    private Context context3;

    private ArrayList<Service> datalistservice;
    private CustomItemService customItemService;

    CheckBox checkBox;

    List<Integer> idsar = new ArrayList<Integer>();


    String data1="";
    String data2="";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // first method.............
    public void ServiceAddIds(String sid12)
    {
        idsar.add(Integer.valueOf(sid12));
       // idsar.add(Integer.valueOf(sid12));
    }
    //second method...............
    public void ServiceRemoveIds(String sid12)
    {
        idsar.remove(Integer.valueOf(sid12));

    }
    //third method......................
    public String getFinalList ()
    {
        String myid ="";

        for(int i:idsar)
        {
            myid+=i+",";
        }
        return myid;
    }

public ServiceAdapter(Context context, ArrayList<Service> datalistservice, CustomItemService customItemService)

    {

        this.context3=context;
        this.datalistservice=datalistservice;
        this.customItemService=customItemService;

    }

    @NonNull
    @Override
    public ServiceAdapter.Serviceviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.services,parent,false);

        return new ServiceAdapter.Serviceviewholder(view);


    }

    @Override
    public void onBindViewHolder( final Serviceviewholder holder, final int position) {


        final Service service=datalistservice.get(position);
        final Context context = holder.imageView.getContext();
        //<----- Add this line
         sharedPreferences= context.getSharedPreferences("vec",Context.MODE_PRIVATE);
         String im=sharedPreferences.getString("car","");
        String carlogo="https://serviceonway.com/UploadedFiles/service_car_logo/";
        String bikelogo ="https://serviceonway.com/UploadedFiles/service_bike_logo/";
        String image = service.getImage();
        if(im.equals("car"))
        {
            Picasso.get()
                    .load(carlogo+image)
                    .fit()
                    .into(holder.imageView);
        }
        else if(im.equals("bike"))
        {
            Picasso.get()
                .load(bikelogo+image)
                .fit()
                .into(holder.imageView);
        }

        final String des = service.getText();
        final String price = service.getPrice();
        final String Id=service.getId();
        String chek= String.valueOf(service.getSelected());
         int s1= holder.checkBox.getId();
        //new checkbox tips

       holder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CheckBox checkBox= (CheckBox) view;
                Service service=datalistservice.get(position);

                if(checkBox.isChecked())
                {
                    service.setSelected(true);
                    ServiceAddIds(datalistservice.get(position).getId());
                    data1 = getFinalList();
                    customItemService.onItemCheck(data1);

                }
                else if(!checkBox.isChecked())
                {
                    service.setSelected(false);
                    ServiceRemoveIds(datalistservice.get(position).getId());
                    data2 = getFinalList();
                    customItemService.onItemCheck(data2);

                }


            }
        });

        holder.bind(datalistservice.get(position),position,customItemService);
        holder.textDes.setText(des);
        holder.textDes.setId(Integer.parseInt(Id));
        holder.textprice.setText("\u20B9 "+price);
        holder.checkBox.setChecked(Boolean.parseBoolean(chek));
        holder.checkBox.setTag(datalistservice.get(position).getId()+"haji");

    }

    @Override
    public int getItemCount()
    {
        return  datalistservice == null ? 0 :datalistservice.size();
    }

    public class Serviceviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public View view;
        TextView textDes,textprice;
        ImageView imageView,imageView2;
        ImageButton imageButton;
        CheckBox checkBox;
        CardView cardView;

        public Serviceviewholder(@NonNull View itemView)
        {
            super(itemView);
            view = itemView;
            textDes=itemView.findViewById(R.id.textviews);
            cardView=itemView.findViewById(R.id.cardviews);
            textprice=itemView.findViewById(R.id.textviewprice);
            imageView=itemView.findViewById(R.id.images);
            checkBox=itemView.findViewById(R.id.checkboxs);


        }


        public void bind(final Service service1, int position, final CustomItemService customItemService)
        {

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    customItemService.onItemClickService(service1);
                   // Toast.makeText(context3,"hello ji", Toast.LENGTH_SHORT).show();
                  //  Service service=datalistservice.get(position);



                }
            });

        }

        @Override
        public void onClick(View view)
        {


        }
    }
}
