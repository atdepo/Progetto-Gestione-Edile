package operativo;

import java.util.ArrayList;

import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;

/**
 * Questa classe cattura il concetto astratto di una Squadra che lavora nel cantiere.
 * Ogni Squadra possiede un capo squadra e un numero di operai che non devono essere impegnati in altri cantieri
 * @author TheDMG8
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
	 * @param ope
	 */
	public void aggiungiOperaio(Operaio ope) {
		Dipendente dip=(Dipendente)ope;
		if(!dip.isImpegnato()) {
			operai.add(ope);
		}
		else 
			throw new IllegalArgumentException();
		
	}
	
	
}
