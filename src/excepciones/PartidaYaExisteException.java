package excepciones;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import util.Constantes;

public class PartidaYaExisteException extends Exception {

	private final static Logger log = Logger.getLogger(NicknameYaExisteException.class);

	public PartidaYaExisteException(String nombrePartida) {
		super("La partida con el nombre: " + nombrePartida + " ya existe");
		PropertyConfigurator.configure(Constantes.logProperties);
		log.warn("La partida con el nombre: " + nombrePartida + " ya existe");
	}
}
