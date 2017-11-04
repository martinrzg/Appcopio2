package com.example.martinruiz.appcopio2.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.martinruiz.appcopio2.R;
import com.example.martinruiz.appcopio2.fragments.ProfileFragment;
import com.example.martinruiz.appcopio2.fragments.RegisterFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle("Appcopio");

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        //Initial fragment
        transaction.replace(R.id.frameLayoutContainer,new RegisterFragment()).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_register:
                        transaction.replace(R.id.frameLayoutContainer,new RegisterFragment()).commit();
                        return true;
                    case R.id.navigation_kit:
                        return true;
                    case R.id.navigation_despensa:
                        return true;
                    case R.id.navigation_stats:
                            return true;
                    case R.id.navigation_profile:
                        transaction.replace(R.id.frameLayoutContainer,new ProfileFragment()).commit();

                        return true;

                }
                return false;
            };

}
