
public abstract class  Dipendente {

	private String nome,cognome;
	private int eta;
	private long codiceDipendente; 
	private boolean pagato,impegnato;
	
	public Dipendente(String nome,String cognome,int eta,long codiceDip) {
		this.nome=nome;
		this.cognome=cognome;
		this.eta=eta;
		this.codiceDipendente=codiceDip;
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getEta() {
		return eta;
	}
	
	public long getCodiceDipendente() {
		return codiceDipendente;
	}
	
	public boolean isPagato() {
		return pagato;
	}
	
	public boolean isImpegnato() {
		return impegnato;
	}
	
}
