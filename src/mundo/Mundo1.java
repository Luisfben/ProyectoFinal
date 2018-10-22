package mundo;

import java.util.ArrayList;

public class Mundo1 {
	// ---------------------------Asociaciones--------------------------
	private ArrayList<NaveJugador> jugadores;
	private Partida partidaActual;
	private NaveJugador jugadorActual;
	private Puntaje primerPuntaje;
	private NaveFactoryMethod factoryNave;
	// ----------------------------Atributos----------------------------
	private boolean enFuncionamiento;

	// --------------------------Getters y Setters-----------------------
	public ArrayList<NaveJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<NaveJugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Partida getPartidaActual() {
		return partidaActual;
	}

	public void setPartidaActual(Partida partidaActual) {
		this.partidaActual = partidaActual;
	}

	public NaveJugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(NaveJugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	public Puntaje getPrimerPuntaje() {
		return primerPuntaje;
	}

	public void setPrimerPuntaje(Puntaje primerPuntaje) {
		this.primerPuntaje = primerPuntaje;
	}

	public NaveFactoryMethod getFactoryNave() {
		return factoryNave;
	}

	public void setFactoryNave(NaveFactoryMethod factoryNave) {
		this.factoryNave = factoryNave;
	}

	public boolean isEnFuncionamiento() {
		return enFuncionamiento;
	}

	public void setEnFuncionamiento(boolean enFuncionamiento) {
		this.enFuncionamiento = enFuncionamiento;
	}

}
