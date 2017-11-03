package com.example.martinruiz.appcopio2.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.martinruiz.appcopio2.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(view,getActivity());
        return view;
    }


    @OnClick(R.id.fabBarcode)
    public void fabBarcodeScanner(){
        //Intent intent = new Intent(getActivity(), BarcodeCaptureActivity.class);
    }

    @OnClick(R.id.fabManual)
    public void fabManual() {

    }
}
