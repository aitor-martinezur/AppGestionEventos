package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AdminContactsActivity extends MenuActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.admincontact);

        //funcionalidad boton anadir
        Button botonAnadir = findViewById(R.id.AddContact);
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminContactsActivity.this, AnadirContactoActivity.class));
            }
        });

        //funcionalidad boton actualizar
        Button botonActualizar = findViewById(R.id.UpdateContact);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminContactsActivity.this, ActualizarContactoActivity.class));
            }
        });

        //funcionalidad boton borrar
        Button botonBorrar = findViewById(R.id.DeleteContact);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminContactsActivity.this, BorrarContactoActivity.class));
            }
        });

    }
}
