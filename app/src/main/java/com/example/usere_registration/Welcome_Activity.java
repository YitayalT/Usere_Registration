package com.example.usere_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome_Activity extends AppCompatActivity {
   private ImageView imageView;
   private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);
        imageView = (ImageView)findViewById(R.id.welcome_Image);
        textView = (TextView)findViewById(R.id.Welcome_Fname);
        ActionBar actionBar = getSupportActionBar();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
startActivity(new Intent(getApplicationContext(),Login_Activity.class));
            }
        }, 1500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share :
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
              return  true;
              case  R.id.about:
                  startActivity(new Intent(getApplicationContext(),About_Activity.class));
                return  true;
            case  R.id.exit:
              finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
