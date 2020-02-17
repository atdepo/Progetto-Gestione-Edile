package dipendenti;
/**
 * Questa classe catura il concetto di un <b>Operaio</b>.
 * Un operaio possiede una particolare specializzazione che individua il tipo di lavoro effettuato.
 * Un operaio viene pagato in base al numero di ore in cui ha lavorato. 
 * Se un operaio effettua delle ore di lavoro straordinarie, queste gli verranno pagate con una retribuzione maggiorata.
 * Il codiceDipendente di un operaio inizia con <b>02</b>
 */
public class Operaio extends Dipendente {
	private int oreLavorate; 
	private int oreStraordinario;
	public enum lavoro {MURATORE,ELETTRICISTA,IDRAULICO,PIASTRELLISTA};
	private lavoro specializzazione;
	private boolean impegnato; 
	/**
	 * Istanzia un Operaio al quale viene stabilito un numero di ore di default
	 * @param nome il nome dell'operaio
	 * @param cognome il cognome dell'operaio
	 * @param eta l'et� dell'operaio
	 * @param matricola la matricola dell'operaio che comporr� parte del suo codiceDipendente
	 */
	public Operaio(String nome, String cognome, int eta,String matricola,lavoro specializzazione) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		this.specializzazione=specializzazione;
		oreLavorate=40;
		oreStraordinario=0;
	}
	/**
	 * Istanzia un Operaio al quale viene stabilito il numero di ore.
	 * @param nome il nome dell'operaio
	 * @param cognome il cognome dell'operaio
	 * @param eta l'et� dell'operaio
	 * @param matricola la matricola dell'operaio che comporr� parte del suo codiceDipendente
	 * @param ore le ore settimanali da lavorare
	 * @throws IllegalArgumentException  Se il numero di ore � maggiore di 70 oppure negativo
	 */
	public Operaio(String nome, String cognome, int eta,String matricola,lavoro specializzazione,int ore) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		this.specializzazione=specializzazione;
		if(oreLavorate>70||oreLavorate<0)
			throw new IllegalArgumentException("Impossibile superare le 70 ore");
		oreLavorate=ore;
		oreStraordinario=0;
	}

	public int getOreLavorate() {
		return oreLavorate;
	}
	
	public int getOreStraordinario() {
		return oreStraordinario;
	}
	
	/**
	 * Aggiunge delle ore straordinarie all'operaio
	 * @param ore
	 * @throws IllegalArgumentException se il numero totale delle ore da lavorare e delle ore di straordinario supera le 70
	 * @author Antonio Della Porta
	 */
	public void setLavoroStraordinario(int ore) {
		if(oreLavorate+ore<70&&oreLavorate+ore>0) {
			oreStraordinario+=ore;
		}
		else
			throw new IllegalArgumentException("Impossibile superare le 70 ore");
	}
	public String generateCodice(String matricola) {
		return "02"+matricola;
	}
	
	public String getSpecializzazione() {
		return specializzazione.name();
	}
	/**
	 * 
	 * Imposta il numero di ore di straordinario a 0
	 * @author Antonio Della Porta
	 */
	public void resetOre() {
		oreStraordinario=0;
	}
	
	public String toString() {
		return super.toString()+"[ore_lavorate= "+oreLavorate+",specializzazione="+specializzazione.name()+",ore_straordinario="+oreStraordinario+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Operaio op=(Operaio)o;
		return op.oreLavorate==oreLavorate&&op.oreStraordinario==oreStraordinario&&op.getSpecializzazione().equals(getSpecializzazione()); 
		}
	
	public Operaio clone() {
		return (Operaio)super.clone();
	}
}
