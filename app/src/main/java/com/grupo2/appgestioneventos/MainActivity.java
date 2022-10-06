package com.grupo2.appgestioneventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
<<<<<<< Updated upstream
}
=======

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

     Recuperar Datos Ejemplo(Puede sobrar alguna llave CUIDADO!!!):

     btnEscribir.setOnClickListener()
        {
            @Override
            public void Onclick(View view){
                tvNombreApellido = findViewById(R.id.tvNombreApellido);
                tvEmail=findViewById(R.id.tvEmail);

                db.collection("usuarios").get()
                    .addCompleteListener(){
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task){
                            if(task.isSuccesfull()){
                                for(QueryDocumentSnapshot document: task.getResult()){
                                    Log.d(TAG,document.getId + "=>" + document.getData());
                                    Map<String,Object> userRead = document.getData();
                                    tvNombreApellido.setText(s);
                                    tvEmail.setText(userRead.get("email").toString());
                                }
                            }else{
                                Log.d(TAG,"Error al recuperar los datos")
                            }
                        }
                    }
            }

        }
    }
*/
>>>>>>> Stashed changes
