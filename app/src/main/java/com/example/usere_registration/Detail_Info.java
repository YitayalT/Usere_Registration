package com.example.usere_registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detail_Info extends AppCompatActivity {
TextView d_fullName,d_userName,d_email,d_mobile,d_gender;
ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__info);

        d_fullName = findViewById(R.id.detailfullname);
        d_userName  = findViewById(R.id.detailUsername);
        d_email = findViewById(R.id.detailEmail);
        d_mobile = findViewById(R.id.detailphone);
        d_gender = findViewById(R.id.detailGender);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
           Intent intent = getIntent();
           String fullName = intent.getStringExtra("fullName").toString().trim();
         d_fullName.setText(fullName);
        String userName = intent.getStringExtra("username").toString().trim();
        d_userName.setText(userName);
        String email = intent.getStringExtra("email").toString().trim();
         d_email.setText(email);
        String phone = intent.getStringExtra("phone").toString().trim();
        d_mobile.setText(phone);
        String gender = intent.getStringExtra("gender").toString().trim();
        d_gender.setText(gender);
        actionBar.setTitle(userName);


    }
}
