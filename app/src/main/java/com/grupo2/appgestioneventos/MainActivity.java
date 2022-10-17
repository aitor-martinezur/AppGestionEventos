package com.grupo2.appgestioneventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //variable para el control de la funcion carga()
    private static boolean control = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        //crea la instancia de la base de datos
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        //crea la variable para la progressbar
        final ProgressBar progressBar = findViewById(R.id.progressBar3);
        //la pone visible
        progressBar.getProgress();

        //lama a la funcion para que pase a la siguiente pantalla
        carga(control);
    }

    //funcion que espera un tiempo especifico y pasa a la siguiente pantalla
    protected void carga(boolean control){
        if(control) {
            //se queda en espera unos segundos y pasa a la siguiente pantalla
            Handler handler = new Handler();
            handler.postDelayed(() -> startActivity(new Intent(MainActivity.this, LoginActivity.class)), 2500); //2,5 segundos
        }
        MainActivity.control = false;
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