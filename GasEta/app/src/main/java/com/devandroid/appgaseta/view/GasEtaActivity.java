package com.devandroid.appgaseta.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devandroid.appgaseta.R;
import com.devandroid.appgaseta.apoio.UtilGasEta;
import com.devandroid.appgaseta.controller.CombustivelController;
import com.devandroid.appgaseta.model.Combustivel;

import java.util.List;

public class GasEtaActivity extends AppCompatActivity {
    private CombustivelController controller;
    private Combustivel combustivelGasolina;
    private Combustivel combustivelEtanol;
    private EditText editGasolina;
    private EditText editEtanol;
    private TextView txtResultado;
    private Button btnCalcular;
    private Button btnLimpar;
    private Button btnSalvar;
    private Button btnFinalizar;
    private double precoGasolina;
    private double precoEtanol;
    private String recomendacao;
    private List<Combustivel> dados;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);
        controller = new CombustivelController(GasEtaActivity.this);
        dados = controller.getListaDeDados();

        Combustivel objetoAlteracao = dados.get(1);
        objetoAlteracao.setNomeDoCombustivel("**Gasolina**");
        objetoAlteracao.setPrecoDoCombustivel(5.97);
        objetoAlteracao.setRecomendacao("**Abastecer com Gasolina**");

        controller.alterar(objetoAlteracao);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosOk = true;
                if (TextUtils.isEmpty(editGasolina.getText())) {
                    editGasolina.setError("* Obrigatório");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }

                if (TextUtils.isEmpty(editEtanol.getText())) {
                    editEtanol.setError("* Obrigatório");
                    editEtanol.requestFocus();
                    isDadosOk = false;
                }

                if (isDadosOk) {
                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());
                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);
                    txtResultado.setText(recomendacao);
                    btnSalvar.setEnabled(true);
                    return;
                }

                Toast.makeText(GasEtaActivity.this, "Por favor, digite os dados obrigatórios", Toast.LENGTH_LONG).show();
                btnSalvar.setEnabled(false);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editGasolina.setText("".trim());
                editEtanol.setText("".trim());
                boolean isExistError = editEtanol.getError() != null || editGasolina.getError() != null;
                if (isExistError) {
                    editGasolina.setError(null);
                    editEtanol.setError(null);
                }
                btnSalvar.setEnabled(false);
                txtResultado.setText(getResources().getString(R.string.txt_resultado));
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvar(combustivelGasolina);
                controller.salvar(combustivelEtanol);


            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GasEtaActivity.this, "GasEta boa economia", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Toast.makeText(GasEtaActivity.this,
//                UtilGasEta.calcularMelhorOpcao(5.12,3.60),
                "Seja Bem Vindo",
                Toast.LENGTH_LONG).show();


    }
}
