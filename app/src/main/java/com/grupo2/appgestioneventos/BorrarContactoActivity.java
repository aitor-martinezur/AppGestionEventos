package com.grupo2.appgestioneventos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class BorrarContactoActivity extends AdminContactsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.deletecontact);

        //instancia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //arraylist de los contactos
        ArrayList<Contacto> contactos = new ArrayList<>();

        Button borrarContacto = findViewById(R.id.borrarContacto);

        //funcionalidad del boton
        borrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarContactos(db, view, contactos);
            }
        });
    }

    //funcion para cargar los datos de los contactos de la base de datos firebase
    /*
     * @param   db          la instancia de la base de datos
     * @param   v           la vista de la actividad
     * @param   contactos   ArrayList donde se van a meter todos los contactos que se recuperen de la base de datos
     */
    public void cargarContactos(FirebaseFirestore db, View v, ArrayList<Contacto> contactos){
        final String TAG = "MyActivity";
        //llamada a la base de datos para recoger la informacion
        db.collection("contactos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            //crea un usuario con la informacion recogida en esa posicion del for
                            Contacto contacto = new Contacto(Integer.parseInt(Objects.requireNonNull(document.getString("id"))), document.getString("nombre"), document.getString("apellido"), document.getString("telefono"), document.getString("email"));
                            //añade el usuario creado al ArrayList de usuarios
                            contactos.add(contacto);
                        }
                    }
                    else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                    //llama a la para crear el contacto
                    funcionBorrarContacto(contactos, db);
                });
    }
    //funcion para recoger los datos y hacer la llamada a la funcion que borra el contacto
    /*
     * @param   contactos   el arraylist con los contactos recogidos de la base de datos
     * @param   db          la instancia de la base de datos
     */
    public void funcionBorrarContacto(ArrayList<Contacto> contactos, FirebaseFirestore db){
        //boton para cuando ha metido los valores y crea el usuario
        Button botonBorrar = findViewById(R.id.borrarContacto);
        botonBorrar.setOnClickListener(view -> {
            //recoge los datos de los campos
            EditText email = findViewById(R.id.NuevoEmailBorrarCont);

            String nuevoEmail = email.getText().toString();

            for(int i=0; i<contactos.size(); i++){
                if (nuevoEmail.equals(contactos.get(i).getEmail())){
                    FuncionesContactos.borrarContacto(contactos.get(i), db);
                    Snackbar.make(view, "Contacto borrado.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "No hay ningun contacto con ese email.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
