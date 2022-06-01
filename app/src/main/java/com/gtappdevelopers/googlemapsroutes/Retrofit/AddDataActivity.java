package com.gtappdevelopers.googlemapsroutes.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.gtappdevelopers.googlemapsroutes.R;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDataActivity extends AppCompatActivity {

    private Button addCourseBtn, deleteCourseBtn;
    private TextInputEditText courseNameEdt, courseDurationEdt, coursePriceEdt, courseLinkEdt, courseImgLinkEdt;
    private String courseId;
    private TextView headTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        headTV = findViewById(R.id.idTVHead);
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        coursePriceEdt = findViewById(R.id.idEdtCoursePrice);
        courseLinkEdt = findViewById(R.id.idEdtCourseLink);
        courseImgLinkEdt = findViewById(R.id.idEdtCourseImageLink);
        deleteCourseBtn = findViewById(R.id.idBtnDeleteCourse);
        loadingPB = findViewById(R.id.idPBLoading);

        if (getIntent().getStringExtra("type") != null && getIntent().getStringExtra("type").equals("edit")) {
            headTV.setText("Update and Delete Course");
            courseId = getIntent().getStringExtra("courseId");
            courseNameEdt.setText(getIntent().getStringExtra("courseName"));
            courseDurationEdt.setText(getIntent().getStringExtra("courseDuration"));
            coursePriceEdt.setText(getIntent().getStringExtra("coursePrice"));
            courseImgLinkEdt.setText(getIntent().getStringExtra("courseImg"));
            courseLinkEdt.setText(getIntent().getStringExtra("courseLink"));
            addCourseBtn.setText("Update Course");
            deleteCourseBtn.setVisibility(View.VISIBLE);
        }

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = courseNameEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String coursePrice = coursePriceEdt.getText().toString();
                String courseImg = courseImgLinkEdt.getText().toString();
                String courseLink = courseLinkEdt.getText().toString();
                if (getIntent().getStringExtra("type") != null && getIntent().getStringExtra("type").equals("edit")) {
                    updateCourse(courseName, courseDuration, coursePrice, courseImg, courseLink, courseId);
                } else {
                    addNewCourse(courseName, courseDuration, coursePrice, courseImg, courseLink);
                }
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse(courseId);
            }
        });

    }

    private void addNewCourse(String courseName, String courseDUration, String coursePrice, String courseImg, String courseLink) {
        loadingPB.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://folkish-star.000webhostapp.com/courses/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("courseName", courseName)
                .addFormDataPart("courseImg", courseImg)
                .addFormDataPart("courseDuration", courseDUration)
                .addFormDataPart("coursePrice", coursePrice)
                .addFormDataPart("courseLink", courseLink)
                .build();

        retrofitAPI.addCourse(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadingPB.setVisibility(View.GONE);
                Log.e("TAG", "RESPONSE IS " + response.code());
                Toast.makeText(AddDataActivity.this, "Course Added successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddDataActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("TAG", "ERROR IS " + t.getMessage());
            }
        });
    }

    private void deleteCourse(String courseId) {
        loadingPB.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://folkish-star.000webhostapp.com/courses/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", courseId)
                .build();

        retrofitAPI.deleteCourse(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(AddDataActivity.this, "Course Deleted..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddDataActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("TAG", "Fail to delete course..");
                Toast.makeText(AddDataActivity.this, "Fail to delete course..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCourse(String courseName, String courseDuration, String coursePrice, String courseImg, String courseLink, String courseId) {
        loadingPB.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://folkish-star.000webhostapp.com/courses/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("courseName", courseName)
                .addFormDataPart("id", courseId)
                .addFormDataPart("courseImg", courseImg)
                .addFormDataPart("courseDuration", courseDuration)
                .addFormDataPart("coursePrice", coursePrice)
                .addFormDataPart("courseLink", courseLink)
                .build();

        retrofitAPI.updateCourse(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(AddDataActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddDataActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddDataActivity.this, "Fail to update course..", Toast.LENGTH_SHORT).show();

            }
        });

    }

}