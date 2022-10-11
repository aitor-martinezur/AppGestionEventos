package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CalendarioActivity extends MainActivity{
    private string rojo="#e74c3c";
    private string verde="#2ecc71";
    private string azul="#2e86c1";
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
    public string colores(){
        if(){

        }elseif(){

        }elseif(){

        }else{
            
        }
    }
}
