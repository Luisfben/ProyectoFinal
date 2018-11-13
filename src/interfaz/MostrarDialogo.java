package interfaz;

public class MostrarDialogo implements Command {
	private IDialogo idialogo;

	public MostrarDialogo(IDialogo idialogo) {
		this.idialogo = idialogo;
	}

	@Override
	public void execute() {
		idialogo.mostrar();
	}

}
