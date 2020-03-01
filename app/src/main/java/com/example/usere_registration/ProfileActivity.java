package com.example.usere_registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView  textViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Active user");

        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        SharedPreferences sharedPreferences = getSharedPreferences("userReg",MODE_PRIVATE);
         String userName = sharedPreferences.getString("userName",null);
        //getting the current user
           textViewUsername.setText(userName);
            if(userName == null){
                User user = SharedPrefManager.getInstance(this).getUser();
                textViewUsername.setText(user.getUsername());
            }

      if(!new SharedPrefManager(this).getLoggedInUser()){
          SharedPrefManager.getInstance(getApplicationContext()).logout();
      }
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
