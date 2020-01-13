package materiali;

public class Prodotto implements Cloneable {
	private String nome;
	private double prezzo;
	private int disponibilita;
	
	public Prodotto(String nome,double prezzoProdottoIniziale,int disponibilita) {
		this.nome=nome;
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	public void rifornimentoProdotto(int quantitaRif) {
		disponibilita+=quantitaRif;
	}
	
	public void scalaProdotto(int quantitaScalo) { // implementare eccezzione 
		disponibilita-=quantitaScalo;
	}
	
	public String toString() {
		return getClass().getName()+"[nome="+nome+",prezzo="+prezzo+",disponibilita"+disponibilita+"]";
	}
	
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=getClass())
			return false;
		Prodotto prod=(Prodotto)o;
		return prod.getNome().equals(nome) && prezzo==prod.getPrezzo() && disponibilita==prod.getDisponibilita();							
	}
	
	public Prodotto clone() {
		try {
			return(Prodotto)super.clone();
			
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	


}
