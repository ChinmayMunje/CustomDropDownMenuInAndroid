package com.gtappdevelopers.googlemapsroutes.RecyclerView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gtappdevelopers.googlemapsroutes.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView lngRV;
    private ArrayList<LanguageRVModal> languageRVModalArrayList;
    private LanguageRVAdapter languageRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        lngRV = findViewById(R.id.idRVLanguages);
        languageRVModalArrayList = new ArrayList<>();
        languageRVAdapter = new LanguageRVAdapter(languageRVModalArrayList, this);
        lngRV.setLayoutManager(new LinearLayoutManager(this));
        lngRV.setAdapter(languageRVAdapter);
        addDataToList();
        languageRVAdapter.notifyDataSetChanged();
    }

    private void addDataToList() {
        languageRVModalArrayList.add(new LanguageRVModal("Python", R.drawable.python, "Beginner", "Web, Desktop","https://www.geeksforgeeks.org/python-programming-language/"));
        languageRVModalArrayList.add(new LanguageRVModal("JavaScript", R.drawable.js, "Beginner", "Web, Desktop, Frontend scripting","https://www.geeksforgeeks.org/javascript/"));
        languageRVModalArrayList.add(new LanguageRVModal("Java", R.drawable.java, "Intermediate", "Spring, Hibernate, Strut","https://www.geeksforgeeks.org/java/"));
        languageRVModalArrayList.add(new LanguageRVModal("C++", R.drawable.c, "Beginner to Intermediate", "Mobile, Desktop, Embedded","https://www.geeksforgeeks.org/c-plus-plus/"));
        languageRVModalArrayList.add(new LanguageRVModal("Swift", R.drawable.swift, "Beginner", "Mobile (Apple iOS apps, specifically)","https://www.geeksforgeeks.org/swift-programming-language/"));
        languageRVModalArrayList.add(new LanguageRVModal("HTML", R.drawable.html, "Beginner", "Frontend","https://www.geeksforgeeks.org/html/"));
        languageRVModalArrayList.add(new LanguageRVModal("PHP", R.drawable.php, "Beginner", "Cross-platform (desktop, mobile, web) Back-end web scripting.","https://www.geeksforgeeks.org/php-tutorials/"));
        languageRVModalArrayList.add(new LanguageRVModal("C#", R.drawable.csharp, "Intermediate", "Cross-platform, including mobile and enterprise software applications","https://www.geeksforgeeks.org/csharp-programming-language/"));
        languageRVModalArrayList.add(new LanguageRVModal("CSS", R.drawable.css, "Beginner", "Frontend","https://www.geeksforgeeks.org/css/"));

    }
}