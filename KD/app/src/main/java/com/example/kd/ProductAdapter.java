package com.example.kd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.myViewHolderClass>  {
    ArrayList<ProductModel> Products;
    Context context;
    public ProductAdapter(ArrayList<ProductModel> Products, Context context) {
        this.Products = Products;
        this.context = context;
    }
    @NonNull
    @Override
    public ProductAdapter.myViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        ProductAdapter.myViewHolderClass viewHolder=new ProductAdapter.myViewHolderClass(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.myViewHolderClass holder, @SuppressLint("RecyclerView") int position) {
        //holder.ProductName.setText(Products.get(position).getName());
        holder.ProductPrice.setText(Products.get(position).getPrice());
        holder.ProductVendor.setText(Products.get(position).getVendor());
        //holder.ProductRating.setText(Products.get(position).getRating());
        //holder.ProductVendorLink.setText(Products.get(position).getVendorLink());
        holder.shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Purchase.class);
                intent.putExtra("url",Products.get(position).getVendorLink());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class myViewHolderClass extends RecyclerView.ViewHolder {
        TextView ProductName;
        TextView ProductVendor;
        TextView ProductPrice;
        TextView ProductVendorLink;
        TextView ProductRating;
        ImageButton shop;
        public myViewHolderClass(@NonNull View itemView) {
            super(itemView);
            //ProductName = itemView.findViewById(R.id.product_name);
            ProductPrice = itemView.findViewById(R.id.product_price);
            //ProductRating = itemView.findViewById(R.id.product_rating);
            ProductVendor = itemView.findViewById(R.id.vendor);
            shop = itemView.findViewById(R.id.product_link);
            //ProductVendorLink = itemView.findViewById(R.id.product_link);
        }
    }

}
