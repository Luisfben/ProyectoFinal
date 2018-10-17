package mundo;

import java.io.Serializable;

public class NaveFactory implements NaveFactoryMethod, Serializable {

	@Override
	public Enemigo createNave(TipoNave tipo, double velocidad, int posX, int posY, int vida, int ancho, int alto,
			int direccion, String rutaImage, String ruta) {
		switch (tipo) {
		case INVASORPULPO:
			return new InvasorPulpo(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
		case INVASORCALAMAR:
			return new InvasorCalamar(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
		case INVASORCANGREJO:
			return new InvasorCangrejo(velocidad, posX, posY, vida, ancho, alto, direccion, rutaImage, ruta);
		default:
			return null;
		}
	}

	@Override
	public Nave createNave(String nombre, String nickname) {
		return new NaveJugador(nombre,nickname);
	}
}
