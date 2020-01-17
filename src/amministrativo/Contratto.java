package amministrativo;
/**
 * 
 * 
 * Questa classe cattura il concetto di un contratto di lavoro per un dipendente.
 * Il contratto è un oggetto immutabile, infatti per cambiare qualche aspetto del contratto deve esserne
 * creato uno nuovo.
 * 
 */
public final class Contratto {
	
	private double stipendio;
	private double bonus;
	
	public Contratto(double stipendio,double bonus) {
		this.bonus=bonus;
		this.stipendio=stipendio;
	}
	
	public double getStipendio() {
		return stipendio;
	}
	
	public double getBonus() {
		return bonus;
	}
	
	

}
