package amministrativo;

import java.io.Serializable;
import java.util.ArrayList;

import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonTrovatoException;

/**
 * Questa classe implementa l'astrazione di un magazzino di una Azienda edile
 * dove vengono depositati i prodotti comprati e parcheggiati i mezzi non
 * attualmente in uso
 * 
 * @author Antonio Della Porta
 */
public class Magazzino implements Serializable {

	private ArrayList<Prodotto> prodottiInMagazino;
	private ArrayList<MacchineDaCantiere> macchineDaCantiere;
	private double capacitaMax; // espressa in m^3
	private int postiMacchine; // espressa in posti

	/**
	 * Istanzia un Magazzino con una capacità massima e un numero massimo di posti
	 * per le macchine
	 * 
	 * @param capacita la capacita espressa in m^3 del magazzino
	 * @param posti    i posti per le macchine
	 * @author Antonio Della Porta
	 */
	public Magazzino(double capacita, int posti) {
		prodottiInMagazino = new ArrayList<Prodotto>();
		macchineDaCantiere = new ArrayList<MacchineDaCantiere>();
		capacitaMax = capacita;
		postiMacchine = posti;
	}

	public ArrayList<Prodotto> getProdottiInMagazzino() {
		return prodottiInMagazino;
	}

	public ArrayList<MacchineDaCantiere> getMacchineInMagazzino() {
		return macchineDaCantiere;
	}

	public double getCapacitaResidua() {
		return capacitaMax;
	}

	public int getPostiDisponibili() {
		return postiMacchine;
	}

	/**
	 * Metodo utilizzato per aggiungere una macchina al magazzino
	 * 
	 * @param macchina la macchina da aggiungere
	 * @throws CapacitaSuperataException se la capacità dei posti per le macchine è
	 *                                   esaurita
	 * @author Antonio Della Porta
	 */
	public void aggiungiMacchina(MacchineDaCantiere macchina) {
		if (postiMacchine > 0) {
			macchineDaCantiere.add(macchina);
			capacitaMax--;
		} else
			throw new CapacitaSuperataException();
	}

	/**
	 * Metodo usato per prendere una macchina dal magazzino
	 * 
	 * @param macchina
	 * @return la macchina richiesta se presente oppure <b>null </b> se non è stata
	 *         trovata
	 * @author Antonio Della Porta
	 */
	public MacchineDaCantiere prendiMacchina(MacchineDaCantiere macchina) {
		for (MacchineDaCantiere m : macchineDaCantiere) {
			if (m.equalsCaratteristiche(macchina)) {
				postiMacchine++;
				return macchineDaCantiere.remove(macchineDaCantiere.indexOf(m));
			}
		}
		return null;
	}

	/**
	 * Metodo utilizzato per aggiungere un prodotto al magazzino
	 * 
	 * @param prodotto il prodotto da aggiungere
	 * @throws CapacitaSuperataException se la lo spazio all'interno del magazzino
	 *                                   non è abbastanza per tenere il prodotto
	 * @author Antonio Della Porta
	 */
	public void aggiungiProdotto(Prodotto prodotto) {
		if ((capacitaMax - prodotto.getSpazioOccupatoTotale()) > 0) {
			for (Prodotto p : prodottiInMagazino) {
				if (p.equalsCaratteristiche(prodotto)) {
					p.sommaDisponibilita(prodotto);
					capacitaMax += prodotto.getSpazioOccupatoTotale();
					return;
				}
			}
			capacitaMax += prodotto.getSpazioOccupatoTotale();
			prodottiInMagazino.add(prodotto);
		} else
			throw new CapacitaSuperataException();
	}

	/**
	 * Metodo utilizzato per prendere un prodotto dal magazzino,se il magazzino non
	 * riesce a soddisfare la richiesta ritorna solamente quello presente al suo
	 * interno
	 * 
	 * @param prodotto il prodotto da prendere
	 * @return il prodotto da restituire
	 * @throws ProdottoNonTrovatoException se il prodotto non è presente all'interno
	 *                                     del magazzino
	 * @author Antonio Della Porta
	 */
	public Prodotto prendiProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for (Prodotto p : prodottiInMagazino) {
			if (p.equalsCaratteristiche(prodotto)) // se il prodotto viene trovato nel magazzino
				if (p.getNumeroPezziDisponibili() >= prodotto.getNumeroPezziDisponibili()) { // e ci sono quantità
																								// necessarie per
																								// soddisfare la
																								// richeista
					capacitaMax -= (p.getSpazioOccupato() * p.getNumeroPezziDisponibili());
					p.scalaProdotto(prodotto.getNumeroPezziDisponibili());
					return prodotto; // ritorni tutta la richiesta
				} else {
					capacitaMax -= p.getSpazioOccupatoTotale();
					prodotto.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili());
					p.scalaProdotto(p.getNumeroPezziDisponibili());
					return prodotto; // altrimenti restituisci solamente la quantità che hai disponibile nel
										// magazzino
				}
		}
		return null;
	}

	/**
	 * Metodo utilizzato per rimuovere un prodotto dal magazzino
	 * @param prodotto il prodotto da rimuovere
	 * @throws ProdottoNonTrovatoException se il prodotto non è presente all'interno del magazzino
	 * @author Antonio Della Porta
	 */
	public void rimuoviProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for (Prodotto p : prodottiInMagazino) {
			if (p.equalsCaratteristiche(prodotto)) {
				int quantita = prodotto.getNumeroPezziDisponibili();
				capacitaMax += p.getSpazioOccupato() * quantita;
				if (p.getNumeroPezziDisponibili() - quantita < 0)
					prodottiInMagazino.remove(prodottiInMagazino.indexOf(prodotto));
				else
					p.setNumeroPezziDisponibili(p.getNumeroPezziDisponibili() - quantita);
				return;
			}
		}
		throw new ProdottoNonTrovatoException();
	}

}
