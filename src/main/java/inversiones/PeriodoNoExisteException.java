package inversiones;

public class PeriodoNoExisteException extends Exception {
	private static final long serialVersionUID = -2681157595314223357L;

    public PeriodoNoExisteException() {
    	super("El periodo no existe");
	}
}