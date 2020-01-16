package amministrativo;

import java.util.ArrayList;

import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonTrovatoException;

public class Magazzino {
	
	private ArrayList<Prodotto> prodottiInMagazino;
	private ArrayList<MacchineDaCantiere> macchineDaCantiere;
	private double capacitaMax; //espressa in m^2
	private int postiMacchine; //espressa in posti
	
<<<<<<< HEAD
	public Magazzino() {
		prodottiInMagazino= new ArrayList<Prodotto>();
		macchineDaCantiere= new ArrayList<MacchineDaCantiere>();
	}


	public void addProdottiMagazino(Prodotto prod) {
		prodottiInMagazino.add(prod);
	}


	public void addMacchineDaCantiere(MacchineDaCantiere mC) {
		macchineDaCantiere.add(mC);
	};
	
	
	
	

=======
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
/*
	public Prodotto prendiProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		
		for(Prodotto p:prodottiInMagazino) {
			if(p.equalsCaratteristiche(prodotto)) 
				if(p.getNumeroPezziDisponibili()>prodotto.getNumeroPezziDisponibili()) {
					p.scalaProdotto(prodotto.getNumeroPezziDisponibili());
					return prodotto;
				}	
		}
		throw new ProdottoNonTrovatoException();
	}
	*/
	public Prodotto rimuoviProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		
		for(Prodotto p:prodottiInMagazino) {
			if(p.equalsCaratteristiche(prodotto)) {
				
				return prodottiInMagazino.remove(prodottiInMagazino.indexOf(prodotto));
				
			}
		}
		throw new ProdottoNonTrovatoException();
		
	}
	
>>>>>>> 3949d7df785f480525ca232667832caf58a3e2c5
}
