package com.devandroid.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devandroid.applistacurso.R;
import com.devandroid.applistacurso.controller.CursoController;
import com.devandroid.applistacurso.controller.PessoaController;
import com.devandroid.applistacurso.model.Curso;
import com.devandroid.applistacurso.model.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PessoaController controller;
    private CursoController cursoController;

    private List<Curso> lisaDeCursos;
    private Pessoa pessoa;
    private EditText editPrimeiroNome;
    private EditText editSobrenomeAluno;
    private EditText editNomeCurso;
    private EditText editTelefoneContato;
    private Button btnLimpar;
    private Button btnSalvar;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new PessoaController(MainActivity.this);
        cursoController = new CursoController();
        lisaDeCursos = cursoController.getListaDeCursos();
        pessoa = new Pessoa();
        controller.buscar(pessoa);

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenomeAluno = findViewById(R.id.editSobrenomeAluno);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobrenomeAluno.setText(pessoa.getSobreNome());
        editNomeCurso.setText(pessoa.getCursoDesejado());
        editTelefoneContato.setText(pessoa.getTelefone());

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSobrenomeAluno.setText("");
                editTelefoneContato.setText("");
                editNomeCurso.setText("");
                controller.limpar();
            }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobreNome(editSobrenomeAluno.getText().toString());
                pessoa.setCursoDesejado(editNomeCurso.getText().toString());
                pessoa.setTelefone(editTelefoneContato.getText().toString());

                Toast.makeText(MainActivity.this,"Salvo " + pessoa.toString(), Toast.LENGTH_LONG).show();

                controller.salvar(pessoa);
            }
        });
        Log.i(getClass().getName(), pessoa.toString());
    }
}