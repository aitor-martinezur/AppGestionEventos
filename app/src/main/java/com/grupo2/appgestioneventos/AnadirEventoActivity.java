package com.grupo2.appgestioneventos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AnadirEventoActivity extends AdminEventsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.addevents);

        //referencia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //handler
        Handler handler = new Handler();

        ArrayList<Evento> eventos = new ArrayList<>();

        //funcionalidad del boton
        Button botonCrear = findViewById(R.id.btn_guardar);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarEventos(db, view, eventos);
            }
        });
        Button botonCancelar = findViewById(R.id.btn_cancelar);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnadirEventoActivity.this, AdminEventsActivity.class));
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
                    funcionCrearEvento(eventos, db);
                });
    }
    //funcion para recoger los datos y hacer la llamada a la funcion que crea el evento
    /*
     * @param   eventos     el arraylist con los contactos recogidos de la base de datos
     * @param   db          la instancia de la base de datos
     */
    public void funcionCrearEvento(ArrayList<Evento> eventos, FirebaseFirestore db){
        //boton para cuando ha metido los valores y crea el usuario
        Button botonCrear = findViewById(R.id.btn_guardar);
        botonCrear.setOnClickListener(view -> {
            //recoge los datos de los campos
            EditText nombre = findViewById(R.id.nombre_evento_edit);
            EditText descripcion = findViewById(R.id.descripcion_edit);
            EditText tipo = findViewById(R.id.tipo_edit);
            TextView creador = findViewById(R.id.tipo_creador);
            EditText fechaInicio = findViewById(R.id.fechaInicioEvento);
            EditText horaInicio = findViewById(R.id.horaInicioEvento);
            EditText fechaFin = findViewById(R.id.fechaFinEvento);
            EditText horaFin = findViewById(R.id.horaFinEvento);

            String nuevoNombre = nombre.getText().toString();
            String nuevaDescripcion = descripcion.getText().toString();
            String nuevoTipo = tipo.getText().toString();
            String nuevoCreador = creador.getText().toString();
            String nuevaFechaIn = fechaInicio.getText().toString();
            String nuevaHoraIn = horaInicio.getText().toString();
            String nuevaFechaFin = fechaFin.getText().toString();
            String nuevaHoraFin = horaFin.getText().toString();

            String nuevaFechaHoraIn = nuevaFechaIn+" "+nuevaHoraIn;
            String nuevaFechaHoraFin = nuevaFechaFin+" "+nuevaHoraFin;

            //mira cual es el ultimo id
            //recoge el id del ultimo user para usar el siguiente
            int ultimoID=0;
            for (int i = 0; i< eventos.size(); i++){
                if (eventos.get(i).getId()>ultimoID){
                    ultimoID = (eventos.get(i).getId())+1;
                }
            }

            //comprueba que no sean nulos
            if ((nuevaDescripcion.isEmpty())||(nuevoNombre.isEmpty())||(nuevoTipo.isEmpty())||(nuevaFechaIn.isEmpty())||(nuevaHoraIn.isEmpty())||(nuevaFechaFin.isEmpty())||(nuevaHoraFin.isEmpty())){
                Snackbar.make(view, "No pueden haber campos vacios.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else{
                Evento evento = new Evento(ultimoID, nuevoNombre, nuevaDescripcion, nuevoTipo, nuevoCreador, nuevaFechaHoraIn, nuevaFechaHoraFin);
                FuncionesEventos.crearEvento(evento, db);
                Snackbar.make(view, "Contacto creado.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //espera 5 segundos para devolverle a la pestaña anterior
                //handler.postDelayed(() -> startActivity(new Intent(AnadirUsuarioActivity.this, AdminUsersActivity.class)), 5000);
            }
        });
    }
    //funcion para ir hacia atras
    public void retrocederPantalla(){
        startActivity(new Intent(AnadirEventoActivity.this, AdminEventsActivity.class));
        this.finish();
    }
}
