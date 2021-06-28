package com.example.editionfactureandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Test extends AppCompatActivity {



    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.test);



        recyclerView=(RecyclerView)findViewById(R.id.recView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
