package codigo.graficos.diagramas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import codigo.graficos.PanelRonda;

public class GraficaCircular {
	
	private final int x,y,diametro;
	
	private int sumo=0;
	private ArrayList<int[]> datos=new ArrayList<int[]>(); 
	private final Color[] colores= {Color.RED,Color.BLUE,Color.green,Color.MAGENTA,Color.ORANGE,Color.GRAY,Color.PINK,Color.WHITE,Color.CYAN}; 
	Random r = new Random();
	private final String[] nombreDatos;
	
	public GraficaCircular(final int x,final int y, final int diametro,final int[] datos,final String[] nombreDatos) {
		
		this.x=x;
		this.y=y;
		this.diametro=diametro;
		this.nombreDatos=nombreDatos;
		this.datos.add(datos);
		//colores = new Color[datos.length];
		for(int i = 0;i<datos.length;i++) {
			//colores[i]=new Color(r.nextInt(155)+100,r.nextInt(155)+100,r.nextInt(155)+100);
			sumo+=datos[i];
		}
	}
	
	
	public void actualizar(int[] datos) {	
		
		this.datos.add(datos);
	}
	
	
	public void dibujar(Graphics g) {
		double anterior=0;
		g.setFont(new Font("Calibri",6,6));
		try {
			actualizarSumo();
			int[] datosADibujar=datos.get(PanelRonda.obtenerRonda());
			
			for(int i = 0;i<datosADibujar.length;i++) {
				g.setColor(colores[i]);
				g.drawRect(x+diametro+2, y+8+i*12, 1, 1);
				if(datosADibujar[i]!=0)	{
					g.fillArc(x, y, diametro, diametro, (int)anterior,(int)(360-anterior));
					anterior+=3.60F*(datosADibujar[i]*100/sumo);
					if(anterior%1!=0) {	
						anterior=(int)anterior+1;
					}
				}
				
				g.setColor(Color.white);
				g.drawString(nombreDatos[i]+" "+datosADibujar[i]+" ("+(datosADibujar[i]*100/sumo)+"%)", x+diametro+5, y+10+i*12);
			}
		} catch (Exception e) {
			
		}
	}


	private void actualizarSumo() {
		sumo=0;
		int[] datosRonda=datos.get(PanelRonda.obtenerRonda());
		for(int i = 0;i<datosRonda.length;i++) {
			sumo+=datosRonda[i];
		}
	}
}