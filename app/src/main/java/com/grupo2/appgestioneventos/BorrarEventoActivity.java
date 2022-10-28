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

public class BorrarEventoActivity extends AdminEventsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.deleteevents);

        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //arraylist de los contactos
        ArrayList<Evento> eventos = new ArrayList<>();

        Button borrarEvento = findViewById(R.id.borrarEvento);

        //funcionalidad del boton
        borrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarEventos(db, view, eventos);
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

    //funcion para cargar los datos de los eventos de la base de datos firebase
    /*
     * @param   db          la instancia de la base de datos
     * @param   v           la vista de la actividad
     * @param   eventos     ArrayList donde se van a meter todos los eventos que se recuperen de la base de datos
     */
    public void cargarEventos(FirebaseFirestore db, View v, ArrayList<Evento> eventos){
        final String TAG = "MyActivity";
        //llamada a la base de datos para recoger la informacion
        db.collection("eventos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            //crea un usuario con la informacion recogida en esa posicion del for
                            Evento evento = new Evento(Integer.parseInt(Objects.requireNonNull(document.getString("id"))), document.getString("nombre"), document.getString("descripcion"), document.getString("tipo"), document.getString("creador"), document.getString("fechaHoraInicio"), document.getString("fechaHoraFin"));
                            //añade el usuario creado al ArrayList de usuarios
                            eventos.add(evento);
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                    //llama a la para crear el contacto
                    funcionBorrarEvento(eventos, db);
                });
    }
    //funcion para recoger los datos y hacer la llamada a la funcion que borra el evento
    /*
     * @param   eventos     el arraylist con los eventos recogidos de la base de datos
     * @param   db          la instancia de la base de datos
     */
    public void funcionBorrarEvento(ArrayList<Evento> eventos, FirebaseFirestore db){
        //boton para cuando ha metido los valores y crea el usuario
        Button botonBorrar = findViewById(R.id.borrarEvento);
        botonBorrar.setOnClickListener(view -> {
            //recoge los datos de los campos
            EditText nombre = findViewById(R.id.NombreEventoBorrar);

            String nuevoNombre = nombre.getText().toString();

            boolean encontrado = false;
            for(int i=0; i<eventos.size(); i++){
                if (nuevoNombre.equals(eventos.get(i).getNombre())){
                    FuncionesEventos.borrarEvento(eventos.get(i), db);
                    Snackbar.make(view, "Evento borrado.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    encontrado = true;
                }
            }
            if (encontrado == false){
                Snackbar.make(view, "No hay ningun evento con ese nombre.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    //funcion para ir hacia atras
    public void retrocederPantalla(){
        startActivity(new Intent(BorrarEventoActivity.this, AdminEventsActivity.class));
        this.finish();
    }
}
