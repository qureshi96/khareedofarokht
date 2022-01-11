package com.example.kd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_account);
    }

    public void call_otp(View view) {
        Intent intent = new Intent(this, OTP.class);
        startActivity(intent);
    }

    public void call_login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}