package dipendenti;

import amministrativo.Contratto;

/**
 * Questa classe cattura il concetto di Dirigente. Un <b>Dirigente</b> è
 * incaricato di gestire tutti i cantieri con una valutazione superiore a
 * 500.000. Un dirigente viene retribuito in base ad uno stipendio fisso a cui
 * si aggiungono dei bonus,nel caso ne diriga uno,per ogni operaio nel suo
 * cantiere. Il codiceDipendente di un dirigente è <b>04</b>
 */
public class Dirigente extends Dipendente implements Responsabile {

	private int numeroOperai;

	/**
	 * Istanzio un dirigente
	 * 
	 * @param nome      il nome del dirigente
	 * @param cognome   il cognome del dirigente
	 * @param eta       l'età del dirigente
	 * @param matricola lla matricola del dirigenet che comporrà parte del suo
	 *                  codiceDipendente
	 * @author Antonio Della Porta
	 */
	public Dirigente(String nome, String cognome, int eta, String matricola) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		numeroOperai = 0;
		setContratto(STIPENDIO_DIRIGENTE, BONUS_DIRIGENTE_PER_OPERAIO);
	}

	public int getNumeroOperai() {
		return numeroOperai;
	}
	/**
	 * Aggiunge degli operai a carico del dirigente
	 * @param numero il numero di operai da aggiungere
	 * @author Antonio Della Porta
	 */
	public void aggiungiOperai(int numero) {
		numeroOperai += numero;
	}
	/**
	 * Rimuove degli operai a carico del dirigente
	 * @param numero
	 * @author Antonio Della Porta
	 */
	public void rimuoviOperai(int numero) {
		if (numeroOperai >= numero)
			numeroOperai -= numero;
	}

	public String generateCodice(String matricola) {
		return "04" + matricola;
	}

	public String toString() {
		return super.toString() + "[numero operai= " + numeroOperai + "]";
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		Dirigente d = (Dirigente) o;
		return d.getNumeroOperai() == numeroOperai;
	}

	public Dirigente clone() {
		return (Dirigente) super.clone();

	}

	public double getPaga() {
		this.setPagato();
		Contratto c = this.getContratto();
		return c.getStipendio() + (c.getBonus() * this.getNumeroOperai());
	}
}
