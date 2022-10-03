package com.grupo2.appgestioneventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //variable para el control de la funcion carga()
    private static boolean control = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        //crea la variable para la progressbar
        final ProgressBar progressBar = findViewById(R.id.progressBar3);
        //la pone visible
        progressBar.getProgress();

        //lama a la funcion para que pase a la siguiente pantalla
        carga(control);

    }

    //funcion que espera un tiempo especifico y pasa a la siguiente pantalla
    protected void carga(boolean control){
        if(control) {
            //se queda en espera unos segundos y pasa a la siguiente pantalla
            Handler handler = new Handler();
            handler.postDelayed(() -> startActivity(new Intent(MainActivity.this, LoginActivity.class)), 3500); //3,5 segundos
        }
        MainActivity.control = false;
    } 
}