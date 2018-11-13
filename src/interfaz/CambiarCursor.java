package interfaz;

import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class CambiarCursor implements Visitor {

	@Override
	public void visit(InterfazSpaceInvaders interfaz) {
		ImageIcon ImagenPrograma = new ImageIcon("./data/imagenes/Naves/arrow.png");
		Toolkit TK = Toolkit.getDefaultToolkit();
		interfaz.setCursor(TK.createCustomCursor(ImagenPrograma.getImage(), new Point(2, 2), "Cursor 01"));
	}
}
