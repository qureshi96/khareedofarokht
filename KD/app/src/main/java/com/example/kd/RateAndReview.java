package com.example.kd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RateAndReview extends AppCompatActivity {
    String image,mobilename,brand;
    TextView phonename;
    ImageView phoneimage;
    EditText review, username;
    RatingBar ratingbar;
    ImageButton submit;
    ArrayList<ReviewModel> datalist= new ArrayList<>();
    RecyclerView recview;
    ReviewAdapter adapter;
    float star;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_and_review);
        Intent intent= getIntent();
        image=intent.getStringExtra("image");
        mobilename=intent.getStringExtra("mobile_name");
        brand = intent.getStringExtra("brand");
        phoneimage=findViewById(R.id.phoneimage);
        phonename=findViewById(R.id.phonename);
        submit = findViewById(R.id.submit_review);
        ratingbar = findViewById(R.id.user_rating);
        review = findViewById(R.id.user_review);
        username =findViewById(R.id.username);
        recview=findViewById(R.id.ratings_and_reviews);
        phonename.setText(mobilename);

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.sample_phone)
                .error(R.drawable.sample_phone)
                .into(phoneimage);

        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new ReviewAdapter(datalist,getApplicationContext());
        recview.setAdapter(adapter);
        DatabaseReference db1=FirebaseDatabase.getInstance().getReference("Mobile").child("BrandName")
                .child(brand).child(mobilename).child("Reviews");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    ReviewModel obj1 = new ReviewModel();
                    obj1.setUsername(snap.child("username").getValue(String.class));
                    obj1.setReview(snap.child("review").getValue(String.class));
                    obj1.setRating(snap.child("rating").getValue(String.class));
                    datalist.add(obj1);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                star = ratingbar.getRating();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db= FirebaseDatabase.getInstance().getReference("Mobile").child("BrandName")
                        .child(brand).child(mobilename).child("Reviews");
                ReviewModel obj=new ReviewModel();
                obj.setRating(String.valueOf(star));
                obj.setReview(review.getText().toString());
                obj.setUsername(username.getText().toString());
                db.push().setValue(obj);
                review.setText("");
                username.setText("");
                datalist.clear();
                adapter.notifyDataSetChanged();

            }
        });
    }

    public void call_product_activity(View view) {
        Intent i = new Intent(this, Product.class);
        startActivity(i);
    }
}