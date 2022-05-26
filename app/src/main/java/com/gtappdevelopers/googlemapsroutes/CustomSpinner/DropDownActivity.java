package com.gtappdevelopers.googlemapsroutes.CustomSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gtappdevelopers.googlemapsroutes.R;

import java.util.ArrayList;

public class DropDownActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<SpinnerModal> spinnerModalArrayList;
    private SpinnerAdapter spinnerAdapter;
    private TextView selectedTV;
    private ImageView selectedIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        spinnerModalArrayList = new ArrayList<>();
        spinnerModalArrayList.add(new SpinnerModal(R.drawable.android, "Android"));
        spinnerModalArrayList.add(new SpinnerModal(R.drawable.js, "Javascript"));
        spinnerModalArrayList.add(new SpinnerModal(R.drawable.java, "Java"));
        spinnerModalArrayList.add(new SpinnerModal(R.drawable.c, "C++"));
        spinnerModalArrayList.add(new SpinnerModal(R.drawable.python, "Python"));

        spinner = findViewById(R.id.idSpinner);
        selectedIV = findViewById(R.id.idIVSelected);
        selectedTV = findViewById(R.id.idTVSelected);

        spinnerAdapter = new SpinnerAdapter(this, spinnerModalArrayList);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerModal modal = (SpinnerModal) adapterView.getItemAtPosition(i);
                String language = modal.getName();
                selectedTV.setText(language);
                selectedIV.setImageResource(modal.getImgID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}