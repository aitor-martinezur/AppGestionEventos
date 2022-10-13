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
        TextView textDATOS = findViewById(R.id.textViewDATOS);
        textDATOS.setVisibility(View.INVISIBLE);

        //accion del boton de login
        Button button = findViewById(R.id.button);
        ArrayList<Usuario> finalUsuarios = usuarios;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //lama a la funcion de login
                login(v, finalUsuarios);
            }
        });

        //carga los usuarios de la base de datos
        usuarios = cargarUsuarios(db, textDATOS);

        for (int i = 0; i < usuarios.size(); i++) {
            Log.d("mya", "pr" + i + " " + usuarios.get(i).toString());
        }
    }

    //funcion de login que coge las credenciales y las comprueba para hacer el inicio de sesion
    public void login(View view, ArrayList<Usuario> usuarios){
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
    public ArrayList<Usuario> cargarUsuarios(FirebaseFirestore db, TextView textView){
        final String TAG = "MyActivity";
        final ArrayList<Usuario> usuarios = new ArrayList<>();
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
                    //PRUEBAS
                    for (int i=0; i<usuarios.size(); i++){
                        Log.d("MYA", ""+i+" --> "+usuarios.get(i).toString());
                        //textView.setText(textView.getText()+"\n"+usuarios.get(i).toString());
                    }
                });
        Log.d("MYAC_PRUEBA", ""+usuarios.size());
        for (int i=0; i<usuarios.size(); i++){
            Log.d("MYA2", ""+i+" --> "+usuarios.get(i).toString());
            //textView.setText(textView.getText()+"\n"+usuarios.get(i).toString());
        }
        return usuarios;
    }
}
