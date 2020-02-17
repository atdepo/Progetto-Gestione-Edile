package dipendenti;
/**
 *Questa classe cattura il concetto di un <b>Impiegato</b>.
 *Un impiegato viene pagato mensilmente in base ai giorni lavorati ogni settimana.
 *Se l'impiegato effettua dei giorni di lavoro straordinari, 
 *questi gli saranno pagati con una retribuzione maggorata.
 *Il <b>codiceDipendente</b> di un Impiegato inizia con <b>01</b>
 */
public class Impiegato extends Dipendente{
	private int giorniLavorati;
	private int giorniLavoroStraordinario;
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
		giorniLavorati=5;
		giorniLavoroStraordinario=0;
		
	}
	/**
	 * Costruttore di un impiegato con un numero di giorni lavorativi indicato.
	 * Se i giorni lavorativi sono più di 7 o minori/uguali di 0,viene lanciata una <b>IllegalArgumentException().</b>
	 * @param nome il nome dell'impiegato
	 * @param cognome il cognome dell'impiegato
	 * @param eta l'età dell'impiegato
	 * @param matricola la matricola dell'impiegato che comporrà parte del suo codiceDipendente
	 * @param giorni i giorni lavorativi richiesti
	 */
	public Impiegato(String nome, String cognome, int eta,String matricola,int giorni) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		if(giorni>7||giorni<=0)
			throw new IllegalArgumentException("Impossibile eccedere i 7 giorni della settimana");
		giorniLavorati=giorni;
		
	}
	
	public int getGiorniLavorati() {
		return giorniLavorati;
	}

	public int getGiorniStraordinario() {
		return giorniLavoroStraordinario;
	}
	/**
	 * Aggiunge dei giorni di lavoro straordinario all'impiegato. Se i giorni che lavora di base e i giorni di straordinario 
	 * sono più di 7 o minori/uguali di 0,viene lanciata una <b>IllegalArgumentException().</b>
	 * 
	 * @param giorni
	 */
	public void setLavoroStraordinario(int giorni) {
		if(giorniLavorati+giorni>7||giorniLavorati+giorni<=0)
			throw new IllegalArgumentException("Impossibile eccedere i 7 giorni della settimana");
		giorniLavoroStraordinario+=giorni;
	}
	
	public void resetOre() {
		giorniLavoroStraordinario=0;
	}
	
	public String toString() {
		return super.toString()+"[giorni_lavorati= "+giorniLavorati+",giorni_lavoro_straordinario="+giorniLavoroStraordinario+"]";
	}
	
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Impiegato imp=(Impiegato)o;
		return imp.giorniLavorati==giorniLavorati && imp.giorniLavoroStraordinario==giorniLavoroStraordinario;
	}
	
	public Impiegato clone() {
		return (Impiegato)super.clone();
	}

	
	public String generateCodice(String matricola) {
		return "01"+matricola;
	}
	
	
}
