package com.example.martinruiz.appcopio2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.martinruiz.appcopio2.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
    }
}
