package es.davante.edadcanina;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_ENTRADA_EDAD = "key_entrada_edad";
    private static final String KEY_EDAD_CANINA = "key_edad_canina";
    private static final String KEY_VALIDACION = "key_validacion";

    private ActivityMainBinding binding;
    private EdadCaninaModelo modelo;
    private EdadCaninaControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        modelo = new EdadCaninaModelo();
        controlador = new EdadCaninaControlador(modelo);

        if (savedInstanceState != null) {
            restaurarEstado(savedInstanceState);
        }

        binding.setModelo(modelo);
        binding.setControlador(controlador);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (modelo != null) {
            outState.putString(KEY_ENTRADA_EDAD, modelo.getEntradaEdad());
            outState.putInt(KEY_EDAD_CANINA, modelo.getEdadCanina());
            outState.putString(KEY_VALIDACION, modelo.getValidacion().name());
        }
    }

    private void restaurarEstado(Bundle savedInstanceState) {
        String entradaGuardada = savedInstanceState.getString(KEY_ENTRADA_EDAD, "");
        String validacionNombre = savedInstanceState.getString(KEY_VALIDACION, Validacion.OK.name());

        Validacion validacionGuardada;
        try {
            validacionGuardada = Validacion.valueOf(validacionNombre);
        } catch (IllegalArgumentException e) {
            validacionGuardada = Validacion.OK;
        }

        modelo.setEntradaEdad(entradaGuardada);

        if (validacionGuardada == Validacion.OK && !entradaGuardada.trim().isEmpty()) {
            modelo.calcularEdadCanina(entradaGuardada);
        } else if (validacionGuardada != Validacion.OK) {
            modelo.calcularEdadCanina(entradaGuardada);
        }
    }
}