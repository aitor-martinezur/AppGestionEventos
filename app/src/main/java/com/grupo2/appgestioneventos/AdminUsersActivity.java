package com.grupo2.appgestioneventos;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import java.util.ArrayList;

public class AdminUsersActivity extends MenuActivity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adminusers);


         //funcionalidad del boton para crear usuario
         Button botonAnadirUsuario = findViewById(R.id.anadirUsuario);
         botonAnadirUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //pasa los valores a la siguiente actividad y la inicia
                 Intent k = new Intent(AdminUsersActivity.this, AnadirUsuarioActivity.class);
                 startActivity(k);
             }
         });
         //funcionalidad del boton actualizar usuario
         Button botonActualizarUsuario = findViewById(R.id.actualizarUsuario);
         botonActualizarUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //pasa los valores a la siguiente actividad y la inicia
                 Intent k = new Intent(AdminUsersActivity.this, ActualizarUsuarioActivity.class);
                 startActivity(k);
             }
         });
         //funcionalidad del boton borrar usuario
         Button botonBorrarUsuario = findViewById(R.id.borrarUsuario);
         botonBorrarUsuario.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //pasa los valores a la siguiente actividad y la inicia
                 Intent k = new Intent(AdminUsersActivity.this, BorrarUsuarioActivity.class);
                 startActivity(k);
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
         startActivity(new Intent(AdminUsersActivity.this, MenuActivity.class));
         this.finish();
    }
}
