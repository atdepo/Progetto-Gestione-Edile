package amministrativo;

import java.util.ArrayList;

import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;

public class Magazzino {
	
	private ArrayList<Prodotto> prodottiMagazino;
	private ArrayList<MacchineDaCantiere> macchineDaCantiere;
	
	public Magazzino() {
		prodottiMagazino= new ArrayList<Prodotto>();
		macchineDaCantiere= new ArrayList<MacchineDaCantiere>();
	}


	public void addProdottiMagazino(Prodotto prod) {
		prodottiMagazino.add(prod);
	}


	public void addMacchineDaCantiere(MacchineDaCantiere mC) {
		macchineDaCantiere.add(mC);
	};
	
	
	
	

}
