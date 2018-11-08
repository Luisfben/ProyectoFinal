package mundo;

import java.util.ArrayList;

public interface ArchivoJugador {
	void guardar(ArrayList<NaveJugador> listaJugadores);

	void close();
}
