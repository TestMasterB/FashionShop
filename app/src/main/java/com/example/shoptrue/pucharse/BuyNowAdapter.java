package com.example.shoptrue.pucharse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoptrue.R;
import com.example.shoptrue.database.CartTable;

import java.util.ArrayList;
import java.util.List;

// Adapter nimmt eine Liste von CartTable Objekten
// onCreateViewHolder stellt die Elemente optisch dar
// onBindViewHolder stellt die relevanten Informationen für das jeweilige Produkt zur Verfügung
// getItemCount stellt die ANzahl der Produkt fest
public class BuyNowAdapter extends RecyclerView.Adapter<BuyNowAdapter.BuyNowViewHolder> {
    Context context;
    List<CartTable> list;

    public BuyNowAdapter(Context context, List<CartTable> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BuyNowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new BuyNowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyNowViewHolder holder, int position) {
        CartTable model= list.get(position);
        Glide.with(context).load(model.getImage()).into(holder.img);
        holder.title.setText(model.getName());
        holder.desc.setText(model.getDescription());
        holder.price.setText(model.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class BuyNowViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView desc;
        TextView price;


        public BuyNowViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.image1);
            title=itemView.findViewById(R.id.item_name);
            desc=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.price);

        }
    }


}
