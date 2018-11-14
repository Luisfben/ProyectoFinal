package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;

import mundo.Partida;
import mundo.Puntaje;

public class DialogoCrearPartida extends JDialog implements ActionListener, IDialogo {

	// -----------------------------------------------------------------
	// ---------------------------Constantes----------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public final static String ACEPTAR = "Aceptar";

	/**
	 * 
	 */
	public final static String CANCELAR = "Cancelar";

	// -----------------------------------------------------------------
	// --------------------------Asociaciones---------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	InterfazSpaceInvaders interfaz;

	/**
	 * 
	 */
	JPanel auxiliar;

	// -----------------------------------------------------------------
	// ----------------------------Atributos----------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	JLabel nombre;

	/**
	 * 
	 */
	JLabel nombre1;

	/**
	 * 
	 */
	JTextField txtNombre;

	/**
	 * 
	 */
	JButton butBotonAceptar;

	/**
	 * 
	 */
	JButton butBotonCancelar;

	TextAutoCompleter textAutoCompletar;
	// -----------------------------------------------------------------
	// ---------------------------Constructor---------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 * @param interfaz
	 */
	public DialogoCrearPartida(InterfazSpaceInvaders interfaz) {

		super(interfaz, false);

		this.interfaz = interfaz;
		setLayout(null);

		auxiliar = new JPanel();
		auxiliar.setLayout(null);

		nombre = new JLabel("Ingrese el nombre    de");
		nombre.setForeground(Color.YELLOW);
		nombre.setFont(new Font("ArcadeClassic", Font.PLAIN, 33));
		nombre.setBounds(10, 30, 350, 20);

		nombre1 = new JLabel("la    partida");
		nombre1.setForeground(Color.YELLOW);
		nombre1.setFont(new Font("ArcadeClassic", Font.PLAIN, 33));
		nombre1.setBounds(10, 55, 240, 20);

		txtNombre = new JTextField();
		textAutoCompletar = new TextAutoCompleter(txtNombre);
		CargarAutoCompletar();
		txtNombre.setBackground(Color.orange);
		txtNombre.setBounds(10, 150, 210, 25);
		txtNombre.setForeground(Color.BLUE);
		txtNombre.setFont(new Font("ArcadeClassic", Font.PLAIN, 25));

		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon("./data/imagenes/fondoAP.jpg");
		imagen.setIcon(icono);
		imagen.setBounds(0, 0, icono.getIconWidth(), icono.getIconHeight());

		butBotonAceptar = new JButton(ACEPTAR);
		butBotonAceptar.setActionCommand(ACEPTAR);
		butBotonAceptar.addActionListener(this);
		butBotonAceptar.setBounds(10, 200, 130, 25);
		butBotonAceptar.setBackground(Color.BLACK);
		butBotonAceptar.setFont(new Font("ArcadeClassic", Font.PLAIN, 20));
		butBotonAceptar.setForeground(Color.YELLOW);

		butBotonCancelar = new JButton(CANCELAR);
		butBotonCancelar.setActionCommand(CANCELAR);
		butBotonCancelar.addActionListener(this);
		butBotonCancelar.setBounds(200, 200, 130, 25);
		butBotonCancelar.setBackground(Color.BLACK);
		butBotonCancelar.setFont(new Font("ArcadeClassic", Font.PLAIN, 20));
		butBotonCancelar.setForeground(Color.green);

		auxiliar.setSize(icono.getIconWidth(), icono.getIconHeight());
		auxiliar.add(nombre);
		auxiliar.add(nombre1);
		auxiliar.add(txtNombre);
		auxiliar.add(butBotonAceptar);
		auxiliar.add(butBotonCancelar);
		auxiliar.add(imagen);

		setTitle("Crear Partida");
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	// -----------------------------------------------------------------
	// ----------------------Manejador de eventos-----------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		if (comando.equals(CANCELAR))
			this.dispose();
		else if (comando.equals(ACEPTAR)) {
			if (txtNombre.getText().equals(null) || txtNombre.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre v�lido", "Error al crear el jugador",
						JOptionPane.ERROR_MESSAGE);
			else {
				interfaz.reqCrearPartida(txtNombre.getText());
				this.dispose();
			}
		}

	}

	// -----------------------------------------------------------------
	// -----------------------------M�todos-----------------------------
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	@Override
	public void mostrar() {
		setSize(400, 225);
		add(auxiliar);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * 
	 * @return
	 */
	public String darNombre() {
		return txtNombre.getText();
	}
	
	private void CargarAutoCompletar() {
		File archivo = new File("./data/puntaje");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			Puntaje primer = ((Puntaje) ois.readObject());
			int contador = 1;
			while(primer != null && contador <= 10){
				textAutoCompletar.addItem(primer.getNombrePartida());
				contador++;
				primer = primer.getSiguiente();
			}			
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
