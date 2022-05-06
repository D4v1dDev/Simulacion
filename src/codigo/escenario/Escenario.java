package codigo.escenario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import codigo.entes.Comida;
import codigo.entes.Criatura;
import codigo.graficos.PanelRonda;
import codigo.graficos.diagramas.GraficaBarras;
import codigo.graficos.diagramas.GraficaCircular;

public class Escenario {
    private static final ArrayList<Criatura> criaturas = new ArrayList<Criatura>();
    private static final ArrayList<Comida> comida = new ArrayList<Comida>();
    private final static int x = 10, y = 10, ancho = 400, alto = 300;
    private final static Rectangle[] LIMITES = {new Rectangle(x, y, ancho, 1), new Rectangle(x, y + alto, ancho, 1), new Rectangle(x, y, 1, alto), new Rectangle(x + ancho, y, 1, alto)};
    private final static Random r = new Random();
    private static int ronda = 1;
    private static GraficaBarras individuosPorRonda;
    private static GraficaCircular individuos;
    private static final int comidaMax = r.nextInt(50) + 70;

    private final static String[] NOMBRE_DATOS = {"Vel=1 & Sen=1", "Vel<1 & Sen=1", "Vel>1 & Sen=1", "Vel=1 & Sen<1", "Vel=1 & Sen>1", "Vel<1 & Sen<1", "Vel>1 & Sen<1", "Vel<1 & Sen>1", "Vel>1 & Sen>1"};

    public Escenario() {
        for (int i = 0; i < r.nextInt(10) + 5; i++) {
            anadirCriatura(new Criatura(1, 1, x, y));
        }
        repartirCriaturas();
        for (int i = 0; i < comidaMax; i++) {
            anadirComida(new Comida(x + r.nextInt(ancho - ancho / 4) + ancho / 8, y + r.nextInt(alto - alto / 4) + alto / 8));
        }
        individuosPorRonda = new GraficaBarras(x + ancho + 10, 50 + y, 200, 100, "Ronda", "Individuos");
        individuosPorRonda.anadirDatos(ronda, criaturas.size());


        individuos = new GraficaCircular(x + ancho + 10, 200 + y, 100, obtenerClasificacionIndividuos(), NOMBRE_DATOS);
    }


    public static void actualizar() {
        int criaturasFullas = 0;
        for (int i = 0; i < criaturas.size(); i++) {
            criaturas.get(i).actualizar();
            if (criaturas.get(i).obtenerComida() == 2 || criaturas.get(i).obtenerEnergia() <= 0) {
                criaturasFullas++;
            }
        }
        if (criaturasFullas == criaturas.size()) {
            terminarRonda();
        }
    }

    private static void terminarRonda() {
        for (int i = 0; i < criaturas.size(); i++) {
            switch (criaturas.get(i).obtenerComida()) {
                case 0:
                    eliminarCriatura(i);
                    break;
                case 2:
                    anadirCriatura(criaturas.get(i).reproducir());

            }
        }
        for (int i = comida.size(); i <= comidaMax; i++) {
            comida.add(new Comida(x + r.nextInt(ancho - ancho / 4) + ancho / 8, y + r.nextInt(alto - alto / 4) + alto / 8));
        }
        for (int i = 0; i < criaturas.size(); i++) {
            criaturas.get(i).renovarEnergia();
        }
        repartirCriaturas();
        ronda++;
        PanelRonda.actualizar();
        individuosPorRonda.anadirDatos(ronda, criaturas.size());
        individuos.actualizar(obtenerClasificacionIndividuos());
    }

    private static int[] obtenerClasificacionIndividuos() {
        int[] vuelta = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < criaturas.size(); i++) {
            int clasificacion = 0;
            if (criaturas.get(i).obtenerVelocidad() < 1) {
                clasificacion += 1;
            } else if (criaturas.get(i).obtenerVelocidad() > 1) {
                clasificacion += 3;
            }
            if (criaturas.get(i).obtenerSentido() < 1) {
                clasificacion += 5;
            } else if (criaturas.get(i).obtenerSentido() > 1) {
                clasificacion += 11;
            }
            switch (clasificacion) {
                case 0:
                    vuelta[0]++;
                    break;
                case 1:
                    vuelta[1]++;
                    break;
                case 3:
                    vuelta[2]++;
                    break;
                case 5:
                    vuelta[3]++;
                    break;
                case 11:
                    vuelta[4]++;
                    break;
                case 6:
                    vuelta[5]++;
                    break;
                case 8:
                    vuelta[6]++;
                    break;
                case 12:
                    vuelta[7]++;
                    break;
                case 14:
                    vuelta[8]++;
                    break;
                default:
                    System.out.print(clasificacion);
            }
        }
        return vuelta;
    }


    public static void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, ancho, alto);
        g.drawString("Ronda " + (PanelRonda.obtenerRonda() + 1), x + ancho + 10, y);
        g.drawString("Individuos : " + criaturas.size(), x + ancho + 10, y + 20);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, ancho, alto);
        individuosPorRonda.dibujar(g);
        for (int i = 0; i < comida.size(); i++) {
            comida.get(i).dibujar(g);
        }

        for (int i = 0; i < criaturas.size(); i++) {
            criaturas.get(i).dibujar(g);
        }
        individuos.dibujar(g);
    }


    public static void repartirCriaturas() {
        int manera = 1;
        switch (manera) {
            case 1:
                for (int i = 0; i < criaturas.size(); i++) {
                    criaturas.get(i).posicionar(r.nextInt(ancho - 50) + x + 32, r.nextInt(alto - 50) + y + 22);
                }
                break;
            case 2:
                for (int i = 0; i < criaturas.size(); i++) {
                    if (i % 4 < 2) {
                        criaturas.get(i).posicionar(r.nextInt(ancho / 2) + ancho / 4 + x, (i % 2 * (alto - y)) + y);
                    } else {
                        criaturas.get(i).posicionar((i % 2 * (ancho - x)) + x, r.nextInt(alto / 2) + alto / 4 + y);
                    }
                }
        }
    }

    public static void eliminarComida(Comida c) {
        for (int i = 0; i < comida.size(); i++) {
            if (comida.get(i) == c) {
                comida.get(i).matar();
                comida.remove(i);
            }
        }
    }

    public static void eliminarCriatura(int i) {
        criaturas.remove(i);
    }

    public static void anadirCriatura(Criatura c) {
        criaturas.add(c);
    }

    public static void anadirComida(Comida c) {
        comida.add(c);
    }

    public static Rectangle[] obtenerLimites() {
        return LIMITES;
    }

    public static Comida obtenerComidaMasCercana(double x, double y) {
        int m = 0;
        for (int i = 0; i < comida.size(); i++) {
            if (comida.get(i).obtenerDistancia(x, y) < comida.get(m).obtenerDistancia(x, y)) {
                m = i;
            }
        }
        if (comida.size() == 0) {
            return null;
        }
        return comida.get(m);
    }
}