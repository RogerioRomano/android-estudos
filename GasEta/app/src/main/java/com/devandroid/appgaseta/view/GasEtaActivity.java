package com.devandroid.appgaseta.view;

import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.devandroid.appgaseta.controller.GasEtaController;

public class GasEtaActivity extends AppCompatActivity {
    private EditText editGasolina;
    private EditText editEtanol;
    private TextView txtResultado;
    private Button btnCalcular;
    private Button btnLimpar;
    private Button btnSalvar;
    private Button btnFinalizar;
    private GasEtaController gasEtaController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);

        gasEtaController = new GasEtaController();

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
                    double precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    double precoEtanol = Double.parseDouble(editEtanol.getText().toString());
                    String melhorOpcao = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);
                    txtResultado.setText(melhorOpcao);
                    return;
                }

                Toast.makeText(GasEtaActivity.this, "Por favor, digite os dados obrigatórios", Toast.LENGTH_LONG).show();
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
                gasEtaController.limpar();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                UtilGasEta.calcularMelhorOpcao(5.12,3.60),
                Toast.LENGTH_LONG).show();


    }
}
