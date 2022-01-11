package com.example.kd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.myViewHolderClass> {
    ArrayList<FavoriteModel> favoriteProducts;
    Context context;

    public FavoriteAdapter(ArrayList<FavoriteModel> favoriteProducts, Context context) {
        this.favoriteProducts = favoriteProducts;
        this.context=context;
    }

    @NonNull
    @Override
    public FavoriteAdapter.myViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_row,parent,false);
        myViewHolderClass viewHolder=new myViewHolderClass(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.myViewHolderClass holder, @SuppressLint("RecyclerView") int position) {
        holder.ProductName.setText(favoriteProducts.get(position).getProductName());
        holder.ProductPrice.setText(favoriteProducts.get(position).getBrand());
        Picasso.get()
                .load(favoriteProducts.get(position).getImage())
                .placeholder(R.drawable.mobile)
                .error(R.drawable.mobile)
                .into(holder.ProductImage);
        holder.ll1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favoriteProducts.get(position).getProductName() != null) {
                    Intent i = new Intent(context, Product.class);
                    i.putExtra("name", favoriteProducts.get(position).getProductName());
                    i.putExtra("brand", favoriteProducts.get(position).getBrand());
                    i.putExtra("image",favoriteProducts.get(position).getImage());
                    context.startActivity(i);
                } else
                    Toast.makeText(context, "No Data Available.", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return favoriteProducts.size();
    }
    public class myViewHolderClass extends RecyclerView.ViewHolder {
        TextView ProductName;
        ImageView ProductImage;
        TextView ProductPrice;
        LinearLayout ll1;

        public myViewHolderClass(@NonNull View itemView) {
            super(itemView);
            ll1= itemView.findViewById(R.id.ll1);
            ProductName = itemView.findViewById(R.id.product_name);
            ProductImage = itemView.findViewById(R.id.product_image);
            ProductPrice = itemView.findViewById(R.id.product_price);
        }
    }

}
