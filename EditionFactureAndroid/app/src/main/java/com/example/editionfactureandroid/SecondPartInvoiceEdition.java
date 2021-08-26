package com.example.editionfactureandroid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.RadioAccessSpecifier;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

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
import java.util.Calendar;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class SecondPartInvoiceEdition extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private float totalTVA;
    private RadioGroup radioGroup2;
    private EditText designation,quantity, puHT,object,paiementPose,acompte,solde,datefin;
    private RadioButton selectedRadio2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.second_part_facture_edition);
        designation = findViewById(R.id.editTextDesignation2);
        quantity = findViewById(R.id.editTextQuantity2);
        puHT = findViewById(R.id.editTextPUHT2);
        object = findViewById(R.id.editTextObject2);
        paiementPose=findViewById(R.id.editTextPaiementPose);
        acompte=findViewById(R.id.editTextAccompte);
        solde = findViewById(R.id.editTextSolde);
        datefin = findViewById(R.id.editTextDateTravaux);


        datefin.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    datefin.setText(current);
                    datefin.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });



        Button createFacture = findViewById(R.id.buttonCreateFacture);
        createFacture.setOnClickListener(this);

        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroupTVA2);
        radioGroup2.clearCheck();
        radioGroup2.check(R.id.radioButton155);
        selectedRadio2=(RadioButton)findViewById(R.id.radioButton155);
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
            String quantite = quantity.getText().toString();
            String puht = puHT.getText().toString();
            if (quantite.isEmpty()){
                quantity.setError("Champ obligatoire");
            }
            else if (puht.isEmpty()){
                puHT.setError("Champ obligatoire");
            }
            else{
                createPDF();
            }

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

        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateFormatter1 =DateTimeFormatter.ofPattern("ddMMyyyy");

        Intent intent =getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String genre = intent.getStringExtra("genre").toUpperCase();
        String adresse = intent.getStringExtra("adresse");
        String todoAdresse = intent.getStringExtra("todoAdresse");
        String code_postal = intent.getStringExtra("code postal");
        String ville = intent.getStringExtra("ville");
        String todoNom = intent.getStringExtra("todoNom");
        String nfacture = intent.getStringExtra("numerofacture");
        String naffaire = intent.getStringExtra("numeroaffaire");

        String designat = designation.getText().toString();
        String objet = object.getText().toString();
        String quantite = quantity.getText().toString();
        String puht = puHT.getText().toString();
        String tva = (String) selectedRadio2.getText();

        Boolean tvaReduite;
        if (tva.equals("5.5 %")||tva.equals("10 %")){
            tvaReduite=true;
        }
        else{
            tvaReduite=false;
        }

        String dateFin=datefin.getText().toString();
        String paiement_pose=paiementPose.getText().toString();
        String soldee=solde.getText().toString();
        String accompte=acompte.getText().toString();

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

        String pdPath = Environment.getExternalStorageDirectory().toString();
        File file = new File(pdPath+"/"+"Cothermie"+"/"+"Facture",upperCaseFirst(nom)+upperCaseFirst(prenom)+LocalDate.now().format(dateFormatter1)+".pdf");
        String path = pdPath+"/"+"Cothermie"+"/"+"Facture"+upperCaseFirst(nom)+upperCaseFirst(prenom)+LocalDate.now().format(dateFormatter1)+".pdf";
        OutputStream outputStream= new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        DeviceRgb blueFont = new DeviceRgb(23, 102, 165);
        DeviceRgb grayBg = new DeviceRgb(219, 220, 221 );

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

        String whiteSpace =" ";

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
        table.addCell(new Cell(1,2).add(new Paragraph(naffaire)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,5).add(new Paragraph("59310 SAMEON")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("N° de facture : ").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph(nfacture)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("Tél : 06 87 54 52 29 ").setFontSize(10)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("Mail : cothermie@gmail.com").setFontSize(10)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(2,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("Devis à l'attention de :").setBold()).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,4).add(new Paragraph(genre +whiteSpace+upperCaseFirst(nom)+whiteSpace+ upperCaseFirst(prenom))).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,5).add(new Paragraph("Date de fin des travaux : "+ dateFin)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph(adresse)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph(code_postal +whiteSpace+ ville)).setBorder(Border.NO_BORDER));



        table.addCell(new Cell(1,9).add(new Paragraph(("NOM DU CLIENT :  ")+(todoNom)).setFontSize(11)).setBorder(Border.NO_BORDER));
        if (tvaReduite==true){
            table.addCell(new Cell(1,9).add(new Paragraph("ADRESSE DES TRAVAUX : " + todoAdresse ).setFontSize(11)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,9).add(new Paragraph("(immeuble achevé depuis plus de deux ans)").setFontSize(9)).setBorder(Border.NO_BORDER));
        }
        else{
            table.addCell(new Cell(1,9).add(new Paragraph("ADRESSE DES TRAVAUX : " + todoAdresse).setFontSize(11)).setBorder(Border.NO_BORDER));

        }

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

        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("SOUS-TOTAL").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+ df.format(totalHt))).setBackgroundColor(grayBg));


        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("T.V.A "+tva).setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+df.format(totalTVA))));

        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("MONTANT TOTAL TTC").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+df.format(TOTAL))).setBackgroundColor(grayBg));

        if (!accompte.matches("")){

            table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("Acompte à la commande").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+accompte)));

        }

        if (!paiement_pose.matches("")){
            table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("Paiement à la pose").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+paiement_pose)));

        }

        if (!soldee.matches("")){
            table.addCell(new Cell(1,4).add(new Paragraph("")).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,3).add(new Paragraph("Solde à la fin des travaux").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+soldee)));

        }

        if ((!soldee.matches(""))||(!paiement_pose.matches(""))||(!accompte.matches(""))){

            float paiementPoseFloat, soldeFloat, acompteFloat ;
            if (soldee.matches("")){
                soldeFloat=0;
            }
            else{
                soldeFloat =new Float(soldee).floatValue();
            }
            if (paiement_pose.matches("")){
                paiementPoseFloat=0;
            }
            else{
                paiementPoseFloat=new Float(paiement_pose).floatValue();
            }
            if (accompte.matches("")){
                acompteFloat=0;
            }
            else{
                acompteFloat=new Float(accompte).floatValue();
            }

            float netAPayer=TOTAL-soldeFloat-acompteFloat-paiementPoseFloat;

            table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("NET A PAYER").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
            table.addCell(new Cell(1,2).add(new Paragraph("€"+whiteSpace+whiteSpace+df.format(netAPayer))).setBackgroundColor(grayBg));

        }

        table.addCell(new Cell(1,9).add(new Paragraph("\n")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,5).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("Date d'échéance :").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph(LocalDate.now().plusDays(7).format(dateFormatter))).setBorder(Border.NO_BORDER));



        table.addCell(new Cell(1,3).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("IBAN : ").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("FR76 1627 5500 0008 0001 9443 058")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,3).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,2).add(new Paragraph("BIC : ").setBold().setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,4).add(new Paragraph("CEPAFRPP627")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(4,9).add(new Paragraph("\n")).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(1,9).add(new Paragraph("Conditions générales de vente :").setFontSize(6).setFontColor(blueFont)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(5,9).add(new Paragraph("Sauf dérogation expresse, la garantie de toute marchandise vendue sera assurée par et dans les limites des conditions de notre fabricant ou fournisseur.\n" +
                "La SARL COTHERMIE conserve la propriété du matériel installé jusqu'au paiement effectif de l'intégralité des factures émises.\n" +
                "Le transfert des risques de perte, vol et détérioration du matériel et dommages qu'il pourrait occasionner est transféré à l'acheteur lors de la livraison.\n" +
                "Les éventuels frais de stationnement seront facturés en sus.").setFontSize(5).setFontColor(blueFont)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1,9).add(new Paragraph("\n")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,9).add(new Paragraph("SARL au capital 10 000 € - 524 128 626 RM 590 - NAF 4322B - n° TVA FR79524128626 - n° siret 52412862600024\n" +
                "Siège social : 1400 Rue de Beaumetz - 59310 Saméon\n" +
                "Responsabilité Civile et Décenale - Police n°165765760001 - Assureur : GROUPAMA").setTextAlignment(TextAlignment.CENTER).setFontSize(6)).setBorder(Border.NO_BORDER));




        document.add(table);
        document.close();
        String subject = designat + " " + nom.toUpperCase() + " " +prenom.toUpperCase();

        ActivityCompat.requestPermissions(this,new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        String stringFile = Environment.getExternalStorageDirectory().getPath()+ File.separator+"Cothermie"+File.separator+"Facture"+File.separator+upperCaseFirst(nom)+upperCaseFirst(prenom)+LocalDate.now().format(dateFormatter1)+".pdf";

        buttonShareFile(stringFile, subject);
        Toast.makeText(this,"Pdf created", Toast.LENGTH_LONG).show();

    }
    public void buttonShareFile(String stringFile, String subject){

        File file = new File (stringFile);
        if (!file.exists()){
            Toast.makeText(this,"File doesn't exists",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("application/pdf");
        intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file));
        intentShare.putExtra(Intent.EXTRA_BCC,
                new String[] { "administration@cothermie.fr"});
        intentShare.putExtra(Intent.EXTRA_TEXT,"Bonjour, \n Veuillez trouver en pièce jointe la facture correspondant aux travaux réalisés. \n\n Salutations, \n\n Corentin LICTEVOUT \n SARL COTHERMIE");

        startActivity(Intent.createChooser(intentShare,"Share the file ..."));
    }
    //





}