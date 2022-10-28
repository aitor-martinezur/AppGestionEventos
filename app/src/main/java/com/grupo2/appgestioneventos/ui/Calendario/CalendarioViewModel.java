package com.grupo2.appgestioneventos.ui.Calendario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendarioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CalendarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("    Tienes estos eventos:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
