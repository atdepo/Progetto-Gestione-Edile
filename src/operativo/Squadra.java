package operativo;

import java.util.ArrayList;

import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;

/**
 * Questa classe cattura il concetto astratto di una Squadra che lavora nel cantiere.
 * Ogni Squadra è specializzata in un tipo di lavoro 
 * possiede un capo squadra  
 * un numero di operai specializzati che non devono essere impegnati in altri cantieri
 *
 */
public class Squadra {
	
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
	
	/**
	 * Metodo per aggiungere un operaio alla lista degli operaio impegnati nella squadra.
	 * l'operaio inoltre non deve essere assegnato ad alcun altro lavoro
	 * @param operaio
	 */
	public void aggiungiOperaio(Operaio operaio) {
		Dipendente dip=(Dipendente)operaio;
		if(!dip.isImpegnato()) {
			operai.add(operaio);
		}
		else 
			throw new IllegalArgumentException();
	}
	
	
}
