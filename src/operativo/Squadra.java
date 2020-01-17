package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.OperaioOccupatoException;

/**
 * Questa classe cattura il concetto astratto di una Squadra che lavora nel cantiere.
 * Ogni Squadra � specializzata in un tipo di lavoro 
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
	
	public void assegnaSquadra(){
		for(Operaio o:operai) {
			o.impegnaDipendente();
		}
	}
	
	public void liberaSquadra() {
		for(Operaio o:operai) {
			o.liberaDipendente();
		}
	}
	
}
