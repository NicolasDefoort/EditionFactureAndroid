package com.example.editionfactureandroid;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FormChaudiere extends AppCompatActivity implements View.OnClickListener {

    EditText nameText,firstNameText,autreText,dateText,phoneText,prixText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.activity_chaudiere_form);


        Button CreatePDF = findViewById(R.id.buttonCreatePDF);
        CreatePDF.setOnClickListener(this);


        nameText = findViewById(R.id.editTextName);
        firstNameText = findViewById(R.id.editTextFirstName);
        autreText = findViewById(R.id.editTextAutre);
        dateText = findViewById(R.id.editTextDate);
        phoneText = findViewById(R.id.editTextPhone);
        prixText = findViewById(R.id.editTextPrix);

        TextView nameTitle = findViewById(R.id.textViewName);
        TextView firstNameTitle = findViewById(R.id.textViewFirstName);
        TextView autreTitle = findViewById(R.id.textViewAutre);
        TextView phoneTitle = findViewById(R.id.textViewPhone);
        TextView prixTitle = findViewById(R.id.textViewPrix);
        TextView dateTitle = findViewById(R.id.textViewDate);

    }



    private void createPdf() throws FileNotFoundException {


        String name = nameText.getText().toString();
        String firstName = firstNameText.getText().toString();
        String date = dateText.getText().toString();
        String phone = phoneText.getText().toString();
        String autre = autreText.getText().toString();
        String prix = prixText.getText().toString();

        String pdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdPath,"myPDF2.pdf");
        OutputStream outputStream= new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);


        Drawable d1= getDrawable(R.drawable.image1);
        Bitmap bitmap1=((BitmapDrawable)d1).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG,100,stream1);
        byte[] bitmapData1 = stream1.toByteArray();

        ImageData imageData1 = ImageDataFactory.create(bitmapData1);
        Image image1 = new Image(imageData1);
        image1.setHeight(50);


        float columnWidth[]={80,80,80,80,80,80};
        Table table = new Table(columnWidth);

        table.addCell(new Cell().add(new Paragraph(name)));
        table.addCell(new Cell().add(new Paragraph(firstName)));
        table.addCell(new Cell().add(new Paragraph(date)));
        table.addCell(new Cell().add(new Paragraph(phone)));
        table.addCell(new Cell().add(new Paragraph(autre)));

        table.addCell(new Cell().add(new Paragraph(prix)));
        table.addCell(new Cell().add(image1));
        table.addCell(new Cell().add(new Paragraph("gdrw")));
        table.addCell(new Cell().add(new Paragraph("rgd")));
        table.addCell(new Cell().add(new Paragraph("vdrd")));
        table.addCell(new Cell().add(new Paragraph("vdr")));

        document.add(table);
        document.close();
        Toast.makeText(this,"Pdf created", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        try {
            createPdf();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
