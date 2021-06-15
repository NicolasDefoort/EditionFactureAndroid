package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TempoInvoice extends AppCompatActivity implements View.OnClickListener {


    EditText numeroaffaire, numerofacture;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.tempo_facture);


        Button next = findViewById(R.id.buttonnextTempoFacture);
        next.setOnClickListener(this);

        numeroaffaire = findViewById(R.id.editTextnumeroaffaireTempoFacture);
        numerofacture = findViewById(R.id.editTextNumeroFactureTempoFacture);


    }

    @Override
    public void onClick(View v) {
        Intent intent3 = new Intent(this, SecondPartInvoiceEdition.class);
        String naffaire=numeroaffaire.getText().toString();
        String nfacture = numerofacture.getText().toString();

        Intent intent2 =getIntent();
        String nom = intent2.getStringExtra("nom");
        String prenom = intent2.getStringExtra("prenom");
        String genre = intent2.getStringExtra("genre").toUpperCase();
        String adresse = intent2.getStringExtra("adresse");
        String todoAdresse = intent2.getStringExtra("todoAdresse");
        String code_postal = intent2.getStringExtra("code postal");
        String ville = intent2.getStringExtra("ville");
        String todoNom = intent2.getStringExtra("todoNom");


        intent3.putExtra("numeroaffaire",naffaire);
        intent3.putExtra("nom",nom);
        intent3.putExtra("prenom",prenom);
        intent3.putExtra("genre",genre);
        intent3.putExtra("adresse",adresse);
        intent3.putExtra("todoAdresse",todoAdresse);
        intent3.putExtra("code postal",code_postal);
        intent3.putExtra("ville",ville);
        intent3.putExtra("todoNom",todoNom);
        intent3.putExtra("numerofacture",nfacture);






        startActivity(intent3);
    }
}
