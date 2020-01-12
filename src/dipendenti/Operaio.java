package dipendenti;
/**
 * Questa classe catura il concetto di un <b>Operaio</b>.
 * Un operaio viene pagato in base al numero di ore in cui ha lavorato. 
 * Se un operaio effettua delle ore di lavoro straordinarie, queste gli verranno pagate con una retribuzione maggiorata.
 * Il codiceDipendente di un operaio inizia con <b>02</b>
 */
public class Operaio extends Dipendente {
	private int ore_lavorate; 
	public Operaio(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		ore_lavorate=40;
	}

	public Operaio(String nome, String cognome, int eta,String matricola,int ore) {
		super(nome, cognome, eta);
		if(ore_lavorate>70)
			throw new IllegalArgumentException();
		ore_lavorate=ore;
	}

	public String generateCodice() {
		return null;
	}

	
}
