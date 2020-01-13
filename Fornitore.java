package repartoamministrativo;

import java.util.ArrayList;

public class Fornitore implements Cloneable {
	
	 private String nomeFornitore;
	 private ArrayList<Prodotto> prodottiInVendita;
	 
	 public Fornitore(String nomeFornitore) {
		 this.nomeFornitore=nomeFornitore;
		 prodottiInVendita=new ArrayList<Prodotto>();
	 }

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public void setNomeFornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
	}

	public ArrayList<Prodotto> getProdottiInVendita() {
		return prodottiInVendita;
	}

	public void AggiungiProdVendita(Prodotto p) {
		prodottiInVendita.add(p);
	}
	
	public void compraProdVendita(int pos, int quantità) {
		prodottiInVendita.get(pos).scalaProdotto(quantità);
	}
	
	public void rimuoviProdVendita(int elem) {
		prodottiInVendita.remove(elem);
	}
	
	public String toString() {
		return getClass().getName()+"[nomeFornitore="+nomeFornitore+",prodottiInVendita="+prodottiInVendita+"]";
	}
	
	@SuppressWarnings("unchecked")
	public Fornitore clone() {
		try {
			Fornitore f=(Fornitore)super.clone();
			ArrayList<Prodotto> clone = (ArrayList<Prodotto>) this.prodottiInVendita.clone();
			f.prodottiInVendita=clone;
			return f;
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	
	public boolean equals(Object o) {
		if(o==null||o.getClass()!=getClass())
			return false;
		Fornitore frn=(Fornitore)o;
		return frn.getNomeFornitore().equals(nomeFornitore) && prodottiInVendita.equals(frn.getProdottiInVendita());
								
	}
	
	
	
	
	 
}
