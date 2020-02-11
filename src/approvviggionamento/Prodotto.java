package approvviggionamento;

import java.io.Serializable;

/**
 * classe che implementa la funzione di un prodotto descrivendo tutte le caratteristiche di un prodotto
 * questo prodotto è destinato alla vendita di fornitori che riforniscono delle aziende edili
 *
 */
public class Prodotto implements Cloneable,Serializable{
	
	private String nomeProdotto;
	private String casaProduttrice;
	private String materialeDiCostruzione;
	private double peso;					//Espresso in kg
	private double lunghezza;				//Espressa in cm
	private double larghezza;				//Espressa in cm
	private double altezza;					//Espressa in cm
	private int consumoEnergetico;  		//Espressa in Watt
	private int anniGaranzia;
	private double prezzo;					//Espresso in euro
	private int numeroPezziDisponibili;
	
	public Prodotto (String nome,String materiale,String casaProduttrice,double peso,double lunghezza,double larghezza,double altezza,int consumo,int anniGaranzia,double prezzoProdottoIniziale,int disponibilita) {
		nomeProdotto=nome;
		this.casaProduttrice=casaProduttrice;
		materialeDiCostruzione=materiale;
		this.peso=peso;
		this.lunghezza=lunghezza;
		this.larghezza=larghezza;
		this.altezza=altezza;
		consumoEnergetico=consumo;
		this.anniGaranzia=anniGaranzia;
		this.prezzo=prezzoProdottoIniziale;
		this.numeroPezziDisponibili=disponibilita;
	}
	
	public String getNome() {
		return nomeProdotto;
	}
	
	public String getCasaProduttrice() {
		return casaProduttrice;
	}
	public String getMaterialeDiCostruzione() {
		return materialeDiCostruzione;
	}
	public double getPeso() {
		return peso;
	}
	public double getLunghezza() {
		return lunghezza;
	}
	public double getLarghezza() {
		return larghezza;
	}
	public double getAltezza() {
		return altezza;
	}
	public int getConsumoEnergetico() {
		return consumoEnergetico;
	}
	public int getAnniGaranzia() {
		return anniGaranzia;
	}
	public int getNumeroPezziDisponibili() {
		return numeroPezziDisponibili;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setNumeroPezziDisponibili(int numero) {
		numeroPezziDisponibili=numero;
	}
	
	
	
	public String getCaratteristicheProdotto() {
		String toReturn="nome prodotto="+nomeProdotto+"\n";
		
		if(!materialeDiCostruzione.isEmpty())
			toReturn+="materiale="+materialeDiCostruzione+"\n";
		if(!casaProduttrice.isEmpty())
			toReturn+="casa produttrice="+casaProduttrice+"\n";
		if(peso!=0.0D)
			toReturn+="peso="+peso+"kg\n";
		if(lunghezza!=0.0D)
			toReturn+="lunghezza="+lunghezza+"cm\n";
		if(larghezza!=0.0D)
			toReturn+="larghezza"+larghezza+"cm\n";
		if(altezza!=0.0D)
			toReturn+="altezza="+altezza+"cm\n";
		if(consumoEnergetico!=0)
			toReturn+="consumo="+consumoEnergetico+"W\n";
		if(anniGaranzia!=0)
			toReturn+="anni di garanzia="+anniGaranzia+"\n";
		toReturn+="numero pezzi disponibili="+numeroPezziDisponibili+"\n";
		return toReturn;
	}
	
	public double getSpazioOccupato() {
		return (lunghezza*larghezza*altezza)/1000000;
	}
	
	public double getSpazioOccupatoTotale() {
		return ((lunghezza*larghezza*altezza)/1000000)*numeroPezziDisponibili;
	}
	
	public void scalaProdotto(int quantitaScalo) { // implementare eccezzione 
		numeroPezziDisponibili-=quantitaScalo;
	}
	
	public boolean equalsCaratteristiche(Prodotto p) {
		if(p==null||p.getClass()!=getClass())
			return false;
		return p.altezza==altezza && p.anniGaranzia==anniGaranzia && p.casaProduttrice.equals(casaProduttrice) && 
			   p.consumoEnergetico==consumoEnergetico && p.larghezza==larghezza && p.lunghezza==lunghezza && p.materialeDiCostruzione.equals(materialeDiCostruzione)&&
			   p.nomeProdotto.equals(nomeProdotto) && p.peso==peso && p.prezzo==prezzo;
	}
	
	public Prodotto sommaDisponibilita (Prodotto a) {
		if(a!=null)
		if(equalsCaratteristiche(a)) {  //se due prodotti hanno le stesse caratteristiche
			this.setNumeroPezziDisponibili(a.getNumeroPezziDisponibili()+getNumeroPezziDisponibili()); //sommiamo i pezzi
			return this;
		}
		return null;
	}
	
	public Prodotto clone() {
		try {
			return (Prodotto)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;		
		}
	}
	
}
