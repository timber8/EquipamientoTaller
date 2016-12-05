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

public class MainActivity extends AppCompatActivity {

    private EditText cuadronomb;
    private EditText cuadromat;
    private EditText cuadroclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuadronomb = (EditText) findViewById(R.id.intronom);
        cuadromat = (EditText) findViewById(R.id.intromat);
        cuadroclave = (EditText) findViewById(R.id.introclave);

    }

    //Función del botón acceso1 para usuarios
    public void acceso1 (View view) {

        String textonomb = cuadronomb.getText().toString();
        String textomat = cuadromat.getText().toString();
        int longitud1 = textonomb.length();
        int longitud2 = textomat.length();
        cuadronomb.setText("");
        cuadromat.setText("");
        if (longitud1 == 0) {
            Toast.makeText(getApplicationContext(), "Por favor, introduce nombre", Toast.LENGTH_LONG).show();
            cuadronomb.requestFocus();
            return;
        }
        if (longitud2 == 0) {
            Toast.makeText(getApplicationContext(), "Por favor, introduce matricula", Toast.LENGTH_LONG).show();
            cuadromat.requestFocus();
            return;
        }
        String mensaje = "Acceso como " + textomat;
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();


        Intent solicitud1 = new Intent(this, VentanaUsuarios.class);
        startActivity(solicitud1);
    }

    //Función del botón acceso2 para administrador
    public void acceso2 (View view) {

        String textoclave = cuadroclave.getText().toString();
        int longitud = textoclave.length();
        cuadroclave.setText("");
        if (longitud == 0) {
            Toast.makeText(getApplicationContext(), "Por favor, introduce clave", Toast.LENGTH_LONG).show();
            cuadronomb.requestFocus();
            return;
        }
        if (textoclave.equals("talleretsii")) {
            String mensaje = "Acceso como Administrador";
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

            Intent solicitud2 = new Intent(this, VentanaAdministrador.class);
            startActivity(solicitud2);

            return;
        }
        Toast.makeText(getApplicationContext(), "Clave incorrecta", Toast.LENGTH_LONG).show();
        cuadronomb.requestFocus();
    }
}
