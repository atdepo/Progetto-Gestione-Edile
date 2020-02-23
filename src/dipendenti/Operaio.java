package dipendenti;

import java.util.ArrayList;

import amministrativo.Contratto;

/**
 * Questa classe cattura il concetto di un <b>Operaio</b>. Un operaio possiede
 * una particolare specializzazione che individua il tipo di lavoro effettuato.
 * Un operaio viene pagato in base al numero di ore in cui ha lavorato. Se un
 * operaio effettua delle ore di lavoro straordinarie, queste gli verranno
 * pagate con una retribuzione maggiorata. Il codiceDipendente di un operaio
 * inizia con <b>02</b>
 */
public class Operaio extends Dipendente {
	private int oreLavorate;
	private int oreStraordinario;
	private static Lavori lavori = new Lavori();
	private String specializzazione;

	/**
	 * Istanzia un Operaio al quale viene stabilito un numero di ore di default
	 * 
	 * @param nome      il nome dell'operaio
	 * @param cognome   il cognome dell'operaio
	 * @param eta       l'età dell'operaio
	 * @param matricola la matricola dell'operaio che comporrà parte del suo
	 *                  codiceDipendente
	 */
	public Operaio(String nome, String cognome, int eta, String matricola, String specializzazione) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		if(lavori.getLavori().contains(specializzazione))
			this.specializzazione=specializzazione;
		else
			throw new IllegalArgumentException();
		oreLavorate = 40;
		oreStraordinario = 0;
		setContratto(STIPENDIO_OPERAIO_ORARIO, BONUS_STRAORDINARIO_OPERAIO);
	}

	/**
	 * Istanzia un Operaio al quale viene stabilito il numero di ore.
	 * 
	 * @param nome      il nome dell'operaio
	 * @param cognome   il cognome dell'operaio
	 * @param eta       l'età dell'operaio
	 * @param matricola la matricola dell'operaio che comporrà parte del suo
	 *                  codiceDipendente
	 * @param ore       le ore settimanali da lavorare
	 * @throws IllegalArgumentException Se il numero di ore è maggiore di 70 oppure
	 *                                  negativo
	 */
	public Operaio(String nome, String cognome, int eta, String matricola, String specializzazione, int ore) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		if(lavori.getLavori().contains(specializzazione))
			this.specializzazione=specializzazione;
		else
			throw new IllegalArgumentException();
		
		if (oreLavorate > 70 || oreLavorate < 0)
			throw new IllegalArgumentException("Impossibile superare le 70 ore");

		oreLavorate = ore;
		oreStraordinario = 0;
		setContratto(STIPENDIO_OPERAIO_ORARIO, BONUS_STRAORDINARIO_OPERAIO);

	}
	/**
	 * Aggiunge una specializzazione possibile per un operaio
	 * @param specializzazione la specializzazione da aggiungere
	 * @author Antonio Della Porta
	 */
	public static void aggiungiSpecializzazione(String specializzazione) {
		lavori.getLavori().add(specializzazione);
	}
	/**
	 * Utilizzato per avere la lista di specializzazioni possibili per un operaio
	 * @return la lista di specializzazioni
	 * @author Antonio Della Porta
	 */
	public static ArrayList<String> getLavori() {
		return lavori.getLavori();
	}

	public int getOreLavorate() {
		return oreLavorate;
	}

	public int getOreStraordinario() {
		return oreStraordinario;
	}

	/**
	 * Aggiunge delle ore straordinarie all'operaio
	 * 
	 * @param ore
	 * @throws IllegalArgumentException se il numero totale delle ore da lavorare e
	 *                                  delle ore di straordinario supera le 70
	 * @author Antonio Della Porta
	 */
	public void setLavoroStraordinario(int ore) {
		if (oreLavorate + ore < 70 && oreLavorate + ore > 0) {
			oreStraordinario += ore;
		} else
			throw new IllegalArgumentException("Impossibile superare le 70 ore");
	}

	public String generateCodice(String matricola) {
		return "02" + matricola;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	/**
	 * 
	 * Imposta il numero di ore di straordinario a 0
	 * 
	 * @author Antonio Della Porta
	 */
	public void resetOre() {
		oreStraordinario = 0;
	}

	public String toString() {
		return super.toString() + "[ore_lavorate= " + oreLavorate + ",specializzazione=" + specializzazione
				+ ",ore_straordinario=" + oreStraordinario + "]";
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		Operaio op = (Operaio) o;
		return op.oreLavorate == oreLavorate && op.oreStraordinario == oreStraordinario
				&& op.getSpecializzazione().equals(getSpecializzazione());
	}

	public Operaio clone() {
		return (Operaio) super.clone();
	}

	public double getPaga() {
		this.setPagato();
		this.resetOre();
		Contratto c = this.getContratto();
		return 4 * this.getOreLavorate() * c.getStipendio() + this.getOreStraordinario() * c.getBonus();
	}
	/**
	 * Astrazione che cattura il concetto di una lista di lavori per un operaio
	 * @author della
	 *
	 */
	private static class Lavori {
		private ArrayList<String> lavori;

		public Lavori() {
			lavori = new ArrayList<String>();
			lavori.add("MURATORE");
			lavori.add("PIASTRELLISTA");
			lavori.add("IDRAULICO");
			lavori.add("ELETTRICISTA");
		}

		private ArrayList<String> getLavori() {
			return lavori;
		}
	}
}
