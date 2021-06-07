
package com.example.editionfactureandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

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



        File folder = new File(Environment.getExternalStorageDirectory() + "/Cothermie");
        System.out.println("-------------");

        System.out.println(Environment.getStorageDirectory() + "/Cothermie");
        boolean success = true;
        if (!folder.exists()) {

            success = folder.mkdir();
            Toast.makeText(this, "Directory Cothermie created", Toast.LENGTH_SHORT).show();
        }
        if (!success) {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
        }


        File folder1 = new File(Environment.getExternalStorageDirectory() + "/Cothermie"+"/Devis");
        boolean success1 = true;
        if (!folder1.exists()) {

            success1 = folder1.mkdir();
            Toast.makeText(this, "Directory Devis created", Toast.LENGTH_SHORT).show();

        }
        if (!success1) {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();

        }

        File folder2 = new File(Environment.getExternalStorageDirectory() + "/Cothermie"+"/Facture");
        boolean success2 = true;
        if (!folder2.exists()) {

            success2 = folder2.mkdir();
            Toast.makeText(this, "Directory Facture created", Toast.LENGTH_SHORT).show();
        }
        if (!success2) {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();

        }

        File folder3 = new File(Environment.getExternalStorageDirectory() + "/Cothermie"+"/Clients");
        boolean success3 = true;
        if (!folder3.exists()) {

            success3 = folder3.mkdir();
            Toast.makeText(this, "Directory Clients created", Toast.LENGTH_SHORT).show();
        }
        if (!success3) {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();

        }
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
            startActivity(new Intent(this, CreateFacture.class));
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

