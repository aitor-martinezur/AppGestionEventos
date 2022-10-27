package com.grupo2.appgestioneventos.ui;
import
public class sha {
    Context context = getActivity();
    SharedPreferences sharedPref = context.getSharedPreferences( getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
    editor.apply();
    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
    int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);

}
