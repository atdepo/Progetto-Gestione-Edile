package amministrativo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.ProdottoNonTrovatoException;
import dipendenti.Operaio.lavoro;

/**
 * Questa classe cattura il concetto di un RepartoAmministrativo di un cantiere.
 * un reparto amministrativo ha il compito di assumere i dipendenti, di pagarli
 * e di gestire le risorse nel magazzino, comprando eventualmente materiali
 * necessari dai fornitori
 * 
 * @author Antonio Della Porta
 */
public class RepartoAmministrativo implements Serializable {

	private ArrayList<Dipendente> dipendenti;
	private ArrayList<Fornitore> fornitori;
	private Magazzino magazzino;

	private double capitale;

	/**
	 * Istanzia un reparto amministrativo con un capitade di partenza ed un
	 * magazzino
	 * 
	 * @param capacita capacità del magazzino
	 * @param posti    posti per le macchine da cantiere nel magazzino
	 * @param capitale capitale di partenza
	 * @author Antonio Della Porta
	 */
	public RepartoAmministrativo(int capacita, int posti, double capitale) {
		dipendenti = new ArrayList<Dipendente>();
		fornitori = new ArrayList<Fornitore>();

		this.capitale = capitale;
		magazzino = new Magazzino(capacita, posti);
	}

	/**
	 * Assumo un dipendnete e lo inserisco nella lista dei dipendenti dell'azienda
	 * 
	 * @param toAdd il dipendente da aggiungere
	 * @author Antonio Della Porta
	 */
	public void assumiDipendente(Dipendente toAdd) {
		if (toAdd != null)
			dipendenti.add(toAdd);
	}

	/**
	 * Licenzia il dipendente controllando prima se costui è impegnato
	 * 
	 * @param daRimuovere il dipendente da rimuovere
	 * @throws IllegalArgumentException se il dipendente risulta impegnato
	 * @author Antonio Della Porta
	 */
	public void licenziamentoDipendente(Dipendente daRimuovere) {
		if (!daRimuovere.isImpegnato()) {
			dipendenti.remove(dipendenti.indexOf(daRimuovere));
		} else if (Dipendente.isImpiegato(daRimuovere)) {
			dipendenti.remove(dipendenti.indexOf(daRimuovere));
		} else
			throw new IllegalArgumentException("Eliminare prima il dipendente dal suo incarico");
	}

	/**
	 * Metodo usato per pagare un dipendente
	 * 
	 * @param daPagare il dipendente da pagare
	 * @return lo stipendio dovuto
	 * @author Antonio Della Porta
	 */
	public double pagaDipendente(Dipendente daPagare) {
		double stipendio = daPagare.getPaga();
		effettuaSpesa(stipendio);
		daPagare.setPagato();
		return stipendio;
	}

	/**
	 * Metodo utilizzato per pagare tutti i dipendenti non pagati
	 * 
	 * @return il totale degli stipendi
	 * @author Antonio Della Porta
	 */
	public double pagaDipendenti() {
		double stipendi = 0.0D;
		for (Dipendente d : dipendenti) {
			if (!d.isPagato())
				stipendi += pagaDipendente(d);
		}
		return stipendi;
	}

	/**
	 * Metodo utilizzato per resettare lo stato del pagamento dei dipendenti
	 * 
	 * @author Antonio Della Porta
	 */
	public void resetStatoPagamento() {
		for (Dipendente d : dipendenti) {
			d.resetPagamento();
		}
	}

	/**
	 * Metodo utilizzato per effettuare una spesa di qualsiasi natura
	 * 
	 * @param spesa la spesa da effettuare
	 * @throws IllegalArgumentException se la spesa da effettuare farebbe andare in
	 *                                  negativo il conto dell'azienda
	 * @author Antonio Della Porta
	 */
	public void effettuaSpesa(double spesa) {
		if (capitale - spesa < 0) {
			throw new IllegalArgumentException("Non si può effettuare questa spesa");
		} else
			capitale -= spesa;
	}

	/**
	 * Metodo utilizzato per incassare soldi dai committenti
	 * 
	 * @param aumento i soldi da incassare
	 * @author Antonio Della Porta
	 */
	public void aumentaCapitale(double aumento) {
		capitale += aumento;
	}

	public ArrayList<Dipendente> getDipendenti() {
		return dipendenti;
	}

	public ArrayList<Fornitore> getFornitori() {
		return fornitori;
	}

	public Magazzino getMagazzino() {
		return magazzino;
	}

	public int totale_Dipendenti() {
		return dipendenti.size();
	}

	public int getNumDirigenti() {
		int count = 0;
		for (Dipendente d : dipendenti) {
			if (Dipendente.isDirigente(d))
				count++;
		}
		return count;
	}

	public int getNumQuadri() {
		int count = 0;
		for (Dipendente d : dipendenti) {
			if (Dipendente.isQuadro(d))
				count++;
		}
		return count;
	}

	public int getNumOperai() {
		int count = 0;
		for (Dipendente d : dipendenti) {
			if (Dipendente.isOperaio(d))
				count++;
		}
		return count;
	}

	public int getNumImpiegati() {
		int count = 0;
		for (Dipendente d : dipendenti) {
			if (Dipendente.isImpiegato(d))
				count++;
		}
		return count;
	}

	public double getCapitale() {
		return capitale;
	}

