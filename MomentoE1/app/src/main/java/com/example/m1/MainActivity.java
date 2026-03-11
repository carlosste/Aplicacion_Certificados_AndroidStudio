package com.example.m1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etNombre, etCodigo;
    private Spinner spPrograma;
    private RadioGroup rgModalidad;
    private CheckBox cbConstancia, cbNotas, cbPazSalvo, cbMatricula, cbPromedio;
    private SwitchCompat swUrgente;
    private Button btnResumen;

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_NOMBRE = "EXTRA_NOMBRE";
    public static final String EXTRA_PROGRAMA = "EXTRA_PROGRAMA";
    public static final String EXTRA_MODALIDAD = "EXTRA_MODALIDAD";
    public static final String EXTRA_CERTIFICADOS = "EXTRA_CERTIFICADOS";
    public static final String EXTRA_URGENTE = "EXTRA_URGENTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        etNombre = findViewById(R.id.et_nombre);
        etCodigo = findViewById(R.id.et_codigo);
        spPrograma = findViewById(R.id.sp_programa);
        rgModalidad = findViewById(R.id.rg_modalidad);
        cbConstancia = findViewById(R.id.cb_constancia);
        cbNotas = findViewById(R.id.cb_notas);
        cbPazSalvo = findViewById(R.id.cb_pazsalvo);
        cbMatricula = findViewById(R.id.cb_matricula);
        cbPromedio = findViewById(R.id.cb_promedio);
        swUrgente = findViewById(R.id.sw_urgente);
        btnResumen = findViewById(R.id.btn_resumen);

        // Configurar Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.programas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPrograma.setAdapter(adapter);

        // Lógica para el Switch de Urgente (Cambio de color dinámico)
        swUrgente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swUrgente.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.ucc_orange)));
                    swUrgente.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.ucc_orange)));
                } else {
                    // Volver a gris por defecto cuando está apagado
                    swUrgente.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray)));
                    swUrgente.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray)));
                }
            }
        });
        // Inicializar estado del switch
        swUrgente.setChecked(false);

        btnResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarYEnviar();
            }
        });
    }

    private void validarYEnviar() {
        String nombre = etNombre.getText().toString().trim();
        String codigo = etCodigo.getText().toString().trim();
        String programa = spPrograma.getSelectedItem().toString();
        int selectedModalidadId = rgModalidad.getCheckedRadioButtonId();

        // Validaciones
        if (nombre.isEmpty() || codigo.isEmpty()) {
            Toast.makeText(this, R.string.error_campos_vacios, Toast.LENGTH_SHORT).show();
            return;
        }

        if (spPrograma.getSelectedItemPosition() == 0) {
            Toast.makeText(this, R.string.error_programa, Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedModalidadId == -1) {
            Toast.makeText(this, R.string.error_modalidad, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> certificados = new ArrayList<>();
        if (cbConstancia.isChecked()) certificados.add(cbConstancia.getText().toString());
        if (cbNotas.isChecked()) certificados.add(cbNotas.getText().toString());
        if (cbPazSalvo.isChecked()) certificados.add(cbPazSalvo.getText().toString());
        if (cbMatricula.isChecked()) certificados.add(cbMatricula.getText().toString());
        if (cbPromedio.isChecked()) certificados.add(cbPromedio.getText().toString());

        if (certificados.isEmpty()) {
            Toast.makeText(this, R.string.error_certificados, Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rbModalidad = findViewById(selectedModalidadId);
        String modalidad = rbModalidad.getText().toString();
        boolean urgente = swUrgente.isChecked();

        // Enviar datos
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra(EXTRA_ID, codigo);
        intent.putExtra(EXTRA_NOMBRE, nombre);
        intent.putExtra(EXTRA_PROGRAMA, programa);
        intent.putExtra(EXTRA_MODALIDAD, modalidad);
        intent.putStringArrayListExtra(EXTRA_CERTIFICADOS, certificados);
        intent.putExtra(EXTRA_URGENTE, urgente);
        startActivity(intent);
    }
}