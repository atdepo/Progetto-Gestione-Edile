package eccezioni;

public class OperaioOccupatoException extends Exception{

	public OperaioOccupatoException() {
		super("Operaio già occupato");
	}
}
