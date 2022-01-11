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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    ArrayList<PhoneModel> datalist;
    Context context;

    public SearchAdapter(ArrayList<PhoneModel> dataList, Context context) {
        this.datalist = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_product_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get()
                .load(datalist.get(position).getImage())
                .placeholder(R.drawable.mobile)
                .error(R.drawable.mobile)
                .into(holder.imageProductFavorite);

        holder.t1.setText(datalist.get(position).getName());


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datalist.get(position).getName() != null) {
                    Intent i = new Intent(context, Product.class);
                    i.putExtra("name", datalist.get(position).getName());
                    i.putExtra("brand", datalist.get(position).getBrand());
                    i.putExtra("image",datalist.get(position).getImage());
                    context.startActivity(i);
                } else
                    Toast.makeText(context, "No Data Available.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProductFavorite;
        TextView t1 ;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProductFavorite = itemView.findViewById(R.id.product_image);
            t1 = itemView.findViewById(R.id.product_name);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
