package es.davante.edadcanina;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.davante.edadcanina.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

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

        binding.setModelo(modelo);
        binding.setControlador(controlador);
    }
}