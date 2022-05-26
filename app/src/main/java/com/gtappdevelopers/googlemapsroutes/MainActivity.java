package com.gtappdevelopers.googlemapsroutes;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imgOne, imgTwo, imgThree, imgFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgOne = findViewById(R.id.idIVOne);
        imgTwo = findViewById(R.id.idIVTwo);
        imgThree = findViewById(R.id.idIVThree);
        imgFour = findViewById(R.id.idIVFour);

        //simple
        Picasso.get().load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.png")
                .into(imgOne);

        //transformed or resized image
        Picasso.get().load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.png")
                .resize(40, 40).centerInside()
                .into(imgTwo);

        //placeholder image
        Picasso.get().load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.png")
                .placeholder(R.drawable.ic_sync)
                .into(imgThree);

        //error image
        Picasso.get().load("https://geeksgod.com/wp-content/uploads/2021/06/GeeksforGeeks.p")
                .error(R.drawable.ic_error)
                .into(imgFour);

    }
}