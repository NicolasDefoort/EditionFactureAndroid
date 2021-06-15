package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class CreateInvoice extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{


    private CheckBox sameName,sameAddress;
    private EditText lastname, firstname, address,postalCode, city,todoAddress,todoName;
    private RadioGroup radioGroup;
    private RadioButton selectedRadio;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.create_devis);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.check(R.id.radioButtonMr);

        selectedRadio=(RadioButton)findViewById(R.id.radioButtonMr);

        radioGroup.setOnCheckedChangeListener(this);

        lastname = findViewById(R.id.editTextLastName);
        firstname =findViewById(R.id.editTextFirstName);
        address = findViewById(R.id.editTextAddress);
        postalCode = findViewById(R.id.editTextPostalCode);
        city= findViewById(R.id.editTextCity);
        todoAddress=findViewById(R.id.editTextToDoAddress);
        todoName =findViewById(R.id.editTextToDoName);

        Button nextButton = findViewById(R.id.buttonNextDevis);
        nextButton.setOnClickListener(this);

        sameName=findViewById(R.id.checkBoxName);
        sameAddress=findViewById(R.id.checkBoxAddress);

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

        String whiteSpace =" ";

        Intent intent =new Intent(this, TempoInvoice.class);

        intent.putExtra("prenom",prenom);
        intent.putExtra("nom",nom);
        intent.putExtra("genre",genre);
        intent.putExtra("adresse",adresse);
        intent.putExtra("code postal",codePostal);
        intent.putExtra("ville",ville);
        intent.putExtra("todoAdresse",todoAdresse(adresse+whiteSpace));
        intent.putExtra("todoNom",todonom(nom+whiteSpace+prenom+whiteSpace));





        if(v.getId()== R.id.buttonNextDevis){
            startActivity(intent);
        }

    }
    public String todoAdresse(String adresse){
        if (sameAddress.isChecked()){
            String todoAdresse = adresse+todoAddress.getText().toString();
            return todoAdresse;
        }

        else{
            String todoAdresse = todoAddress.getText().toString();
            return todoAdresse;
        }
    }

    public String todonom(String nom){
        if (sameAddress.isChecked()){
            String todoNom = nom+todoName.getText().toString();
            return todoNom;
        }
        else{
            String todoNom = todoName.getText().toString();
            return todoNom;
        }
    }
}
