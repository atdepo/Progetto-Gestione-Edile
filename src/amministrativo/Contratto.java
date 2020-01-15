package amministrativo;

public class Contratto {
	
	//date assunzione e durata contratto
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
