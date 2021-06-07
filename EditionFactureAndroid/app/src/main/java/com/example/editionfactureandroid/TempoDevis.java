package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TempoDevis extends AppCompatActivity implements View.OnClickListener {


    EditText numeroaffaire;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.tempo_devis);


        Button next = findViewById(R.id.buttonNextTempoDevis);
        next.setOnClickListener(this);

         numeroaffaire = findViewById(R.id.editTextAffaireNumberTempoDevis);


    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(this, SecondPartDevisEdition.class);
        String naffaire=numeroaffaire.getText().toString();

        Intent intent =getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String genre = intent.getStringExtra("genre").toUpperCase();
        String adresse = intent.getStringExtra("adresse");
        String todoAdresse = intent.getStringExtra("todoAdresse");
        String code_postal = intent.getStringExtra("code postal");
        String ville = intent.getStringExtra("ville");
        String todoNom = intent.getStringExtra("todoNom");


        intent1.putExtra("numeroaffaire",naffaire);
        intent1.putExtra("nom",nom);
        intent1.putExtra("prenom",prenom);
        intent1.putExtra("genre",genre);
        intent1.putExtra("adresse",adresse);
        intent1.putExtra("todoAdresse",todoAdresse);
        intent1.putExtra("code postal",code_postal);
        intent1.putExtra("ville",ville);
        intent1.putExtra("todoNom",todoNom);



        startActivity(intent1);
    }
}
