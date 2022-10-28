package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ActualizarEventoActivity extends AdminEventsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.updateevents);


        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Evento> eventos = new ArrayList<>();

        //boton para cuando ha metido los valores y crea el usuario
        Button botonActualizar = findViewById(R.id.btn_guardar_actu);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarEventos(db, view, eventos);
            }
        });

        Button cancelar = findViewById(R.id.btn_cancelar_actu);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActualizarEventoActivity.this, AdminEventsActivity.class));
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
                    funcionActualizarEvento(eventos, db);
                });
    }

    //funcion para recoger los datos y hacer la llamada a la funcion que actualiza el evento
    /*
     * @param   eventos     el arraylist con los eventos recogidos de la base de datos
     * @param   db          la instancia de la base de datos
     */
    public void funcionActualizarEvento(ArrayList<Evento> eventos, FirebaseFirestore db){
        //boton para cuando ha metido los valores y crea el usuario
        Button botonActualizar = findViewById(R.id.btn_guardar_actu);
        botonActualizar.setOnClickListener(view -> {
            //recoge los datos de los campos
            EditText nombre = findViewById(R.id.nombre_evento_edit_actu);
            EditText descripcion = findViewById(R.id.descripcion_edit_actu);
            EditText tipo = findViewById(R.id.tipo_edit_actu);
            EditText fechaInicio = findViewById(R.id.fecha_evento_edit_actu);
            EditText horaInicio = findViewById(R.id.hora_evento_edit_actu);
            EditText fechaFin = findViewById(R.id.fecha_fin_edit_actu);
            EditText horaFin = findViewById(R.id.hora_fin_edit_actu);

            String nuevoNombre = nombre.getText().toString();
            String nuevaDescripcion = descripcion.getText().toString();
            String nuevoTipo = tipo.getText().toString();
            String nuevaFechaIn = fechaInicio.getText().toString();
            String nuevaHoraIn = horaInicio.getText().toString();
            String nuevaFechaFin = fechaFin.getText().toString();
            String nuevaHoraFin = horaFin.getText().toString();

            String nuevaFechaHoraIn = nuevaFechaIn+" "+nuevaHoraIn;
            String nuevaFechaHoraFin = nuevaFechaFin+" "+nuevaHoraFin;

            boolean encontrado = false;
            for (int i=0; i<eventos.size(); i++){
                if(nuevoNombre.equals(eventos.get(i).getNombre())){
                    //crea el nuevo contacto y lo actualiza
                    Evento evento = new Evento(eventos.get(i).getId(), nuevoNombre, nuevaDescripcion, nuevoTipo, eventos.get(i).getCreador(), nuevaFechaHoraIn, nuevaFechaHoraFin);
                    FuncionesEventos.actualizarEvento(evento, db);
                    Snackbar.make(view, "Evento actualizado.", Snackbar.LENGTH_LONG)
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
        startActivity(new Intent(ActualizarEventoActivity.this, AdminEventsActivity.class));
        this.finish();
    }
}
