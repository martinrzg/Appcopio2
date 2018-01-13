package com.example.martinruiz.appcopio2.activities;

import android.content.Intent;
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
import butterknife.OnClick;

public class GenerateQR extends AppCompatActivity {

    @BindView(R.id.qrImage) ImageView qrImage;
    String data = "qwerty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        ButterKnife.bind(this);


        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 800);
        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
                //qrImage.setImageBitmap(bitmap);
            Glide.with(this).load(bitmap).into(qrImage);
        } catch (WriterException e) {

        }
    }

    @OnClick(R.id.share)
    public void share(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, data);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


}
