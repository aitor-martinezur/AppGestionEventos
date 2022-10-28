package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class AdminContactsActivity extends MenuActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.admincontact);

        //funcionalidad boton anadir
        Button botonAnadirContacto = findViewById(R.id.AddContact);
        botonAnadirContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminContactsActivity.this, AnadirContactoActivity.class));
            }
        });

        //funcionalidad boton actualizar
        Button botonActualizarContacto = findViewById(R.id.UpdateContact);
        botonActualizarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminContactsActivity.this, ActualizarContactoActivity.class));
            }
        });

        //funcionalidad boton borrar
        Button botonBorrarContacto = findViewById(R.id.DeleteContact);
        botonBorrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminContactsActivity.this, BorrarContactoActivity.class));
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
        startActivity(new Intent(AdminContactsActivity.this, MenuActivity.class));
        this.finish();
    }
}
