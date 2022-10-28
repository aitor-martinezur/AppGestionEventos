package com.grupo2.appgestioneventos;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminEventsActivity extends MenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adminevents);

        //funcionalidad boton anadir
        Button botonAnadir = findViewById(R.id.AddEvent);
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminEventsActivity.this, AnadirEventoActivity.class));
            }
        });

        //funcionalidad boton actualizar
        Button botonActualizar = findViewById(R.id.updateEvent);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminEventsActivity.this, ActualizarEventoActivity.class));
            }
        });

        //funcionalidad boton borrar
        Button botonBorrar = findViewById(R.id.DeleteEvent);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminEventsActivity.this, BorrarEventoActivity.class));
            }
        });

        //funcionalidad del boton para ir hacia atras
        ImageButton botonAtras = findViewById(R.id.action_settings_back);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrocederPantalla();
            }
        });
    }
    //funcion para ir hacia atras
    public void retrocederPantalla(){
        startActivity(new Intent(AdminEventsActivity.this, MenuActivity.class));
        this.finish();
    }
}

