package amministrativo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import approvviggionamento.Fornitore;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.ProdottoNonTrovatoException;
import dipendenti.Operaio.lavoro;

/**
 * Questa classe cattura il concetto  di un RepartoAmministrativo di un cantiere.
 * un reparto amministrativo ha il compito di assumere i dipendenti e 
 *
 */
public class RepartoAmministrativo implements Serializable {
		
	private static final double STIPENDIO_IMPIEGATO=7.0D;
	private static final double STIPENDIO_OPERAIO_ORARIO=7.0D;
	private static final double STIPENDIO_QUADRO=1780.0D;	
	private static final double STIPENDIO_DIRIGENTE=2750.0D;
	private static final double BONUS_QUADRO=2;
	private static final double BONUS_DIRIGENTE_PER_OPERAIO=5.0D;
	private static final double BONUS_STRAORDINARIO_IMPIEGATO=9.0D;
	private static final double BONUS_STRAORDINARIO_OPERAIO=8.0D;
	
	
	private ArrayList<Dipendente> dipendenti;
	private ArrayList<Fornitore> fornitori;
	
	private int numDirigenti;
	private int numQuadri;
	private int numOperai;
	private int numImpiegati;
	
	private double capitale;
	
	private Magazzino magazzino;
	
	public RepartoAmministrativo() {
		dipendenti=new ArrayList<Dipendente>();
		fornitori= new ArrayList<Fornitore>();
		numDirigenti=0;
		numQuadri=0;
		numOperai=0;
		numImpiegati=0;
		capitale=0;
		magazzino=new Magazzino(0,0);
	}
	
	public RepartoAmministrativo(int capacita,int posti,double capitale) {
		dipendenti=new ArrayList<Dipendente>();
		fornitori= new ArrayList<Fornitore>();
		numDirigenti=0;
		numQuadri=0;
		numOperai=0;
		numImpiegati=0;
		this.capitale=capitale;
		magazzino=new Magazzino(capacita,posti);
	}
	
	
	public void assumiDirigente(String nome, String cognome, int eta) {
		numDirigenti++;
		Dirigente toAdd=new Dirigente(nome, cognome, eta, Integer.toString(numDirigenti));
		toAdd.setContratto(STIPENDIO_DIRIGENTE, BONUS_DIRIGENTE_PER_OPERAIO);
		dipendenti.add(toAdd);
	}
	
	public void assumiQuadro(String nome, String cognome, int eta) {
		numQuadri++;
		Quadro toAdd =new Quadro(nome, cognome, eta, Integer.toString(numQuadri));
		toAdd.setContratto(STIPENDIO_QUADRO, BONUS_QUADRO);
		dipendenti.add(toAdd);
	}
	
	public void assumiOperaio(String nome, String cognome, int eta,lavoro specializzazione,int ore) {
		numOperai++;
		Operaio toAdd=new Operaio(nome, cognome, eta, Integer.toString(numOperai), specializzazione, ore);
		toAdd.setContratto(STIPENDIO_OPERAIO_ORARIO,BONUS_STRAORDINARIO_OPERAIO);
		dipendenti.add(toAdd);
	}
	
	public void assumiOperaio(String nome, String cognome, int eta,lavoro specializzazione) {
		numOperai++;
		Operaio toAdd=new Operaio(nome, cognome, eta, Integer.toString(numOperai), specializzazione);
		toAdd.setContratto(STIPENDIO_OPERAIO_ORARIO,BONUS_STRAORDINARIO_OPERAIO);
		dipendenti.add(new Operaio(nome, cognome, eta, Integer.toString(numOperai), specializzazione));
	}
	
	public void assumiImpiegato(String nome, String cognome, int eta,int giorni) {
		numImpiegati++;
		Impiegato toAdd = new Impiegato(nome, cognome, eta, Integer.toString(numImpiegati),giorni);
		toAdd.setContratto(STIPENDIO_IMPIEGATO, BONUS_STRAORDINARIO_OPERAIO);
		dipendenti.add(toAdd);
	}
	
	public void assumiImpiegato(String nome, String cognome, int eta) {
		numImpiegati++;
		Impiegato toAdd = new Impiegato(nome, cognome, eta, Integer.toString(numImpiegati));
		toAdd.setContratto(STIPENDIO_IMPIEGATO, BONUS_STRAORDINARIO_OPERAIO);
		dipendenti.add(new Impiegato(nome, cognome, eta, Integer.toString(numImpiegati)));
	}
	
