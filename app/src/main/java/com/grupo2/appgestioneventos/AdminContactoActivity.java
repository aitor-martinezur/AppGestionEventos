package com.grupo2.appgestioneventos;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
public class AdminContactoActivity extends MenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.admincontact);

       // ArrayList<Contactos> contactos = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
        //    contactos = (ArrayList<Contactos>) getIntent().getExtras().getSerializable("keyContactos");
        }
        Button botonAnadirContacto = findViewById(R.id.);
        //ArrayList<Usuario> finalUsuarios1 = usuarios;
        botonAnadirContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pasa los valores a la siguiente actividad y la inicia
               // Intent k = new Intent(AdminUsersActivity.this, AnadirUsuarioActivity.class);
                //k.putExtra("keyUsuarios", finalUsuarios1);
                startActivity(k);
            }
        });

    }

}
