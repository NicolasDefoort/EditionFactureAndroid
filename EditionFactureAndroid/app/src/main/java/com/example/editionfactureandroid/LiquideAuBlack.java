package com.example.editionfactureandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class LiquideAuBlack extends AppCompatActivity implements View.OnClickListener {

    EditText nomBlack,prenomBlack, sommeBlack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.liquideblack);

        Button button = findViewById(R.id.buttonBlack);
        nomBlack = findViewById(R.id.editTextNomBlack);
        prenomBlack = findViewById(R.id.editTextPrenomBlack);
        sommeBlack = findViewById(R.id.editTextSommeBlack);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = nomBlack.getText().toString();
        String firstname = prenomBlack.getText().toString();
        String total = sommeBlack.getText().toString();
        buttonShareBlack(name,firstname,total);
    }

    public void buttonShareBlack(String name, String firstname, String total){

        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("application/pdf");
        intentShare.putExtra(Intent.EXTRA_SUBJECT,"Bidon"+ " "+name+" "+ firstname);
        intentShare.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[] { "Administration@cothermie.fr "});
        intentShare.putExtra(Intent.EXTRA_TEXT   , name+ " " +firstname + " "  + total+ " €");

        startActivity(Intent.createChooser(intentShare,"Share the file ..."));
    }
}
