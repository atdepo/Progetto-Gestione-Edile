package approvviggionamento;

public class Prodotto implements Cloneable {
	private Caratteristiche caratteristiche;
	private double prezzo;
	private int disponibilita;
	
	public Prodotto(String nome,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public Prodotto(String nome,double lunghezza,double larghezza,double altezza,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome, lunghezza, larghezza, altezza);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public Prodotto(String nome, double peso,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome, peso);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public Prodotto(String nome,double peso, String casaProduttrice,int anniGaranzia,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome, peso, casaProduttrice, anniGaranzia);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public Prodotto(String nome,double peso,String materiale,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome, peso, materiale);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	
	public Prodotto(String nome,double peso, String casaProduttrice,int consumo,int anniGaranzia,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome, peso, casaProduttrice, consumo, anniGaranzia);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
		
	}
	
	public Prodotto (String nome,String materiale,String casaProduttrice,double peso,double lunghezza,double larghezza,double altezza,int consumo,int anniGaranzia,double prezzoProdottoIniziale,int disponibilita) {
		caratteristiche= new Caratteristiche(nome, materiale, casaProduttrice, peso, lunghezza, larghezza, altezza, consumo, anniGaranzia);
		this.prezzo=prezzoProdottoIniziale;
		this.disponibilita=disponibilita;
	}
	public String getNome() {
		return caratteristiche.getNome();
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public String getCaratteristiche() {
		return caratteristiche.getCaratteristicheProdotto();
	}
	
	public int getDisponibilita() {
		return disponibilita;
	}
	public void rifornimentoProdotto(int quantitaRif) {
		disponibilita+=quantitaRif;
	}
	
	public void scalaProdotto(int quantitaScalo) { // implementare eccezzione 
		disponibilita-=quantitaScalo;
	}
	
}
