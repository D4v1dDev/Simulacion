package codigo.graficos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelVelocidad extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JButton pausa=new JButton("Pause");
	private static JButton mas=new JButton("x2");
	private static JButton menos=new JButton("/2");
	private static JLabel velocidad=new JLabel("x1.0");
	private int ancho;

	private int alto;
	public static double multiplicador=1;
	
	public PanelVelocidad(final int x,final int y, final int ancho,final int alto) {
		setBackground(Color.BLACK);
		setBounds(x,y,ancho,alto);
		setLayout(null);
		this.ancho=ancho;
		this.alto=alto;
		menos.setBounds(ancho/2-85,alto/10, 50, 30);
		menos.addActionListener(this);
		add(menos);
		
		pausa.setBounds(ancho/2-35, alto/10, 70, 30);
		pausa.addActionListener(this);
		add(pausa);
		
		mas.setBounds(ancho/2+35, alto/10, 50, 30);
		mas.addActionListener(this);
		add(mas);
		
		velocidad.setBounds(ancho/2-(velocidad.getText().length()*9/2), alto/3, ancho/2-12-velocidad.getText().length()*9, 50);
		add(velocidad);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(menos==e.getSource() && multiplicador>0.2) {
			multiplicador/=2;
		}
		if(pausa==e.getSource()) {
			if(multiplicador==0) {
				multiplicador=1;
			}else {
				multiplicador=0;
			}
		}
		if(mas==e.getSource() && multiplicador<3) {
			multiplicador*=2;
		}
		
		velocidad.setText("x"+multiplicador);
		velocidad.setBounds(ancho/2-(velocidad.getText().length()*9/2), alto/3, ancho/2-12-velocidad.getText().length()*9, 50);
	}
	
}
