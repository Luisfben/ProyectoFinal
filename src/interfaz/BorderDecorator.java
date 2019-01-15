package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class BorderDecorator extends JComponent {
	protected JComponent component;

	public BorderDecorator(JComponent c) {
		this.component = c;
		this.setLayout(new BorderLayout());
		this.add(component);
	}

	public void paint(Graphics g) {
		super.paint(g);
		int height = this.getHeight();
		int width = this.getWidth();
		g.setColor(Color.red);
		g.drawRect(0, 0, width - 1, height - 1);
	}

	public void setForeground(Color c) {
		this.component.setForeground(c);
	}

	public void setFont(Font f) {
		this.component.setFont(f);
	}
}