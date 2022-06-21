package com.gtappdevelopers.googlemapsroutes.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.gtappdevelopers.googlemapsroutes.R;

import java.util.HashMap;
import java.util.Map;

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
                    updateCourseVolley(courseName, courseDuration, coursePrice, courseImg, courseLink, courseId);
                } else {
                    addNewCourseVolley(courseName, courseDuration, coursePrice, courseImg, courseLink);
                }
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourseVolley(courseId);
            }
        });
    }

    private void addNewCourseVolley(String courseName, String courseDUration, String coursePrice, String courseImg, String courseLink) {
        String url = "https://folkish-star.000webhostapp.com/courses/addCourse.php";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(AddDataActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddDataActivity.this, "Course Added successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddDataActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(AddDataActivity.this, "Fail to add course..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("courseName", courseName);
                params.put("courseDuration", courseDUration);
                params.put("coursePrice", coursePrice);
                params.put("courseImg", courseImg);
                params.put("courseLink", courseLink);
                return params;
            }
        };
        queue.add(request);
    }

    private void deleteCourseVolley(String courseId) {
        String url = "https://folkish-star.000webhostapp.com/courses/deleteCourse.php";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(AddDataActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddDataActivity.this, "Course Deleted successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddDataActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(AddDataActivity.this, "Fail to delete course..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", courseId);
                return params;
            }
        };
        queue.add(request);
    }

    private void updateCourseVolley(String courseName, String courseDuration, String coursePrice, String courseImg, String courseLink, String courseId) {
        String url = "https://folkish-star.000webhostapp.com/courses/updateCourse.php";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(AddDataActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddDataActivity.this, "Course Updated successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddDataActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(AddDataActivity.this, "Fail to update course..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", courseId);
                params.put("courseName", courseName);
                params.put("courseDuration", courseDuration);
                params.put("coursePrice", coursePrice);
                params.put("courseImg", courseImg);
                params.put("courseLink", courseLink);
                return params;
            }
        };
        queue.add(request);
    }

}