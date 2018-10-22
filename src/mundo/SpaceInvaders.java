package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import excepciones.NicknameYaExisteException;
import excepciones.PartidaYaExisteException;

/**
 * Clase principal del mundo que representa el juego.
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebasti�n Quintero Yoshioka
 *         Proyecto final - Algoritmos y programaci�n II.
 */
public class SpaceInvaders extends MundoBuilder1{

	/**
	 * 
	 */
	private boolean enFuncionamiento;

	public void buildMundo(boolean enFuncionamiento) {
		
		mundo1.setFactoryNave(new NaveFactory());
		
		this.enFuncionamiento = enFuncionamiento;
		
		ArrayList<NaveJugador> jugadores = new ArrayList<NaveJugador>();
		
		mundo1.setJugadores(jugadores);

		mundo1.setPartidaActual(null);
		
		mundo1.setJugadorActual(null);

		mundo1.setPrimerPuntaje(null);
		
		try {
			deserializarJugador();
			deserializarPuntaje();
		} catch (ClassNotFoundException | IOException e) {

		}
	}

	// -----------------------------------------------------------------
	// -----------------------------M�todos-----------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 * @return
	 */
	public boolean getEnFuncionamiento() {
		return this.enFuncionamiento;
	}

	/**
	 * 
	 * @param enFuncionamiento
	 */
	public void setEnFuncionamiento(boolean enFuncionamiento) {
		this.enFuncionamiento = enFuncionamiento;
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public NaveJugador buscarJugador(String nickname) {
		NaveJugador naveBuscada = null;
		boolean buscado = false;

		for (int i = 0; i < mundo1.getJugadores().size() && !buscado; i++) {
			if (mundo1.getJugadores().get(i).getNickname().equalsIgnoreCase(nickname)) {
				naveBuscada = mundo1.getJugadores().get(i); 
				buscado = true;
			}
		}

		return naveBuscada;
	}

	/**
	 * 
	 * @param nombre
	 * @param directorio
	 * @throws NicknameYaExisteException
	 * @throws IOException
	 */
	public void agregarJugador(String nombre, String nickname) throws NicknameYaExisteException, IOException {

		if (buscarJugador(nickname) == null) {
			Nave agregar = mundo1.getFactoryNave().createNave(nombre, nickname);
			mundo1.getJugadores().add((NaveJugador) agregar);
			mundo1.setJugadorActual((NaveJugador) agregar);
			mundo1.getJugadorActual().setPosInicialX(300);
			mundo1.getJugadorActual().setPosIncialY(410);
			mundo1.getJugadorActual().setAncho(30);
			mundo1.getJugadorActual().setAlto(19);
			serializarJugador();
		} else
			throw new NicknameYaExisteException(nickname);

	}

	/**
	 * @throws IOException
	 * 
	 */
	public void serializarJugador() throws IOException {

		File archivo = new File("./data/jugador");

		FileOutputStream fos = new FileOutputStream(archivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(mundo1.getJugadores());

		oos.close();
		fos.close();

	}

	public void iniciarPartida () {
		mundo1.getJugadorActual().setVida(3);
	}

	/**
	 * 
	 * @param ruta
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void deserializarJugador() throws IOException, ClassNotFoundException {

		File archivo = new File("./data/jugador");

		FileInputStream fis = new FileInputStream(archivo);
		ObjectInputStream ois = new ObjectInputStream(fis);

		mundo1.setJugadores((ArrayList<NaveJugador>) ois.readObject());

		ois.close();
		fis.close();
	}

	public ArrayList<Partida> darPartidasJugador() {
		ArrayList<Partida> partidas = new ArrayList<Partida>();
		if (mundo1.getJugadorActual().getPartidaRaiz() != null)
			mundo1.getJugadorActual().getPartidaRaiz().inorden(partidas);

		return partidas;
	}

	public void crearPartida(String nombre) throws PartidaYaExisteException, IOException {
		mundo1.setPartidaActual(mundo1.getJugadorActual().crearPartida(nombre));
		mundo1.getPartidaActual().setPuntaje(new Puntaje(0, mundo1.getJugadorActual().getNickname(), mundo1.getPartidaActual().getNombre()));
		serializarJugador();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<NaveJugador> ordenarPorNickname() {

		ArrayList<NaveJugador> jugadoresOrdenados = (ArrayList<NaveJugador>) mundo1.getJugadores().clone();

		if (mundo1.getJugadores() != null) {
			for (int i = 1; i < jugadoresOrdenados.size(); i++) {
				for (int j = i; j > 0 && jugadoresOrdenados.get(j - 1).getNickname()
						.compareTo(jugadoresOrdenados.get(j).getNickname()) > 0; j--) {
					NaveJugador temp = (NaveJugador) jugadoresOrdenados.get(j);
					jugadoresOrdenados.set(j, jugadoresOrdenados.get(j - 1));
					jugadoresOrdenados.set(j - 1, temp);
				}
			}

		}
		return jugadoresOrdenados;

	}

	
	/**
	 * 
	 */
	public boolean busquedaRapida(String nickname){

		ArrayList<NaveJugador> jugadoresOrdenados = ordenarPorNickname();
		boolean encontrado = false;

		int posicion = -1;
		int inicio = 0;
		int fin = jugadoresOrdenados.size( ) - 1;
		while( inicio <= fin && posicion == -1 && !encontrado)	{
			int medio = ( inicio + fin ) / 2;
			NaveJugador mitad = ( NaveJugador )jugadoresOrdenados.get( medio );
			if( mitad.getNickname().compareToIgnoreCase(nickname) == 0 ){
				posicion = medio;
				encontrado = true;
			}
			else if(mitad.getNickname().compareToIgnoreCase(nickname) > 0){
				fin = medio - 1;
			}
			else{
				inicio = medio + 1;
			}
		}

		if(encontrado)
			mundo1.setJugadorActual((NaveJugador) jugadoresOrdenados.get(posicion));

		return encontrado;
	}

	public void agregarPuntaje(Puntaje puntaje) {
		if (mundo1.getPrimerPuntaje() == null) {
			mundo1.setPrimerPuntaje(puntaje);
		} else {   
			if (mundo1.getPrimerPuntaje().getPuntuacion() < puntaje.getPuntuacion()) {

				puntaje.setSiguiente(mundo1.getPrimerPuntaje());
				mundo1.getPrimerPuntaje().setAnterior(puntaje);
				mundo1.setPrimerPuntaje(puntaje);
			} else {

				Puntaje aux = mundo1.getPrimerPuntaje();


				while (aux.getSiguiente() != null && aux.getSiguiente().getPuntuacion() >= puntaje.getPuntuacion()) {

					aux = aux.getSiguiente();
				}

				Puntaje nuevaSiguiente = null;

				if (aux.getSiguiente() != null) {
					nuevaSiguiente = aux.getSiguiente();
					nuevaSiguiente.setAnterior(puntaje);
				}

				aux.setSiguiente(puntaje);
				puntaje.setAnterior(aux);
				puntaje.setSiguiente(nuevaSiguiente);

			}
		}
	}

	public ArrayList<String> mejoresPuntajes(){

		ArrayList<String> mejoresPuntajes = new ArrayList<String>();
		Puntaje primer = mundo1.getPrimerPuntaje();
		int contador = 1;
		while(primer != null && contador <= 10){
			mejoresPuntajes.add(contador + " " + primer.toString());
			contador++;
			primer = primer.getSiguiente();
		}

		return mejoresPuntajes;
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void serializarPuntaje() throws IOException {

		File archivo = new File("./data/puntaje");

		FileOutputStream fos = new FileOutputStream(archivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(mundo1.getPrimerPuntaje());

		oos.close();
		fos.close();

	}

	/**
	 * 
	 * @param ruta
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void deserializarPuntaje() throws IOException, ClassNotFoundException {

		File archivo = new File("./data/puntaje");

		FileInputStream fis = new FileInputStream(archivo);
		ObjectInputStream ois = new ObjectInputStream(fis);

		mundo1.setPrimerPuntaje( (Puntaje) ois.readObject());

		ois.close();
		fis.close();
	}

	public int puntosPorVida(){
		return (mundo1.getJugadorActual().getVida()*200);
	}

	public int puntosPorDisparos(){
		return mundo1.getJugadorActual().getCantidadDisparos();
	}

	public void eliminarPartida() throws IOException{
		Puntaje nuevoPuntaje = new Puntaje(mundo1.getPartidaActual().getPuntaje().getPuntuacion(), mundo1.getJugadorActual().getNickname(), mundo1.getPartidaActual().getNombre());
		agregarPuntaje(nuevoPuntaje);
		mundo1.getJugadorActual().setPartidaRaiz(mundo1.getJugadorActual().getPartidaRaiz().eliminar(mundo1.getPartidaActual().getNombre()));
		serializarJugador();
		serializarPuntaje();
	}

}
