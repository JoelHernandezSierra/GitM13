package com.example.coversor;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner typeSpinner;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeSpinner = findViewById(R.id.typeSpinner);
        nextButton = findViewById(R.id.nextButton);

        // El spinner y sus tipos de conversiones
        String[] conversionTypes = {"Peso", "Volumen", "Distancia", "Temperatura", "Almacenamiento de datos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, conversionTypes);
        typeSpinner.setAdapter(adapter);

        // Click del botón siguiente
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Conversor_2.class);
                intent.putExtra("conversionType", typeSpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}