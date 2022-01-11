package com.example.kd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SpecificationAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specification_analysis);
    }

    public void call_Product(View view) {
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);
    }
}