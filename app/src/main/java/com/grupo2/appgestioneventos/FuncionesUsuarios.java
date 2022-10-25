package com.grupo2.appgestioneventos;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FuncionesUsuarios {
    //Archivo con las funciones para crear, borrar y editar usuarios

    //funcion para crear un usuario nuevo en la base de datos
    /* 
     * @param   nuevoUsuario    el objeto usuario con toda la informacion para meterlo en la base de datos
     * @param   db              la instancia de la base de datos
     */
    public static void crearUsuario(Usuario nuevoUsuario, FirebaseFirestore db){
        //carga los usuarios actuales de la base de datos
        ArrayList<Usuario> usuarios = new ArrayList<>();
        final String TAG = "MyActivity";
        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("id", nuevoUsuario.getId());
        data.put("email", nuevoUsuario.getEmail());
        data.put("nombre", nuevoUsuario.getNombre());
        data.put("apellido", nuevoUsuario.getApellido());
        data.put("contrasena", nuevoUsuario.getContrasena());

        //crea el usuario en la base de datos
        db.collection("usuarios").document("usuario"+(nuevoUsuario.getId()-2)).set(data);
    }

    //funcion para actualizar un usuario de la base de datos
    /* 
     * @param   usuario el objeto usuario con toda la informacion para meterlo en la base de datos
     * @param   db      la instancia de la base de datos
     */
    public static void actualizarUsuario(Usuario usuario, FirebaseFirestore db){
        //pasa el objeto usuario actualizado a la base de datos
        //coge la referencia del documento con el id-2 para que no se actualizen el usuario admin y prueba
        DocumentReference referencia = db.collection("usuarios").document("usuario"+(usuario.getId()-2));

        //actualiza los campos
        //EMAIL
        referencia
                .update("email", usuario.getEmail())
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
        //NOMBRE
        referencia
                .update("nombre", usuario.getNombre())
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
                .update("apellido", usuario.getApellido())
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
        //CONTRASENA
        referencia
                .update("contrasena", usuario.getContrasena())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MYAC-CONTRASENA", "Actualizado correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MYAC", "Error updating document", e);
                    }
                });
    }

    //funcion para borrar un usuario de la base de datos
    /*
     * @param   usuario el objeto usuario que se quiere borrar
     * @param   db      la referencia a la base de datos
     */
    public static void borrarUsuario(Usuario usuario, FirebaseFirestore db){
        //le manda el valor null a la direccion del usuario en la base de datos para borrarlo
        db.collection("usuarios").document("usuario"+(usuario.getId()-2))
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
