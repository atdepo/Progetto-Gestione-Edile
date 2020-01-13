package approvviggionamento;

/**
 * Questa classe cattura il concetto delle caratteristiche di un prodotto.
 * Un prodotto può anche non possedere tutte le caratteristiche, in tale caso le altre 
 * caratteristiche verranno impostate con valori nulli di default;
 */
public class Caratteristiche {

	private String nomeProdotto;
	private String casaProduttrice;
	private String materialeDiCostruzione;
	private double peso;
	private double lunghezza;
	private double larghezza;
	private double altezza;
	private int consumoEnergetico; //Espressa in Watt
	private int anniGaranzia;
	
	public Caratteristiche(String nome) {
		nomeProdotto=nome;
		casaProduttrice="";
		materialeDiCostruzione="";
		peso=0.0D;
		lunghezza=0.0D;
		larghezza=0.0D;
		altezza=0.0D;
		consumoEnergetico=0;
		anniGaranzia=0;
	}
	
	public Caratteristiche(String nome,double lunghezza,double larghezza,double altezza) {
		nomeProdotto=nome;
		casaProduttrice="";
		materialeDiCostruzione="";
		peso=0.0D;
		this.lunghezza=lunghezza;
		this.larghezza=larghezza;
		this.altezza=altezza;
		consumoEnergetico=0;
		anniGaranzia=0;
	}
	
	public Caratteristiche(String nome, double peso) {
		nomeProdotto=nome;
		casaProduttrice="";
		materialeDiCostruzione="";
		this.peso=peso;
		lunghezza=0.0D;
		larghezza=0.0D;
		altezza=0.0D;
		consumoEnergetico=0;
		anniGaranzia=0;
	}
	
	public Caratteristiche(String nome,double peso, String casaProduttrice,int anniGaranzia) {
		nomeProdotto=nome;
		this.casaProduttrice=casaProduttrice;
		materialeDiCostruzione="";
		this.peso=peso;
		lunghezza=0.0D;
		larghezza=0.0D;
		altezza=0.0D;
		consumoEnergetico=0;
		this.anniGaranzia=anniGaranzia;
	}
	
	public Caratteristiche(String nome,double peso,String materiale) {
		nomeProdotto=nome;
		casaProduttrice="";
		materialeDiCostruzione=materiale;
		this.peso=peso;
		lunghezza=0.0D;
		larghezza=0.0D;
		altezza=0.0D;
		consumoEnergetico=0;
		anniGaranzia=0;
	}
	
	public Caratteristiche(String nome,double peso, String casaProduttrice,int consumo,int anniGaranzia) {
		nomeProdotto=nome;
		this.casaProduttrice=casaProduttrice;
		materialeDiCostruzione="";
		this.peso=peso;
		lunghezza=0.0D;
		larghezza=0.0D;
		altezza=0.0D;
		consumoEnergetico=consumo;
		this.anniGaranzia=anniGaranzia;
	}
	
	public Caratteristiche(String nome,String materiale,String casaProduttrice,double peso,double lunghezza,double larghezza,double altezza,int consumo,int anniGaranzia) {
		
		nomeProdotto=nome;
		this.casaProduttrice=casaProduttrice;
		materialeDiCostruzione=materiale;
		this.peso=peso;
		this.lunghezza=lunghezza;
		this.larghezza=larghezza;
		this.altezza=altezza;
		consumoEnergetico=consumo;
		this.anniGaranzia=anniGaranzia;
	}
	
	public String getNome() {
		return nomeProdotto;
	}
	
	public String getCaratteristicheProdotto() {
		String toReturn="[nome prodotto="+nomeProdotto+"\n";
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
	
}
