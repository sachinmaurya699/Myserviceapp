package com.sossolution.serviceonway.Class;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Myadapter extends RecyclerView.Adapter< Myadapter.Myviewholder>{
    public Myadapter()
    {

    }

    @NonNull
    @Override
    public Myadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.Myviewholder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Myviewholder  extends RecyclerView.ViewHolder{
        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
