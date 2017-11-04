package com.example.martinruiz.appcopio2.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.martinruiz.appcopio2.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateQR extends AppCompatActivity {

    @BindView(R.id.qrImage) ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        ButterKnife.bind(this);


        QRGEncoder qrgEncoder = new QRGEncoder("qwerty", null, QRGContents.Type.TEXT, 1200);
        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
                //qrImage.setImageBitmap(bitmap);
            Glide.with(this).load(bitmap).into(qrImage);
        } catch (WriterException e) {

        }

    }
}
