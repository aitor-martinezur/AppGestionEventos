package com.grupo2.appgestioneventos.ui.Eventos;

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
import com.grupo2.appgestioneventos.Evento;
import com.grupo2.appgestioneventos.databinding.FragmentEventsBinding;

import java.util.ArrayList;
import java.util.Objects;

public class EventosFragment extends Fragment {

    private FragmentEventsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EventosViewModel slideshowViewModel =
                new ViewModelProvider(this).get(EventosViewModel.class);

        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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

                    TextView columnaNombre = binding.nameEvent;
                    TextView columnaDescripcion = binding.descripcionEvent;
                    TextView columnaTipo = binding.tipoEvento;
                    TextView columnaFechaHora = binding.fechaHoraEvento;

                    for (int i=0; i<eventos.size(); i++){
                        columnaNombre.setText(columnaNombre.getText()+eventos.get(i).getNombre()+"\n");
                        columnaDescripcion.setText(columnaDescripcion.getText()+eventos.get(i).getDescripcion()+"\n");
                        columnaFechaHora.setText(columnaFechaHora.getText()+eventos.get(i).getHoraFechaInicio()+"\n");
                        columnaTipo.setText(columnaTipo.getText()+eventos.get(i).getTipo()+"\n");
                    }
                });
    }
}