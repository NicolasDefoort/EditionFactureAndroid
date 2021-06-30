package com.example.editionfactureandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TempoInvoice extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper1 db1;
    EditText numeroaffaire, numerofacture;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.tempo_facture);
        db1= new DatabaseHelper1( this);
        Button next = findViewById(R.id.buttonnextTempoFacture);
        next.setOnClickListener(this);

        numeroaffaire = findViewById(R.id.editTextnumeroaffaireTempoFacture);
        numerofacture = findViewById(R.id.editTextNumeroFactureTempoFacture);


        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("yyMMdd");
        //NO DATA
        Cursor cur = db1.getAllData();
        System.out.println("__________________________________________");
        System.out.println(cur.getInt(0) != null);
        if (cur != null) {

            int a = cur.getInt(0);
            String aa = String.valueOf(a);
            int b = cur.getInt(1);
            String c = cur.getString(2);

            if (c != LocalDate.now().format(DateTimeFormatter.ofPattern("dd"))) {
                db1.updateData(aa, 1, LocalDate.now().format(DateTimeFormatter.ofPattern("dd")));
            } else {
                db1.updateData(aa, b + 1, LocalDate.now().format(DateTimeFormatter.ofPattern("dd")));
            }
            Cursor cur1 = db1.getAllData();

            int d = cur1.getInt(0);
            String dd = String.valueOf(a);
            int e = cur1.getInt(1);
            String ee = String.valueOf(e);
            String f = cur1.getString(2);

            numeroaffaire.setText(LocalDate.now().format(dateFormatter) + e);
            numerofacture.setText(LocalDate.now().format(dateFormatter) + e);
        }
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
