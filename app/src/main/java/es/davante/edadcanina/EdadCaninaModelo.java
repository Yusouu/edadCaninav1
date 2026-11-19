package es.davante.edadcanina;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class EdadCaninaModelo extends BaseObservable {

    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 0;
    public static final int EDAD_MAXIMA = 30;

    private String entradaEdad = "";
    private int edadHumana;
    private int edadCanina;
    private Validacion validacion = Validacion.OK;

    @Bindable
    public String getEntradaEdad() {
        return entradaEdad;
    }

    public void setEntradaEdad(String entradaEdad) {
        String valor = entradaEdad == null ? "" : entradaEdad;
        if (!valor.equals(this.entradaEdad)) {
            this.entradaEdad = valor;
            notifyPropertyChanged(BR.entradaEdad);
        }
    }

    @Bindable
    public int getEdadHumana() {
        return edadHumana;
    }

    @Bindable
    public int getEdadCanina() {
        return edadCanina;
    }

    @Bindable
    public Validacion getValidacion() {
        return validacion;
    }

    private void setEdadCanina(int edadCanina) {
        if (this.edadCanina != edadCanina) {
            this.edadCanina = edadCanina;
            notifyPropertyChanged(BR.edadCanina);
        }
    }

    private void setValidacion(Validacion validacion) {
        if (this.validacion != validacion) {
            this.validacion = validacion;
            notifyPropertyChanged(BR.validacion);
        }
    }

    public Validacion calcularEdadCanina(String entradaTexto) {
        setEntradaEdad(entradaTexto);
        String textoLimpio = entradaTexto == null ? "" : entradaTexto.trim();

        if (textoLimpio.isEmpty()) {
            setEdadCanina(0);
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        int edadParseada;
        try {
            edadParseada = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e) {
            setEdadCanina(0);
            setValidacion(Validacion.NO_NUMERO);
            return validacion;
        }

        if (edadParseada < EDAD_MINIMA || edadParseada > EDAD_MAXIMA) {
            setEdadCanina(0);
            setValidacion(Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        edadHumana = edadParseada;
        setEdadCanina(edadParseada * FACTOR_CANINO);
        setValidacion(Validacion.OK);
        return validacion;
    }

    public void limpiar() {
        setEntradaEdad("");
        edadHumana = 0;
        setEdadCanina(0);
        setValidacion(Validacion.OK);
    }
}