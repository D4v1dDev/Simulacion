package codigo;


public class Constantes {

	public static final int LADO_SPRITE = 32;

	public static int ANCHO_JUEGO = 640;
	public static int ALTO_JUEGO = 360;

	// public static int ANCHO_PANTALLA_COMPLETA = 1920;
	// public static int ALTO_PANTALLA_COMPLETA = 1080;

	public static int ANCHO_PANTALLA_COMPLETA = 1280;
	public static int ALTO_PANTALLA_COMPLETA = 720;

	// 4:3

	public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;

	public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
	public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

	public static int MARGEN_X = ANCHO_JUEGO / 2 - LADO_SPRITE / 2;
	public static int MARGEN_Y = ALTO_JUEGO / 2 - LADO_SPRITE / 2;

	public static String RUTA_MAPA = "C:\\Users\\CASA\\Desktop\\Java Files\\After-D\\src\\recursos\\mapas\\03.adm";
	public static String RUTA_ICONO_RATON = "C:\\Users\\CASA\\Desktop\\Java Files\\After-D\\src\\recursos\\imagenes\\iconos\\iconoCursor.png";
	public static String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/2.png";
	public static String RUTA_PERSONAJE_PISTOLA = "/imagenes/hojasPersonajes/4.png";
	public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/iconoVentana.png";
	public static String RUTA_LOGOTIPO = "/imagenes/iconos/logotipo.png";
	public static String RUTA_OBJETOS = "/imagenes/hojasObjetos/1.png";
	public static String RUTA_OBJETOS_ARMAS = "/imagenes/hojasObjetos/armas.png";
	public static String RUTA_ENEMIGOS = "/imagenes/hojasEnemigos/";
	
	//DATOS
	
	public static final int PIXEL_METRO=20;

}