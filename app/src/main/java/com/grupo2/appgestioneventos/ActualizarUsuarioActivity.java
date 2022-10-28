package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ActualizarUsuarioActivity extends AdminUsersActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.updateuser);

        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();

        FloatingActionButton actualizarUsuario = findViewById(R.id.nextActu);
        ArrayList<Usuario> finalUsuarios = usuarios;
        actualizarUsuario.setOnClickListener(new View.OnClickListener() {
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
    //funcion para ir hacia atras
    public void retrocederPantalla(){
        startActivity(new Intent(ActualizarUsuarioActivity.this, AdminUsersActivity.class));
        this.finish();
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

                    EditText email = findViewById(R.id.NuevoEmailActu);
                    EditText nuevoNombre = findViewById(R.id.NuevoNombreActu);
                    EditText nuevoApellido = findViewById(R.id.NuevoApellidoActu);
                    EditText nuevaContrasena = findViewById(R.id.NuevaContrasenaActu);

                    for(int i = 0; i< usuarios.size(); i++){
                        if (email.getText().toString().equals(usuarios.get(i).getEmail())){
                            Usuario usuarioActualizado = new Usuario(usuarios.get(i).getId(), usuarios.get(i).getEmail(), nuevoNombre.getText().toString(), nuevoApellido.getText().toString(), nuevaContrasena.getText().toString());
                            FuncionesUsuarios.actualizarUsuario(usuarioActualizado, db);
                            Snackbar.make(v, "Usuario actualizado.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else {
                            Snackbar.make(v, "El email introducido no existe.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                });
    }
}
