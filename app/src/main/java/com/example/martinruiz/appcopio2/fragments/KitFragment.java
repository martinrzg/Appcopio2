package com.example.martinruiz.appcopio2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.martinruiz.appcopio2.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class KitFragment extends Fragment {


    public KitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kit, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
