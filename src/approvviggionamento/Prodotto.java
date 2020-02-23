package approvviggionamento;

import java.io.Serializable;

/**
 * Questa classe definisce l'astrazione di un prodotto descrivendone tutte le
 * caratteristiche
 * 
 * @author Antonio Della Porta
 */
public class Prodotto implements Cloneable, Serializable {

	private String nomeProdotto;
	private String casaProduttrice;
	private String materialeDiCostruzione;
	private double peso; // Espresso in kg
	private double lunghezza; // Espressa in cm
	private double larghezza; // Espressa in cm
	private double altezza; // Espressa in cm
	private int consumoEnergetico; // Espressa in Watt
	private int anniGaranzia;
	private double prezzo; // Espresso in euro
	private int numeroPezziDisponibili;

	/**
	 * Istanzia un nuovo Prodotto con tutte le sue caratteristiche <b>NB: Per il
	 * corretto utilizzo dell'oggetto è obbligatorio solamente il nome, tutte le
	 * altre caratteristiche sono opzionali e possono essere riempite con valori di
	 * default</b>
	 * 
	 * @param nome                   nome del prodotto
	 * @param materiale              il materiale del prodotto
	 * @param casaProduttrice        la casa produttrice del prodotto
	 * @param peso                   il peso del prodotto
	 * @param lunghezza              la lunghezza del prodotto
	 * @param larghezza              la larghezza del prodotto
	 * @param altezza                l'altezza del prodotto
	 * @param consumo                il consumo del prodotto
	 * @param anniGaranzia           gli anni di garanzia del prodotto
	 * @param prezzoProdottoIniziale il prezzo del prodotto
	 * @param disponibilita          il numero di pezzi disponibili
	 * @author Antonio Della Porta
	 */
	public Prodotto(String nome, String materiale, String casaProduttrice, double peso, double lunghezza,
			double larghezza, double altezza, int consumo, int anniGaranzia, double prezzoProdottoIniziale,
			int disponibilita) {
		nomeProdotto = nome;
		this.casaProduttrice = casaProduttrice;
		materialeDiCostruzione = materiale;
		this.peso = peso;
		this.lunghezza = lunghezza;
		this.larghezza = larghezza;
		this.altezza = altezza;
		consumoEnergetico = consumo;
		this.anniGaranzia = anniGaranzia;
		this.prezzo = prezzoProdottoIniziale;
		this.numeroPezziDisponibili = disponibilita;
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
		numeroPezziDisponibili = numero;
	}

	/**
	 * Metodo utilizzato per la formattazzione delle caratteristiche di un prodotto
	 * 
	 * @return una stringa contente le caratteristiche del prodotto formattate riga
	 *         per riga
	 * @author Antonio Della Porta
	 */
	public String getCaratteristicheProdotto() {
		String toReturn = "nome prodotto=" + nomeProdotto + "\n";

		if (!materialeDiCostruzione.isEmpty())
			toReturn += "materiale=" + materialeDiCostruzione + "\n";
		if (!casaProduttrice.isEmpty())
			toReturn += "casa produttrice=" + casaProduttrice + "\n";
		if (peso != 0.0D)
			toReturn += "peso=" + peso + "kg\n";
		if (lunghezza != 0.0D)
			toReturn += "lunghezza=" + lunghezza + "cm\n";
		if (larghezza != 0.0D)
			toReturn += "larghezza=" + larghezza + "cm\n";
		if (altezza != 0.0D)
			toReturn += "altezza=" + altezza + "cm\n";
		if (consumoEnergetico != 0)
			toReturn += "consumo=" + consumoEnergetico + "W\n";
		if (anniGaranzia != 0)
			toReturn += "anni di garanzia=" + anniGaranzia + "\n";
		toReturn += "numero pezzi disponibili=" + numeroPezziDisponibili + "\n";
		return toReturn;
	}

	/**
	 * Metodo utilizzato per il calcolo dello spazio occupato da un prodotto. Per
	 * semplicità viene considerato un cubo ideale che contenga idealmente il
	 * prodotto considerato
	 * 
	 * @return il volume del cubo ideale che contiene il prodotto
	 * @author Antonio Della Porta
	 */
	public double getSpazioOccupato() {
		return (lunghezza * larghezza * altezza) / 1000000;
	}

	/**
	 * Metodo utilizzato per il calcolo dello spazio totale occupato da un prodotto.
	 * Per semplicità viene considerato un cubo ideale che contenga idealmente tutti
	 * i pezzi disponibili del prodotto considerato
	 * 
	 * @return il volume del cubo ideale che contiene i prodotti
	 * @author Antonio Della Porta
	 */
	public double getSpazioOccupatoTotale() {
		return ((lunghezza * larghezza * altezza) / 1000000) * numeroPezziDisponibili;
	}

	/**
	 * Metodo utilizzato per scalare un certo numero di prodotti
	 * 
	 * @param quantitaScalo la quantità da scalare che deve essere <= della quantità
	 *                      presente
	 * @author Antonio Della Porta
	 */
	public void scalaProdotto(int quantitaScalo) {
		if (quantitaScalo <= numeroPezziDisponibili)
			numeroPezziDisponibili -= quantitaScalo;
	}

	/**
	 * Metodo utilizzato per confrontare il prodotto rappresentato dal parametro
	 * implicito e il prodotto passato come parametro esplicito sulla base delle
	 * loro caratteristiche(escluso il numero dei pezzi disponibili)
	 * 
	 * @param p il prodotto da conforntare
	 * @return <b>true</b> se i prodotti corrispondono, <b>false</b> altrimenti
	 * @author Antonio Della Porta
	 */
	public boolean equalsCaratteristiche(Prodotto p) {
		if (p == null || p.getClass() != getClass())
			return false;
		return p.altezza == altezza && p.anniGaranzia == anniGaranzia && p.casaProduttrice.equals(casaProduttrice)
				&& p.consumoEnergetico == consumoEnergetico && p.larghezza == larghezza && p.lunghezza == lunghezza
				&& p.materialeDiCostruzione.equals(materialeDiCostruzione) && p.nomeProdotto.equals(nomeProdotto)
				&& p.peso == peso && p.prezzo == prezzo;
	}

	/**
	 * Controlla se il rpodotto rappresentato dal parametro implicito e il prodotto
	 * passato come parametro esplicito sono lo stesso prodotto(a dispetto del numero di pezzi disponibili), se ciò è vero vengono sommate le disponibilitò dei prodotti
	 * 
	 * @param a il prodotto da controllare
	 * @return il prodotto contenente le capacità sommate
	 * @author Antonio Della Porta
	 */
	public Prodotto sommaDisponibilita(Prodotto a) {
		if (a != null)
			if (equalsCaratteristiche(a)) { // se due prodotti hanno le stesse caratteristiche
				this.setNumeroPezziDisponibili(a.getNumeroPezziDisponibili() + getNumeroPezziDisponibili()); // sommiamo
																												// i
																												// pezzi
				return this;
			}
		return null;
	}

	public Prodotto clone() {
		try {
			return (Prodotto) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
