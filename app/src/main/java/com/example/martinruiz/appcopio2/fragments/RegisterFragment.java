package com.example.martinruiz.appcopio2.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinruiz.appcopio2.R;
import com.example.martinruiz.appcopio2.activities.BarcodeCaptureActivity;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private static final int RC_BARCODE_CAPTURE = 9001;

    @BindView(R.id.famAddProduct) FloatingActionMenu famAddProduct;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.fabBarcode)
    public void fabBarcodeScanner(){

        famAddProduct.close(true);

        Intent intent = new Intent(getActivity(), BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
        intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
        startActivityForResult(intent, RC_BARCODE_CAPTURE);

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    showDialogAddProduct("Agregar producto","Indique la cantidad", 0);
                    Toast.makeText(getActivity(), barcode.displayValue, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No barcode captured", Toast.LENGTH_SHORT).show();
                }
            } else {

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showDialogAddProduct(String title, String message, int id){
        final AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setPositiveButton("Agregar",null)
                .setNegativeButton("Cancelar",null)
                .create();
        if(title != null) dialog.setTitle(title);
        if(message != null) dialog.setMessage(message);
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_product,null);
        dialog.setView(viewInflated);
        ImageView imageViewPreviewProduct = viewInflated.findViewById(R.id.imageViewPreviewProduct);
        TextView textViewProductName = viewInflated.findViewById(R.id.textViewProductName);
        EditText editTextProductQty = viewInflated.findViewById(R.id.editTextProductQty);
        String url = "https://www.sams.com.mx/images/product-images/img_medium/000805011m.jpg";
        Picasso.with(getActivity()).load(url).transform(new CropCircleTransformation()).into(imageViewPreviewProduct);

        dialog.setOnShowListener(dialogInterface -> {
            Button buttonPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button buttonNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            buttonPositive.setOnClickListener(view -> {
                dialog.dismiss();
                //TODO save to database

            });
            buttonNegative.setOnClickListener(view -> {dialog.dismiss();});
        });
        dialog.create();
        dialog.show();

    }


    @OnClick(R.id.fabManual)
    public void fabManual() {

    }
}
