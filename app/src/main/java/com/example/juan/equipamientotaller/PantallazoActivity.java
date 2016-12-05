package com.example.juan.equipamientotaller;

// Clase para el pantallazo de incio

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Window;
import java.util.Timer;
import java.util.TimerTask;

public class PantallazoActivity extends AppCompatActivity {
    private static final long RETRASO = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pantalla_inicial);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent solicitud = new Intent().setClass(
                        PantallazoActivity.this, MainActivity.class);
                startActivity(solicitud);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, RETRASO);
    }
}