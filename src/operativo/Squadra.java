package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.OperaioOccupatoException;

/**
 * Questa classe cattura il concetto astratto di una Squadra che lavora nel
 * cantiere. Ogni Squadra è specializzata in un tipo di lavoro possiede un capo
 * squadra un numero di operai specializzati che non devono essere impegnati in
 * altri cantieri
 *
 */
public class Squadra implements Serializable {

	private Quadro capoSquadra;
	private ArrayList<Operaio> operai;

	/**
	 * Istanzia una Squadra vuota con solo il caposquadra
	 * 
	 * @param capoSquadra il caposquadra
	 */
	public Squadra(Quadro capoSquadra) {
		this.capoSquadra = capoSquadra;
		operai = new ArrayList<Operaio>();
	}

	public Quadro getCapoSquadra() {
		return capoSquadra;
	}

	public ArrayList<Operaio> getOperai() {
		return operai;
	}

	public int getNumeroOperai() {
		return operai.size();
	}

	/**
	 * Metodo per aggiungere un operaio alla lista degli operai impegnati nella
	 * squadra. L'operaio inoltre non deve essere assegnato ad alcun altra squadra
	 * su nessun altro cantiere
	 * 
	 * @param operaio
	 * @throws OperaioOccupatoException
	 */
	public void aggiungiOperaio(Operaio operaio) throws OperaioOccupatoException {
		if (!(operaio.isImpegnato() || operai.contains(operaio))) {
			operai.add(operaio);
		} else
			throw new OperaioOccupatoException();
	}

	/**
	 * Metodo utilizzato per rimuovere un operaio da una squadra
	 * 
	 * @param operaio l'operaio da rimuovere
	 * @author Antonio Della Porta
	 */
	public void rimuoviOperaio(Operaio operaio) {
		operaio.liberaDipendente();
		operai.remove(operaio);
	}

	/**
	 * Metodo utilizzato per rimuovere il caposquadra di una squadra
	 * 
	 * @author Antonio Della Porta
	 */
	public void rimuoviCapoSquadra() {
		capoSquadra.liberaDipendente();
		capoSquadra.setCaposquadra(false);
		capoSquadra = null;
	}
	/**
	 * Metodo per assegnare il caposquadra ad una squadra
	 * @param d il caposquadra da assegnare
	 * @author Antonio Della Porta
	 */
	public void assegnaCapoSquadra(Quadro d) {
		if (capoSquadra == null) {
			capoSquadra = d;
			d.setCaposquadra(true);
			d.impegnaDipendente();
		}
	}
	/**
	 * Metodo utilizzato per impegnare una squadra ad un cantiere
	 * 
	 * @author Antonio Della Porta
	 */
	public void assegnaSquadra() {
		capoSquadra.impegnaDipendente();
		capoSquadra.setCaposquadra(true);
		for (Operaio o : operai) {
			o.impegnaDipendente();
		}
	}
	/**
	 * Metodo utilizzato per liberare una squadra da un cantiere
	 * 
	 * @author Antonio Della Porta
	 */
	public void liberaSquadra() {
		capoSquadra.liberaDipendente();
		capoSquadra.setCaposquadra(false);
		for (Operaio o : operai) {
			o.liberaDipendente();
		}
	}

}
