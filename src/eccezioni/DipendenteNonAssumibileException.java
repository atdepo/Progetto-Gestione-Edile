package eccezioni;

public class DipendenteNonAssumibileException extends IllegalArgumentException{

	public DipendenteNonAssumibileException() {
		super("Non si pu� legalmente assumere questo operaio");
	}
}
