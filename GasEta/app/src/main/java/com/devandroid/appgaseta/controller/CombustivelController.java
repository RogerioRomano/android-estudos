package com.devandroid.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import com.devandroid.appgaseta.database.GasEtaDB;
import com.devandroid.appgaseta.model.Combustivel;
import com.devandroid.appgaseta.view.GasEtaActivity;

import java.util.List;

public class CombustivelController extends GasEtaDB {
    private SharedPreferences preferences;
    private SharedPreferences.Editor dadosPreferences;
    private final int PREFERENCES_MODE = 0;
    private static final String NOME_PREFERENCES = "pref_gaseta";
    public CombustivelController(GasEtaActivity activity){
        super(activity);
        preferences = activity.getSharedPreferences(NOME_PREFERENCES, PREFERENCES_MODE);
        dadosPreferences = preferences.edit();
    }

    public void salvar(Combustivel combustivel){
        ContentValues dados =  new ContentValues();

        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("preco", (float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();

        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        salvarObjecto("Combustivel", dados);
    }

    public List<Combustivel> getListaDeDados(){
        return listarDados();
    }

    public void alterar(Combustivel combustivel){
        ContentValues dados =  new ContentValues();
        dados.put("id", combustivel.getId());
        dados.put("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel", combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        alterarObjeto("Combustivel", dados);
    }

    public void limpar(){
        dadosPreferences.clear();
        dadosPreferences.apply();
    }
}
