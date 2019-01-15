package excepciones;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import util.Constantes;

public class NicknameYaExisteException extends Exception {
	
	private final static Logger log = Logger.getLogger(NicknameYaExisteException.class);

	public NicknameYaExisteException(String nombre) {
		super("El jugador con el nombre " + nombre + " ya existe");
		PropertyConfigurator.configure(Constantes.logProperties);
		log.warn("El jugador con el nombre " + nombre + " ya existe");
	}
}
