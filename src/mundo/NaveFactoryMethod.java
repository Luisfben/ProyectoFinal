package mundo;

public interface NaveFactoryMethod {
	/* invasor pulpo, calamar, cangrejo */
	public Enemigo createNave(TipoNave tipo, double velocidad, int posX, int posY, int vida, int ancho, int alto,
			int direccion, String rutaImage, String ruta);

	/* nave jugador */
	public Nave createNave(String nombre, String nickname);
}
