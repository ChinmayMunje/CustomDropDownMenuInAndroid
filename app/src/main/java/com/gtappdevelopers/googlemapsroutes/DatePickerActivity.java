package com.gtappdevelopers.googlemapsroutes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {

    private LinearLayout simpleDateLL, materialDateLL;
    private TextView simpleDateTV, materialDateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        simpleDateLL = findViewById(R.id.idLLDate);
        materialDateLL = findViewById(R.id.idLLMaterialDatePicker);
        simpleDateTV = findViewById(R.id.idTVDate);
        materialDateTV = findViewById(R.id.idTVMaterialDatePicker);
        simpleDateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        materialDateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker();
            }
        });
    }

    private void materialDatePicker() {
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Select a date");
        MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                String date = materialDatePicker.getHeaderText();
                SimpleDateFormat input = new SimpleDateFormat("dd MMM yyyy");
                SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    materialDateTV.setText(output.format(input.parse(date)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void datePicker() {
        final Calendar calendar = Calendar.getInstance();
        int mYear, mMonth, mDay;
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                simpleDateTV.setText(selectedDate);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}