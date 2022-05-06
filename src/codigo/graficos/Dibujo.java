package codigo.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import codigo.Constantes;
import codigo.GestorPrincipal;
import codigo.escenario.Escenario;


public class Dibujo extends Canvas {

	private static final long serialVersionUID = -6227038142688953660L;
	
	private int ancho;
	private int alto;

	public Dibujo(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(ancho, alto));
		setFocusable(true);
		requestFocus();
		
		new Escenario();
	}

	public void actualizar() {
		Escenario.actualizar();
	}

	public void dibujar() {
		final BufferStrategy buffer = getBufferStrategy();

		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		final Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

		if (Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0) {
			g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
		}
		
		Escenario.dibujar(g);
		
		g.setColor(Color.RED);
		g.drawString("FPS : "+GestorPrincipal.obtenerFPS(), 10, 10);
		
		Toolkit.getDefaultToolkit().sync();

		g.dispose();

		buffer.show();
	}
	
	public int obtenerAncho() {
		return ancho;
	}

	public int obtenerAlto() {
		return alto;
	}
}