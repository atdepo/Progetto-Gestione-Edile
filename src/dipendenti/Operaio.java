package dipendenti;
/**
 * Questa classe catura il concetto di un <b>Operaio</b>.
 * Un operaio viene pagato in base al numero di ore in cui ha lavorato. 
 * Se un operaio effettua delle ore di lavoro straordinarie, queste gli verranno pagate con una retribuzione maggiorata.
 * Il codiceDipendente di un operaio inizia con <b>02</b>
 */
public class Operaio extends Dipendente {
	private int ore_lavorate; 
	/**
	 * Costruttore standard di Operaio in cui viene stabilito un numero di ore di default
	 * @param nome il nome dell'operaio
	 * @param cognome il cognome dell'operaio
	 * @param eta l'età dell'operaio
	 * @param matricola la matricola dell'operaio che comporrà parte del suo codiceDipendente
	 */
	public Operaio(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		ore_lavorate=40;
	}
	/**
	 * Costruttore standard di Operaio in cui viene stabilito il numero di ore. Se il numero di ore è maggiore di 70
	 * viene lanciata una IllegalArgumentException()
	 * @param nome il nome dell'operaio
	 * @param cognome il cognome dell'operaio
	 * @param eta l'età dell'operaio
	 * @param matricola la matricola dell'operaio che comporrà parte del suo codiceDipendente
	 * @param ore le ore settimanali da lavorare
	 */
	public Operaio(String nome, String cognome, int eta,String matricola,int ore) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		if(ore_lavorate>70)
			throw new IllegalArgumentException();
		ore_lavorate=ore;
	}

	public String generateCodice(String matricola) {
		return "02"+matricola;
	}
	
	public String toString() {
		return super.toString()+"[ore_lavorate= "+ore_lavorate+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Operaio op=(Operaio)o;
		return op.ore_lavorate==ore_lavorate; 
		}
	
	public Operaio clone() {
		return (Operaio)super.clone();
	}
	
	

}
