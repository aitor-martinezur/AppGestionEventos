package com.grupo2.appgestioneventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ActualizarUsuarioActivity extends AdminUsersActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.updateuser);

        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            usuarios = (ArrayList<Usuario>) getIntent().getExtras().getSerializable("keyUsuarios");
        }

        FloatingActionButton actualizarUsuario = findViewById(R.id.nextActu);
        EditText email = findViewById(R.id.NuevoEmailActu);
        EditText nuevoNombre = findViewById(R.id.NuevoNombreActu);
        EditText nuevoApellido = findViewById(R.id.NuevoApellidoActu);
        EditText nuevaContrasena = findViewById(R.id.NuevaContrasenaActu);

        ArrayList<Usuario> finalUsuarios = usuarios;
        actualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< finalUsuarios.size(); i++){
                    if (email.getText().equals(finalUsuarios.get(i).getEmail())){
                        Usuario usuarioActualizado = new Usuario(finalUsuarios.get(i).getId(), finalUsuarios.get(i).getEmail(), nuevoNombre.getText().toString(), nuevoApellido.getText().toString(), nuevaContrasena.getText().toString());
                        FuncionesUsuarios.actualizarUsuario(usuarioActualizado, db);
                        Snackbar.make(view, "Usuario actualizado.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else {
                        Snackbar.make(view, "El email introducido no existe.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
    }
}
