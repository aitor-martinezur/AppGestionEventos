package com.grupo2.appgestioneventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BorrarUsuarioActivity extends AdminUsersActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.deleteuser);

        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            usuarios = (ArrayList<Usuario>) getIntent().getExtras().getSerializable("keyUsuarios");
        }

        Button borrarUsuario = findViewById(R.id.borrarUsuario);
        EditText email = findViewById(R.id.NuevoEmailDele);
        EditText contrasena = findViewById(R.id.NuevaContrasenaDele);

        ArrayList<Usuario> finalUsuarios = usuarios;
        //funcionalidad del boton
        borrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //comprueba q la contrasena y el email coincidan
                for (int i = 0; i< finalUsuarios.size(); i++){
                    if ((email.getText().toString().equals(finalUsuarios.get(i).getEmail()))&&(contrasena.getText().toString().equals(finalUsuarios.get(i).getContrasena()))){
                        FuncionesUsuarios.borrarUsuario(finalUsuarios.get(i), db);
                        Snackbar.make(view, "Usuario borrado.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else{
                        Snackbar.make(view, "El email y/o la contraseña no son correctos.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
    }
}
