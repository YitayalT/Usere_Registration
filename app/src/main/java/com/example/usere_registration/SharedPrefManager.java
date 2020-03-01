package com.example.usere_registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "userReg";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_FName = "keyfname";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_MOBILE = "keymobile";

    public static SharedPrefManager mInstance;
    private static Context mCtx;
    private SharedPreferences sharedPreferences;
    private   SharedPreferences.Editor editor;
    public SharedPrefManager(Context context) {
        mCtx = context;
        sharedPreferences = context.getSharedPreferences("userReg",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FName, user.getFullname());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_MOBILE, user.getPhoneNo());
        editor.putString(KEY_GENDER, user.getGender());
        editor.apply();
    }


    public  void userLogIn(boolean userLogIn){
        editor.putBoolean("loggedin", userLogIn);
        editor.apply();
    }

    public boolean getLoggedInUser(){
        return  sharedPreferences.getBoolean("loggedin", false);
    }
    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn( boolean check) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }
    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PASSWORD, null));

    }
    //this method will logout the user
    public void logout() {
        userLogIn(false);
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login_Activity.class));
    }
}
