package com.grupo2.appgestioneventos;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FuncionesEventos {
    //Archivo con las funciones para crear, borrar y editar eventos

    //funcion para crear un evento nuevo en la base de datos
    /*
     * @param   nuevoEvento   el objeto evento con toda la informacion para meterlo en la base de datos
     * @param   db            la instancia de la base de datos
     */
    public static void crearEvento(Evento nuevoEvento, FirebaseFirestore db){
        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("id", String.valueOf(nuevoEvento.getId()));
        data.put("nombre", nuevoEvento.getNombre());
        data.put("descripcion", nuevoEvento.getDescripcion());
        data.put("tipo", nuevoEvento.getTipo());
        data.put("creador", nuevoEvento.getCreador());
        data.put("fechaHoraInicio", nuevoEvento.getHoraFechaInicio());
        data.put("fechaHoraFin", nuevoEvento.getHoraFechaFin());

        //crea el usuario en la base de datos
        db.collection("eventos").document("evento"+(nuevoEvento.getId())).set(data);
    }

    //funcion para actualizar un evento de la base de datos
    /*
     * @param   evento      el objeto contacto con toda la informacion para meterlo en la base de datos
     * @param   db          la instancia de la base de datos
     */
    public static void actualizarEvento(Evento evento, FirebaseFirestore db){
        //pasa el objeto evento actualizado a la base de datos
        DocumentReference referencia = db.collection("eventos").document("evento"+(evento.getId()));

        //actualiza los campos
        //ID
        referencia
                .update("id", String.valueOf(evento.getId()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-ID", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
        //NOMBRE
        referencia
                .update("nombre", evento.getNombre())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-NOMBRE", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
        //DESCRIPCION
        referencia
                .update("descripcion", evento.getDescripcion())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-APELLIDO", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
        //TIPO
        referencia
                .update("tipo", evento.getTipo())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-TELEFONO", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
        //CREADOR
        referencia
                .update("creador", evento.getCreador())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-EMAIL", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
        //FECHAHORAINICIO
        referencia
                .update("fechaHoraInicio", evento.getHoraFechaInicio())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-EMAIL", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
        //FECHAHORAFIN
        referencia
                .update("fechaHoraFin", evento.getHoraFechaFin())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-EMAIL", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
    }

    //funcion para borrar un evento de la base de datos
    /*
     * @param   evento      el objeto evento que se quiere borrar
     * @param   db          la referencia a la base de datos
     */
    public static void borrarEvento(Evento evento, FirebaseFirestore db){
        db.collection("eventos").document("evento"+(evento.getId()))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error deleting document", e);
                    }
                });
    }
}
