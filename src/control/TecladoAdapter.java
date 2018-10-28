package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import interfaz.InterfazSpaceInvaders;
import mundo.MundoBuilder;
import mundo.NaveJugador;

public class TecladoAdapter implements KeyListener, PalancaMando {

	private Teclado teclado;

	private MundoBuilder actu;

	private NaveJugador navesita;

	private InterfazSpaceInvaders interfaz;

	public TecladoAdapter(Teclado teclado) {
		this.teclado = teclado;
		this.actu = teclado.getActu();
		this.navesita = teclado.getNavesita();
		this.interfaz = teclado.getInterfaz();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		this.teclado.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (actu.getMundo().isEnFuncionamiento()) {

			navesita = actu.getMundo().getJugadorActual();
			if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) { // .VK_SPACE

				if (navesita.getDisparoUno() == null) {
					navesita.disparar(interfaz.darPosActualJugador(), 410);
					interfaz.startHiloJugador();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) { // .VK_LEFT) {
				navesita.mover(-1);
				interfaz.getPanelNivel().updateUI();
			}

			if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) { // VK_RIGHT) {
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

	@Override
	public void keyReleased(KeyEvent e) {
		this.teclado.keyReleased(e);
	}

}
