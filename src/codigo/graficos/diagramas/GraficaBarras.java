package codigo.graficos.diagramas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import codigo.graficos.PanelRonda;

public class GraficaBarras {

    private final int x, y, ancho, alto;

    private final ArrayList<Integer> abscisas = new ArrayList<>(), ordenadas = new ArrayList<>();


    private int apogeoAbscisas = 0;
    private int apogeoOrdenadas = 0;

    private final String nombreA, nombreO;

    public GraficaBarras(final int x, final int y, final int ancho, final int alto, final String abscisas, final String ordenadas) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        nombreA = abscisas;
        nombreO = ordenadas;

    }

    private void encontrarApogeoAbscisas() {
        int ind = 0;
        for (int i = 0; i < abscisas.size(); i++) {
            if (abscisas.get(i) > abscisas.get(ind)) {
                ind = i;
            }
        }
        apogeoAbscisas = abscisas.get(ind);
    }

    private void encontrarApogeoOrdenadas() {
        int ind = 0;
        for (int i = 0; i < ordenadas.size(); i++) {
            if (ordenadas.get(i) > ordenadas.get(ind)) {
                ind = i;
            }
        }
        apogeoOrdenadas = ordenadas.get(ind);
    }


    public void dibujar(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawRect(x, y, ancho, alto);

        g.setFont(new Font("Calibri", Font.BOLD, 8));

        g.drawString(nombreA, x + ancho - 5, y + alto + 14);
        g.drawString(nombreO, x, y - 8);

        g.setFont(new Font("Calibri", Font.PLAIN, 4));
        int incremento = apogeoOrdenadas / (ancho / 8) + 1;
        for (int i = 0; i < apogeoAbscisas; i += incremento) {
            g.drawString("" + (apogeoAbscisas - i), (int) (x + ((100 - i * 100 / apogeoAbscisas) * ancho / 100)), y + alto + 6);
            g.drawLine(x + ((100 - i * 100 / apogeoAbscisas) * ancho / 100), y + alto - 2, (x + ((100 - i * 100 / apogeoAbscisas) * ancho / 100)), y + alto + 2);
        }
        incremento = apogeoOrdenadas / (alto / 8) + 1;
        for (int i = 0; i < apogeoOrdenadas; i += incremento) {

            g.drawString("" + i, x - 8, (y + ((100 - i * 100 / apogeoOrdenadas) * 100 / alto)));
            g.drawLine(x - 2, (y + ((100 - i * 100 / apogeoOrdenadas) * 100 / alto)), x + 2, (y + ((100 - i * 100 / apogeoOrdenadas) * 100 / alto)));
        }

        for (int i = 0; i < abscisas.size(); i++) {
            g.fillRect(x + i * ancho / abscisas.size(), y + alto - (ordenadas.get(i) * alto / apogeoOrdenadas), ((i + 1) * ancho / abscisas.size()) - (i * ancho / abscisas.size()), (ordenadas.get(i) * alto / apogeoOrdenadas));
        }
        g.setColor(Color.RED);
        g.drawLine(x + (((PanelRonda.obtenerRonda() + 1) * 100 / apogeoAbscisas) * ancho / 100), y, (x + (((PanelRonda.obtenerRonda() + 1) * 100 / apogeoAbscisas) * ancho / 100)), y + alto);
    }

    public void anadirDatos(int abscisa, int ordenada) {
        abscisas.add(abscisa);
        ordenadas.add(ordenada);
        encontrarApogeoAbscisas();
        encontrarApogeoOrdenadas();
    }
}
