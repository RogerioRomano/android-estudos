package com.devandroid.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.devandroid.applistacurso.R;
import com.devandroid.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoa;
    Pessoa outraPessoa;
    String dadosPessoa;

    String dadosOutraPessoa;
    EditText editPrimeiroNome;

    EditText editSobrenomeAluno;

    EditText editNomeCurso;

    EditText editTelefoneContato;

    Button btnLimpar;

    Button btnSalvar;

    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pessoa = new Pessoa();
        pessoa.setPrimeiroNome("Rogério");
        pessoa.setSobreNome("Romano");
        pessoa.setCursoDesejado("Java");
        pessoa.setTelefone("11-929886655");

        outraPessoa = new Pessoa();
        outraPessoa.setPrimeiroNome("Luiz");
        outraPessoa.setSobreNome("Alvez");
        outraPessoa.setCursoDesejado("Java");
        outraPessoa.setTelefone("11-955446622");

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenomeAluno = findViewById(R.id.editSobrenomeAluno);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        editPrimeiroNome.setText(outraPessoa.getPrimeiroNome());
        editSobrenomeAluno.setText(outraPessoa.getSobreNome());
        editNomeCurso.setText(outraPessoa.getCursoDesejado());
        editTelefoneContato.setText(outraPessoa.getTelefone());

        /*
        dadosPessoa = "Primeiro nome: ";
        dadosPessoa += pessoa.getPrimeiroNome();
        dadosPessoa += " Sobrenome: ";
        dadosPessoa += pessoa.getSobreNome();
        dadosPessoa += " Curso Desejado: ";
        dadosPessoa += pessoa.getCursoDesejado();
        dadosPessoa += "Telefone Contato";
        dadosPessoa += pessoa.getTelefone();
        */


        Log.i(getClass().getName(), pessoa.toString());
        Log.i(getClass().getName(), outraPessoa.toString());

    }
}