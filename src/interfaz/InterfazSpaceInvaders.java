package interfaz;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.Teclado;
import excepciones.NicknameYaExisteException;
import excepciones.PartidaYaExisteException;
import hilos.HiloAnimacionEnemigos;
import hilos.HiloAuxiliarCreaDisparo;
import hilos.HiloDisparoEnemigos;
import hilos.HiloDisparoJugador;
import hilos.HiloEnemigos;
import mundo.MundoBuilder1;
import mundo.NaveJugador;
import mundo.Partida;
import mundo.SpaceInvaders;

public class InterfazSpaceInvaders extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static CardLayout card = new CardLayout();

	public static Container contenedor;

	private PanelImagenInicial imagen;

	private Teclado tecladito;

	private PanelMenu panelMenu;

	private PanelNivel panelNivel;

	private HiloEnemigos hilitoEnemigo;

	private HiloDisparoJugador hilitoDisparo;

	private HiloDisparoEnemigos hilitoEnemigoDisparo;

	private HiloAuxiliarCreaDisparo hilitoAuxiliar;

	private HiloAnimacionEnemigos hilitoAnimacion;

	private boolean pausa;
	
	private static InterfazSpaceInvaders instancia;
	
	private MundoBuilder1 mundoBuilder;

	public static InterfazSpaceInvaders getInstance() {
		if(instancia==null) {
			instancia = new InterfazSpaceInvaders();
		}
		return instancia;
	}
	
	public void constructInterfaceSpaceInvader() {
		TestInterfazSpaceInvaders();
	}
	
	public void setMundoBuilder1(MundoBuilder1 mb) {
		mundoBuilder= mb;
	}
	
	private void TestInterfazSpaceInvaders() {

		mundoBuilder.creaMundo();
		mundoBuilder.buildMundo(false);

		panelMenu = new PanelMenu(this);
		panelNivel = new PanelNivel(mundoBuilder.getMundo().getPartidaActual(), mundoBuilder, this);

		imagen = new PanelImagenInicial(this);
		addKeyListener(imagen);
		contenedor = this.getContentPane();
		card.addLayoutComponent(imagen, "Inicio");
		card.addLayoutComponent(panelMenu, "Men�");
		card.addLayoutComponent(panelNivel, "Juego");

		contenedor.add(imagen);
		contenedor.add(panelMenu);
		contenedor.add(panelNivel);

		contenedor.setLayout(card);
		card.show(contenedor, "Inicio");

		tecladito = new Teclado(this, mundoBuilder);
		addKeyListener(tecladito);

		setSize(640, 480);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));

	}

	/**
	 * 
	 * @param nombre
	 */
	public void cambiarPanel(String nombre) {
		if (nombre.equals("Men�")) {
			card.show(contenedor, "Men�");
		} else if (nombre.equals("Juego")) {
			card.show(contenedor, "Juego");
		}
	}

	/**
	 * 
	 */
	public void cerrar() {
		try {
			mundoBuilder.serializarJugador();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * 
	 * @return
	 */
	public boolean estaEnPausa() {
		return pausa;
	}

	/**
	 * 
	 * @param paus
	 */
	public void cambiarPausa (boolean paus) {
		this.pausa = paus;
	}

	/**
	 * 
	 */
	public void startHiloEnemigo() {
		for (int i = 0; i < mundoBuilder.getMundo().getPartidaActual().getEnemigos().length; i++) {
			for (int j = 0; j < mundoBuilder.getMundo().getPartidaActual().getEnemigos()[0].length; j++) {
				if (mundoBuilder.getMundo().getPartidaActual().getEnemigos()[i][j] != null) {
					hilitoEnemigo = new HiloEnemigos(mundoBuilder.getMundo().getPartidaActual().getEnemigos()[i][j], this);
					hilitoEnemigo.start();
				}
			}
		}
	}

	/**
	 * 
	 */
	public void startHiloAuxiliar() {
		hilitoAuxiliar = new HiloAuxiliarCreaDisparo(mundoBuilder.getMundo().getPartidaActual(), this);
		hilitoAuxiliar.start();
	}

	/**
	 * 
	 */
	public void startHiloAnimacion() {
		for (int i = 0; i < mundoBuilder.getMundo().getPartidaActual().getEnemigos().length; i++) {
			for (int j = 0; j < mundoBuilder.getMundo().getPartidaActual().getEnemigos()[0].length; j++) {
				if (mundoBuilder.getMundo().getPartidaActual().getEnemigos()[i][j] != null) {
					hilitoAnimacion = new HiloAnimacionEnemigos(mundoBuilder.getMundo().getPartidaActual().getEnemigos()[i][j], this);
					hilitoAnimacion.start();
				}
			}
		}
	}

	/**
	 * 
	 */
	public void startHiloDisparoEnemigo() {
		hilitoEnemigoDisparo = new HiloDisparoEnemigos(mundoBuilder.getMundo().getPartidaActual(), this, mundoBuilder);
		hilitoEnemigoDisparo.start();
	}

	/**
	 * 
	 */
	public void startHiloJugador() {
		hilitoDisparo = new HiloDisparoJugador((NaveJugador) mundoBuilder.getMundo().getJugadorActual(), this,
				mundoBuilder.getMundo().getPartidaActual().getEnemigos(), mundoBuilder.getMundo().getPartidaActual());
		hilitoDisparo.start();
	}

	/**
	 * 
	 * @return
	 */
	public int darPosActualJugador() {
		return panelNivel.getPosJugadorActualX();
	}

	/**
	 * 
	 * @return
	 */
	public boolean estaEnFuncionamiento() {
		//return mundo.getEnFuncionamiento();
		return mundoBuilder.getMundo().isEnFuncionamiento();
	}

	/**
	 * 
	 * @param salida
	 */
	public void modificarFuncionamiento (boolean salida) {
		mundoBuilder.getMundo().setEnFuncionamiento(salida);
	}

	/**
	 * 
	 * @return
	 */
	public PanelNivel getPanelNivel() {
		return panelNivel;
	}

	/**
	 * 
	 * @return
	 */
	public NaveJugador getJugadorActual() {
		return mundoBuilder.getMundo().getJugadorActual();
	}

	/**
	 * 
	 * @param panelNivel
	 */
	public void setPanelNivel(PanelNivel panelNivel) {
		this.panelNivel = panelNivel;
	}

	/**
	 * 
	 * @return
	 */
	public PanelMenu getPanelMenu() {
		return panelMenu;
	}

	/**
	 * 
	 * @param panelMenu
	 */
	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}

	/**
	 * 
	 */
	public void iniciarTodosLosHilos() {
		mundoBuilder.getMundo().setEnFuncionamiento(true);
		startHiloJugador();
		startHiloEnemigo();
		startHiloAnimacion();
		startHiloAuxiliar();
		startHiloDisparoEnemigo();
	}

	/**
	 * 
	 */
	public void matarHilos (){
		hilitoAnimacion = null;
		hilitoAuxiliar = null;
		hilitoDisparo = null;
		hilitoEnemigoDisparo = null;
		hilitoEnemigo = null;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void reqCrearPartida(String nombre) {
		try {
			mundoBuilder.crearPartida(nombre);
			mundoBuilder.getMundo().getPartidaActual().inicializarPartida();
			actualizarPartidas();
			actualizarPartidaActual(nombre);
			panelNivel.setPartida(mundoBuilder.getMundo().getPartidaActual());
			mundoBuilder.iniciarPartida();
			cambiarPanel("Juego");
		} catch (PartidaYaExisteException | IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al crear la partida", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 
	 * @param nombre
	 * @param nickname
	 */
	public void reqAgregarJugador(String nombre, String nickname) {
		try {
			mundoBuilder.agregarJugador(nombre, nickname);
			panelMenu.repaint();
			actualizarJugadores();
			actualizarJugadorActual(nickname);
		} catch (NicknameYaExisteException | IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error al agregar el jugador",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 
	 * @param nickname
	 */
	public void actualizarJugadorActual(String nickname) {
		if (!nickname.equals("")) {
			NaveJugador actual = mundoBuilder.buscarJugador(nickname);
			mundoBuilder.getMundo().setJugadorActual(actual);
			panelMenu.repaint();
		} else
			JOptionPane.showMessageDialog(this, "Por favor cree alg�n jugador", "No existen jugadores",
					JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 
	 * @param nombre
	 */
	public void actualizarPartidaActual(String nombre) {

		Partida partidaActual = mundoBuilder.getMundo().getJugadorActual().getPartidaRaiz().buscarPartida(nombre);
		mundoBuilder.getMundo().setPartidaActual(partidaActual);
		panelNivel.setPartida(partidaActual);
		iniciarTodosLosHilos();

	}

	/**
	 * 
	 */
	public void actualizarJugadores() {
		ArrayList<NaveJugador> jugadores = mundoBuilder.getMundo().getJugadores();
		if (jugadores == null)
			jugadores = new ArrayList<>();
		panelMenu.getDialogoSeleccionarJugador().cambiarListaJugadores(jugadores);
	}

	/**
	 * 
	 */
	public void actualizarPartidas() {
		ArrayList<Partida> partidas = mundoBuilder.darPartidasJugador();
		if (partidas.size() == 0)
			partidas = new ArrayList<Partida>();
		panelMenu.getDialogoSeleccionarPartida().cambiarListaPartidas(partidas);
	}

	/**
	 * 
	 */
	public void nivelCompleto () {
		try {
			if (mundoBuilder.getMundo().getPartidaActual().nivelCompleto()) {
				iniciarTodosLosHilos();
			} else {
				panelMenu.repaint();
				mundoBuilder.eliminarPartida();
				actualizarPartidas();
				cambiarPanel("Men�");	
				panelMenu.repaint();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void perder(){
		panelMenu.repaint();
		try {
			mundoBuilder.eliminarPartida();
		} catch (IOException e) {
			e.printStackTrace();
		}
		actualizarPartidas();
		cambiarPanel("Men�");	
		panelMenu.repaint();
	}

	/**
	 * 
	 */
	public void ordenarJugadores() {
		ArrayList<NaveJugador> jugadores = mundoBuilder.ordenarPorNickname();
		if (jugadores == null)
			jugadores = new ArrayList<>();
		panelMenu.getDialogoSeleccionarJugador().cambiarListaJugadores(jugadores);
	}

	/**
	 * 
	 * @param nickname
	 */
	public void loginRapido(String nickname){
		if(!mundoBuilder.busquedaRapida(nickname)){
			JOptionPane.showMessageDialog(null, "El jugador con el nickname " + nickname + " no existe",
					"Jugador no encontrado", JOptionPane.ERROR_MESSAGE);
		}
		panelMenu.repaint();
	}

	/**
	 * 
	 */
	public void mejoresPuntajes(){
		panelMenu.setDialogoMejoresPuntajes(new DialogoMejoresPuntajes(this,mundoBuilder.mejoresPuntajes()));
		panelMenu.getDialogoMejoresPuntajes().mostrar();	
	}


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InterfazSpaceInvaders ventana = InterfazSpaceInvaders.getInstance();
		ventana.setMundoBuilder1(new SpaceInvaders());
		ventana.constructInterfaceSpaceInvader();
		ventana.setVisible(true);
	}

}
