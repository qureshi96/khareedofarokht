package com.example.kd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class PricePrediction extends AppCompatActivity {
    ImageView graph;
    StorageReference storageref;
    TextView phonename, min, max, curr, std, trend;
    ImageView phoneimage;
    String image,mobilename;
    String phone="Apple iPhone 13 mini_";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_prediction);
        Intent intent= getIntent();
        phoneimage=findViewById(R.id.phoneimage);
        phonename=findViewById(R.id.phonename);
        min = findViewById(R.id.minimum);
        max = findViewById(R.id.maximum);
        curr = findViewById(R.id.currentPrice);
        std = findViewById(R.id.standardDeviation);
        trend = findViewById(R.id.trend);

        mobilename=intent.getStringExtra("mobile_name");
        image=intent.getStringExtra("image");

        phonename.setText(mobilename);

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.sample_phone)
                .error(R.drawable.sample_phone)
                .into(phoneimage);
        phone= mobilename;
        graph= findViewById(R.id.graph);

        storageref = FirebaseStorage.getInstance().getReference("Biannually");

        storageref.child(phone+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("TAG", "onCreate: "+ uri);
                Picasso.get()
                        .load(uri)
                        .placeholder(R.drawable.dummy_chart)
                        .error(R.drawable.dummy_chart)
                        .into(graph);
            }
        });

    }

    public void call_Product(View view) {
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);
    }
}