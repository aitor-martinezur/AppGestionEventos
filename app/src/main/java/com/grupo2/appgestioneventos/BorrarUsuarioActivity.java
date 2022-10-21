package com.grupo2.appgestioneventos;

import android.os.Bundle;

import java.util.ArrayList;

public class BorrarUsuarioActivity extends AdminUsersActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.deleteuser);

        //arraylist de los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            usuarios = (ArrayList<Usuario>) getIntent().getExtras().getSerializable("keyUsuarios");
        }
    }
}
