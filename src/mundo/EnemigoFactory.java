package mundo;

import java.io.Serializable;

public class EnemigoFactory implements EnemigoFactoryMethod, Serializable {

	@Override
	public Enemigo createEnemigo(TipoNave tipo, double velocidad, int posX, int posY, int vida, int ancho, int alto,
			int direccion, String rutaImage, String ruta) {
		switch (tipo) {
		case INVASORPULPO:
			return new InvasorPulpo(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
		case INVASORCALAMAR:
			return new InvasorCalamar(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
		case INVASORCANGREJO:
			return new InvasorCangrejo(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
		default:
			return new NullEnemigo(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
			//return null;
		}
	}

}
