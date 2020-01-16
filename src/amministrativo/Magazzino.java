package amministrativo;

import java.util.ArrayList;

import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonTrovatoException;
/**
 * questa classe implementa la funzione di un magazzino di una Azienda edile 
 * specifiche: capacita massima di materiale e numero posti autoMezzi
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
	
	public void addProdotto(Prodotto prodotto) {
		if(capacitaMax>prodotto.getSpazioOccupatoTotale())
			for(Prodotto p:prodottiInMagazino) {
				if(p.equalsCaratteristiche(prodotto)) {
				p.setNumeroPezziDisponibili(prodotto.getNumeroPezziDisponibili());
				
				}
			}
		else
			prodottiInMagazino.add(prodotto);
		}

	public Prodotto prendiProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		
		for(Prodotto p:prodottiInMagazino) {
			if(p.equalsCaratteristiche(prodotto)) 
				if(p.getNumeroPezziDisponibili()>prodotto.getNumeroPezziDisponibili()) {
					capacitaMax+=(p.getSpazioOccupato()*p.getNumeroPezziDisponibili());
					p.scalaProdotto(prodotto.getNumeroPezziDisponibili());
					return prodotto;
				}	
		}
		throw new ProdottoNonTrovatoException();
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
