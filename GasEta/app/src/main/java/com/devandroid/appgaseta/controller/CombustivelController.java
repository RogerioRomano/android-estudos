package com.devandroid.appgaseta.controller;

import android.content.SharedPreferences;

import com.devandroid.appgaseta.model.Combustivel;
import com.devandroid.appgaseta.view.GasEtaActivity;

public class CombustivelController {
    private SharedPreferences preferences;
    private SharedPreferences.Editor dadosPreferences;
    private final int PREFERENCES_MODE = 0;
    private static final String NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){
        preferences = activity.getSharedPreferences(NOME_PREFERENCES, PREFERENCES_MODE);
        dadosPreferences = preferences.edit();
    }

    public void salvar(Combustivel combustivel){
        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("preco", (float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();
    }

    public void limpar(){
        dadosPreferences.clear();
        dadosPreferences.apply();
    }
}
