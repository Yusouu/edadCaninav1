package es.davante.edadcanina;

public class EdadCaninaModelo {

    public static final int FACTOR_CANINO = 7;
    public static final int EDAD_MINIMA = 0;
    public static final int EDAD_MAXIMA = 30;

    private int edadHumana;
    private int edadCanina;
    private Validacion validacion = Validacion.OK;

    public int getEdadHumana() {
        return edadHumana;
    }

    public int getEdadCanina() {
        return edadCanina;
    }

    public Validacion getValidacion() {
        return validacion;
    }


    public Validacion calcularEdadCanina(String entradaTexto) {
        String textoLimpio = entradaTexto == null ? "" : entradaTexto.trim();

        if (textoLimpio.isEmpty()) {
            validacion = Validacion.VACIO;
            edadCanina = 0;
            return validacion;
        }

        int edadParseada;
        try {
            edadParseada = Integer.parseInt(textoLimpio);
        } catch (NumberFormatException e) {
            validacion = Validacion.NO_NUMERO;
            edadCanina = 0;
            return validacion;
        }

        if (edadParseada < EDAD_MINIMA || edadParseada > EDAD_MAXIMA) {
            validacion = Validacion.FUERA_DE_RANGO;
            edadCanina = 0;
            return validacion;
        }

        edadHumana = edadParseada;
        edadCanina = edadParseada * FACTOR_CANINO;
        validacion = Validacion.OK;
        return validacion;
    }


    public void limpiar() {
        edadHumana = 0;
        edadCanina = 0;
        validacion = Validacion.OK;
    }
}