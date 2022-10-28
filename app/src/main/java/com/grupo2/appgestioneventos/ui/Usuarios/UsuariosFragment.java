package com.grupo2.appgestioneventos.ui.Usuarios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.grupo2.appgestioneventos.Contacto;
import com.grupo2.appgestioneventos.Usuario;
import com.grupo2.appgestioneventos.databinding.FragmentUsersBinding;

import java.util.ArrayList;
import java.util.Objects;

public class UsuariosFragment extends Fragment {

    private FragmentUsersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UsuariosViewModel galleryViewModel =
                new ViewModelProvider(this).get(UsuariosViewModel.class);

        binding = FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Contacto> contactos = new ArrayList<>();
        cargarContactos(db, contactos);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //funcion para cargar los datos de los contactos de la base de datos firebase
    /*
     * @param   db          la instancia de la base de datos
     * @param   contactos   ArrayList donde se van a meter todos los contactos que se recuperen de la base de datos
     */
    public void cargarContactos(FirebaseFirestore db, ArrayList<Contacto> contactos){
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

                    TextView columnaNombres = binding.nameContact;
                    TextView columnaApellido = binding.apellidoContact;
                    TextView columnaTelefono = binding.telefonoContact;
                    TextView columnaEmail = binding.emailContact;

                    for (int i=0; i<contactos.size(); i++){
                        columnaNombres.setText(columnaNombres.getText()+contactos.get(i).getNombre()+"\n");
                        columnaApellido.setText(columnaApellido.getText()+contactos.get(i).getApellido()+"\n");
                        columnaTelefono.setText(columnaTelefono.getText()+contactos.get(i).getTelefono()+"\n");
                        columnaEmail.setText(columnaEmail.getText()+contactos.get(i).getEmail()+"\n");
                    }
                });
    }
}