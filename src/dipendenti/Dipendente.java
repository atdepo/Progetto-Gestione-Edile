package dipendenti;

import amministrativo.Contratto;
import eccezioni.DipendenteNonAssumibileException;

/**
 * Questa classe cattura il concetto di un Dipendente.
 * Ogni dipendente possiede uno schema di retribuzione differente quindi non viene gestita in questa astrazione 
 * la sua retribuzione
 */
public abstract class Dipendente implements Cloneable {

	private String nome,cognome,codiceDipendente;
	private int eta;
	private boolean pagato,impegnato;
	private Contratto contratto;
	
	/**
	 * Costruttore di un Dipendente
	 * 
	 * @param nome Nome del dipendente
	 * @param cognome Cognome del dipendente 
	 * @param eta eta del dipendente
	 * @param codiceDip codice identificativo del dipendente
	 */
	public Dipendente(String nome,String cognome,int eta) {
		if(eta<18||eta>68)
			throw new DipendenteNonAssumibileException();
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
	
	public void setContratto(double stipendio,double bonus) {
		contratto=new Contratto(stipendio, bonus);
	}
	
	public Contratto getContratto() {
		return contratto;
	}
	
	public boolean isPagato() {
		return pagato;
	}
	
	public boolean isImpegnato() {
		return impegnato;
	}
	
	public void impegnaDipendente() {
		impegnato=true;
	}
	
	
	public void liberaDipendente() {
		impegnato=false;
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
	
	
	public static boolean isImpiegato(Dipendente d) {
		if(d.codiceDipendente.substring(0, 2).equals("01"))
			return true;
		else
			return false;
	}
	
	public static boolean isOperaio(Dipendente d) {
		if(d.codiceDipendente.substring(0, 2).equals("02"))
			return true;
		else
			return false;
	}
	
	public static boolean isQuadro(Dipendente d) {
		if(d.codiceDipendente.substring(0, 2).equals("03"))
			return true;
		else
			return false;
	}
	
	public static boolean isDirigente(Dipendente d) {
		if(d.codiceDipendente.substring(0, 2).equals("04"))
			return true;
		else
			return false;
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
