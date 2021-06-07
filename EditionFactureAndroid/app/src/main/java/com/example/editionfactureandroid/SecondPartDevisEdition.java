package com.example.editionfactureandroid;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SecondPartDevisEdition extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private float totalTVA;
    private RadioGroup radioGroup2;
    private EditText designation,quantity, puHT,object;
    private RadioButton selectedRadio2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.second_part_devis_edition);
        designation = findViewById(R.id.editTextDesignation);
        quantity = findViewById(R.id.editTextQuantity);
        puHT = findViewById(R.id.editTextPUHT);
        object = findViewById(R.id.editTextObject);

        Button createDevis = findViewById(R.id.buttonCreateDevis);
        createDevis.setOnClickListener(this);

        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroupTVA);
        radioGroup2.clearCheck();
        radioGroup2.setOnCheckedChangeListener(this);



    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        selectedRadio2= (RadioButton)group.findViewById(checkedId);



        //String r1=(String) selectedRadio2.getText();
        //Toast.makeText(this,r1, Toast.LENGTH_LONG).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        try {
            createPDF();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createPDF() throws FileNotFoundException{

        DecimalFormat df = new DecimalFormat ( ) ;
        df.setMaximumFractionDigits ( 2 ) ;
        df.setMinimumFractionDigits ( 2 ) ;
        df.setDecimalSeparatorAlwaysShown ( true ) ;

        Intent intent =getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String genre = intent.getStringExtra("genre").toUpperCase();
        String adresse = intent.getStringExtra("adresse");
        String todoAdresse = intent.getStringExtra("todoAdresse");
        String code_postal = intent.getStringExtra("code postal");
        String ville = intent.getStringExtra("ville");
        String todoNom = intent.getStringExtra("todoNom");
        String numeroaffaire = intent.getStringExtra("numeroaffaire");


        String designat = designation.getText().toString();
        String objet = object.getText().toString();
        String quantite = quantity.getText().toString();
        String puht = puHT.getText().toString();
        String tva = (String) selectedRadio2.getText();

        int quantiteInt = new Integer(quantite).intValue();
        float puhtfloat = new Float(puht).floatValue();

        float totalHt=quantiteInt*puhtfloat;

        String stringTotalHt=Float.toString(totalHt);

        int radioButtonID = radioGroup2.getCheckedRadioButtonId();





        if (radioButtonID==R.id.radioButton155){
            totalTVA= (float) (totalHt*0.05);
        }

        else if (radioButtonID==R.id.radioButton210){
            totalTVA= (float) (totalHt*0.1);
        }

        else if (radioButtonID==R.id.radioButton320){
            totalTVA= (float) (totalHt*0.2);
        }
        else{
            Toast.makeText(this,"ERROR", Toast.LENGTH_LONG).show();
        }

        float TOTAL = totalTVA+totalHt;


        String whiteSpace =" ";
        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");


         /*File folder = new File(Environment.getExternalStorageDirectory() + "/Cothermie"+"/Clients"+upperCaseFirst(nom)+upperCaseFirst(prenom));
        boolean success = true;
        if (!folder.exists()) {
            Toast.makeText(this, "Directory Client created", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (!success) {
            Toast.makeText(this, "Failed - Error", Toast.LENGTH_SHORT).show();
        }*/



        String pdPath = Environment.getExternalStorageDirectory().toString();
        File file = new File(pdPath+"/"+"Cothermie"+"/"+"Devis",upperCaseFirst(nom)+upperCaseFirst(prenom)+".pdf");

        OutputStream outputStream= new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        DeviceRgb blueFont = new DeviceRgb(23, 102, 165);
        DeviceRgb grayBg = new DeviceRgb(219, 220, 221  );

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

        Drawable d3= getDrawable(R.drawable.devis);
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

        table.addCell(new Cell(3,1).add(image2).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(3,1).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,3).add(image3).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,6).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,1).add(new Paragraph("Date : ").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph(LocalDate.now().format(dateFormatter))).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,5).add(new Paragraph("1400 Rue de Beaumetz")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("N° d'affaire : ").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph(numeroaffaire)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,7).add(new Paragraph("59310 SAMEON")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("Offre  valable 1 mois").setFontSize(8)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("Tél : 06 87 54 52 29 ").setFontSize(10)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("Fax : 03 27 41 52 47").setFontSize(10)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("Mail : cothermie@gmail.com").setFontSize(10)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(4,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("Devis à l'attention de :").setBold()).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,4).add(new Paragraph(genre +whiteSpace+upperCaseFirst(nom)+whiteSpace+ upperCaseFirst(prenom) )).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph(adresse)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph(code_postal +whiteSpace+ ville)).setBorder(Border.NO_BORDER));
        System.out.println("/////////////////////////////");
        table.addCell(new Cell(1,9).add(new Paragraph(("NOM DU CLIENT :  ")+(todoNom)).setFontSize(11)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("ADRESSE DES TRAVAUX : " + todoAdresse).setFontSize(11)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("OBJET : "+objet).setFontSize(11)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(2,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,5).add(new Paragraph("Désignation").setBold()).setBackgroundColor(grayBg));
        table.addCell(new Cell(1,1).add(new Paragraph("Quantité").setBold()).setBackgroundColor(grayBg));
        table.addCell(new Cell(1,1).add(new Paragraph("PU HT").setBold()).setBackgroundColor(grayBg));
        table.addCell(new Cell(1,2).add(new Paragraph("Total HT").setBold()).setBackgroundColor(grayBg));

        table.addCell(new Cell(1,5).add(new Paragraph(designat)));
        table.addCell(new Cell(1,1).add(new Paragraph(quantite)));
        table.addCell(new Cell(1,1).add(new Paragraph("€"+ whiteSpace +whiteSpace+puht)));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+df.format(totalHt))));
        System.out.println("/////////////////////////////");
        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("SOUS-TOTAL").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+ df.format(totalHt))).setBackgroundColor(grayBg));
        System.out.println("/////////////////////////////");

        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("T.V.A "+tva).setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+df.format(totalTVA))));

        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("TOTAL ").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+df.format(TOTAL))).setBackgroundColor(grayBg));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,3).add(new Paragraph("CONDITIONS DE REGLEMENT").setFontSize(11).setBold()).setBackgroundColor(grayBg).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,6).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("30 % à la commande").setFontSize(10)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("60 % à la pose du matériel").setFontSize(10)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("10 % à la facturation fin de travaux").setFontSize(10)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));


        table.addCell(new Cell(1,3).add(new Paragraph("MENTION MANUSCRITE").setFontSize(11).setBold()).setBackgroundColor(grayBg).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,6).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("Devis reçu avant exécution des travaux - Bon pour accord").setFontSize(10)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("\n")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,4).add(new Paragraph("DATE").setFontSize(8)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,5).add(new Paragraph("SIGNATURE").setFontSize(8)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(3,4).add(new Paragraph(LocalDate.now().format(dateFormatter))).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,5).add(image3).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(4,9).add(new Paragraph("\n\n")).setBorder(Border.NO_BORDER));


        table.addCell(new Cell(1,9).add(new Paragraph("Conditions générales de vente :").setFontSize(6).setFontColor(blueFont)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(5,9).add(new Paragraph("Sauf dérogation expresse, la garantie de toute marchandise vendue sera assurée par et dans les limites des conditions de notre fabricant ou fournisseur.\n" +
                "La SARL COTHERMIE conserve la propriété du matériel installé jusqu'au paiement effectif de l'intégralité des factures émises.\n" +
                "Le transfert des risques de perte, vol et détérioration du matériel et dommages qu'il pourrait occasionner est transféré à l'acheteur lors de la livraison.\n" +
                "Les éventuels frais de stationnement seront facturés en sus.").setFontSize(5).setFontColor(blueFont)).setBorder(Border.NO_BORDER));



        document.add(table);
        document.close();


        Toast.makeText(this,"Pdf created", Toast.LENGTH_LONG).show();
        System.out.println(pdPath);


    }
}
