package dipendenti;

import java.io.Serializable;

import amministrativo.Contratto;
import eccezioni.DipendenteNonAssumibileException;

/**
 * Questa classe cattura il concetto di un Dipendente. Ogni dipendente possiede
 * uno schema di retribuzione differente e viene pagato in base al suo impiego e
 * secondo le somme concordate nel contratto
 */
public abstract class Dipendente implements Cloneable, Serializable {

	protected static final double STIPENDIO_IMPIEGATO = 50.0D;
	protected static final double STIPENDIO_OPERAIO_ORARIO = 7.0D;
	protected static final double STIPENDIO_QUADRO = 1780.0D;
	protected static final double STIPENDIO_DIRIGENTE = 2750.0D;
	protected static final double BONUS_QUADRO = 6;
	protected static final double BONUS_DIRIGENTE_PER_OPERAIO = 15.0D;
	protected static final double BONUS_STRAORDINARIO_IMPIEGATO = 80.0D;
	protected static final double BONUS_STRAORDINARIO_OPERAIO = 12.0D;

	private String nome, cognome, codiceDipendente;
	private int eta;
	private boolean pagato, impegnato;
	private Contratto contratto;

	/**
	 * Costruttore di un Dipendente
	 * 
	 * @param nome    Nome del dipendente
	 * @param cognome Cognome del dipendente
	 * @param eta     eta del dipendente
	 */
	public Dipendente(String nome, String cognome, int eta) {
		if (eta < 18 || eta > 68)
			throw new DipendenteNonAssumibileException();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		pagato = false;
		impegnato = false;
	}

	/**
	 * Metodo astratto da ridefinire in ogni sottoclasse, responssabile di calcolare
	 * lo stipendio dovuto
	 * 
	 * @return lo stipendio da percepire
	 * @author Antonio Della Porta
	 */
	public abstract double getPaga();

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

	public void setContratto(double stipendio, double bonus) {
		contratto = new Contratto(stipendio, bonus);
	}

	public Contratto getContratto() {
		return contratto;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato() {
		pagato = true;
	}

	public void resetPagamento() {
		pagato = false;
	}

	public boolean isImpegnato() {
		return impegnato;
	}

	public void impegnaDipendente() {
		impegnato = true;
	}

	public void liberaDipendente() {
		impegnato = false;
	}

	/**
	 * In base alla mansione viene creato questo codice che univocamente identifica
	 * un dipendente
	 * 
	 * @param matricola la matricola del dipendente
	 * @return codiceDipendente univoco per mansione
	 */
	protected abstract String generateCodice(String matricola);

	/**
	 * Il codice del dipendente identifica la sua mansione e di conseguenza la sua
	 * retribuzione
	 * 
	 * @param codice
	 */
	protected void setCodiceDipendente(String codice) {
		codiceDipendente = codice;
	}

	/**
	 * metodo statico che controlla le prime due cifre della matricola per vedere se
	 * un dipendente è un Impiegato
	 * 
	 * @param d il dipendente da controllare
	 * @return <b>true</b> se il dipendente è un impiegato,<b>false</b> altrimenti
	 * @author Antonio Della Porta
	 */
	public static boolean isImpiegato(Dipendente d) {
		if (d.codiceDipendente.substring(0, 2).equals("01"))
			return true;
		else
			return false;
	}

	/**
	 * Metodo statico che controlla le prime due cifre della matricola per vedere se
	 * un dipendente è un Operaio
	 * 
	 * @param d il dipendente da controllare
	 * @return <b>true</b> se il dipendente è un operaio,<b>false</b> altrimenti
	 * @author Antonio Della Porta
	 */
	public static boolean isOperaio(Dipendente d) {
		if (d.codiceDipendente.substring(0, 2).equals("02"))
			return true;
		else
			return false;
	}

	/**
	 * Metodo statico che controlla le prime due cifre della matricola per vedere se
	 * un dipendente è un Quadro
	 * 
	 * @param d il dipendente da controllare
	 * @return <b>true</b> se il dipendente è un quadro,<b>false</b> altrimenti
	 * @author Antonio Della Porta
	 */
	public static boolean isQuadro(Dipendente d) {
		if (d.codiceDipendente.substring(0, 2).equals("03"))
			return true;
		else
			return false;
	}

	/**
	 * Metodo statico che controlla le prime due cifre della matricola per vedere se
	 * un dipendente è un Dirigente
	 * 
	 * @param d il dipendente da controllare
	 * @return <b>true</b> se il dipendente è un dirigente,<b>false</b> altrimenti
	 * @author Antonio Della Porta
	 */
	public static boolean isDirigente(Dipendente d) {
		if (d.codiceDipendente.substring(0, 2).equals("04"))
			return true;
		else
			return false;
	}

	public String toString() {
		return getClass().getSimpleName() + "[nome=" + nome + ",cognome=" + cognome + ",eta=" + eta
				+ ",codiceDipendente=" + codiceDipendente + ",pagato=" + pagato + ",impegnato=" + impegnato + "]";
	}

	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass())
			return false;
		Dipendente dip = (Dipendente) o;
		return dip.isImpegnato() == impegnato && dip.isPagato() == pagato && dip.getNome().equals(nome)
				&& dip.getCognome().equals(cognome) && dip.eta == eta && dip.codiceDipendente.equals(codiceDipendente);
	}

	public Dipendente clone() {
		try {
			return (Dipendente) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}

	}

}
