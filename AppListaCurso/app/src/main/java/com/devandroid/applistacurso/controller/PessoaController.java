package com.devandroid.applistacurso.controller;

import android.content.SharedPreferences;
import android.util.Log;

import com.devandroid.applistacurso.model.Pessoa;
import com.devandroid.applistacurso.view.MainActivity;

public class PessoaController {

    SharedPreferences sharedPreferences;

    public static final String NAME_PREFERENCES = "pref_listavip";

    SharedPreferences.Editor listaVip;

    public PessoaController(MainActivity mainActivity) {
        sharedPreferences = mainActivity.getSharedPreferences(NAME_PREFERENCES, 0);
        listaVip = sharedPreferences.edit();
    }

    public void salvar(Pessoa pessoa) {
        Log.i(getClass().getSimpleName(), pessoa.toString());
        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("sobreNome", pessoa.getSobreNome());
        listaVip.putString("nomeCurso", pessoa.getCursoDesejado());
        listaVip.putString("telefoneContato", pessoa.getTelefone());
        listaVip.apply();
    }

    public Pessoa buscar(Pessoa pessoa) {
        pessoa.setPrimeiroNome(sharedPreferences.getString("primeiroNome", ""));
        pessoa.setSobreNome(sharedPreferences.getString("sobreNome", ""));
        pessoa.setCursoDesejado(sharedPreferences.getString("nomeCurso", ""));
        pessoa.setTelefone(sharedPreferences.getString("telefoneContato", ""));

        return pessoa;
    }

    public void limpar() {
        listaVip.clear();
        listaVip.apply();
    }


}
