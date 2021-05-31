package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class CreateDevis extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {



    private EditText lastname, firstname, address,postalCode, city,todoAddress;
    private RadioGroup radioGroup;
    private RadioButton selectedRadio;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.create_devis);


        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(this);

        lastname = findViewById(R.id.editTextLastName);
        firstname =findViewById(R.id.editTextFirstName);
        address = findViewById(R.id.editTextAddress);
        postalCode = findViewById(R.id.editTextPostalCode);
        city= findViewById(R.id.editTextCity);
        todoAddress=findViewById(R.id.editTextToDoAddress);

        Button nextButton = findViewById(R.id.buttonNextDevis);
        nextButton.setOnClickListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        selectedRadio= (RadioButton)group.findViewById(checkedId);
        //String r1=(String) selectedRadio.getText();
        //Toast.makeText(this,r1, Toast.LENGTH_LONG).show();

    }




    @Override
    public void onClick(View v) {

        String prenom = firstname.getText().toString();
        String nom = lastname.getText().toString();
        String genre = (String) selectedRadio.getText();
        String adresse = address.getText().toString();
        String codePostal = postalCode.getText().toString();
        String ville = city.getText().toString();
        String todoAdresse = todoAddress.getText().toString();


        Intent intent =new Intent(this, SecondPartDevisEdition.class);

        intent.putExtra("prenom",prenom);
        intent.putExtra("nom",nom);
        intent.putExtra("genre",genre);
        intent.putExtra("adresse",adresse);
        intent.putExtra("code postal",codePostal);
        intent.putExtra("ville",ville);
        intent.putExtra("todoAdresse",todoAdresse);

        if(v.getId()== R.id.buttonNextDevis){
            startActivity(intent);

        }
    }
}
