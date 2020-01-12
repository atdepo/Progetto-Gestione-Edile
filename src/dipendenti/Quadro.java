package dipendenti;
/**
 * Questa classe cattura il concetto di un <b>Quadro</b>.
 * Un Quadro può essere responsabile di un cantiere quando il suo valore è al di sotto di 500.000 euro.
 * In questo caso il quadro otterrà una maggiorazione al suo stipendio fisso del 5% del valore del cantiere ogni mese che lo dirigerà.
 * Mentre se un quadro è caposquadra di un gruppo di operai otterrà  una maggiorazione al suo stipendio base del 2% del valore del cantiere
 * per ogni mese che sarà caposquadra.
 * Il codiceDipendente del Quadro inizia con <b>03 </b>
 */
public class Quadro extends Dipendente implements Responsabile{

	public Quadro(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		
	}

	public String generateCodice() {
		
		return null;
	}

}
