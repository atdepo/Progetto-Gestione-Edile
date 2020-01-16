package approvviggionamento;

import java.util.ArrayList;
/**
 * 
 * Questa classe implementa il concetto di un Fornitore 
 * che si occupa di vendere prodotti ad una azienda cliente
 */
public class Fornitore {

	 private String nomeFornitore;
	 private ArrayList<Prodotto> prodottiInVendita;
	 private ArrayList<MacchineDaCantiere> macchineDaCantiereInVendita;
	 public Fornitore(String nomeFornitore) {
		 this.nomeFornitore=nomeFornitore;
		 prodottiInVendita=new ArrayList<Prodotto>();
		 macchineDaCantiereInVendita = new ArrayList<MacchineDaCantiere>();
	 }

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public ArrayList<Prodotto> getProdottiInVendita() {
		return prodottiInVendita;
	}

	public ArrayList<MacchineDaCantiere> getMacchineDaCantiere(){
		return macchineDaCantiereInVendita;
	}
	
	public void addProdotto(Prodotto prodotto) {
		if(prodotto!=null && prodottiInVendita!=null)
		prodottiInVendita.add(prodotto);
	}
	
	public void addMacchina(MacchineDaCantiere macchina) {
		if(macchina!=null && macchineDaCantiereInVendita!=null)
		macchineDaCantiereInVendita.add(macchina);
	}
	
	public Prodotto compraProdotto(Prodotto p) {
		for(Prodotto prod : prodottiInVendita) {
			if(prod.equals(p)) {
				if(prod.getNumeroPezziDisponibili()>=p.getNumeroPezziDisponibili()) {
					Prodotto daRestituire=prod.clone();
					prod.scalaProdotto(p.getNumeroPezziDisponibili());
					return daRestituire;
				}
			}
		}
		return null;
	}
	
	
	
	public void rimuoviProdVendita(int elem) { //da cambiare
		prodottiInVendita.remove(elem);
	}
}
