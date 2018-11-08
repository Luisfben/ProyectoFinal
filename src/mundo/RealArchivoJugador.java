package mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RealArchivoJugador implements ArchivoJugador {

	FileOutputStream fos;
	ObjectOutputStream oos;

	public RealArchivoJugador(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		try {
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void guardar(ArrayList<NaveJugador> listaJugadores) {
		try {
			oos.writeObject(listaJugadores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
