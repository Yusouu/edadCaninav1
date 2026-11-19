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
    private String etapaVida = "";
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
    public String getEtapaVida() {
        return etapaVida;
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

    private void setEtapaVida(String etapaVida) {
        String valor = etapaVida == null ? "" : etapaVida;
        if (!valor.equals(this.etapaVida)) {
            this.etapaVida = valor;
            notifyPropertyChanged(BR.etapaVida);
        }
    }

    private void setValidacion(Validacion validacion) {
        if (this.validacion != validacion) {
            this.validacion = validacion;
            notifyPropertyChanged(BR.validacion);
        }
    }

    private String calcularEtapa(int edadCaninaCalculada) {
        if (edadCaninaCalculada <= 14) {
            return "Cachorro / Joven";
        } else if (edadCaninaCalculada <= 49) {
            return "Adulto";
        } else {
            return "Senior";
        }
    }

    public Validacion calcularEdadCanina(String entradaTexto) {
        setEntradaEdad(entradaTexto);
        String textoLimpio = entradaTexto == null ? "" : entradaTexto.trim();

        if (textoLimpio.isEmpty()) {
            setEdadCanina(0);
            setEtapaVida("");
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        int edadParseada;
        try {
            edadParseada = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e) {
            setEdadCanina(0);
            setEtapaVida("");
            setValidacion(Validacion.NO_NUMERO);
            return validacion;
        }

        if (edadParseada < EDAD_MINIMA || edadParseada > EDAD_MAXIMA) {
            setEdadCanina(0);
            setEtapaVida("");
            setValidacion(Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        edadHumana = edadParseada;
        int canina = edadParseada * FACTOR_CANINO;
        setEdadCanina(canina);
        setEtapaVida(calcularEtapa(canina));
        setValidacion(Validacion.OK);
        return validacion;
    }

    public void limpiar() {
        setEntradaEdad("");
        edadHumana = 0;
        setEdadCanina(0);
        setEtapaVida("");
        setValidacion(Validacion.OK);
    }
}