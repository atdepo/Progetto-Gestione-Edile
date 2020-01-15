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
	private int giorni_lavoro_straordinario;
	/**
	 * Costruttore di un impiegato con un numero di giorni lavorativi default
	 * 
	 * @param nome il nome dell'impiegato
	 * @param cognome il cognome dell'impiegato
	 * @param eta l'et� dell'impiegato
	 * @param matricola la matricola dell'impiegato che comporr� parte del suo codiceDipendente
	 */
	public Impiegato(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		giorni_lavorati=5;
		giorni_lavoro_straordinario=0;
		
	}
	/**
	 * Costruttore di un impiegato con un numero di giorni lavorativi indicato.
	 * Se i giorni lavorativi sono pi� di 7 o minori/uguali di 0,viene lanciata una <b>IllegalArgumentException().</b>
	 * @param nome il nome dell'impiegato
	 * @param cognome il cognome dell'impiegato
	 * @param eta l'et� dell'impiegato
	 * @param matricola la matricola dell'impiegato che comporr� parte del suo codiceDipendente
	 * @param giorni i giorni lavorativi richiesti
	 */
	public Impiegato(String nome, String cognome, int eta,String matricola,int giorni) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		if(giorni>7||giorni<=0)
			throw new IllegalArgumentException("Impossibile eccedere i 7 giorni della settimana");
		giorni_lavorati=giorni;
		
	}
	
	public int getGiorniLavorati() {
		return giorni_lavorati;
	}

	public int getGiorniStraordinario() {
		return giorni_lavoro_straordinario;
	}
	/**
	 * Aggiunge dei giorni di lavoro straordinario all'impiegato. Se i giorni che lavora di base e i giorni di straordinario 
	 * sono pi� di 7 o minori/uguali di 0,viene lanciata una <b>IllegalArgumentException().</b>
	 * 
	 * @param giorni
	 */
	public void setLavoroStraordinario(int giorni) {
		if(giorni_lavorati+giorni>7||giorni_lavorati+giorni<=0)
			throw new IllegalArgumentException("Impossibile eccedere i 7 giorni della settimana");
		giorni_lavoro_straordinario=giorni;
	}
	
	public void resetOre() {
		giorni_lavorati=0;
		giorni_lavoro_straordinario=0;
	}
	
	public String toString() {
		return super.toString()+"[giorni_lavorati= "+giorni_lavorati+"giorni_lavoro_straordinario"+giorni_lavoro_straordinario+"]";
	}
	
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Impiegato imp=(Impiegato)o;
		return imp.giorni_lavorati==giorni_lavorati && imp.giorni_lavoro_straordinario==giorni_lavoro_straordinario;
	}
	
	public Impiegato clone() {
		return (Impiegato)super.clone();
	}

	
	public String generateCodice(String matricola) {
		return "01"+matricola;
	}
	
	
}
