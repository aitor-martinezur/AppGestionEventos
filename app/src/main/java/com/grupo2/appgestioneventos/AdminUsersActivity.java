package com.grupo2.appgestioneventos;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;

public class AdminUsersActivity extends MenuActivity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adminusers);

         //arraylist de los usuarios
         ArrayList<Usuario> usuarios = new ArrayList<>();
         Bundle extras = getIntent().getExtras();
         if (extras!=null) {
             usuarios = (ArrayList<Usuario>) getIntent().getExtras().getSerializable("keyUsuarios");
         }

         //funcionalidad del boton para crear usuario
         Button botonAnadirUsuario = findViewById(R.id.anadirUsuario);
         ArrayList<Usuario> finalUsuarios1 = usuarios;
         botonAnadirUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //pasa los valores a la siguiente actividad y la inicia
                 Intent k = new Intent(AdminUsersActivity.this, AnadirUsuarioActivity.class);
                 k.putExtra("keyUsuarios", finalUsuarios1);
                 startActivity(k);
             }
         });
         //funcionalidad del boton actualizar usuario
         Button botonActualizarUsuario = findViewById(R.id.actualizarUsuario);
         botonActualizarUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });
         //funcionalidad del boton borrar usuario
         Button botonBorrarUsuario = findViewById(R.id.borrarUsuario);
         ArrayList<Usuario> finalUsuarios = usuarios;
         botonBorrarUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //pasa los valores a la siguiente actividad y la inicia
                 Intent k = new Intent(AdminUsersActivity.this, BorrarUsuarioActivity.class);
                 k.putExtra("keyUsuarios", finalUsuarios);
                 startActivity(k);
             }
         });
    }
}
