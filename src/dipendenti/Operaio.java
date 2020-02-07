package dipendenti;
/**
 * Questa classe catura il concetto di un <b>Operaio</b>.
 * Un operaio possiede una particolare specializzazione che individua il tipo di lavoro effettuato.
 * Un operaio viene pagato in base al numero di ore in cui ha lavorato. 
 * Se un operaio effettua delle ore di lavoro straordinarie, queste gli verranno pagate con una retribuzione maggiorata.
 * Il codiceDipendente di un operaio inizia con <b>02</b>
 */
public class Operaio extends Dipendente {
	private int ore_lavorate; 
	private int ore_straordinario;
	public enum lavoro {MURATORE,ELETTRICISTA,IDRAULICO,PIASTRELLISTA};
	private lavoro specializzazione;
	private boolean impegnato; 
	/**
	 * Costruttore standard di Operaio in cui viene stabilito un numero di ore di default
	 * @param nome il nome dell'operaio
	 * @param cognome il cognome dell'operaio
	 * @param eta l'et� dell'operaio
	 * @param matricola la matricola dell'operaio che comporr� parte del suo codiceDipendente
	 */
	public Operaio(String nome, String cognome, int eta,String matricola,lavoro specializzazione) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		this.specializzazione=specializzazione;
		ore_lavorate=40;
		ore_straordinario=0;
	}
	/**
	 * Costruttore standard di Operaio in cui viene stabilito il numero di ore. Se il numero di ore � maggiore di 70 oppure negativo
	 * viene lanciata una IllegalArgumentException()
	 * @param nome il nome dell'operaio
	 * @param cognome il cognome dell'operaio
	 * @param eta l'et� dell'operaio
	 * @param matricola la matricola dell'operaio che comporr� parte del suo codiceDipendente
	 * @param ore le ore settimanali da lavorare
	 */
	public Operaio(String nome, String cognome, int eta,String matricola,lavoro specializzazione,int ore) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		this.specializzazione=specializzazione;
		if(ore_lavorate>70||ore_lavorate<0)
			throw new IllegalArgumentException();
		ore_lavorate=ore;
		ore_straordinario=0;
	}

	public int getOre_lavorate() {
		return ore_lavorate;
	}
	
	public int getOre_straordinario() {
		return ore_straordinario;
	}
	public void incre_Ore_straordinario(int ore_straordinario) {
		this.ore_straordinario += ore_straordinario;
	}
	public String generateCodice(String matricola) {
		return "02"+matricola;
	}
	
	public String getSpecializzazione() {
		return specializzazione.name();
	}
	
	public void resetOre() {
		ore_straordinario=0;
	}
	
	public String toString() {
		return super.toString()+"[ore_lavorate= "+ore_lavorate+",specializzazione="+specializzazione.name()+",ore_straordinario="+ore_straordinario+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Operaio op=(Operaio)o;
		return op.ore_lavorate==ore_lavorate&&op.ore_straordinario==ore_straordinario&&op.getSpecializzazione().equals(getSpecializzazione()); 
		}
	
	public Operaio clone() {
		return (Operaio)super.clone();
	}
}
