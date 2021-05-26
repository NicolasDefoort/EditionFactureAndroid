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
import com.itextpdf.layout.borders.Border;
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

        Drawable d2= getDrawable(R.drawable.image2);
        Bitmap bitmap2=((BitmapDrawable)d2).getBitmap();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG,100,stream2);
        byte[] bitmapData2 = stream2.toByteArray();

        ImageData imageData2 = ImageDataFactory.create(bitmapData2);
        Image image2= new Image(imageData2);
        image2.setHeight(50);

        Drawable d3= getDrawable(R.drawable.image3);
        Bitmap bitmap3=((BitmapDrawable)d3).getBitmap();
        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.PNG,100,stream3);
        byte[] bitmapData3 = stream3.toByteArray();

        ImageData imageData3 = ImageDataFactory.create(bitmapData3);
        Image image3= new Image(imageData3);
        image3.setHeight(50);


        float columnWidth[]={50,50,50,50,50,50,50,50,50};
        Table table = new Table(columnWidth);

        table.addCell(new Cell(3,3).add(image1).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,1).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,1).add(image2));
        table.addCell(new Cell(3,1).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,3).add(image3).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,6).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,1).add(new Paragraph("Date : ")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph(date)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,5).add(new Paragraph("1400 Rue de Beaumetz")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("N° d'affaire : ")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("2104161/1")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("Tél : "+date)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("Fax : "+date)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("Mail : "+date)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(4,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("A l'attention de :")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("Monsieur POIRSON")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("408 Rue de Beaumetz")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("59310 SAMEON")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(4,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("NOM DU CLIENT : ")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("ADRESSE DES TRAVAUX : ")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("OBJET : ")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(2,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        document.add(table);
        document.close();

        Toast.makeText(this,"Pdf created", Toast.LENGTH_LONG).show();
        System.out.println(pdPath);

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
