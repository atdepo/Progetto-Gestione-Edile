package dipendenti;
/**
 *Questa classe cattura il concetto di un <b>Impiegato</b>.
 *Un impiegato viene pagato in base ai giorni lavorati. Se l'impiegato effettua dei giorni di lavoro 
 *straordinari, questi gli saranno pagati con una retribuzione maggorata.
 *Il <b>codiceDipendente</b> di un Impiegato inizia con <b>01</b>
 */
public class Impiegato extends Dipendente{
	private int giorni_lavorati;
	
	public Impiegato(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		giorni_lavorati=5;
		
	}
	
	public Impiegato(String nome, String cognome, int eta,String matricola,int giorni) {
		super(nome, cognome, eta);
		if(giorni>7)
			throw new IllegalArgumentException();
		giorni_lavorati=giorni;
		
	}
	
	public int getGiorniLavorati() {
		return giorni_lavorati;
	}

	public String toString() {
		return super.toString()+"[giorni_lavorati= "+giorni_lavorati+"]";
	}
	
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Impiegato imp=(Impiegato)o;
		return imp.getGiorniLavorati()==giorni_lavorati;
	}
	
	public Impiegato clone() {
		return (Impiegato)super.clone();
	}

	
	public String generateCodice() {
		return null;
	}
	
	
}
