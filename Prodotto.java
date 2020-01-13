package repartoamministrativo;

public class Prodotto implements Cloneable {
	private String nome;
	private double prezzo;
	private int disponibilita;
	
	public Prodotto(String nome,double prezzoProdottoIniziale,int disponibilita) {
		this.nome=nome;
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void ridPrezze(double ridPrezzo) {
		prezzo-=ridPrezzo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public int getDisponibilitā() {
		return disponibilita;
	}

	public void setDisponibilitā(int disponibilitā) {
		this.disponibilita = disponibilitā;
	}
	
	public void rifornimentoProdotto(int quantitaRif) {
		disponibilita+=quantitaRif;
	}
	
	public void scalaProdotto(int quantitāScalo) { // implementare eccezzione 
		disponibilita-=quantitāScalo;
	}
	
	public String toString() {
		return getClass().getName()+"[nome="+nome+",prezzo="+prezzo+",disponibilit"+disponibilita+"]";
	}
	
	public Prodotto clone() {
		try {
			return(Prodotto)super.clone();
			
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=getClass())
			return false;
		Prodotto prod=(Prodotto)o;
		return prod.getNome().equals(nome) && prezzo==prod.getPrezzo() && disponibilita==prod.getDisponibilitā();
								
	}
	


}
