package com.example.sportifyadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Adminlogin extends AppCompatActivity {

    private EditText email, password;
    private Button login;
//    private boolean loginclickflag = false;
//    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.changePassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email = email.getText().toString();
                final String Password = password.getText().toString();

                if(dataCheck(Email, Password)){
                    if(Email.equals("ADMIN") && Password.equals("ADMIN@123")){
                        Intent intent = new Intent(Adminlogin.this, AdminHome.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(Adminlogin.this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


    private boolean dataCheck(String email, String password) {
        if (email.equals("") ||password.equals("")) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}



