package com.example.martinruiz.appcopio2.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martinruiz.appcopio2.MainActivity;
import com.example.martinruiz.appcopio2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    /* --- Activity View components --- */
    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;
    private EditText password;
    private EditText repeatPass;
    private Button create;

    /* --- Internal Members --- */
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        initViewComponents();
    }

    private void initViewComponents(){
        AppCompatActivity _this = this;

        /* Definitions */
        name = findViewById(R.id.sign_up_name);
        lastName = findViewById(R.id.sign_up_last_name);
        email = findViewById(R.id.sign_up_email);
        phoneNumber = findViewById(R.id.sign_up_phone_number);
        password = findViewById(R.id.sign_up_password);
        repeatPass = findViewById(R.id.sign_up_repeat_pass);
        create = (Button) findViewById(R.id.sign_up_create);

        /* Behaviour */
        create.setOnClickListener(v -> {
            registerUser();
        });
    }

    private void registerUser(){
        AppCompatActivity _this = this;

        String sEmail = email.getText().toString();
        String sPass = password.getText().toString();

        auth.createUserWithEmailAndPassword(sEmail, sPass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.i("Info","Email: "+sEmail+", Pass: "+sPass);
                    if (task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        // Change to MAIN ACTIVITY
                        Intent i = new Intent(_this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(_this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        Log.i("SignUp", task.getResult().toString());
                    }
                }
            });

    }
}
