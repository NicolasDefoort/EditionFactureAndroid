
package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.activity_main);
        Button formChaudiere = findViewById(R.id.buttonFactureChaudiere);
        formChaudiere.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonFactureChaudiere)
        {
            startActivity(new Intent(this,FormChaudiere.class));
        }
    }
}

