package com.grupo2.appgestioneventos;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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

        //accion del boton de login
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::login);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        TextView textDATOS = findViewById(R.id.textViewDATOS);
        textDATOS.setVisibility(View.INVISIBLE);
        usuarios = cargarUsuarios(db, textDATOS);
        for (int i = 0; i<usuarios.size(); i++){
            Log.d("mya", "pr"+i+" "+usuarios.get(i).toString());
        }





    }

    //funcion de login que coge las credenciales
    public void login(View view){
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText password = findViewById(R.id.editTextTextPassword);

        //comprueba las credenciales
        //si es administrador
        if ((email.getText().toString().equals(""))&&(password.getText().toString().equals(""))){
            //NOTIFICACION INICIO SESION ADMINISTRADOR
            Snackbar.make(view, "Sesión iniciada como administrador.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        //si es usuario
        else if((email.getText().toString().equals(""))&&(password.getText().toString().equals(""))) {
            //NOTIFICACION INICIO SESION USUARIO
            Snackbar.make(view, "Sesión iniciada.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        //credenciales incorrectas
        else{
            //NOTIFICACION CREDENCIALES INCORRECTAS
            Snackbar.make(view, "El email o la contraseña no son correctos.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    //funcion para cargar los datos de los usuarios de la base de datos firebase
    protected ArrayList<Usuario> cargarUsuarios(FirebaseFirestore db, TextView textView){
        final String TAG = "MyActivity";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            Usuario usuario = new Usuario(Integer.parseInt(document.getString("id")), document.getString("email").toString(), document.getString("contrasena").toString(), document.getString("nombre").toString(), document.getString("apellido").toString());
                            usuarios.add(usuario);
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                    for (int i=0; i<usuarios.size(); i++){
                        Log.d("MYA", ""+i+" --> "+usuarios.get(i).toString());
                    }
                });
        for (int i = 0; i<usuarios.size(); i++){
            Log.d("mya", "pr"+i+" "+usuarios.get(i).toString());
        }
        return usuarios;
    }
}
