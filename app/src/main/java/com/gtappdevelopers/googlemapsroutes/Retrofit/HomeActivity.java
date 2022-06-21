package com.gtappdevelopers.googlemapsroutes.Retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gtappdevelopers.googlemapsroutes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton addFAB;
    private RecyclerView courseRV;
    private List<DataModal> courseArrayList;
    private CoursesRVAdapter coursesRVAdapter;
    private ProgressBar loadingPB;
    String url = "https://folkish-star.000webhostapp.com/courses/readCourse.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addFAB = findViewById(R.id.idFABAdd);
        loadingPB = findViewById(R.id.idPBLoading);
        courseRV = findViewById(R.id.idRVCourse);
        courseArrayList = new ArrayList<>();

        readCourses();
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
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loadingPB.setVisibility(View.GONE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String courseName = jsonObject.getString("courseName");
                        String courseDuration = jsonObject.getString("courseDuration");
                        String coursePrice = jsonObject.getString("coursePrice");
                        String courseImg = jsonObject.getString("courseImg");
                        String courseLink = jsonObject.getString("courseLink");
                        courseArrayList.add(new DataModal(id, courseName, courseImg, courseDuration, coursePrice, courseLink));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(HomeActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
}