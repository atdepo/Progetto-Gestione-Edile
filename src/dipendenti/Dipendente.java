package dipendenti;

/**
 * Questa classe cattura il concetto astratto di un Dipendente.
 * Ogni dipendente possiede uno schema di retribuzione differente quindi non viene gestita in questa astrazione 
 * la sua retribuzione

 */
public abstract class Dipendente implements Cloneable {
	//questa è una responsabilità che deve avere la classe dipendente?
	protected static final double STIPENDIO_IMPIEGATO=1500.0D;
	protected static final double STIPENDIO_OPERAIO=1450.0D;
	protected static final double STIPENDIO_QUADRO=1780.0D;	
	protected static final double STIPENDIO_DIRIGENTE=2750.0D;
	//DA DEFINIRE
	protected static final double BONUS_IMPIEGATO=4.0D;
	protected static final double BONUS_OPERAIO=4.0D;
	protected static final double STRAORDINARIO_IMPIEGATO=4.0D;
	protected static final double STRAORDINARIO_OPERAIO=4.0D;

	private String nome,cognome,codiceDipendente;
	private int eta;
	private double stipendio; 
	private boolean pagato,impegnato;
	
	/**
	 * Costruttore di un Dipendente
	 * 
	 * @param nome Nome del dipendente
	 * @param cognome Cognome del dipendente 
	 * @param eta eta del dipendente
	 * @param codiceDip codice identificativo del dipendente
	 */
	public Dipendente(String nome,String cognome,int eta) {
		this.nome=nome;
		this.cognome=cognome;
		this.eta=eta;
		pagato=false;
		impegnato=false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getEta() {
		return eta;
	}
	
	public String getCodiceDipendente() {
		return codiceDipendente;
	}
	
	public boolean isPagato() {
		return pagato;
	}
	
	public boolean isImpegnato() {
		return impegnato;
	}
	
	/**
	 * In base alla mansione viene creato questo codice che univocamente identifica un dipendente
	 * @param matricola la matricola del dipendente
	 * @return codiceDipendente univoco per mansione
	 */
	protected abstract String generateCodice(String matricola);
	
	/**
	 * Il codice del dipendente identifica la sua mansione e di conseguenza la sua retribuzione
	 * @param codice
	 */
	protected void setCodiceDipendente(String codice) {
		codiceDipendente= codice;
	}
	
	public String toString() {
		return getClass().getName()+"[nome="+nome+",cognome="+cognome+",eta="+eta+",codiceDipendente="+codiceDipendente+",pagato="+pagato+",impegnato="+impegnato+"]";
	}
	
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=getClass())
			return false;
		Dipendente dip=(Dipendente)o;
		return dip.isImpegnato()==impegnato && dip.isPagato()==pagato && dip.getNome().equals(nome) && 
				dip.getCognome().equals(cognome) && 
				dip.eta==eta && dip.codiceDipendente.equals(codiceDipendente);		
	}
	
	public Dipendente clone() {
		try {
			return (Dipendente)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	
	}

}
