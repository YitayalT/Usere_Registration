package com.example.usere_registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class About_Activity extends AppCompatActivity {
  private ImageView imageView;
  private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_);
         ActionBar actionBar = getSupportActionBar();
          actionBar.setTitle("about developers");
        imageView = (ImageView)findViewById(R.id.imageView_about);
        textView = (TextView)findViewById(R.id.textView_about);
    }
}
