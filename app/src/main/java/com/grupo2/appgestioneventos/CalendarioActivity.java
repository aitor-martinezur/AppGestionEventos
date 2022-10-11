package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.media.tv.TvView;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.Time;

public class CalendarioActivity extends MainActivity{
    private String rojo="#e74c3c";
    private String verde="#2ecc71";
    private String azul="#2e86c1";
    private String nombre="";
    private Time fecha;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String bbdd="jdbc:mysql://localhost:3306/gestion";
    private static final String usuario ="root";
    private static final String clave="";
    //Fondo paguina
    protected void fondo(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        ConstraintLayout constraintLayout = findViewById(R.id.activity_login_id);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }
    //Conexion con la base de datos
    public void conexion(String nombre, Time fecha){
        /*Declaramos una variable para almacenar la cadena de conexión.
    Primero la iniciamos en null.*/
        Connection conex = null;

        //Controlamos la excepciones que puedan surgir al conectarnos a la BBDD
        try {
            //Registrar el driver
            Class.forName(driver);
            //Creamos una conexión a la Base de Datos
            conex = DriverManager.getConnection(bbdd, usuario, clave);

            // Si hay errores informamos al usuario.
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos.\n"
                    + e.getMessage().toString());
        }
        // Devolvemos la conexión.
        return conex;
    }
    //Colores segun eventos
    public String colores(String rojo, String verde, String azul){
        if(){

        }else if(){

        }else if(){

        }else{

        }
    }
}
