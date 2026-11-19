package es.davante.edadcanina;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 0;
    private static final int EDAD_MAXIMA = 30;

    // Usamos el binding generado desde activity_main.xml en lugar de findViewById
    private ActivityMainBinding binding;

    private void mostrarError(String mensaje) {
        // Mostramos el error en el campo de entrada y limpiamos el resultado anterior
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }

    private void calcularEdad() {
        String texto = binding.ageInput.getText().toString().trim();

        if (texto.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }

        // Limpiamos el error si la validación ha sido correcta
        binding.ageInput.setError(null);
        int resultadoCanino = edad * FACTOR_CANINO;
        binding.resultText.setText(getString(R.string.tu_edad_format, resultadoCanino));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inflamos el layout envuelto en <layout> y obtenemos el binding sin findViewById
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    }
}