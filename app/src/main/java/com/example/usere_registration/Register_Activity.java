package com.example.usere_registration;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    public DatabaseManager databaseManager = new DatabaseManager(this) ;
    private EditText fullName, userName,email,password,mobile;
    private Button submit;
    RadioGroup radioGroup;
    private RadioButton male, female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Registration form");

        fullName = (EditText) findViewById(R.id.fullName);
        userName = (EditText) findViewById(R.id.editTextUsername);
        email =  (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        mobile = (EditText) findViewById(R.id.phoneNumber);
        submit = (Button) findViewById(R.id.buttonRegister);
        radioGroup = (RadioGroup) findViewById(R.id.radioGender);
        male = (RadioButton) findViewById(R.id.radioButtonMale);
        female = (RadioButton) findViewById(R.id.radioButtonFemale);
        submit.setOnClickListener(this);

      }

    @Override
    public void onClick(View v) {


        String f_name = fullName.getText().toString().trim();
        String u_name = userName.getText().toString().trim();
        String p_password = password.getText().toString();
        String Email = email.getText().toString();
        String  phone = mobile.getText().toString();
        String gender = ((RadioButton) findViewById(
                         radioGroup.getCheckedRadioButtonId())).getText()
                .toString();




        if (TextUtils.isEmpty(f_name)) {
            fullName.setError("Please enter fullname");
            fullName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(u_name)) {
            userName.setError("Please enter username");
            userName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(p_password)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Email)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(phone)) {
            mobile.setError("Enter a phoneNo");
            mobile.requestFocus();
            return;
        }


        databaseManager.addUser(f_name, u_name, p_password, Email, phone, gender);
        Toast.makeText(this, "user is added to database", Toast.LENGTH_LONG).show();

        User user = new User(u_name,p_password );
        //storing the user in shared preferences
        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
        //starting the profile activity
        finish();
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
    }
}
