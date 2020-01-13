package dipendenti;
/**
 *Questa classe cattura il concetto di un <b>Impiegato</b>.
 *Un impiegato viene pagato mensilmente in base ai giorni lavorati ogni settimana.
 *Se l'impiegato effettua dei giorni di lavoro straordinari, 
 *questi gli saranno pagati con una retribuzione maggorata.
 *Il <b>codiceDipendente</b> di un Impiegato inizia con <b>01</b>
 */
public class Impiegato extends Dipendente{
	private int giorni_lavorati;
	/**
	 * Costruttore di un impiegato con un numero di giorni lavorativi default
	 * 
	 * @param nome il nome dell'impiegato
	 * @param cognome il cognome dell'impiegato
	 * @param eta l'età dell'impiegato
	 * @param matricola la matricola dell'impiegato che comporrà parte del suo codiceDipendente
	 */
	public Impiegato(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		giorni_lavorati=5;
		
	}
	/**
	 * Costruttore di un impiegato con un numero di giorni lavorativi indicato.
	 * Se il numero di giorni lavorativi è maggiore di 7 viene lanciata una IllegalArgumentException()
	 * @param nome il nome dell'impiegato
	 * @param cognome il cognome dell'impiegato
	 * @param eta l'età dell'impiegato
	 * @param matricola la matricola dell'impiegato che comporrà parte del suo codiceDipendente
	 * @param giorni i giorni lavorativi richiesti
	 */
	public Impiegato(String nome, String cognome, int eta,String matricola,int giorni) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
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
		return imp.giorni_lavorati==giorni_lavorati;
	}
	
	public Impiegato clone() {
		return (Impiegato)super.clone();
	}

	
	public String generateCodice(String matricola) {
		return "01"+matricola;
	}
	
	
}
