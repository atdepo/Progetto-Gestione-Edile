package approvviggionamento;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.ProdottoNonTrovatoException;
/**
 * 
 * Questa classe implementa il concetto di un Fornitore 
 * che si occupa di vendere prodotti ad una azienda cliente
 */
public class Fornitore implements Serializable{

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
	
	public void aggiungiProdotto(Prodotto prodotto) {
		if(prodotto!=null && prodottiInVendita!=null)
				
		prodottiInVendita.add(prodotto);
	}
	
	public void aggiungiMacchina(MacchineDaCantiere macchina) {
		if(macchina!=null && macchineDaCantiereInVendita!=null)
		macchineDaCantiereInVendita.add(macchina);
	}
	
	public MacchineDaCantiere compraMacchina(MacchineDaCantiere macchina) {
		
		try {
			MacchineDaCantiere daComprare=cercaMacchina(macchina);
			macchineDaCantiereInVendita.remove(daComprare);
			return daComprare;
			
		} catch (IllegalArgumentException e) {
			return null;
		}
		
	}
	
	public Prodotto compraProdotto(Prodotto p) {
		try {
			Prodotto prod= cercaProdotto(p);
			if(prod.getNumeroPezziDisponibili()>=p.getNumeroPezziDisponibili()) {
				prod.scalaProdotto(p.getNumeroPezziDisponibili());
				Prodotto daRestituire=prod.clone();
				daRestituire.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili());
				return daRestituire;
			}
			else 
				return null;
		} catch (ProdottoNonTrovatoException e) {
			return null;
		}
	}
	
	public Prodotto cercaProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for(Prodotto prod : prodottiInVendita) {
			if(prod.equalsCaratteristiche(prodotto)) 
					return prod;
		}
		throw new ProdottoNonTrovatoException();
	}
	
	public MacchineDaCantiere cercaMacchina(MacchineDaCantiere daCercare) {
		for(MacchineDaCantiere m:macchineDaCantiereInVendita) {
			if(m.equalsCaratteristiche(daCercare))
				return m;
		}
		throw new IllegalArgumentException("Macchina non trovata");
	}
	
	public void rimuoviProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		prodottiInVendita.remove(cercaProdotto(prodotto));
	}
	
	public void rimuoviMacchina(MacchineDaCantiere macchina) {
		macchineDaCantiereInVendita.remove(cercaMacchina(macchina));
	}
}
