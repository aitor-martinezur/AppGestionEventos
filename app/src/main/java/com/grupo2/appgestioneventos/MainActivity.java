package com.grupo2.appgestioneventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //variable para el control de la funcion carga()
    private static boolean control = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        //crea la instancia de la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //crea la variable para la progressbar
        final ProgressBar progressBar = findViewById(R.id.progressBar3);
        //la pone visible
        progressBar.getProgress();

        //lama a la funcion para que pase a la siguiente pantalla
        Map<String, Object> usuarios = carga(control, db);

    }

    //funcion que espera un tiempo especifico y pasa a la siguiente pantalla
    protected Map carga(boolean control, FirebaseFirestore db){
        Map<String, Object> usuarios = new HashMap<>();
        if(control) {
            //llama a la funcion que recoge los datos de los usuarios de la base de datos
            usuarios = cargarUsuarios(db);

            //se queda en espera unos segundos y pasa a la siguiente pantalla
            Handler handler = new Handler();
            handler.postDelayed(() -> startActivity(new Intent(MainActivity.this, LoginActivity.class)), 2500); //2,5 segundos
        }
        MainActivity.control = false;
        return usuarios;
    }

    //funcion para cargar los datos de los usuarios de la base de datos firebase
    protected Map cargarUsuarios(FirebaseFirestore db){
        final String TAG = "MyActivity";
        Map<String, Object> usuariosa = new HashMap<>();
        Map<String, Object> finalUsuariosa = usuariosa;
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //variable para almacenar los valores recogidos
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                finalUsuariosa.put(document.getId(), document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });
        return finalUsuariosa;
    }
}

/*
    METER DATOS EN LA BASE DE DATOS
    -------------------------------
    INSTANCIA BaseDatos ->
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RECOGER DATOS ->
    Map<String, Object> user = new HashMap<>();
    user.put("name", user.getText().toString());
    ...

    ENVIAR DATOS ->
    db.collection("coleccion").document("documento").set(datos)
    .addOnSuccessListener(new OnSuccessListener<Void>(){
        @Override
        public void onSuccess(Void aVoid){
        Log.d(TAG, "LLAMADA EXITOSA");
        }
    })
    .addOnFailureListener(new OnFailureListener<Void>(){
        @Override
        public void onFailure(Void aVoid){
        Log.d(TAG, "LLAMADA ERRONEA");
        }
    })

*/