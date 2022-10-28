package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class AnadirUsuarioActivity extends AdminUsersActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adduser);

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //referencia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //handler
        Handler handler = new Handler();

        //boton para cuando ha metido los valores y crea el usuario
        FloatingActionButton botonCrear = findViewById(R.id.nextCrearUs);
        botonCrear.setOnClickListener(view -> {
            cargarUsuarios(db, view, usuarios);
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
                    for (int i = 0; i< usuarios.size(); i++){
                        if (usuarios.get(i).getId()>ultimoID){
                            ultimoID = (usuarios.get(i).getId())+1;
                        }
                    }

                    //comprueba que no sean nulos
                    if ((nuevoEmail.isEmpty())||(nuevoNombre.isEmpty())||(nuevoApellido.isEmpty())||(nuevaContrasena.isEmpty())){
                        Snackbar.make(v, "No pueden haber campos vacios.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else{
                        Usuario usuario = new Usuario(ultimoID+1, nuevoEmail, nuevaContrasena, nuevoNombre, nuevoApellido);
                        FuncionesUsuarios.crearUsuario(usuario, db);
                        Snackbar.make(v, "Usuario creado.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        //espera 5 segundos para devolverle a la pestaña anterior
                        //handler.postDelayed(() -> startActivity(new Intent(AnadirUsuarioActivity.this, AdminUsersActivity.class)), 5000);
                    }
                });
    }

    //funcion para ir hacia atras
    public void retrocederPantalla(){
        startActivity(new Intent(AnadirUsuarioActivity.this, AdminUsersActivity.class));
        this.finish();
    }
}
