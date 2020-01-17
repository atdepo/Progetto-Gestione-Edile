package amministrativo;

import java.util.ArrayList;

import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonTrovatoException;
/**
 * Questa classe implementa l'astrazione di un magazzino di una Azienda edile
 * dove vengono depositati i prodotti comprati e parcheggiati i mezzi non attualmente in uso 
 * 
 * 
 */
public class Magazzino {
	
	private ArrayList<Prodotto> prodottiInMagazino;
	private ArrayList<MacchineDaCantiere> macchineDaCantiere;
	private double capacitaMax; //espressa in m^2
	private int postiMacchine; //espressa in posti
	
	public Magazzino(double capacita,int posti) {
		prodottiInMagazino= new ArrayList<Prodotto>();
		macchineDaCantiere= new ArrayList<MacchineDaCantiere>();
		capacitaMax= capacita;
		postiMacchine=posti;
	}
	
	
	public void addMacchina(MacchineDaCantiere macchina) {
		if(postiMacchine>0) {
		macchineDaCantiere.add(macchina);
		capacitaMax--;
		
		}
		else
			throw new CapacitaSuperataException();
	}
	

	public MacchineDaCantiere rimuoviMacchina(MacchineDaCantiere macchina) {
		
		for(MacchineDaCantiere p:macchineDaCantiere) {
			if(p.equalsCaratteristiche(macchina)) {
				postiMacchine--;
				return macchineDaCantiere.remove(macchineDaCantiere.indexOf(macchina));
			}
		}
		throw new IllegalArgumentException();
	}
	
	public void aggiungiProdotto(Prodotto prodotto) {
		if(capacitaMax>prodotto.getSpazioOccupatoTotale()) {
			for(Prodotto p:prodottiInMagazino) {
				if(p.equalsCaratteristiche(prodotto))
					p.sommaDisponibilita(prodotto);
					return;
			}
			prodottiInMagazino.add(prodotto);
		}
		else
			throw new CapacitaSuperataException();
	}

	public Prodotto prendiProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for(Prodotto p:prodottiInMagazino) {
			if(p.equalsCaratteristiche(prodotto)) 				//se il prodotto viene trovato nel magazzino
				if(p.getNumeroPezziDisponibili()>=prodotto.getNumeroPezziDisponibili()) { // e ci sono quantità necessarie per soddisfare la richeista
					capacitaMax+=(p.getSpazioOccupato()*p.getNumeroPezziDisponibili());
					p.scalaProdotto(prodotto.getNumeroPezziDisponibili());
					return prodotto;   //ritorni tutta la richiesta
				}	
				else {
					capacitaMax+=p.getSpazioOccupatoTotale();
					prodotto.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili());
					p.scalaProdotto(p.getNumeroPezziDisponibili());
					return prodotto; //altrimenti restituisci solamente la quantità che hai disponibile nel magazzino 
				}
		}
		//prodottononinvendita,prodottonondisponibile,quantitanondisponibile
		return null;
	}
	
	public Prodotto rimuoviProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		
		for(Prodotto p:prodottiInMagazino) {
			if(p.equalsCaratteristiche(prodotto)) {
				capacitaMax+=p.getSpazioOccupatoTotale();
				return prodottiInMagazino.remove(prodottiInMagazino.indexOf(prodotto));
			}
		}
		throw new ProdottoNonTrovatoException();
	}
	

	 
}
