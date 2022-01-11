package com.example.kd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Product extends AppCompatActivity {
    TextView mobilename;
    ImageButton reviewbutton;
    ImageView mobileimage;
    String image, mobile_name, brand;
    RecyclerView recview;
    ProductAdapter adapter;
    Button lowtohigh, hightolow;
    ImageButton favbutton,filter;
    Context context;
    boolean fil=true;
    boolean alreadyfav;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();
    String[] user1 = userEmail.split("@");
    //String favuser= FirebaseAuth.getInstance().getCurrentUser().getEmail();
    ArrayList<ProductModel> datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mobileimage = findViewById(R.id.productimage);
        mobilename=findViewById(R.id.phonename);
        Intent intent=getIntent();
        mobile_name= intent.getStringExtra("name");
        image =intent.getStringExtra("image");
        brand = intent.getStringExtra("brand");
        mobilename.setText(mobile_name);
        reviewbutton=findViewById(R.id.ratings);
        recview=findViewById(R.id.pricesRecyclerView);
        lowtohigh = findViewById(R.id.lowtohigh);
        hightolow= findViewById(R.id.hightolow);
        favbutton = findViewById(R.id.add_to_fav);
        filter = findViewById(R.id.filter);

        DatabaseReference db3=FirebaseDatabase.getInstance().getReference().child("Favourites").child(user1[0]);
        db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()) {
                    Log.d("TAG", "onDataChange: "+snap.child("productName").getValue(String.class));
                    if (snap.child("productName").getValue(String.class) .contains(mobile_name)) {
                        alreadyfav = true;
                        Log.d("TAG", "Already in favourite" + mobile_name);

                    }
                    else alreadyfav=false;
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fil==true){
                    Collections.sort(datalist, new Comparator<ProductModel>() {
                        @Override
                        public int compare(ProductModel o1, ProductModel o2) {

                            return o1.getPrice1() - o2.getPrice1();
                        }});
                    adapter.notifyDataSetChanged();
                    fil=false;
                }
                else if(fil == false){
                    Collections.sort(datalist, new Comparator<ProductModel>() {
                        @Override
                        public int compare(ProductModel o1, ProductModel o2) {

                            return o2.getPrice1() - o1.getPrice1();
                        }});
                    adapter.notifyDataSetChanged();
                    fil=true;
                }
            }
        });
        favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(alreadyfav!=true) {
                    FavoriteModel obj = new FavoriteModel(image, mobile_name, brand);
                    Log.d("TAG", "onClick: Alreadyfavfalse");
                    DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Favourites").child(user1[0]);
                    db2.push().setValue(obj);
                    Context context = getApplicationContext();
                    CharSequence text = "Added to favourites";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if(alreadyfav==true){
                    Context context = getApplicationContext();
                    CharSequence text = "Already in favourites";
                    int duration = Toast.LENGTH_SHORT;
                    Log.d("TAG", "onClick:  alreadyfav true");
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }


                            }


            });

        lowtohigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(datalist, new Comparator<ProductModel>() {
                    @Override
                    public int compare(ProductModel o1, ProductModel o2) {

                        return o1.getPrice1() - o2.getPrice1();
                    }});
                adapter.notifyDataSetChanged();
            }
        });
        hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(datalist, new Comparator<ProductModel>() {
                    @Override
                    public int compare(ProductModel o1, ProductModel o2) {

                        return o2.getPrice1() - o1.getPrice1();
                    }});


                adapter.notifyDataSetChanged();
            }
        });

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.sample_phone)
                .error(R.drawable.sample_phone)
                .into(mobileimage);

        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter= new ProductAdapter(datalist,getApplicationContext());
        recview.setAdapter(adapter);

        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Mobile").child("BrandName").child(brand).child(mobile_name);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    ProductModel obj = new ProductModel();
                    obj.setName(snap.child("MobileName").getValue(String.class));

                    obj.setVendorLink(snap.child("VendorLink").getValue(String.class));
                    obj.setVendor(snap.child("Vendor").getValue(String.class));

                    String pr = snap.child("Price").getValue(String.class);
                    if(pr!=null)
                    {    obj.setPrice(pr);
                        //Log.d("TAG", " "+obj.getVendor());
                        if (snap.child("Vendor").getValue(String.class).contains("Zaibis")){
                            Log.d("Vendor", "I am Zaibis ");
                            String[] prrr=pr.split(".00");
                            String[] pr1=prrr[0].split("₨");
                            Log.d("TAG", "onDataChange: "+ pr1[1] );
                            if(pr1.length>=2){
                                Log.d("Price", " "+ prrr[0]);
                                obj.setPrice1(Integer.parseInt(pr1[1].replaceAll("[\\D]","")));
                            }
                        }
                        else{
                            String []prr=pr.split("s");
                            if(prr.length>=2) {
                                obj.setPrice1(Integer.parseInt(prr[1].replaceAll("[\\D]", "")));
                                // Log.d("usman", obj.getPrice1() + "");
                            }}
                    }
                    else {
                        obj.setPrice1(999999);
                        obj.setPrice("See Price at website");
                    }
                    Log.d("usman", obj.getPrice1() + ": "+obj.getPrice() +": "+ obj.getVendor());
                    datalist.add(obj);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_ratings_and_reviews(view);
            }
        });

    }



    public void call_price_prediction(View view) {
        Intent i = new Intent(this, PricePrediction.class);
        i.putExtra("mobile_name",mobile_name);
        i.putExtra("brand",brand);
        i.putExtra("image",image);
        startActivity(i);
    }

    public void turn_on_notifications(View view) {
        Toast.makeText(getApplicationContext(),"Notifications have been turned on!",Toast.LENGTH_LONG).show();
    }

    public void call_product_specifications(View view) {
        Intent i = new Intent(this, ProductDetails.class);
        startActivity(i);
    }

    public void call_specifications_analysis(View view) {
        Intent i = new Intent(this, SpecificationAnalysis.class);
        startActivity(i);
    }

    public void call_main_activity(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void call_ratings_and_reviews(View view) {
        Intent i = new Intent(this, RateAndReview.class);
        i.putExtra("mobile_name",mobile_name);
        i.putExtra("brand",brand);
        i.putExtra("image",image);
        startActivity(i);
    }
}