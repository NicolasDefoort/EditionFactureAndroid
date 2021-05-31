
package com.example.editionfactureandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.main_activity);
        FloatingActionButton exitButton = findViewById(R.id.buttonExit);
        exitButton.setOnClickListener(this);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonExit)
        {
            MainActivity.this.finish();
            System.exit(0);

        }
        if(v.getId()== R.id.button1){
            startActivity(new Intent(this, CreateDevis.class));

        }
        if(v.getId()== R.id.button2){

        }
        if(v.getId()== R.id.button3){

        }
        if(v.getId()== R.id.button4){

        }
        if(v.getId()== R.id.button5){

        }
        if(v.getId()== R.id.button6){

        }





    }
}

