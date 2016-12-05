package com.example.juan.equipamientotaller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatosLaboratorio extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Laboratorio (codigo INTEGER PRIMARY KEY AUTOINCREMENT , nombre TEXT, estado TEXT)";

    // Constructor
    public DatosLaboratorio(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Crea la base de datos por primera vez
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);

    }


    //Cuando hay una versión nueva de la base de datos actúa
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Elimina la versión antigua
        db.execSQL("DROP TABLE IF EXISTS Laboratorio");
        //Crea la nueva versión
        db.execSQL(sqlCreate);

    }


}
