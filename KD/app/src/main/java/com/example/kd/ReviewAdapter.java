package com.example.kd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    ArrayList<ReviewModel> datalist;
    Context context;

    public ReviewAdapter(ArrayList<ReviewModel> datalist, android.content.Context applicationContext) {
        this.datalist = datalist;
        this.context = applicationContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_and_review_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.username.setText(datalist.get(position).getUsername());
        holder.review.setText(datalist.get(position).getReview());
        holder.ratingbar.setRating(Float.valueOf(datalist.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, review;
        RatingBar ratingbar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.user_name);
            review=itemView.findViewById(R.id.review);
            ratingbar=itemView.findViewById(R.id.user_rating);
        }
    }
}
