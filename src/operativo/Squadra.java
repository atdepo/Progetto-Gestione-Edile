package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.OperaioOccupatoException;

/**
 * Questa classe cattura il concetto astratto di una Squadra che lavora nel cantiere.
 * Ogni Squadra è specializzata in un tipo di lavoro 
 * possiede un capo squadra  
 * un numero di operai specializzati che non devono essere impegnati in altri cantieri
 *
 */
public class Squadra implements Serializable{
	
	private Quadro capoSquadra;
	private ArrayList<Operaio> operai;
	
	/**
	 * Costruttore di una Squadra
	 * @param capoSquadra
	 */
	public Squadra (Quadro capoSquadra) {
		this.capoSquadra=capoSquadra;
		operai=new ArrayList<Operaio>();
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
	 * Metodo per aggiungere un operaio alla lista degli operaio impegnati nella squadra.
	 * l'operaio inoltre non deve essere assegnato ad alcun altro lavoro
	 * @param operaio
	 * @throws OperaioOccupatoException 
	 */
	public void aggiungiOperaio(Operaio operaio) throws OperaioOccupatoException {
		if(!(operaio.isImpegnato()||operai.contains(operaio))) {
			operai.add(operaio);
		}
		else 
			throw new OperaioOccupatoException();
	}
	
	public void rimuoviOperaio(Operaio operaio) {
		operaio.liberaDipendente();
		operai.remove(operaio);
	}
	
	public void rimuoviCapoSquadra() {
		capoSquadra.liberaDipendente();
		capoSquadra=null;
	}
	
	public void assegnaCapoSquadra(Quadro d) {
		if(capoSquadra==null) {
			capoSquadra=d;
			d.impegnaDipendente();
		}
		else
			throw new IllegalArgumentException("Caposquadra già assegnato, rimuoverlo prima di assegnarne uno nuovo");
	}
	public void assegnaSquadra(){
		capoSquadra.impegnaDipendente();
		for(Operaio o:operai) {
			o.impegnaDipendente();
		}
	}
	
	public void liberaSquadra() {
		capoSquadra.liberaDipendente();
		for(Operaio o:operai) {
			o.liberaDipendente();
		}
	}
	

}
