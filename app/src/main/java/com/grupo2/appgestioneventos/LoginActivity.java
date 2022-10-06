package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.AttachedSurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginActivity extends MainActivity {
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

        Map<String, Object>[] usuarios = cargarUsuarios(db);

        TextView textDATOS = findViewById(R.id.textViewDATOS);
        textDATOS.setText(textDATOS.getText()+"\n"+usuarios[0]);


    }

    //funcion de login que coge las credenciales
    public void login(View view){
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);

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
    protected Map<String, Object>[] cargarUsuarios(FirebaseFirestore db){
        final String TAG = "MyActivity";
        final Map<String, Object>[] usuarios = new Map[]{new HashMap<>()};
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //variable para almacenar los valores recogidos
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                usuarios[0] = document.getData();
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        //Log.d(TAG, "Array: "+usuarios.get(0));
        Map<String, Object>[] usuarios2 = usuarios;
        Log.d(TAG, "Array: "+usuarios[0]);
        return new Map[]{usuarios[0]};
    }
}