	/**
	 * Metodo utilizzato per aggiungere un fornitore alla lista dei fornitori
	 * 
	 * @param fornitore il fornitore da aggiungere
	 * @author Antonio Della Porta
	 */
	public void aggiungiFornitore(Fornitore fornitore) {
		if (!fornitori.contains(fornitore)) {
			fornitori.add(fornitore);
		}
	}

	/**
	 * Metodo utilizzato per rimuovere un fornitore alla lista dei fornitori
	 * 
	 * @param fornitore
	 * @throws IllegalArgumentException se il fornitore non è presente nella lista
	 *                                  dei fornitori
	 * @author Antonio Della Porta
	 */
	public void removeFornitore(Fornitore fornitore) {
		if (fornitori.contains(fornitore)) {
			fornitori.remove(fornitore);
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Metodo utilizzato per comprare un prodotto dai Fornitori
	 * 
	 * @param prodotto il prodotto da comprare
	 * @return il prodotto comprato dal Fornitore
	 * @throws ProdottoNonTrovatoException Se il prodotto non è stato trovato da
	 *                                     nessun fornitore
	 * @author Antonio Della Porta
	 */
	public Prodotto compraProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for (Fornitore f : fornitori) {
			Prodotto daComprare = f.compraProdotto(prodotto);
			if (daComprare != null) { // se sono riuscito a trovare il prodotto specificato in quantità necessaria
				capitale -= daComprare.getNumeroPezziDisponibili() * daComprare.getPrezzo();
				return daComprare; // ritorno il prodotto

			}
		}
		throw new ProdottoNonTrovatoException();
	}

	/**
	 * Metodo utilizzato per aggiungewre un prodotto al magazzino
	 * 
	 * @param prodotto il prodotto da aggiungere
	 * @author Antonio Della Porta
	 */
	public void aggiungiProdottoAlMagazzino(Prodotto prodotto) {
		magazzino.aggiungiProdotto(prodotto);
	}

	/**
	 * Metodo utilizzato per restituire un prodotto che viene cercato prima nel
	 * magazzino, se la quantità presente risulta adeguata per soddisfare la
	 * richiesta viene ritornato il prodotto preso dal magazzino, viceversa la
	 * quantità rimanente per soddisfare la richiesta viene comprata dai fornitori
	 * 
	 * @param prodotto il prodotto da acquistare
	 * @return il prodotto acquistato
	 * @throws ProdottoNonTrovatoException se nessuno dei fornitori è riuscito
	 *                                     eventualmente a soddisfare la richiesta
	 * @author Antonio Della Porta
	 */
	public Prodotto getProdotto(Prodotto prodotto) {
		try {
			Prodotto daRestituire = magazzino.prendiProdotto(prodotto.clone());
			if (daRestituire != null) {
				if (daRestituire.getNumeroPezziDisponibili() == prodotto.getNumeroPezziDisponibili()) { // se il
																										// magazzino è
																										// riuscito a
																										// soddisfarre
																										// la richiesta
					return daRestituire; // ritorno il prodotto richiesto
				} else {
					int quantita_rimanente = prodotto.getNumeroPezziDisponibili()
							- daRestituire.getNumeroPezziDisponibili();
					Prodotto tmp = prodotto.clone(); // creo un "dummy" del prodotto modificandogli solamente il numero
														// di elementi
					tmp.setNumeroPezziDisponibili(quantita_rimanente);// togliamo i pezzi gia ottenuti dal magazzino
					Prodotto p = compraProdotto(tmp);
					return daRestituire.sommaDisponibilita(p);
				}
			} else
				return compraProdotto(prodotto);

		} catch (ProdottoNonTrovatoException e) {
			return null;
		}
	}

	/**
	 * Metodo utilizzato per aggiungere una macchina al magazzino
	 * 
	 * @param macchina la macchina da aggiungere
	 * @author Antonio Della Porta
	 */
	public void aggiungiMacchinaAlMagazzino(MacchineDaCantiere macchina) {
		magazzino.aggiungiMacchina(macchina);
	}

	/**
	 * Metodo utilizzato per comprare una macchina dai fornitori
	 * 
	 * @param m la macchina da comprare
	 * @return la macchina richiesta
	 * @throws IllegalArgumentException se nessun fornitore possiede la macchina
	 *                                  richiesta da comprare
	 * @author Antonio Della Porta
	 */
	public MacchineDaCantiere compraMacchina(MacchineDaCantiere m) {
		for (Fornitore f : fornitori) {
			MacchineDaCantiere daComprare = f.compraMacchina(m);
			if (daComprare != null) { // se sono riuscito a trovare il prodotto specificato in quantità necessaria
				capitale -= daComprare.getPrezzo();
				return daComprare; // ritorno il prodotto
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Metodo utilizzato per restituire una macchina che viene prima cercata nel
	 * magazzino, se non viene trovata viene comprata da uno dei fornitori
	 * 
	 * @param m la macchina da comprare
	 * @return la macchina richiesta
	 * @author Antonio Della Porta
	 */
	public MacchineDaCantiere getMacchina(MacchineDaCantiere m) {
		MacchineDaCantiere daRestituire = magazzino.prendiMacchina(m);
		if (daRestituire != null)
			return daRestituire;
		else
			return compraMacchina(m);
	}
}
