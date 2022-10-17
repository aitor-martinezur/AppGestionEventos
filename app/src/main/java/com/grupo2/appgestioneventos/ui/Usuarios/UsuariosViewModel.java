package com.grupo2.appgestioneventos.ui.Usuarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UsuariosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UsuariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is usuarios fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}