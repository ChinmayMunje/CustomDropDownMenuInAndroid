package com.gtappdevelopers.googlemapsroutes.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gtappdevelopers.googlemapsroutes.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton addFAB;
    private RecyclerView courseRV;
    private List<DataModal> courseArrayList;
    private CoursesRVAdapter coursesRVAdapter;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addFAB = findViewById(R.id.idFABAdd);
        loadingPB = findViewById(R.id.idPBLoading);
        courseRV = findViewById(R.id.idRVCourse);
        courseArrayList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://folkish-star.000webhostapp.com/courses/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<List<DataModal>> call = retrofitAPI.getCourses();
        call.enqueue(new Callback<List<DataModal>>() {
            @Override
            public void onResponse(Call<List<DataModal>> call, Response<List<DataModal>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    String courseName = response.body().get(i).getCourseName();
                    String courseId = response.body().get(i).getCourseId();
                    String courseImg = response.body().get(i).getCourseImg();
                    String courseDuration = response.body().get(i).getCourseDuration();
                    String coursePrice = response.body().get(i).getCoursePrice();
                    String courseLink = response.body().get(i).getCourseLink();
                    Log.e("TAG", "ID is " + courseId);
                    courseArrayList.add(new DataModal(courseId, courseName, courseImg, courseDuration, coursePrice, courseLink));
                }
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DataModal>> call, Throwable t) {
                Log.e("TAG", "ERROR IS " + t.getMessage());
            }
        });


        coursesRVAdapter = new CoursesRVAdapter(this, courseArrayList);
        courseRV.setAdapter(coursesRVAdapter);
        coursesRVAdapter.notifyDataSetChanged();

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AddDataActivity.class);
                startActivity(i);
            }
        });
    }

    private void readCourses() {

    }
}