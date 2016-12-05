package com.example.juan.equipamientotaller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaUsuarios extends AppCompatActivity {

    SQLiteDatabase db;

    private EditText cuadroeq;
    private EditText cuadrocod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_usuarios);

        //Se abre la base de datos (DatosLaboratorio) en modo escritura
        DatosLaboratorio laboratorio= new DatosLaboratorio(this, "DatosLaboratorio", null, 1);
        db = laboratorio.getWritableDatabase();

        cuadroeq = (EditText) findViewById(R.id.introeq);
        cuadrocod = (EditText) findViewById(R.id.introcod);

    }
    //Función del botón consultar
    public void consultar(View view) {
        String[] texto = new String[] {cuadroeq.getText().toString()};
        Cursor c = db.rawQuery(" SELECT * FROM Laboratorio WHERE nombre=? ", texto);
        if (c.moveToFirst()) {
            TextView cuadrocons = (TextView) findViewById(R.id.textcons);
            cuadrocons.setText("");
            do {
                Integer codigo = c.getInt(0);
                String nombre = c.getString(1);
                String estado = c.getString(2);
                cuadrocons.append("Codigo: " + codigo + "    Nombre: " + nombre + "  Estado: " + estado + "\n");
            } while (c.moveToNext());
        }
        cuadroeq.setText("");
    }

    //Función del botón reservar
    public void reservar(View view) {
        String[] texto = new String[] {cuadrocod.getText().toString()};
        String textocod = cuadrocod.getText().toString();
        Cursor c = db.rawQuery(" SELECT * FROM Laboratorio WHERE codigo=? ", texto);
        if (c.moveToFirst()) {;
            do {
                String estado = c.getString(2);
                if (estado.equals("ND")) {
                    Toast.makeText(getApplicationContext(), "Equipo no disponible", Toast.LENGTH_LONG).show();
                }
                if (estado.equals("D")) {
                    Toast.makeText(getApplicationContext(), "Has reservado el equipo " + textocod + "", Toast.LENGTH_LONG).show();
                    String textoestado = "ND";
                    db.execSQL("UPDATE Laboratorio SET estado='" + textoestado + "' WHERE codigo='" + textocod +"'");
                }
            } while (c.moveToNext());
        }
        cuadrocod.setText("");
    }

    //Función del botón devolver
    public void devolver(View view) {
        String[] texto = new String[] {cuadrocod.getText().toString()};
        String textocod = cuadrocod.getText().toString();
        Cursor c = db.rawQuery(" SELECT * FROM Laboratorio WHERE codigo=? ", texto);
        if (c.moveToFirst()) {;
            do {
                String estado = c.getString(2);
                if (estado.equals("D")) {
                    Toast.makeText(getApplicationContext(), "No puede devolver un equipo disponible", Toast.LENGTH_LONG).show();
                }
                if (estado.equals("ND")) {
                    Toast.makeText(getApplicationContext(), "Has devuelto el equipo " + textocod + "", Toast.LENGTH_LONG).show();
                    String textoestado = "D";
                    db.execSQL("UPDATE Laboratorio SET estado='" + textoestado + "' WHERE codigo='" + textocod +"'");
                }
            } while (c.moveToNext());
        }
        cuadrocod.setText("");
    }

    //Función del botón listar
    public void listar (View view){

        Intent solicitud = new Intent(this, VentanaDestinoListado.class);
        startActivity(solicitud);
    }
}