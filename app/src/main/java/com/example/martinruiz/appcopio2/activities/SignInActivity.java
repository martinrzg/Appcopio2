package com.example.martinruiz.appcopio2.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinruiz.appcopio2.activities.MainActivity;
import com.example.martinruiz.appcopio2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    /* --- Activity View components --- */
    private EditText email;
    private EditText password;
    private Button signIn;
    private TextView signUp;

    /* --- Internal Members --- */
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        initViewComponents();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    private void initViewComponents(){
        AppCompatActivity _this = this;

        /* Definitions */
        email = (EditText) findViewById(R.id.login_et_email);
        password = (EditText) findViewById(R.id.login_et_password);
        signIn = (Button) findViewById(R.id.login_btn_signin);
        signUp = (TextView) findViewById(R.id.login_btn_signup);

        /* Behaviour */
        signIn.setOnClickListener( v -> {
            // Auth
            String sEmail = email.getText().toString().trim();
            String sPass = password.getText().toString().trim();
            signIn(sEmail, sPass);
        });

        signUp.setOnClickListener(v -> {
            Intent i = new Intent(_this, SignUpActivity.class);
            startActivity(i);
        });
    }


    private void signIn(String sEmail, String sPassword) {
        AppCompatActivity _this = this;

        auth.signInWithEmailAndPassword(sEmail, sPassword)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = auth.getCurrentUser();
                        // CHANGE TO MAIN ACTIVITY
                        Intent i = new Intent(_this, MainActivity.class);
                        startActivity(i);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SignIn", "signInWithEmail:failure", task.getException());
                        Toast.makeText(SignInActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    if (!task.isSuccessful()) {

                    }
                }
            });
    }

}
