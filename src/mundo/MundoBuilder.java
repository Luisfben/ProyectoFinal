package mundo;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.NicknameYaExisteException;
import excepciones.PartidaYaExisteException;

public abstract class MundoBuilder {
	protected Mundo mundo;

	public Mundo getMundo() {
		return mundo;
	}

	public void crearMundo() {
		mundo = new Mundo();
	}

	public abstract void buildMundo(boolean enFuncionamiento);
	
	public abstract NaveJugador buscarJugador(String nickname);

	public abstract void agregarJugador(String nombre, String nickname) throws NicknameYaExisteException, IOException;

	public abstract void serializarJugador() throws IOException;

	public abstract void iniciarPartida();

	public abstract void deserializarJugador() throws IOException, ClassNotFoundException;

	public abstract ArrayList<Partida> darPartidasJugador();

	public abstract void crearPartida(String nombre) throws PartidaYaExisteException, IOException;

	public abstract ArrayList<NaveJugador> ordenarPorNickname();

	public abstract boolean busquedaRapida(String nickname);

	public abstract void agregarPuntaje(Puntaje puntaje);

	public abstract ArrayList<String> mejoresPuntajes();

	public abstract void serializarPuntaje() throws IOException;

	public abstract void deserializarPuntaje() throws IOException, ClassNotFoundException;

	public abstract int puntosPorVida();

	public abstract int puntosPorDisparos();

	public abstract void eliminarPartida() throws IOException;
}
