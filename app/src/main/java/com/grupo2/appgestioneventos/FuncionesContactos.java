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

public class FuncionesContactos {
    //Archivo con las funciones para crear, borrar y editar contactos

    //funcion para crear un contacto nuevo en la base de datos
    /*
     * @param   nuevoContacto   el objeto contacto con toda la informacion para meterlo en la base de datos
     * @param   db              la instancia de la base de datos
     */
    public static void crearContacto(Contacto nuevoContacto, FirebaseFirestore db){
        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("nombre", nuevoContacto.getNombre());
        data.put("apellido", nuevoContacto.getApellido());
        data.put("telefono", nuevoContacto.getTelefono());
        data.put("email", nuevoContacto.getEmail());

        //crea el usuario en la base de datos
        db.collection("contactos").document("contacto"+(nuevoContacto.getId()-1)).set(data);
    }

    //funcion para actualizar un contacto de la base de datos
    /*
     * @param   contacto    el objeto contacto con toda la informacion para meterlo en la base de datos
     * @param   db          la instancia de la base de datos
     */
    public static void actualizarContacto(Contacto contacto, FirebaseFirestore db){
        //pasa el objeto contacto actualizado a la base de datos
        //coge la referencia del documento con el id-1 para que no se actualizen el contacto prueba
        DocumentReference referencia = db.collection("contactos").document("contacto"+(contacto.getId()-1));

        //actualiza los campos
        //ID
        referencia
                .update("id", String.valueOf(contacto.getId()))
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
                .update("nombre", contacto.getNombre())
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
        //APELLIDO
        referencia
                .update("apellido", contacto.getApellido())
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
        //TELEFONO
        referencia
                .update("telefono", contacto.getTelefono())
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
        //EMAIL
        referencia
                .update("email", contacto.getEmail())
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

    //funcion para borrar un contacto de la base de datos
    /*
     * @param   contacto    el objeto contacto que se quiere borrar
     * @param   db          la referencia a la base de datos
     */
    public static void borrarContacto(Contacto contacto, FirebaseFirestore db){
        db.collection("contactos").document("usuario"+(contacto.getId()-1))
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
