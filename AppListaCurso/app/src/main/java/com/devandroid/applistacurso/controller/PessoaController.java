package com.devandroid.applistacurso.controller;

import android.util.Log;

import com.devandroid.applistacurso.model.Pessoa;

public class PessoaController {
    public void salvar(Pessoa pessoa) {
        Log.i(getClass().getSimpleName(), pessoa.toString());
    }
}
