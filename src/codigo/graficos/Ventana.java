package codigo.graficos;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import codigo.CargadorRecursos;


public class Ventana extends JFrame{

	private static final long serialVersionUID = 5979421777239930009L;
	
	private String titulo;
	private final ImageIcon icono;
	JPanel velocidad,selectorRonda;
	public Ventana(final String titulo, final Dibujo sd) {
		this.titulo = titulo;
		
		velocidad=new PanelVelocidad(sd.obtenerAncho()*5/12,sd.obtenerAlto()*7/8,sd.obtenerAncho()/6,sd.obtenerAlto()*4/48);
		selectorRonda=new PanelRonda(840,90,400,20);
		BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/monstruo.png");
		this.icono = new ImageIcon(imagen);
		
		configurarVentana(sd);
	}

	private void configurarVentana(final Dibujo d) {
		setTitle(titulo);
		setIconImage(icono.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		add(selectorRonda,BorderLayout.CENTER);
		add(velocidad,BorderLayout.CENTER);
		add(d, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}