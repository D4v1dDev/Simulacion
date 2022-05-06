package codigo.graficos;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelRonda extends JPanel implements ChangeListener{

	private static final long serialVersionUID = 1L;

	private static JSlider selector;
	
	private static int ronda=1;

	private static int rondaMax=1;
	
	public PanelRonda(final int x,final int y, final int ancho,final int alto) {
		setBackground(Color.BLACK);
		setBounds(x,y,ancho,alto);
		setLayout(null);
		
		
		selector=new JSlider(JSlider.HORIZONTAL,1,rondaMax+1,1);
		selector.setBackground(Color.BLACK);
		
		selector.setBounds(0,0,ancho,alto);
		selector.setPaintTrack(false);
		selector.setVisible(false);
		selector.addChangeListener(this);
		add(selector);
	}
	
	public static void actualizar() {
		rondaMax++;
		selector.setMaximum(rondaMax+1);
		selector.setMajorTickSpacing(selector.getWidth()/rondaMax);
		selector.setVisible(true);
	}
	
	public static void establecerRondaMax(final int ronda) {
		rondaMax=ronda;
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		ronda=selector.getValue()-1;
	}

	public static int obtenerRonda() {
		return ronda-1;
	}
}
