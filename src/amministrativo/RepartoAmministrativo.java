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

	private static final double STIPENDIO_IMPIEGATO = 50.0D;
	private static final double STIPENDIO_OPERAIO_ORARIO = 7.0D;
	private static final double STIPENDIO_QUADRO = 1780.0D;
	private static final double STIPENDIO_DIRIGENTE = 2750.0D;
	private static final double BONUS_QUADRO = 6;
	private static final double BONUS_DIRIGENTE_PER_OPERAIO = 5.0D;
	private static final double BONUS_STRAORDINARIO_IMPIEGATO = 80.0D;
	private static final double BONUS_STRAORDINARIO_OPERAIO = 8.0D;

	private ArrayList<Dipendente> dipendenti;
	private ArrayList<Fornitore> fornitori;
	private Magazzino magazzino;

	private int numDirigenti;
	private int numQuadri;
	private int numOperai;
	private int numImpiegati;

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
		numDirigenti = 0;
		numQuadri = 0;
		numOperai = 0;
		numImpiegati = 0;
		this.capitale = capitale;
		magazzino = new Magazzino(capacita, posti);
	}

	/**
	 * Si assume un dirigente e lo si inserisce nella lista dei dipendenti del
	 * reparto amministrativo
	 * 
	 * @param nome    Nome del Dirigente
	 * @param cognome Cognome del Dirigente
	 * @param eta     età del dirigente
	 * @author Antonio Della Porta
	 */
	public void assumiDirigente(String nome, String cognome, int eta) {
		numDirigenti++;
		Dirigente toAdd = new Dirigente(nome, cognome, eta, Integer.toString(numDirigenti));
		toAdd.setContratto(STIPENDIO_DIRIGENTE, BONUS_DIRIGENTE_PER_OPERAIO);
		dipendenti.add(toAdd);
	}

	/**
	 * Si assume un Quadro e lo si inserisce nella lista dei dipendenti del reparto
	 * amministrativo
	 * 
	 * @param nome    nome del Quadro
	 * @param cognome cognome del Quadro
	 * @param eta     età del quadro
	 * @author Antonio Della Porta
	 */
	public void assumiQuadro(String nome, String cognome, int eta) {
		numQuadri++;
		Quadro toAdd = new Quadro(nome, cognome, eta, Integer.toString(numQuadri));
		toAdd.setContratto(STIPENDIO_QUADRO, BONUS_QUADRO);
		dipendenti.add(toAdd);
	}

	/**
	 * Si assume un operaio con un numero di ore prestabilite da lavorare e lo si
	 * assegna al reparto amministrativo
	 * 
	 * @param nome             nome dell'Operaio
	 * @param cognome          cognome dell'Operaio
	 * @param eta              eta dell'Operaio
	 * @param specializzazione specializzazione dell'operaio che puo essere:
	 *                         <b>MURATORE ELETTRICISTA IDRAULICO PIASTRELLISTA</b>
	 * @param ore              ore da lavorare
	 * @throws IllegalArgumentException se il numero di ore è maggiore di 70
	 * @author Antonio Della Porta
	 */
	public void assumiOperaio(String nome, String cognome, int eta, lavoro specializzazione, int ore) {
		numOperai++;
		Operaio toAdd = new Operaio(nome, cognome, eta, Integer.toString(numOperai), specializzazione, ore);
		toAdd.setContratto(STIPENDIO_OPERAIO_ORARIO, BONUS_STRAORDINARIO_OPERAIO);
		dipendenti.add(toAdd);
	}

	/**
	 * Si assume un operaio con un numero di ore di default da lavorare e lo si
	 * assegna al reparto amministrativo
	 * 
	 * @param nome             nome dell'Operaio
	 * @param cognome          cognome dell'Operaio
	 * @param eta              eta dell'Operaio
	 * @param specializzazione specializzazione dell'operaio che puo essere:
	 *                         <b>MURATORE ELETTRICISTA IDRAULICO PIASTRELLISTA</b>
	 * @author Antonio Della Porta
	 */
	public void assumiOperaio(String nome, String cognome, int eta, lavoro specializzazione) {
		numOperai++;
		Operaio toAdd = new Operaio(nome, cognome, eta, Integer.toString(numOperai), specializzazione);
		toAdd.setContratto(STIPENDIO_OPERAIO_ORARIO, BONUS_STRAORDINARIO_OPERAIO);
		dipendenti.add(toAdd);
	}

	/**
	 * Si assume un impiegato con un numero di giorni da lavorare prestabilito e lo
	 * si assegna al reparto amministrativo
	 * 
	 * @param nome    nome dell'Impiegato
	 * @param cognome cognome dell'Impiegato
	 * @param eta     età dell'Impiegato
	 * @param giorni  giorni da lavorare
	 * @throws IllegalArgumentException se il numero di giorni è maggiore di 7
	 * @author Antonio Della Porta
	 */
	public void assumiImpiegato(String nome, String cognome, int eta, int giorni) {
		numImpiegati++;
		Impiegato toAdd = new Impiegato(nome, cognome, eta, Integer.toString(numImpiegati), giorni);
		toAdd.setContratto(STIPENDIO_IMPIEGATO, BONUS_STRAORDINARIO_IMPIEGATO);
		toAdd.impegnaDipendente();
		dipendenti.add(toAdd);
	}

	/**
	 * Si assume un impiegato con un numero di giorni da lavorare di default e lo si
	 * assegna al reparto amministrativo
	 * 
	 * @param nome    nome dell'Impiegato
	 * @param cognome cognome dell'Impiegato
	 * @param eta     età dell'Impiegato
	 * @author Antonio Della Porta
	 */
	public void assumiImpiegato(String nome, String cognome, int eta) {
		numImpiegati++;
		Impiegato toAdd = new Impiegato(nome, cognome, eta, Integer.toString(numImpiegati));
		toAdd.setContratto(STIPENDIO_IMPIEGATO, BONUS_STRAORDINARIO_IMPIEGATO);
		toAdd.impegnaDipendente();
		dipendenti.add(toAdd);
	}

	/**
	 * Metodo di servizio privato usato per pagare un dirigente
	 * 
	 * @param d il Dirigente da pagare
	 * @return lo stipendio da corrispondere al Dirigente
	 * @author Antonio Della Porta
	 */
	private double pagaDirigente(Dipendente d) {
		Dirigente toPay = (Dirigente) d;
		Contratto c = toPay.getContratto();
		d.setPagato();
		if (toPay.isImpegnato())
			return c.getStipendio() + c.getBonus() * toPay.getNumeroOperai();
		return c.getStipendio();
	}

	/**
	 * Metodo di servizio privato usato per pagare un impiegato
	 * 
	 * @param d l'impiegato da pagare
	 * @return lo stipendio dovuto
	 * @author Antonio Della Porta
	 */
	private double pagaImpiegato(Dipendente d) {
		Contratto c = d.getContratto();
		d.setPagato();
		Impiegato i = (Impiegato) d;
		double saldo = 4 * i.getGiorniLavorati() * c.getStipendio() + i.getGiorniStraordinario() * c.getBonus();
		i.resetOre();
		return saldo;
	}

	/**
	 * Metodo di servizio privato usato per pagare un operaio
	 * 
	 * @param d l'operaio da pagare
	 * @return lo stipendio dovuto
	 * @author Antonio Della Porta
	 */
	private double pagaOperaio(Dipendente d) {
		Contratto c = d.getContratto();
		Operaio i = (Operaio) d;
		d.setPagato();
		double saldo = 4 * i.getOreLavorate() * c.getStipendio() + i.getOreStraordinario() * c.getBonus();
		i.resetOre();
		return saldo;

	}

	/**
	 * Metodo di servizio privato usato per pagare un quadro
	 * 
	 * @param d il quadro da pagare
	 * @return lo stipendio dovuto
	 * @author Antonio Della Porta
	 */
	private double pagaQuadro(Dipendente d) {
		Contratto c = d.getContratto();
		Quadro i = (Quadro) d;
		d.setPagato();
		double saldo = c.getStipendio();
		if (i.isCaposquadra() || i.isResponsabile())
			return saldo + ((saldo / 100) * c.getBonus());

		return saldo;
	}

	/**
	 * Metodo utilizzato per il pagamento di tutti dipendenti assunti utilizzando
	 * gli appositi metodi di servizio
	 * 
	 * @author Antonio Della Porta
	 */
	public void pagamentoAssunti() {
		double speseDipendentiAzienda = 0;
		for (Dipendente d : dipendenti) {

			if (Dipendente.isDirigente(d)) {
				speseDipendentiAzienda += pagaDirigente(d);
			}

			else if (Dipendente.isImpiegato(d)) {
				speseDipendentiAzienda += pagaImpiegato(d);

			}

			else if (Dipendente.isOperaio(d)) {
				speseDipendentiAzienda += pagaOperaio(d);

			}

			else if (Dipendente.isQuadro(d)) {
				speseDipendentiAzienda += pagaQuadro(d);

			}
		}
		System.out.println("Spese Totali Pagamento:" + speseDipendentiAzienda);
		effettuaSpesa(speseDipendentiAzienda);
		System.out.println("Capitale attuale:" + capitale);
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
		return numDirigenti;
	}

	public int getNumQuadri() {
		return numQuadri;
	}

	public int getNumOperai() {
		return numOperai;
	}

	public int getNumImpiegati() {
		return numImpiegati;
	}

	public double getCapitale() {
		return capitale;
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
			if (Dipendente.isDirigente(daRimuovere))
				numDirigenti--;
			if (Dipendente.isOperaio(daRimuovere))
				numOperai--;
			if (Dipendente.isQuadro(daRimuovere))
				numQuadri--;
			int index = dipendenti.indexOf(daRimuovere);
			dipendenti.remove(index);
		} else if (Dipendente.isImpiegato(daRimuovere)) {
			int index = dipendenti.indexOf(daRimuovere);
			dipendenti.remove(index);
		} else
			throw new IllegalArgumentException("Eliminare prima il dipendente dal suo incarico");
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
