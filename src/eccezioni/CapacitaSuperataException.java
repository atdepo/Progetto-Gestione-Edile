package eccezioni;

public class CapacitaSuperataException extends RuntimeException{

	public CapacitaSuperataException() {
		super("Capacit� massima ecceduta");
	}
}
