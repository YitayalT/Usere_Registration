package com.example.usere_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Users";
    private static final String COLUMN_FNAME = "fullname";
    private static final String COLUMN_NAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_Phone = "mobile";
    private static final String COLUMN_gender = "gender";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                "    " + COLUMN_FNAME  + " text," +
                "    "  + COLUMN_NAME +  "  text," +
                "    " +  COLUMN_PASSWORD + " text primary key," +
                "    " + COLUMN_EMAIL + " text," +
                "    " + COLUMN_Phone + " text," +
                "    " + COLUMN_gender + " text" +
                ")";


        /*
         * Executing the string to create the table
         * */
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(sqLiteDatabase);
    }
    boolean addUser(String fname, String name, String password,
                        String email,String phone,String gender) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME, fname);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_Phone, phone);
        contentValues.put(COLUMN_gender, gender);
        SQLiteDatabase db = getWritableDatabase();
        long result=db.insert(TABLE_NAME, null, contentValues);

        if (result==-1){
            return false;
        }
        else {
            return true;
        }


    }
    public boolean checkUser(String userName, String password){
        String [] columns = {COLUMN_PASSWORD};
        SQLiteDatabase database = getReadableDatabase();
        String fetch = COLUMN_NAME + "=?" + " and " +COLUMN_PASSWORD + "=?";
        String [] selectionArgs = {userName,password};
        Cursor cursor = database.query(TABLE_NAME, columns, fetch, selectionArgs,
                null, null, null);

        int count = cursor.getCount();
        cursor.close();
        database.close();

        if(count > 0)
            return  true;
        else
            return false;
    }

    Cursor getAllEmployees() {
        SQLiteDatabase Db = getReadableDatabase();
        Cursor cursor= Db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return  cursor;
    }
}