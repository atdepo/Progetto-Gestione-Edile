package approvviggionamento;

import java.io.Serializable;

/**
 * Questa classe cattura il concetto delle Macchine che vengono usate all'interno dei cantieri ad esempio gli escavatori meccanici oppure le betoniere.
 * 
 */
public class MacchineDaCantiere implements Cloneable,Serializable{

	private String nome;
	private String casaMadre;
	private double peso;    //espresso in kg
	private double potenza; //espressa in cavalli
	private double prezzo;  //espressa in euro
	
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
	
	public String getCaratteristiche() {
		String caratteristiche="Nome="+nome+
								"\n Casa Madre="+casaMadre+
								"\n Peso="+peso+
								"\n Potenza="+potenza+
								"\n Prezzo="+prezzo+"\n";
		return caratteristiche;
	}
	
	public boolean equalsCaratteristiche(MacchineDaCantiere macchina) {
		if(macchina==null)
			return false;
		return nome.equals(macchina.getNome())&&casaMadre.equals(macchina.getCasaMadre())&&peso==macchina.getPeso()&&potenza==macchina.getPotenza();
	}
	
	public MacchineDaCantiere clone() {
		try {
			return (MacchineDaCantiere)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;	
		}
	}
}
