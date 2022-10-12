package com.experientialetc.Hr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterOfTeam extends RecyclerView.Adapter<AdapterOfTeam.MyViewHolder>
{


    Context context;
    ArrayList<RecordsOfTeam> arrayList;

    public AdapterOfTeam(Context context, ArrayList<RecordsOfTeam> arrayList)

    {
        this.context=context;
        this.arrayList= arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view=LayoutInflater.from(context).inflate(R.layout.custom_layout_of_team,parent,false);

        MyViewHolder holder=new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        RecordsOfTeam obj = arrayList.get(position);
        holder.name.setText(""+obj.getName());
        holder.cost.setText("₹ "+obj.getCost());
        Glide.with(context).load(obj.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,cost;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.nameOfMakeup);
            cost=itemView.findViewById(R.id.rupeeOfMakeup);
            imageView=itemView.findViewById(R.id.imageViewOfMakeup);
        }
    }

}
