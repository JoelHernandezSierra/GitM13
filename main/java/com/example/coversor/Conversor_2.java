package com.example.coversor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class Conversor_2 extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnitSpinner, toUnitSpinner;
    private Button convertButton, backButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor2);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backButton);

        // Recuperar el tipo de conversión del intent
        String conversionType = getIntent().getStringExtra("conversionType");

        // Llenar los spinners con unidades según el tipo de conversión

        String[] units;
        switch (conversionType) {
            case "Peso":
                units = new String[]{"Microgramo", "Milligramo", "Gramo", "Kilogramo", "Tonelada"};
                break;
            case "Volumen":
                units = new String[]{"Mililitro", "Litro", "Metro Cubico"};
                break;
            case "Distancia":
                units = new String[]{"mm", "cm", "m", "km"};
                break;
            case "Temperatura":
                units = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
                break;
            case "Almacenamiento de datos":
                units = new String[]{"bit", "byte", "KB", "MB", "GB", "TB"};
                break;
            default:
                units = new String[]{};
                break;
        }

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnitSpinner.setAdapter(unitAdapter);
        toUnitSpinner.setAdapter(unitAdapter);

        // Clic del botón Convertir
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromUnit = fromUnitSpinner.getSelectedItem().toString();
                String toUnit = toUnitSpinner.getSelectedItem().toString();
                String inputValueText = inputValue.getText().toString();

                if (inputValueText.isEmpty()) {
                    resultTextView.setText("Por favor, ingresa un valor.");
                    return;
                }

                double value = Double.parseDouble(inputValueText);
                double result = 0.0;

                // Lógica para la conversión
                result = convertUnits(fromUnit, toUnit, value);

                resultTextView.setText("Resultado: " + result + " " + toUnit);
            }
        });

        // Clic del botón "Atrás"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Esto cierra la actividad actual y regresa a la anterior
            }
        });
    }

    // Método para convertir unidades
    private double convertUnits(String fromUnit, String toUnit, double value) {
        // Conversión a metros (o la unidad base) para simplificar
        Map<String, Double> conversionFactors = new HashMap<>();
        conversionFactors.put("Microgramo", 0.000001);
        conversionFactors.put("Milligramo", 0.001);
        conversionFactors.put("Gramo", 1.0);
        conversionFactors.put("Kilogramo", 1000.0);
        conversionFactors.put("Tonelada", 1000000.0);
        conversionFactors.put("Mililitro", 0.001);
        conversionFactors.put("Litro", 1.0);
        conversionFactors.put("Metro Cubico", 1000.0);
        conversionFactors.put("mm", 0.001);
        conversionFactors.put("cm", 0.01);
        conversionFactors.put("m", 1.0);
        conversionFactors.put("km", 1000.0);
        conversionFactors.put("Celsius", 1.0);
        conversionFactors.put("Fahrenheit", 1.0);
        conversionFactors.put("Kelvin", 1.0);
        conversionFactors.put("bit", 0.125);
        conversionFactors.put("byte", 1.0);
        conversionFactors.put("KB", 1024.0);
        conversionFactors.put("MB", 1024 * 1024.0);
        conversionFactors.put("GB", 1024 * 1024 * 1024.0);
        conversionFactors.put("TB", 1024 * 1024 * 1024 * 1024.0);

        // Convertir de la unidad de origen a la unidad base (grados Celsius, metros, etc.)
        double baseValue = value * conversionFactors.get(fromUnit);

        // Convertir de la unidad base a la unidad de destino
        return baseValue / conversionFactors.get(toUnit);
    }
}
