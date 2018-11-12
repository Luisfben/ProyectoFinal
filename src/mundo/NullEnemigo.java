package mundo;

public class NullEnemigo extends Enemigo{

	public NullEnemigo(double velocidad, int posX, int posY, int vida, int ancho, int alto, int direccion,
			String rutaImage, String rutaImage2) {
		super(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage="./data/imagenes/Naves/t0.png", rutaImage2="./data/imagenes/Naves/t1.png");

		rutaImage = ("./data/imagenes/Naves/t0.png");
		setPuntosPorMuerte(30);
	}

}
