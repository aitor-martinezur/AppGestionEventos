package com.grupo2.appgestioneventos;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AnadirUsuarioActivity extends AdminUsersActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adduser);

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            usuarios = (ArrayList<Usuario>) getIntent().getExtras().getSerializable("keyUsuarios");
        }
        //referencia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //handler
        Handler handler = new Handler();

        //boton para cuando ha metido los valores y crea el usuario
        FloatingActionButton botonCrear = findViewById(R.id.nextCrearUs);
        ArrayList<Usuario> finalUsuarios = usuarios;
        botonCrear.setOnClickListener(view -> {
            //recoge los datos de los campos
            EditText email = findViewById(R.id.NuevoEmailActu);
            EditText nombre = findViewById(R.id.NuevoNombreActu);
            EditText apellido = findViewById(R.id.NuevoApellidoActu);
            EditText contrasena = findViewById(R.id.NuevaContrasenaActu);

            String nuevoEmail = email.getText().toString();
            String nuevoNombre = nombre.getText().toString();
            String nuevoApellido = apellido.getText().toString();
            String nuevaContrasena = contrasena.getText().toString();

            //mira cual es el ultimo id
            //recoge el id del ultimo user para usar el siguiente
            int ultimoID=0;
            for (int i = 0; i< finalUsuarios.size(); i++){
                if (finalUsuarios.get(i).getId()>ultimoID){
                    ultimoID = (finalUsuarios.get(i).getId())+1;
                }
            }

            //comprueba que no sean nulos
            if ((nuevoEmail.isEmpty())||(nuevoNombre.isEmpty())||(nuevoApellido.isEmpty())||(nuevaContrasena.isEmpty())){
                Snackbar.make(view, "No pueden haber campos vacios.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else{
                Usuario usuario = new Usuario(ultimoID+1, nuevoEmail, nuevaContrasena, nuevoNombre, nuevoApellido);
                FuncionesUsuarios.crearUsuario(usuario, db);
                Snackbar.make(view, "Usuario creado.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //espera 5 segundos para devolverle a la pestaña anterior
                //handler.postDelayed(() -> startActivity(new Intent(AnadirUsuarioActivity.this, AdminUsersActivity.class)), 5000);
            }
        });
    }
}
