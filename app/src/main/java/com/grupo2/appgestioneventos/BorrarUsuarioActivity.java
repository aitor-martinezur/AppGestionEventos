package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class BorrarUsuarioActivity extends AdminUsersActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.deleteuser);

        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();

        Button borrarUsuario = findViewById(R.id.borrarUsuario);

        //funcionalidad del boton
        borrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarUsuarios(db, view, usuarios);
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

    //funcion para cargar los datos de los usuarios de la base de datos firebase
    /*
     * @param   db          la instancia de la base de datos
     * @param   v           la vista de la actividad
     * @param   usuarios    ArrayList donde se van a meter todos los usuarios que se recuperen de la base de datos
     */
    public void cargarUsuarios(FirebaseFirestore db, View v, ArrayList<Usuario> usuarios){
        final String TAG = "MyActivity";
        //llamada a la base de datos para recoger la informacion
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            //crea un usuario con la informacion recogida en esa posicion del for
                            Usuario usuario = new Usuario(Integer.parseInt(Objects.requireNonNull(document.getString("id"))), document.getString("email"), document.getString("contrasena"), document.getString("nombre"), document.getString("apellido"));
                            //añade el usuario creado al ArrayList de usuarios
                            usuarios.add(usuario);
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                    EditText email = findViewById(R.id.NuevoEmailDele);
                    EditText contrasena = findViewById(R.id.NuevaContrasenaDele);

                    //comprueba q la contrasena y el email coincidan
                    for (int i = 0; i< usuarios.size(); i++){
                        if ((email.getText().toString().equals(usuarios.get(i).getEmail()))&&(contrasena.getText().toString().equals(usuarios.get(i).getContrasena()))){
                            FuncionesUsuarios.borrarUsuario(usuarios.get(i), db);
                            Snackbar.make(v, "Usuario borrado.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else{
                            Snackbar.make(v, "El email y/o la contraseña no son correctos.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                });
    }

    //funcion para ir hacia atras
    public void retrocederPantalla(){
        startActivity(new Intent(BorrarUsuarioActivity.this, AdminUsersActivity.class));
        this.finish();
    }
}
