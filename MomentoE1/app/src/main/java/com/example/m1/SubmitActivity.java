package com.example.m1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitActivity extends AppCompatActivity {

    private TextView tvComprobante;
    private Button btnCompartir, btnSalir;
    private String resumenFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        tvComprobante = findViewById(R.id.tv_comprobante);
        btnCompartir = findViewById(R.id.btn_compartir);
        btnSalir = findViewById(R.id.btn_salir);

        resumenFinal = getIntent().getStringExtra("RESUMEN_FINAL");
        tvComprobante.setText(resumenFinal);

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirSolicitud();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver al inicio y limpiar el stack
                Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void compartirSolicitud() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Mi Solicitud de Certificados UCC:\n\n" + resumenFinal);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}