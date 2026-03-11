package com.example.m1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private TextView tvResumen;
    private ImageView ivResumen;
    private Button btnConfirmar, btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvResumen = findViewById(R.id.tv_resumen_detalle);
        ivResumen = findViewById(R.id.iv_resumen);
        btnConfirmar = findViewById(R.id.btn_confirmar);
        btnEditar = findViewById(R.id.btn_editar);

        // Obtener datos del Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.EXTRA_ID);
        String nombre = intent.getStringExtra(MainActivity.EXTRA_NOMBRE);
        String programa = intent.getStringExtra(MainActivity.EXTRA_PROGRAMA);
        String modalidad = intent.getStringExtra(MainActivity.EXTRA_MODALIDAD);
        ArrayList<String> certificados = intent.getStringArrayListExtra(MainActivity.EXTRA_CERTIFICADOS);
        boolean urgente = intent.getBooleanExtra(MainActivity.EXTRA_URGENTE, false);

        // Construir el texto del resumen
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiante: ").append(nombre).append("\n");
        sb.append("Código: ").append(id).append("\n");
        sb.append("Programa: ").append(programa).append("\n");
        sb.append("Modalidad: ").append(modalidad).append("\n");
        sb.append("Urgente: ").append(urgente ? "Sí" : "No").append("\n\n");
        sb.append("Certificados solicitados:\n");
        for (String cert : certificados) {
            sb.append("- ").append(cert).append("\n");
        }

        // Cálculo de costo opcional (Bono)
        double costoBase = 10000;
        double total = certificados.size() * costoBase;
        if (urgente) total += 5000;
        sb.append("\nCosto Total Estimado: $").append(total);

        tvResumen.setText(sb.toString());

        // Cambiar imagen según modalidad o urgencia (Requisito)
        if (urgente) {
            ivResumen.setImageResource(android.R.drawable.ic_dialog_alert);
        } else if (modalidad.equalsIgnoreCase("Virtual")) {
            ivResumen.setImageResource(android.R.drawable.ic_menu_today);
        } else {
            ivResumen.setImageResource(android.R.drawable.ic_menu_myplaces);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Vuelve al MainActivity anterior
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitIntent = new Intent(SummaryActivity.this, SubmitActivity.class);
                submitIntent.putExtra("RESUMEN_FINAL", tvResumen.getText().toString());
                startActivity(submitIntent);
            }
        });
    }
}