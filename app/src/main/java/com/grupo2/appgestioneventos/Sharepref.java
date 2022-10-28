package com.grupo2.appgestioneventos;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Sharepref extends AppCompatActivity  {

    private EditText rojo1;
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        rojo1=(EditText)findViewById(R.id.rojo1);
        SharedPreferences preferences = getSharedPreferences("fragment_settings", Context.MODE_PRIVATE);
        rojo1.setText(preferences.getString("rojo1",""));
    }
    public void Guardar(View view){
        SharedPreferences preferencias=getSharedPreferences("fragment_settings",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor=preferencias.edit();
        Obj_editor.putString("",rojo1.getText().toString());
        Obj_editor.commit();
        finish();
    }
}
