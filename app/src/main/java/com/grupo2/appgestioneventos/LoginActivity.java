package com.grupo2.appgestioneventos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class LoginActivity extends MainActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        //crea la instancia de la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //establece el fondo animado con sus parametros
        ConstraintLayout constraintLayout = findViewById(R.id.activity_login_id);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        ArrayList<Usuario> usuarios = new ArrayList<>();

        //accion del boton de login
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            //lama a la funcion de login
            cargarUsuarios(db, v, usuarios);
        });
    }

    //funcion de login que coge las credenciales y las comprueba para hacer el inicio de sesion
    public void login(View view, ArrayList<Usuario> usuarios){
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText password = findViewById(R.id.editTextTextPassword);

        Handler handler = new Handler();


        //comprueba las credenciales
        //comprueba el email y contraseña introducidos con el contendio del array de usuarios para hacer la comprobacion
        for(int i=0; i<usuarios.size(); i++) {
            //si es administrador
            //lo comprueba con la posicion 0 del array directamnte porque el usuario administador siempre va a estar ahi
            if ((email.getText().toString().equals(usuarios.get(i).getEmail())/*"admin"*/) && (password.getText().toString().equals(usuarios.get(i).getContrasenia()/*"admin"*/))) {
                //pasa los valores a la siguiente actividad y la inicia
                Intent k = new Intent(LoginActivity.this, MenuActivity.class);
                k.putExtra("keyUsuarios", usuarios);
                k.putExtra("keyNumUsuario", i);
                startActivity(k);
                //cierra la actividad
                this.finish();
            }
            //si es usuario
            else if ((email.getText().toString().equals(usuarios.get(i).getEmail())) && (password.getText().toString().equals(usuarios.get(i).getContrasenia()))) {
                //pasa los valores a la siguiente actividad y la inicia
                Intent k = new Intent(LoginActivity.this, MenuActivity.class);
                k.putExtra("keyUsuarios",usuarios);
                k.putExtra("keyNumUsuario", i);
                startActivity(k);
                //cierra la actividad
                this.finish();
            }
            //credenciales incorrectas
            else {
                //NOTIFICACION CREDENCIALES INCORRECTAS
                Snackbar.make(view, "El email o la contraseña no son correctos.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    //funcion para cargar los datos de los usuarios de la base de datos firebase
    public ArrayList<Usuario> cargarUsuarios(FirebaseFirestore db, View v, ArrayList<Usuario> usuarios){
        final String TAG = "MyActivity";
        //llamada a la base de datos para recoger la informacion
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            //crea un usuario con la informacion recogida en esa posicion del for
                            Usuario usuario = new Usuario(Integer.parseInt(document.getString("id")), document.getString("email"), document.getString("contrasena"), document.getString("nombre"), document.getString("apellido"));
                            //añade el usuario creado al ArrayList de usuarios
                            usuarios.add(usuario);
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }

                    login(v, usuarios);
                });
        return usuarios;
    }
}