package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import interfaz.InterfazSpaceInvaders;
import mundo.NaveJugador;
import mundo.MundoBuilder;

/**
 * 
 * @author Manuel Alejandro Coral Lozano - Juan Sebasti�n Quintero Yoshioka
 *         Proyecto final - Algoritmos y programaci�n II.
 */
public class Teclado implements KeyListener, PalancaMando {

	// -----------------------------------------------------------------
	// ----------------------------Atributos----------------------------
	// -----------------------------------------------------------------

	// public Partida actu;

	private MundoBuilder actu;

	public NaveJugador navesita;

	public InterfazSpaceInvaders interfaz;

	// -----------------------------------------------------------------
	// -----------------------------M�todos-----------------------------
	// -----------------------------------------------------------------

	public Teclado(InterfazSpaceInvaders principal, MundoBuilder actual) {
		// TODO Auto-generated constructor stub
		interfaz = principal;
		actu = actual;
		navesita = actu.getMundo().getJugadorActual();

	}

	public void keyPressed(KeyEvent e) {

		if (actu.getMundo().isEnFuncionamiento()) {
			navesita = actu.getMundo().getJugadorActual();
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {

				if (navesita.getDisparoUno() == null) {
					navesita.disparar(interfaz.darPosActualJugador(), 410);
					interfaz.startHiloJugador();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				navesita.mover(-1);
				interfaz.getPanelNivel().updateUI();
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				navesita.mover(1);
				interfaz.getPanelNivel().updateUI();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			interfaz.cerrar();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (interfaz.estaEnPausa()) {
				interfaz.modificarFuncionamiento(true);
				interfaz.cambiarPausa(false);
				interfaz.iniciarTodosLosHilos();
			} else {
				interfaz.modificarFuncionamiento(false);
				interfaz.cambiarPausa(true);
			}
		}
	}

	/**
	 * 
	 */
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * 
	 */
	public void keyTyped(KeyEvent e) {

	}

	public MundoBuilder getActu() {
		return actu;
	}

	public void setActu(MundoBuilder actu) {
		this.actu = actu;
	}

	public NaveJugador getNavesita() {
		return navesita;
	}

	public void setNavesita(NaveJugador navesita) {
		this.navesita = navesita;
	}

	public InterfazSpaceInvaders getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(InterfazSpaceInvaders interfaz) {
		this.interfaz = interfaz;
	}

}
