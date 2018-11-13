package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "niveles")
public class Niveles {
	@Id
	@Column(name = "nivel")
	private int nivel;
	@Column(name = "filasenemigo")
	private int filasEnemigo;
	@Column(name = "columnaenemigo")
	private int columnaEnemigos;
	@Column(name = "vidaenemigos")
	private int vidaEnemigos;
	@Column(name = "cantidadenemigos")
	private int cantidadEnemigos;
	@Column(name = "velocidadenemigos")
	private int velocidadEnemigos;
	@Column(name = "posxprimerenemigo")
	private int XPrimerEnemigo;
	@Column(name = "posyprimerenemigo")
	private int posYPrimerEnemigo;
	@Column(name = "anchoenemigos")
	private int anchoEnemigos;
	@Column(name = "altoenemigos")
	private int altoEnemigos;

	public Niveles() {

	}
/*
	public Nivel(int filasEnemigo, int columnaEnemigos, int vidaEnemigos, int cantidadEnemigos, int velocidadEnemigos,
			int xPrimerEnemigo, int posYPrimerEnemigo, int anchoEnemigos, int altoEnemigos) {
		super();
		this.filasEnemigo = filasEnemigo;
		this.columnaEnemigos = columnaEnemigos;
		this.vidaEnemigos = vidaEnemigos;
		this.cantidadEnemigos = cantidadEnemigos;
		this.velocidadEnemigos = velocidadEnemigos;
		XPrimerEnemigo = xPrimerEnemigo;
		this.posYPrimerEnemigo = posYPrimerEnemigo;
		this.anchoEnemigos = anchoEnemigos;
		this.altoEnemigos = altoEnemigos;
	}
*/
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getFilasEnemigo() {
		return filasEnemigo;
	}

	public void setFilasEnemigo(int filasEnemigo) {
		this.filasEnemigo = filasEnemigo;
	}

	public int getColumnaEnemigos() {
		return columnaEnemigos;
	}

	public void setColumnaEnemigos(int columnaEnemigos) {
		this.columnaEnemigos = columnaEnemigos;
	}

	public int getVidaEnemigos() {
		return vidaEnemigos;
	}

	public void setVidaEnemigos(int vidaEnemigos) {
		this.vidaEnemigos = vidaEnemigos;
	}

	public int getCantidadEnemigos() {
		return cantidadEnemigos;
	}

	public void setCantidadEnemigos(int cantidadEnemigos) {
		this.cantidadEnemigos = cantidadEnemigos;
	}

	public int getVelocidadEnemigos() {
		return velocidadEnemigos;
	}

	public void setVelocidadEnemigos(int velocidadEnemigos) {
		this.velocidadEnemigos = velocidadEnemigos;
	}

	public int getXPrimerEnemigo() {
		return XPrimerEnemigo;
	}

	public void setXPrimerEnemigo(int xPrimerEnemigo) {
		XPrimerEnemigo = xPrimerEnemigo;
	}

	public int getPosYPrimerEnemigo() {
		return posYPrimerEnemigo;
	}

	public void setPosYPrimerEnemigo(int posYPrimerEnemigo) {
		this.posYPrimerEnemigo = posYPrimerEnemigo;
	}

	public int getAnchoEnemigos() {
		return anchoEnemigos;
	}

	public void setAnchoEnemigos(int anchoEnemigos) {
		this.anchoEnemigos = anchoEnemigos;
	}

	public int getAltoEnemigos() {
		return altoEnemigos;
	}

	public void setAltoEnemigos(int altoEnemigos) {
		this.altoEnemigos = altoEnemigos;
	}

	@Override
	public String toString() {
		return "Nivel [nivel=" + nivel + ", filasEnemigo=" + filasEnemigo + ", columnaEnemigos=" + columnaEnemigos
				+ ", vidaEnemigos=" + vidaEnemigos + ", cantidadEnemigos=" + cantidadEnemigos + ", velocidadEnemigos="
				+ velocidadEnemigos + ", XPrimerEnemigo=" + XPrimerEnemigo + ", posYPrimerEnemigo=" + posYPrimerEnemigo
				+ ", anchoEnemigos=" + anchoEnemigos + ", altoEnemigos=" + altoEnemigos + "]";
	}

}
