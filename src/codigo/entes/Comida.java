package codigo.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
 

public class Comida {
	
	private double x,y;
	
	private static final int ancho=3,alto=3;
	private Color c=new Color(250,0,120);
	
	public Comida(final double x, final double y) {
		this.x=x;
		this.y=y;
	}
	
	public void dibujar(Graphics g) {
		g.setColor(c);
		g.fillRect((int)x, (int)y, ancho, alto);
	}
	
	public int obtenerDistancia(final double x,final double y) {
		return (int) (Math.abs(this.x-(x+ancho/2))+Math.abs(this.y-(y+alto/2)));
	}

	public Rectangle obtenerLimites() {
		return new Rectangle((int)x,(int)y,ancho,alto);
	}

	public void matar() {
		c=Color.WHITE;
	}

	public int obtenerDistanciaX(final double x) {
		return (int)(this.x-(x+ancho/2));
	}
	public int obtenerDistanciaY(final double y) {
		return (int) (this.y-(y+alto/2));
	}

}
