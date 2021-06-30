package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;


public class CreateFacture extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    DatabaseHelper myDb;
    private CheckBox sameName,sameAddress;
    private EditText lastname, firstname, address,postalCode, city,todoAddress,todoName;
    private RadioGroup radioGroup;
    private RadioButton selectedRadio;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.create_facture_by_id);


        myDb = new DatabaseHelper(this);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(this);



        lastname = findViewById(R.id.editTextLastName);
        firstname =findViewById(R.id.editTextFirstName);
        address = findViewById(R.id.editTextAddress);
        postalCode = findViewById(R.id.editTextPostalCode);
        city= findViewById(R.id.editTextCity);
        todoAddress=findViewById(R.id.editTextToDoAddress);
        todoName =findViewById(R.id.editTextToDoName);
        Button delete = findViewById(R.id.buttonDeleteDevis);

        delete.setOnClickListener(this);

        Button nextButton = findViewById(R.id.buttonNextDevis);
        nextButton.setOnClickListener(this);

        sameName=findViewById(R.id.checkBoxName);
        sameAddress=findViewById(R.id.checkBoxAddress);

        Intent intent =getIntent();
        lastname.setText(intent.getStringExtra("nom"));
        firstname.setText(intent.getStringExtra("prenom"));
        address.setText(intent.getStringExtra("adresse"));
        postalCode.setText(intent.getStringExtra("code postal"));
        city.setText(intent.getStringExtra("ville"));
        todoAddress.setText(intent.getStringExtra("todoAdresse"));
        todoName.setText(intent.getStringExtra("todoNom"));



        if(intent.getStringExtra("genre").equals("MONSIEUR")){
        radioGroup.check(R.id.radioButtonMr);}
        if(intent.getStringExtra("genre").equals("Madame".toUpperCase())){
            radioGroup.check(R.id.radioButtonMme);}

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

        Intent intent1 =new Intent(this, TempoFacture.class);

        intent1.putExtra("prenom",prenom);
        intent1.putExtra("nom",nom);
        intent1.putExtra("genre",genre);
        intent1.putExtra("adresse",adresse);
        intent1.putExtra("code postal",codePostal);
        intent1.putExtra("ville",ville);
        intent1.putExtra("todoAdresse",todoAdresse(adresse+whiteSpace));
        intent1.putExtra("todoNom",todonom(nom+whiteSpace+prenom+whiteSpace));

        Intent intent =getIntent();
        String test=intent.getStringExtra("id");
        intent1.putExtra("id",test);
        intent1.putExtra("objet",intent.getStringExtra("objet"));
        intent1.putExtra("designation",intent.getStringExtra("designation"));
        intent1.putExtra("puht",intent.getStringExtra("puht"));
        intent1.putExtra("quantite",intent.getStringExtra("quantite"));
        intent1.putExtra("numaffaire",intent.getStringExtra("numaffaire"));
        intent1.putExtra("tva",intent.getStringExtra("tva"));


        if(v.getId()== R.id.buttonNextDevis){
            if(genre.isEmpty()){
                Toast.makeText(this,"RadioButton obligatoire", Toast.LENGTH_LONG).show();
            }
            else if (nom.isEmpty()){
                lastname.setError("Champ obligatoire");
            }
            else if (prenom.isEmpty()){
                firstname.setError("Champ obligatoire");
            }
            else {
                startActivity(intent1);

            }

        }

        if(v.getId()== R.id.buttonDeleteDevis){
            Integer deletedRows = myDb.deleteData(test);
            if(deletedRows > 0 ){
                Toast.makeText(this,"Devis deleted", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Failed to delete devis", Toast.LENGTH_LONG).show();
            }
            startActivity(new Intent(this, MainActivity.class));
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