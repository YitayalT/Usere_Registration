package com.example.usere_registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
//    private Toolbar toolbar;
    private RecyclerView myRecyclerView;
    private UserAdapter mydAdapter;
    private  RecyclerView.LayoutManager mLayoutManager;
    private DatabaseManager databaseManager;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      toolbar = (Toolbar)findViewById(R.id.toolBar);
//      setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("List of logged in users");
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        userList = new ArrayList<>();
        databaseManager = new DatabaseManager(this);
        SQLiteDatabase sqLiteDatabase = databaseManager.getReadableDatabase();

        if (sqLiteDatabase != null){
         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseManager.TABLE_NAME, null);
           if(cursor.getCount() == 0){
               Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
           }
           else{
               while (cursor.moveToNext()){

                userList.add(new User(cursor.getString(0),
                                         cursor.getString(1),
                                         cursor.getString(2),
                                         cursor.getString(3),
                                         cursor.getString(4),
                                         cursor.getString(5)));
               }

               mLayoutManager = new LinearLayoutManager(this);
               myRecyclerView.setLayoutManager(mLayoutManager);
               mydAdapter = new UserAdapter(userList, this);
               myRecyclerView.setAdapter(mydAdapter);
               myRecyclerView.setHasFixedSize(true);
               mydAdapter.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)v.getTag();
           int position = viewHolder.getAdapterPosition();
           User user = userList.get(position);
           String fullName = user.getFullname();
           String userName = user.getUsername();
           String  email = user.getEmail();
           String phone = user.getPhoneNo();
           String gender = user.getGender();

            Intent intent = new Intent(getApplicationContext(),Detail_Info.class);

                       intent.putExtra("username", userName);
                       intent.putExtra("fullName", fullName);
                       intent.putExtra("email", email);
                      intent.putExtra("phone", phone);
                       intent.putExtra("gender", gender);
                 startActivity(intent);
            finish();
                   }
               });
               SharedPreferences sharedPreferences = getSharedPreferences("userReg",MODE_PRIVATE);
               final String userName = sharedPreferences.getString("userName",null);
               mydAdapter.setOnLongClickListener(new View.OnLongClickListener() {
                   @Override
                   public boolean onLongClick(View v) {
                       RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)v.getTag();
                       int position = viewHolder.getAdapterPosition();
                       User user = userList.get(position);
                       if(user.getUsername().equals(userName)){
                           Toast.makeText(getApplicationContext(), "you can`t delete active users", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           userList.remove(position);
                           mydAdapter.notifyItemRemoved(position);
                       }

                       return true;
                   }
               });
           }
        }
        else{
            Toast.makeText(this, "data is null...", Toast.LENGTH_SHORT).show();
        }

    }

}
