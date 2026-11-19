package es.davante.edadcanina;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EdadCaninaModeloTest {

    private EdadCaninaModelo modelo;

    @Before
    public void setUp() {
        modelo = new EdadCaninaModelo();
    }

    @Test
    public void edadValida_calculaCorrectamente() {
        Validacion resultado = modelo.calcularEdadCanina("6");
        assertEquals(Validacion.OK, resultado);
        assertEquals(42, modelo.getEdadCanina());
    }

    @Test
    public void textoVacio_devuelveValidacionVacio() {
        Validacion resultado = modelo.calcularEdadCanina("   ");
        assertEquals(Validacion.VACIO, resultado);
    }

    @Test
    public void textoNoNumerico_devuelveValidacionNoNumero() {
        Validacion resultado = modelo.calcularEdadCanina("abc");
        assertEquals(Validacion.NO_NUMERO, resultado);
    }

    @Test
    public void edadMenorQueMinima_devuelveFueraDeRango() {
        Validacion resultado = modelo.calcularEdadCanina("-1");
        assertEquals(Validacion.FUERA_DE_RANGO, resultado);
    }

    @Test
    public void edadMayorQueMaxima_devuelveFueraDeRango() {
        Validacion resultado = modelo.calcularEdadCanina("31");
        assertEquals(Validacion.FUERA_DE_RANGO, resultado);
    }

    @Test
    public void edadLimiteCero_esValidaYDaCero() {
        Validacion resultado = modelo.calcularEdadCanina("0");
        assertEquals(Validacion.OK, resultado);
        assertEquals(0, modelo.getEdadCanina());
    }
    @Test
    public void edadValida_calculaEtapaVida() {
        modelo.calcularEdadCanina("6");
        assertEquals(42, modelo.getEdadCanina());
        assertEquals("Adulto", modelo.getEtapaVida());
    }
}