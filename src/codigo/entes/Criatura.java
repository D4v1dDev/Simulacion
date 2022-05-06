package codigo.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import codigo.CargadorRecursos;
import codigo.Constantes;
import codigo.GestorPrincipal;
import codigo.escenario.Escenario;
import codigo.graficos.PanelVelocidad;

public class Criatura {

    private double x = 50, y = 50;
    private double velocidadX, velocidadY;
    private static final int ANCHO = 15, ALTO = 15;

    private double ENERGIA = 500;
    private static final double POSIBILIDAD_MUTACION = 0.5;

    private static final BufferedImage sprite = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/monstruo.png");

    private final double VELOCIDAD;
    private final double SENTIDO;

    private byte comida = 0;

    private static final Random r = new Random();

    public Criatura(final double vel, final double sen) {
        VELOCIDAD = vel;
        SENTIDO = sen;
    }

    public Criatura(final double vel, final double sen, final int x, final int y) {
        VELOCIDAD = vel;
        SENTIDO = sen;
        this.x = x;
        this.y = y;
    }

    public void actualizar() {
        if (ENERGIA > 0 && comida < 2) {
            if (GestorPrincipal.obtenerSegundos() % 1 <= 0.2) {
                if (GestorPrincipal.obtenerAPS() != 0) {
                    elegirDireccion();
                }
            }

            if (velocidadY == -1) if (Escenario.obtenerLimites()[0].intersects(x, y, ANCHO, ALTO)) {
                velocidadY = 1;
            }
            if (velocidadX == -1) if (Escenario.obtenerLimites()[2].intersects(x, y, ANCHO, ALTO)) {
                velocidadX = 1;
            }
            if (velocidadX == 1) if (Escenario.obtenerLimites()[3].intersects(x, y, ANCHO, ALTO)) {
                velocidadX = -1;
            }
            if (velocidadY == 1) if (Escenario.obtenerLimites()[1].intersects(x, y, ANCHO, ALTO)) {
                velocidadY = -1;
            }

            Comida c = Escenario.obtenerComidaMasCercana(x + ANCHO / 2, y + ALTO / 2);
            if (c != null) {
                if (c.obtenerDistancia(x + ANCHO / 2, y + ALTO / 2) < SENTIDO * Constantes.PIXEL_METRO) {
                    ENERGIA -= c.obtenerDistancia(x + ANCHO / 2, y + ALTO / 2) / Constantes.PIXEL_METRO;
                    velocidadX = c.obtenerDistanciaX(x + ANCHO / 2);
                    velocidadY = c.obtenerDistanciaY(y + ANCHO / 2);
                    if (Math.abs(velocidadX) <= Math.abs(velocidadY)) {
                        if (velocidadY <= 0) {
                            velocidadX = (velocidadX / velocidadY) * -VELOCIDAD;
                            velocidadY = -VELOCIDAD;
                        } else {
                            velocidadX = (velocidadX / velocidadY) * VELOCIDAD;
                            velocidadY = VELOCIDAD;
                        }
                    } else {
                        if (velocidadX <= 0) {
                            velocidadY = (velocidadY / velocidadX) * -VELOCIDAD;
                            velocidadX = -VELOCIDAD;
                        } else {
                            velocidadY = (velocidadY / velocidadX) * VELOCIDAD;
                            velocidadX = VELOCIDAD;
                        }
                    }
                }

                if (c.obtenerLimites().intersects(new Rectangle((int) x, (int) y, ANCHO, ALTO))) {
                    comida++;
                    Escenario.eliminarComida(Escenario.obtenerComidaMasCercana(x + ANCHO / 2, y + ALTO / 2));
                }
                if (GestorPrincipal.obtenerAPS() != 0) {
                    moverse();
                }
            }
        }
    }

    private void elegirDireccion() {
        velocidadX = r.nextInt(3) - 1;
        velocidadY = r.nextInt(3) - 1;
        ENERGIA -= Math.abs(velocidadX) * VELOCIDAD + Math.abs(velocidadY) * VELOCIDAD;
    }

    private void moverse() {
        x += (velocidadX * VELOCIDAD * Constantes.PIXEL_METRO) * PanelVelocidad.multiplicador / GestorPrincipal.obtenerAPS();
        y += (velocidadY * VELOCIDAD * Constantes.PIXEL_METRO) * PanelVelocidad.multiplicador / GestorPrincipal.obtenerAPS();
    }

    public void dibujar(Graphics g) {
        g.drawImage(sprite, (int) x, (int) y, ANCHO, ALTO, null);

        g.setColor(new Color((int) (119 + 0.84 * (100 - (ENERGIA / 5))), 50 + (int) (2.01 * (ENERGIA / 5)), 32 + (int) (0.67 * (ENERGIA / 5))));
        g.drawRect((int) x, (int) y + ALTO, (int) (ENERGIA * ANCHO / 500), 1);

    }

    public void posicionar(final int x, final int y) {
        this.x = x;
        this.y = y;
    }


    public Criatura reproducir() {
        return (new Criatura(mutar(VELOCIDAD), mutar(SENTIDO)));
    }

    public double mutar(double valor) {
        if (r.nextInt(100) < POSIBILIDAD_MUTACION * 100) {
            double x = r.nextDouble() * 0.6;
            return valor + x - 0.3;
        } else {
            return valor;
        }
    }

    public int obtenerComida() {
        return comida;
    }

    public int obtenerEnergia() {
        return (int) ENERGIA;
    }

    public void renovarEnergia() {
        ENERGIA = 500;
        comida = 0;
    }

    public double obtenerVelocidad() {
        return VELOCIDAD;
    }

    public double obtenerSentido() {
        return SENTIDO;
    }
}
