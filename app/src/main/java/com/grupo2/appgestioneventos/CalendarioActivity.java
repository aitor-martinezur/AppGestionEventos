package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CalendarioActivity extends MainActivity{
    private String rojo="#e74c3c";
    private String verde="#2ecc71";
    private String azul="#2e86c1";
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
    //Colores segun eventos
    public String colores(){
        if(){

        }else if(){

        }else if(){

        }else{

        }
    }
}
