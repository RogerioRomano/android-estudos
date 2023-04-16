package com.devandroid.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.devandroid.applistacurso.R;
import com.devandroid.applistacurso.controller.CursoController;
import com.devandroid.applistacurso.controller.PessoaController;
import com.devandroid.applistacurso.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PessoaController pessoaController;
    private CursoController cursoController;

    private List<String> nomesDosCursos;
    private Pessoa pessoa;
    private EditText editPrimeiroNome;
    private EditText editSobrenomeAluno;
    private EditText editNomeCurso;
    private EditText editTelefoneContato;
    private Button btnLimpar;
    private Button btnSalvar;
    private Button btnFinalizar;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        pessoaController = new PessoaController(MainActivity.this);
        cursoController = new CursoController();
        nomesDosCursos = cursoController.dadosParaSpinner();
        pessoa = new Pessoa();
        pessoaController.buscar(pessoa);

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenomeAluno = findViewById(R.id.editSobrenomeAluno);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);
        spinner = findViewById(R.id.spinner);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobrenomeAluno.setText(pessoa.getSobreNome());
        editNomeCurso.setText(pessoa.getCursoDesejado());
        editTelefoneContato.setText(pessoa.getTelefone());

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        //Adapter
        //Layout
        //Injetar o Adapter ao Spinner - A lista será gerada.

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                cursoController.dadosParaSpinner());

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);


        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSobrenomeAluno.setText("");
                editTelefoneContato.setText("");
                editNomeCurso.setText("");
                pessoaController.limpar();
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

                pessoaController.salvar(pessoa);
            }
        });
        Log.i(getClass().getName(), pessoa.toString());
    }
}