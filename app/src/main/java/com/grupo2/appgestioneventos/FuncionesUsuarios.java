package com.grupo2.appgestioneventos;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class FuncionesUsuarios {
    //Archivo con las funciones para crear, borrar y editar usuarios

    //funcion para crear un usuario nuevo en la base de datos
    /* 
     * @param   nuevoUsuario    el objeto usuario con toda la informacion para meterlo en la base de datos
     * @param   dbr             la referencia a la instancia de la base de datos
     * @param   ultimoID        el ultimo id de los usuarios ya existentes
     */
    public static void crearUsuario(Usuario nuevoUsuario, DatabaseReference dbr, int ultimoID){
        //parametros para poder cargar los usuarios
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //carga los usuarios actuales de la base de datos
        ArrayList<Usuario> usuarios = new ArrayList<>();
        final String TAG = "MyActivity";
        //llamada a la base de datos para recoger la informacion
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            //crea un usuario con la informacion recogida en esa posicion del for
                            Usuario usuario = new Usuario(Integer.parseInt(Objects.requireNonNull(document.getString("id"))), document.getString("email"), document.getString("contrasena"), document.getString("nombre"), document.getString("apellido"));
                            //añade el usuario creado al ArrayList de usuarios
                            usuarios.add(usuario);
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }


                    //pasa el objeto usuario a la base de datos
                    dbr.child("usuarios").child("usuario"+(ultimoID+1)).setValue(nuevoUsuario);
                });
    }

    //funcion para actualizar un usuario de la base de datos
    /* 
     * @param   usuario el objeto usuario con toda la informacion para meterlo en la base de datos
     * @param   dbr     la referencia a la instancia de la base de datos
     */
    public static void actualizarUsuario(Usuario usuario, DatabaseReference dbr){
        //pasa el objeto usuario actualizado a la base de datos
        dbr.child("usuarios").child("usuario"+usuario.getId()).setValue(usuario);
    }

    //funcion para borrar un usuario de la base de datos
    /*
     * @param   usuario el objeto usuario que se quiere borrar
     * @param   dbr     la referencia a la instancia de la base de datos
     */
    public static void borrarUsuario(Usuario usuario, DatabaseReference dbr){
        //le manda el valor null a la direccion del usuario en la base de datos para borrarlo
        dbr.child("usuarios").child("usuario"+usuario.getId()).setValue(null);
    }
}

/*
 * referencia a la instancia de la base de datos
  DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
 * nuevo usuario - 
  mDatabase.child("users").child(userId).setValue(user);
 * actualizar usuario -
   mDatabase.child("users").child(userId).child("username").setValue(name);
 * borrar usuario -
   mDatabase.child("users").child(userId).child("username").setValue(null);
 */


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
