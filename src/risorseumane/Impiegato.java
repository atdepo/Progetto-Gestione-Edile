package risorseumane;

public class Impiegato extends Dipendente{
	private int stipendio;
	public Impiegato(String nome, String cognome, int eta, long codiceDip) {
		super(nome, cognome, eta, codiceDip);
		//TODO this.stipendio=STIPENDIO;
		
	}

	public int getStipendio() {
		return stipendio;
	}
	
	public void changeStipendio(int stipendio) {
		this.stipendio=stipendio;
	}
	
	public String toString() {
		return super.toString()+"[stipendio= "+stipendio+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Impiegato imp=(Impiegato)o;
		return imp.getStipendio()==stipendio;
	}
	
	public Impiegato clone() {
		return (Impiegato)super.clone();
	}
	
}
