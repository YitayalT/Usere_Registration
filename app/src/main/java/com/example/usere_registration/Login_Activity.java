package com.example.usere_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login_Activity extends AppCompatActivity {
     private SharedPrefManager sharedPrefManager;
    private  DatabaseManager databaseManager;
    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        ActionBar actionBar = getSupportActionBar();
         actionBar.setTitle("Log in page");
         databaseManager = new DatabaseManager(this);
         sharedPrefManager = new SharedPrefManager(this);
         if(sharedPrefManager.getLoggedInUser()){
             startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
             finish();
         }
        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn(true)) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }
        //if user presses on login
            //calling the method login
            findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //first getting the values
                    String username = editTextUsername.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    if (TextUtils.isEmpty(username)) {
                        editTextUsername.setError("Please enter your username");
                        editTextUsername.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        editTextPassword.setError("Please enter your password");
                        editTextPassword.requestFocus();
                        return;
                    }
                    Boolean result = databaseManager.checkUser(username, password);
                      if (result== true) {
                          SharedPreferences sharedPreferences = getSharedPreferences("userReg", MODE_PRIVATE);
                          SharedPreferences.Editor editor = sharedPreferences.edit();
                                      editor.putString("userName", username);
                                      editor.putString("password", password);
                                      editor.apply();
                                      sharedPrefManager.userLogIn(true);
                          startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                      }
                      else
                           Toast.makeText(getApplicationContext(), "check your user name and password unless " +
                                   ",please sign in first!", Toast.LENGTH_SHORT).show();
                }
            });
            findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open register screen
                    startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                }
            });
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

