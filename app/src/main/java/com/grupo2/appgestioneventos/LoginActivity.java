package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        //establece el fondo animado con sus parametros
        ConstraintLayout constraintLayout = findViewById(R.id.activity_login_id);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //accion del boton de login
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::login);

    }

    //funcion de login que coge las credenciales
    public void login(View view){
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);

        //comprueba las credenciales
        //si es administrador
        if ((email.getText().toString().equals(""))&&(password.getText().toString().equals(""))){
            //NOTIFICACION INICIO SESION ADMINISTRADOR
            Snackbar.make(view, "Sesión iniciada como administrador.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        //si es usuario
        else if((email.getText().toString().equals(""))&&(password.getText().toString().equals(""))) {
            //NOTIFICACION INICIO SESION USUARIO
            Snackbar.make(view, "Sesión iniciada.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        //credenciales incorrectas
        else{
            //NOTIFICACION CREDENCIALES INCORRECTAS
            Snackbar.make(view, "El email o la contraseña no son correctos.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
