package eccezioni;

public class CapacitaSuperataException extends RuntimeException{

	public CapacitaSuperataException() {
		super("Capacità massima ecceduta");
	}
}
