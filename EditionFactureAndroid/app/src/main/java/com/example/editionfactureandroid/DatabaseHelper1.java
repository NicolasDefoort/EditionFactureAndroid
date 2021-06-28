package com.example.editionfactureandroid;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.itextpdf.layout.element.Tab;

public class DatabaseHelper1  extends SQLiteOpenHelper {



    public static final String DATABASE_NAME1 = "database1.db";
    public static final String TABLE_NAME = "table_test";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NUMERO";
    public static final String COL_3 = "JOUR";









    public DatabaseHelper1( Context context) {
        super(context, DATABASE_NAME1, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NUMERO TEXT   , " +
                "JOUR TEXT"+
                ")" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String numero,String jour){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,numero);
        contentValues.put(COL_3,jour);


        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res =db.rawQuery("SELECT * FROM " +
                TABLE_NAME+" ORDER BY ID DESC", null);
        return res;
    }



    public Integer deleteData(String id){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }

    public boolean updateData(String id,String numero,String jour){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,numero);
        contentValues.put(COL_3,jour);
        db.update(TABLE_NAME,contentValues, " ID = ?", new String[]{id});
        return true;

    }
}
