package approvviggionamento;
/**
 * Questa classe cattura il concetto delle Macchine che vengono usate all'interno dei cantieri ad esempio gli escavatori meccanici oppure le betoniere.
 * 
 */
public class MacchineDaCantiere implements Cloneable{

	private String nome;
	private String casaMadre;
	private double peso;
	private double potenza;
	private double prezzo;
	
	public MacchineDaCantiere(String nome,String casaMadre,double peso,double potenza,double prezzo) {
		this.nome=nome;
		this.casaMadre=casaMadre;
		this.peso=peso;
		this.potenza=potenza;
		this.prezzo=prezzo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCasaMadre() {
		return casaMadre;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public double getPotenza() {
		return potenza;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public MacchineDaCantiere clone() {
		try {
			return (MacchineDaCantiere)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;		}
	}
}
