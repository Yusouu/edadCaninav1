package es.davante.edadcanina;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EdadCaninaModelo modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        modelo = new EdadCaninaModelo();
        binding.setModelo(modelo);

        binding.btnCalc.setOnClickListener(view -> {
            Validacion estado = modelo.calcularEdadCanina(modelo.getEntradaEdad());
            if (estado == Validacion.OK) {
                binding.ageInput.setError(null);
            } else if (estado == Validacion.VACIO) {
                binding.ageInput.setError(getString(R.string.error_vacio));
                binding.ageInput.requestFocus();
            } else if (estado == Validacion.NO_NUMERO) {
                binding.ageInput.setError(getString(R.string.error_no_numero));
                binding.ageInput.requestFocus();
            } else if (estado == Validacion.FUERA_DE_RANGO) {
                binding.ageInput.setError(
                        getString(R.string.error_rango, EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA)
                );
                binding.ageInput.requestFocus();
            }
        });
    }
}