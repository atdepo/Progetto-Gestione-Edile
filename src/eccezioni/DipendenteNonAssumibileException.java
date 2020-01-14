package eccezioni;

public class DipendenteNonAssumibileException extends IllegalArgumentException{

	public DipendenteNonAssumibileException() {
		super("Non si può legalmente assumere questo operaio");
	}
}
