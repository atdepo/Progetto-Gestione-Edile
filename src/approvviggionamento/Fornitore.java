package approvviggionamento;

import java.util.ArrayList;

import eccezioni.ProdottoNonTrovatoException;
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
	/**
	 * Se due prodotti condividono le caratteristiche, vengono sommate le disponibilità dei pezzi
	 * @param a il primo prodotto 
	 * @param b il secondo prodotto
	 * @return il prodotto avente numero_pezzi=numero_pezzi(a)+numero_pezzi(b)
	 */
	
	
	public void aggiungiProdotto(Prodotto prodotto) {
		if(prodotto!=null && prodottiInVendita!=null)
				
		prodottiInVendita.add(prodotto);
	}
	
	public void aggiungiMacchina(MacchineDaCantiere macchina) {
		if(macchina!=null && macchineDaCantiereInVendita!=null)
		macchineDaCantiereInVendita.add(macchina);
	}
	
	public Prodotto compraProdotto(Prodotto p) {
		try {
			Prodotto prod= cercaProdotto(p);
			if(prod.getNumeroPezziDisponibili()>=p.getNumeroPezziDisponibili()) {
				prod.scalaProdotto(p.getNumeroPezziDisponibili());
				Prodotto daRestituire=prod.clone();
				daRestituire.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili());
				System.out.println(daRestituire.getCaratteristicheProdotto()+"\n\n"+p.getNumeroPezziDisponibili()+"\n\n");
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
	
	
	public void rimuoviProdVendita(int elem) { //da cambiare
		prodottiInVendita.remove(elem);
	}
}
