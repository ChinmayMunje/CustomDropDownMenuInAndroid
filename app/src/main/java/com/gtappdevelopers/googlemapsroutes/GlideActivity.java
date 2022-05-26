package com.gtappdevelopers.googlemapsroutes;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GlideActivity extends AppCompatActivity {

    private ImageView imgOne, imgTwo, imgThree, imgFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        imgOne = findViewById(R.id.idIVImgOne);
        imgTwo = findViewById(R.id.idIVImgTwo);
        imgThree = findViewById(R.id.idIVImgThree);
        imgFour = findViewById(R.id.idIVImgFour);

        Glide.with(this).load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.png")
                .into(imgOne);


        Glide.with(this).load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.png")
                .override(40, 40).centerInside().into(imgTwo);

        Glide.with(this).load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.png")
                .placeholder(R.drawable.ic_sync).into(imgThree);

        Glide.with(this).load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.pn")
                .error(R.drawable.ic_error)
                .into(imgFour);


    }
}