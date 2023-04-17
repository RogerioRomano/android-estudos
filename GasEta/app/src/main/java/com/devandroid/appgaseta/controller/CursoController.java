package com.devandroid.appgaseta.controller;

import com.devandroid.appgaseta.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoController {
    private List listCursos;

    public List getListaDeCursos() {

        listCursos = new ArrayList<Curso>();
        listCursos.add(new Curso("JAVA"));
        listCursos.add(new Curso("HTML"));
        listCursos.add(new Curso("C#"));
        listCursos.add(new Curso("Python"));
        listCursos.add(new Curso("PHP"));
        listCursos.add(new Curso("Fluter"));
        listCursos.add(new Curso("Dart"));

        return listCursos;
    }
    
    public ArrayList<String> dadosParaSpinner(){
        ArrayList<String> dados = new ArrayList<>();
        for (int i = 0; i < getListaDeCursos().size(); i++) {
            Curso objeto = (Curso) getListaDeCursos().get(i);
            dados.add(objeto.getNomeDoCursoDesejado());
        }
        return dados;
    }
}
