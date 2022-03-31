package com.example.all4sport;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHorder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHorder(LayoutInflater.from(context).inflate(R.layout.item_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHorder holder, int position) {
        String name = items.get(position).getNom();
        holder.nameView.setText(name);
        String quantite = items.get(position).getQuantite();
        holder.quantiteView.setText(quantite);
        holder.imageView.setImageResource(items.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
