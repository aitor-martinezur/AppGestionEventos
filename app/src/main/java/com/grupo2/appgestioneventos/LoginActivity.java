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

        //accion del boton de login
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::login);

        ArrayList<String> usuarios = new ArrayList<>();
        TextView textDATOS = findViewById(R.id.textViewDATOS);
        usuarios = cargarUsuarios(db, usuarios, textDATOS);





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
    protected ArrayList<String> cargarUsuarios(FirebaseFirestore db, ArrayList<String> datos, TextView textView){
        final String TAG = "MyActivity";
        //estructura de guardado de usuarios
        //-> [usuario]-[id],[email],[pass] ([cant. usuarios][3])
        String[][] usuarioFormateado = new String[0][0];
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            datos.add(document.getData().toString());
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }

                    //Log.d(TAG, "Array: "+usuarios.get(0));
                    for(int i=0; i<datos.size(); i++){
                        Log.d("MyA", "Array(pos->"+i+"): "+ datos.get(i));
                    }
                    for(int i=0; i<datos.size(); i++) {
                        textView.setText(textView.getText() + "\n" + datos.get(i));
                    }
                    //limpiar los datos para guardarlos
                    //-> [usuario]-[id],[email],[pass] ([cant. usuarios][3])
                    String[][] usuario = new String[datos.size()][3];
                    for (int i=0; i<datos.size(); i++){
                        usuario[i][0] = datos.get(i).replace("{", "");
                    }
                    for(int i=0; i<3; i++) {
                        usuario[0][i] = usuario[i][0].replace("{contrasena=admin, id=1, email=admin}","1");
                    }
                });
        return datos;
    }
}
