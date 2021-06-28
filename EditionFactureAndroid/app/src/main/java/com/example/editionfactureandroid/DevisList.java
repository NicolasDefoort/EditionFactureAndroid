package com.example.editionfactureandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itextpdf.kernel.geom.Line;

import java.util.ArrayList;

public class DevisList extends AppCompatActivity {

    RecyclerView recyclerView;
    public ArrayList<model> dataholder = new ArrayList<>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.devis_list);

        recyclerView=(RecyclerView)findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DatabaseHelper(this).getAllData();

        while(cursor.moveToNext()){
            model obj = new model(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13),
                    cursor.getString(14),
                    cursor.getString(15),
                    cursor.getString(16)
            );
            dataholder.add(obj);
        }

        myAdapter adapter = new myAdapter(dataholder);
        recyclerView.setAdapter(adapter);


    }
}
