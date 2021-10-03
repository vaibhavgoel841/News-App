package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    public ArrayList<news>arr=new ArrayList<>();
private  Clickevent clickent;

    public RecyclerViewAdapter(Context context, Clickevent clickent) {
        this.context = context;
        this.clickent = clickent;
    }

    @NonNull

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
//String str=arr.get(position);
        holder.textView.setText(arr.get(position).title);

holder.description.setText(arr.get(position).discription);
        Glide.with(holder.itemView.getContext()).load(arr.get(position).imageTourl).into(holder.image);
    }
    public void UpdateNews(ArrayList<news>updatedNews){
        arr.clear();
        arr.addAll(updatedNews);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
public TextView textView;
public ImageView image;
public TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            image=itemView.findViewById(R.id.image);
            description=itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickent.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
