package com.example.editionfactureandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {



        public static final String DATABASE_NAME = "database.db";
        public static final String TABLE_NAME = "devis_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "Firstname";
        public static final String COL_3 = "Lastname";
        public static final String COL_4 = "Adresse";
        public static final String COL_5 = "Ville";
        public static final String COL_6 = "CodePostal";
        public static final String COL_7 = "Genre";
        public static final String COL_8 = "Objet";
        public static final String COL_9 = "TodoNom";
        public static final String COL_10 = "TodoAdresse";
        public static final String COL_11 = "Désignation";
        public static final String COL_12 = "Quantité";
        public static final String COL_13 = "PuHt";
        public static final String COL_14 = "Tva";
        public static final String COL_15 = "NuméroAffaire";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FIRSTNAME TEXT ," +
                "LASTNAME TEXT , " +
                "ADRESSE TEXT, " +
                "VILLE TEXT, " +
                "CODEPOSTAL TEXT," +
                "GENRE TEXT,"+
                "OBJET TEXT, "+
                "TODONOM TEXT, "+
                "TODOADRESSE TEXT, "+
                "DESIGNATION TEXT, "+
                "QUANTITE TEXT, "+
                "PUHT TEXT, "+
                "TVA TEXT, "+
                "NUMAFFAIRE TEXT"+
                ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String firstname, String lastname,String adresse, String ville,String codePostal,String genre,String objet,String todoNom ,String todoAdresse, String puht, String tva){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,adresse);
        contentValues.put(COL_5,ville);
        contentValues.put(COL_6,codePostal);
        contentValues.put(COL_7,genre);
        contentValues.put(COL_8,objet);
        contentValues.put(COL_9,todoNom);
        contentValues.put(COL_10,todoAdresse);
        contentValues.put(COL_13,puht);
        contentValues.put(COL_14,tva);





        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;

        }
        else{
            return true;
        }
    }
}
