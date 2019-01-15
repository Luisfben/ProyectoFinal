package mundo;

import java.util.ArrayList;

public class ProxyArchivoJugador implements ArchivoJugador {

	private RealArchivoJugador realArchivoJugador;
	private String nombreArchivo;

	public ProxyArchivoJugador(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	@Override
	public void guardar(ArrayList<NaveJugador> listaJugadores) {
		if (realArchivoJugador == null) {
			realArchivoJugador = new RealArchivoJugador(nombreArchivo);
		}
		realArchivoJugador.guardar(listaJugadores);
	}

	@Override
	public void close() {
		if (realArchivoJugador != null) {
			realArchivoJugador.close();
		}
	}
}
