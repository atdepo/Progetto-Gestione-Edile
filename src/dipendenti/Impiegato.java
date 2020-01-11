package dipendenti;
/**
 *Questa classe cattura il concetto di un Impiegato.
 *Un impiegato viene pagato in base al numero di ore in cui ha lavorato. 
 *Se un impiegato effettua delle ore di lavoro straordinarie, queste gli verranno pagate con una retribuzione maggiorata.
 *Il <b>codiceDipendente</b> di un Impiegato inizia con <b>01</b>
 */
public class Impiegato extends Dipendente{
	private int ore_lavorate;
	
	public Impiegato(String nome, String cognome, int eta) {
		super(nome, cognome, eta);
		
		//this.setCodiceDipendente();
		
	}
	public int getOreLavorate() {
		return ore_lavorate;
	}

	public String toString() {
		return super.toString()+"[ore_lavorate= "+ore_lavorate+"]";
	}
	
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Impiegato imp=(Impiegato)o;
		return imp.getOreLavorate()==ore_lavorate;
	}
	
	public Impiegato clone() {
		return (Impiegato)super.clone();
	}

	@Override
	public String generateCodice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
