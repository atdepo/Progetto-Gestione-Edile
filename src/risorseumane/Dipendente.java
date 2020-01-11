package risorseumane;

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
	
	public String toString() {
		return getClass().getName()+"[nome="+nome+",cognome="+cognome+",eta="+eta+"codiceDipendente="+codiceDipendente+",pagato="+pagato+",impegnato"+impegnato+"]";
	}
	
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=getClass())
			return false;
		Dipendente dip=(Dipendente)o;
		return dip.isImpegnato()==impegnato && dip.isPagato()==pagato && dip.getNome().equals(nome) && 
				dip.getCognome().equals(cognome) && 
				dip.eta==eta && dip.codiceDipendente==codiceDipendente;		
	}
	
	public Dipendente clone() {
		try {
			return (Dipendente)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	

	
	
	
	
}
