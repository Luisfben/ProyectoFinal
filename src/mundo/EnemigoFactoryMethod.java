package mundo;

public interface EnemigoFactoryMethod {
	/* invasor pulpo, calamar, cangrejo */
	public Enemigo createEnemigo(TipoNave tipo, double velocidad, int posX, int posY, int vida, int ancho, int alto,
			int direccion, String rutaImage, String ruta);

}
