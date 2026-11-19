package es.davante.edadcanina;

public class EdadCaninaControlador {

    private final EdadCaninaModelo modelo;

    public EdadCaninaControlador(EdadCaninaModelo modelo) {
        this.modelo = modelo;
    }

    public void onCalcular() {
        modelo.calcularEdadCanina(modelo.getEntradaEdad());
    }

    public void onLimpiar() {
        modelo.limpiar();
    }
}