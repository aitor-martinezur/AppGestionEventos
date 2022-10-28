package com.grupo2.appgestioneventos;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class AnadirContactoActivity extends AdminContactsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.addcontact);

        //referencia a la base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //handler
        Handler handler = new Handler();



        //funcionalidad del boton
        FloatingActionButton botonCrear = findViewById(R.id.nextCrearCont);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarContactos(db, view /*contactos*/);
            }
        });
    }

    //funcion para cargar los datos de los contactos de la base de datos firebase
    /*
     * @param   db          la instancia de la base de datos
     * @param   v           la vista de la actividad
     * @param   contactos   ArrayList donde se van a meter todos los contactos que se recuperen de la base de datos
     */
    public void cargarContactos(FirebaseFirestore db, View v /*ArrayList<Contacto> contactos*/){
        final String TAG = "MyActivity";
        ArrayList<Contacto> contactos = new ArrayList<>();
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
                    funcionCrearContacto(contactos, db);
                });
    }
    //funcion para recoger los datos y hacer la llamada a la funcion que crea el contacto
    /*
     * @param   contactos   el arraylist con los contactos recogidos de la base de datos
     * @param   db          la instancia de la base de datos
     */
    public void funcionCrearContacto(ArrayList<Contacto> contactos, FirebaseFirestore db){
        //boton para cuando ha metido los valores y crea el usuario
        FloatingActionButton botonCrear = findViewById(R.id.nextCrearCont);
        botonCrear.setOnClickListener(view -> {
            //recoge los datos de los campos
            EditText nombre = findViewById(R.id.NuevoNombreContacto);
            EditText apellido = findViewById(R.id.NuevoApellidoContacto);
            EditText telefono = findViewById(R.id.NuevoTelefonoContacto);
            EditText email = findViewById(R.id.NuevoEmailContacto);

            String nuevoEmail = email.getText().toString();
            String nuevoNombre = nombre.getText().toString();
            String nuevoApellido = apellido.getText().toString();
            String nuevoTelefono = telefono.getText().toString();

            //mira cual es el ultimo id
            //recoge el id del ultimo user para usar el siguiente
            int ultimoID=0;
            for (int i = 0; i< contactos.size(); i++){
                if (contactos.get(i).getId()>ultimoID){
                    ultimoID = (contactos.get(i).getId())+1;
                }
            }

            //comprueba que no sean nulos
            if ((nuevoEmail.isEmpty())||(nuevoNombre.isEmpty())||(nuevoApellido.isEmpty())||(nuevoTelefono.isEmpty())){
                Snackbar.make(view, "No pueden haber campos vacios.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else{
                Contacto contacto = new Contacto((ultimoID), nuevoNombre, nuevoApellido, nuevoTelefono, nuevoEmail);
                FuncionesContactos.crearContacto(contacto, db);
                Snackbar.make(view, "Contacto creado.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //espera 5 segundos para devolverle a la pestaña anterior
                //handler.postDelayed(() -> startActivity(new Intent(AnadirUsuarioActivity.this, AdminUsersActivity.class)), 5000);
            }
        });
    }
}