	public double pagaDirigente(Dipendente d) {
		Dirigente toPay=(Dirigente)d;
		Contratto c=toPay.getContratto();
		if(toPay.isImpegnato())
			return c.getStipendio()+c.getBonus()*toPay.getNumeroOperai();
		return c.getStipendio();
	}
	
	public double pagaImpiegato(Impiegato i) {
		Contratto c=i.getContratto();
		double saldo=i.getGiorniLavorati()*c.getStipendio()+i.getGiorniStraordinario()*c.getBonus();
		i.resetOre();
		return saldo;
	}
	
	public double pagaOperaio(Dipendente d) {
		Contratto c=d.getContratto();
		Operaio i= (Operaio)d;
		double saldo=i.getOre_lavorate()*c.getStipendio()+i.getOre_straordinario()*c.getBonus();
		i.resetOre();
		return saldo;
		
	}	
	
	public double pagaQuadro(Dipendente d) {
		Contratto c=d.getContratto();
		Quadro i= (Quadro)d;
		
		double saldo=c.getStipendio();
		if(i.isCaposquadra()||i.isResponsabile())
			return saldo+((saldo/100)*c.getBonus());
		
		return saldo;
	}
	
	public void pagamentoAssunti() {
		double speseDipendentiAzienda=0;
		for(Dipendente d:dipendenti) {	
			
			if(Dipendente.isDirigente(d)) {
				speseDipendentiAzienda+=pagaDirigente(d);
			}
			
			else if(Dipendente.isImpiegato(d)) {
				//speseDipendentiAzienda+=pagaImpiegato(d);
			}
			
			else if(Dipendente.isOperaio(d)) {
				speseDipendentiAzienda+=pagaOperaio(d);
			}
			
			else if(Dipendente.isQuadro(d)) {
				speseDipendentiAzienda+=pagaQuadro(d);
			}		
		}
		capitale-=speseDipendentiAzienda;
	}
	
	public ArrayList<Dipendente> getDipendenti(){
		return (ArrayList<Dipendente>) dipendenti.clone();
	}
	
	public ArrayList<Fornitore> getFornitori(){
		return (ArrayList<Fornitore>) fornitori.clone();
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
	
	public Dipendente licenziamentoDipendente(Dipendente daRimuovere) {
		return dipendenti.remove(dipendenti.indexOf(daRimuovere));
	}
	
	public void aggiungiFornitore(Fornitore fornitore) {
		if(!fornitori.contains(fornitore)) {
			fornitori.add(fornitore);
		}
	}
	
	public void removeFornitore(Fornitore fornitore) {
		if(fornitori.contains(fornitore)) {
			fornitori.remove(fornitore);
		}
		else
			throw new IllegalArgumentException();
	}
	
	public Prodotto compraProdotto(Prodotto prodotto) throws ProdottoNonTrovatoException {
		for(Fornitore f: fornitori) {
			Prodotto daComprare=f.compraProdotto(prodotto);
			if(daComprare!=null) { //se sono riuscito a trovare il prodotto specificato in quantità necessaria
				capitale-=daComprare.getNumeroPezziDisponibili()*daComprare.getPrezzo();
				return daComprare; //ritorno il prodotto
			
			}
		}	
		throw new ProdottoNonTrovatoException();
	}
	
	public void aggiungiProdottoAlMagazzino(Prodotto prodotto) {
		magazzino.aggiungiProdotto(prodotto);
	}
	
	public Prodotto getProdotto(Prodotto prodotto){ 
		try {
			Prodotto daRestituire= magazzino.prendiProdotto(prodotto.clone());
			if(daRestituire!=null) {
				if(daRestituire.getNumeroPezziDisponibili()==prodotto.getNumeroPezziDisponibili()) { // se il magazzino è riuscito a soddisfarre la richiesta 
					return daRestituire; //ritorno il prodotto richiesto
				}
				else {
					int quantita_rimanente=prodotto.getNumeroPezziDisponibili()-daRestituire.getNumeroPezziDisponibili();
					Prodotto tmp=prodotto.clone(); // creo un "dummy" del prodotto modificandogli solamente il numero di elementi
					tmp.setNumeroPezziDisponibili(quantita_rimanente);// togliamo i pezzi gia ottenuti dal magazzino
					Prodotto p= compraProdotto(tmp);
					return daRestituire.sommaDisponibilita(p);
				}
			}
			else
			return compraProdotto(prodotto);

		} catch (ProdottoNonTrovatoException e) {
			System.out.println("qua?");
			return null;
			}
		
	}
	
}

