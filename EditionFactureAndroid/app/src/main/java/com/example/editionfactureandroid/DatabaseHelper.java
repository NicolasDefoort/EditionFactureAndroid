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
        public static final String COL_4 = "Total";






    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FIRSTNAME TEXT ," +
                "LASTNAME TEXT , " +
                "TOTAL FLOAT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String firstname, String lastname,String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,price);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;

        }
        else{
            return true;
        }
    }
}
