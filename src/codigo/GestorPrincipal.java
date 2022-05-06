package codigo;

import codigo.graficos.Dibujo;
import codigo.graficos.PanelVelocidad;
import codigo.graficos.Ventana;

public class GestorPrincipal {
    private boolean enFuncionamiento = false;
    private final String titulo;
    private final int ancho, alto;

    public static Dibujo d;

    private static int fps = 0;
    private static int aps = 0;
    private static double segundos = 0;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public static void main(String[] args) {
        GestorPrincipal gp = new GestorPrincipal("Simulacion", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();
    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
    }

    private void inicializar() {
        d = new Dibujo(ancho, alto);
        new Ventana(titulo, d);
    }

    private void iniciarBuclePrincipal() {
        int actualizacionesAcumuladas = 0;
        int framesAcumulados = 0;

        final int NS_POR_SEGUNDO = 1000000000;
        int APS_OBJETIVO = 60;
        double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        long referenciaSegundos = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            segundos = (double) (inicioBucle - referenciaSegundos) / NS_POR_SEGUNDO;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {

                if (PanelVelocidad.multiplicador != 0) {
                    actualizar();

                    APS_OBJETIVO = (int) (60 * PanelVelocidad.multiplicador);
                    NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
                }


                actualizacionesAcumuladas++;
                delta--;

            }

            dibujar();
            framesAcumulados++;

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

                aps = actualizacionesAcumuladas;
                fps = framesAcumulados;

                actualizacionesAcumuladas = 0;
                framesAcumulados = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }

    private void actualizar() {
        d.actualizar();
    }


    private void dibujar() {
        d.dibujar();
    }

    public static int obtenerFPS() {
        return fps;
    }

    public static int obtenerAPS() {
        return aps;
    }

    public static double obtenerSegundos() {
        return segundos;
    }
}
