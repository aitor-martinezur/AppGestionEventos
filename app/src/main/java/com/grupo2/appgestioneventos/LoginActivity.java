package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

public class LoginActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        AnimationDrawable animDrawable = (AnimationDrawable) findViewById(R.id.activity_login_id).getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

    }
}
