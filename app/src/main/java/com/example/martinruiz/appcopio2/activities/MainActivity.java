package com.example.martinruiz.appcopio2.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.martinruiz.appcopio2.R;
import com.example.martinruiz.appcopio2.fragments.RegisterFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction transaction;
    public static String collectionCenterId = "-Ky3LNUPgnxn0y1MN2t2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle("TEST");

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //Initial fragment
        transaction.replace(R.id.frameLayoutContainer,new RegisterFragment()).commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_dashboard:
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                }
                return false;
            };

}
