package approvviggionamento;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.ProdottoNonTrovatoException;

/**
 * 
 * Questa classe implementa il concetto di un Fornitore che si occupa di vendere
 * prodotti e macchine ad una azienda cliente
 */
public class Fornitore implements Serializable {

	private String nomeFornitore;
	private ArrayList<Prodotto> prodottiInVendita;
	private ArrayList<MacchineDaCantiere> macchineDaCantiereInVendita;

	/**
	 * Istanzia un nuovo fornitore
	 * 
	 * @param nomeFornitore il nome del fornitore
	 * @author Antonio Della Porta
	 */
	public Fornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
		prodottiInVendita = new ArrayList<Prodotto>();
		macchineDaCantiereInVendita = new ArrayList<MacchineDaCantiere>();
	}

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public ArrayList<Prodotto> getProdottiInVendita() {
		return prodottiInVendita;
	}

	public ArrayList<MacchineDaCantiere> getMacchineDaCantiere() {
		return macchineDaCantiereInVendita;
	}

	/**
	 * Aggiunge un prodotto alla sua lista di prodotti in vendita
	 * 
	 * @param prodotto il prodotto da aggiungere
	 * @author Antonio Della Porta
	 */
	public void aggiungiProdotto(Prodotto prodotto) {
		if (prodotto != null && prodottiInVendita != null)

			prodottiInVendita.add(prodotto);
	}

	/**
	 * Aggiunge una macchina alla sua lista di macchine in vendita
	 * 
	 * @param macchina la macchina da aggiungere
	 * @author Antonio Della Porta
	 */
	public void aggiungiMacchina(MacchineDaCantiere macchina) {
		if (macchina != null && macchineDaCantiereInVendita != null)
			macchineDaCantiereInVendita.add(macchina);
	}

	/**
	 * Metodo utilizzato quando un cliente chiede di comprare una macchina
	 * 
	 * @param macchina la macchina richiesta
	 * @return la macchina richiesta se trovata oppure <b>null </b>altrimenti
	 * @throws IllegalArgumentException se la macchina non è presente nel catalogo
	 * @author Antonio Della Porta
	 */
	public MacchineDaCantiere compraMacchina(MacchineDaCantiere macchina) {
		try {
			MacchineDaCantiere daComprare = cercaMacchina(macchina);
			macchineDaCantiereInVendita.remove(daComprare);
			return daComprare;

		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	/**
	 * Metodo utilizzato quando un cliente chiede di comprare un prodotto
	 * 
	 * @param p il prodotto richiesto
	 * @return il prodotto richiesto oppure <b>null</b> altrimenti
	 * @author Antonio Della Porta
	 */
	public Prodotto compraProdotto(Prodotto p) {
		try {
			Prodotto prod = cercaProdotto(p);
			if (prod.getNumeroPezziDisponibili() >= p.getNumeroPezziDisponibili()) {
				prod.scalaProdotto(p.getNumeroPezziDisponibili());
				Prodotto daRestituire = prod.clone();
				daRestituire.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili());
				return daRestituire;
			} else
				return null;
		} catch (ProdottoNonTrovatoException e) {
			return null;
		}
	}

	/**
	 * Metodo utilizzato per cercare un prodotto all'interno del magazzino
	 * 
	 * @param prodotto il prodotto da cercare
	 * @return il prodotto richiesto
	 * @throws ProdottoNonTrovatoException se il prodotto non è presente nella lista
	 *                                     dei prodotti
	 * @author Antonio Della Porta
	 */
	public Prodotto cercaProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for (Prodotto prod : prodottiInVendita) {
			if (prod.equalsCaratteristiche(prodotto))
				return prod;
		}
		throw new ProdottoNonTrovatoException();
	}

	/**
	 * Metodo utilizzato per cercare una macchina all'interno del magazzino
	 * 
	 * @param daCercare la macchina da cercare
	 * @return la macchina richiesta
	 * @throws IllegalArgumentException se la macchina non è stata trovata nel
	 *                                  magazzino
	 * @author Antonio Della Porta
	 */
	public MacchineDaCantiere cercaMacchina(MacchineDaCantiere daCercare) {
		for (MacchineDaCantiere m : macchineDaCantiereInVendita) {
			if (m.equalsCaratteristiche(daCercare))
				return m;
		}
		throw new IllegalArgumentException("Macchina non trovata");
	}

	/**
	 * Rimuove un prodotto dalla lista dei prodotti in vendita
	 * @param prodotto il prodotto da rimuovere
	 * @throws ProdottoNonTrovatoException se il prodotto non è stato trovato
	 * @author Antonio Della Porta
	 */
	public void rimuoviProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		prodottiInVendita.remove(cercaProdotto(prodotto));
	}
	/**
	 * Rimuove una macchina dalla lista delle macchine in vendita
	 * @param macchina la macchina da rimuovere
	 * @author Antonio Della Porta
	 */
	public void rimuoviMacchina(MacchineDaCantiere macchina) {
		macchineDaCantiereInVendita.remove(cercaMacchina(macchina));
	}
}
