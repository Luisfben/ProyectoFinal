package hilos;


import interfaz.InterfazSpaceInvaders;
import mundo.Partida;
import mundo.MundoBuilder;

public class HiloDisparoEnemigos extends Thread {

	private Partida partidaEnemigos;
	private MundoBuilder space;
	private InterfazSpaceInvaders interfaz;
	
	public HiloDisparoEnemigos(Partida a, InterfazSpaceInvaders p, MundoBuilder b) {
		// TODO Auto-generated constructor stub

		partidaEnemigos = a;
		interfaz = p;
		space = b;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (interfaz.estaEnFuncionamiento()) {
			
			for (int i = 0; i < partidaEnemigos.getEnemigos().length; i++) {
				for (int j = 0; j < partidaEnemigos.getEnemigos()[0].length; j++) {
					
					if (partidaEnemigos.getEnemigos()[i][j] != null) {
						if (partidaEnemigos.getEnemigos()[i][j].getDisparoUno() != null) {
							partidaEnemigos.getEnemigos()[i][j].getDisparoUno().shoot1();
							
							if (partidaEnemigos.getEnemigos()[i][j].getDisparoUno().hitsJugador(space.getMundo().getJugadorActual())) {
								partidaEnemigos.getEnemigos()[i][j].eliminarDisparo();
								space.getMundo().getJugadorActual().golpe(1);
							}
						}
					}
				}
			}
			
			try {
				sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			interfaz.getPanelNivel().updateUI();
		}
	}
	
}
