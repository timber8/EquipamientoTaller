package com.example.juan.equipamientotaller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VentanaDestinoListado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_destino_listado);

        SQLiteDatabase db;
        DatosLaboratorio laboratorio= new DatosLaboratorio(this, "DatosLaboratorio", null, 1);
        db = laboratorio.getWritableDatabase();

        Cursor c = db.rawQuery(" SELECT * FROM Laboratorio", null);
        if (c.moveToFirst()) {
            TextView cuadrolistado = (TextView) findViewById(R.id.textlistado);
            cuadrolistado.setText("");
            do {
                Integer codigo = c.getInt(0);
                String nombre = c.getString(1);
                String estado = c.getString(2);
                cuadrolistado.append("Codigo: " + codigo + "\t\t\t\tNombre: " + nombre + "\t\t\t\tEstado: " + estado + "\n");
            } while (c.moveToNext());
        }
    }
}