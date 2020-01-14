package approvviggionamento;

public class Prodotto implements Cloneable{
	
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
		return toReturn;
	}
	
	public void rifornimentoProdotto(int quantitaRif) {
		numeroPezziDisponibili+=quantitaRif;
	}
	
	public void scalaProdotto(int quantitaScalo) { // implementare eccezzione 
		numeroPezziDisponibili-=quantitaScalo;
	}
	
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=getClass())
			return false;
		Prodotto p=(Prodotto)o;
		return p.altezza==altezza && p.anniGaranzia==anniGaranzia && p.casaProduttrice.equals(casaProduttrice) && 
			   p.consumoEnergetico==consumoEnergetico && p.larghezza==larghezza && p.lunghezza==lunghezza && p.materialeDiCostruzione.equals(materialeDiCostruzione)&&
			   p.nomeProdotto.equals(nomeProdotto) && p.peso==peso && p.prezzo==prezzo;
	}
	
	public Prodotto clone() {
		try {
			return (Prodotto)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;		
		}
	}
	
}
