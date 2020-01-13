package operativo;

import java.util.ArrayList;

import dipendenti.Dirigente;
import dipendenti.Responsabile;
/**
 * Questa classe cattura il concetto  di un Cantiere.
 * Ogni Cantiere possiede un Resposabile che gestisce il cantiere 
 * delle Squadre e un valore di cantiere
 * ha attributi che definiscono l'estenzione in m^2 del cantiere e la posizione geografica
 * 
 *
 */
public class Cantiere {
	private Responsabile responsabile;
	
	private double valore;
	private ArrayList<Squadra> Squadre;
	
	private Geolocalizzazione posizioneCantiere;
	private double estenzione;
	
	public Cantiere(double val,Geolocalizzazione posCant,double est) {
		this.valore=val;
		this.posizioneCantiere=posCant;
		this.estenzione=est;
		Squadre=new ArrayList<Squadra>();
	
	}
	
	public Responsabile getResponsabile() {
		return responsabile;
	}

	public double getValore() {
		return valore;
	}

	public ArrayList<Squadra> getSquadre() {
		return Squadre;
	}

	public Geolocalizzazione getPosizioneCantiere() {
		return posizioneCantiere;
	}

	public double getEstenzione() {
		return estenzione;
	}

	
	public void assegnaResponsabile(Responsabile resp) {
		if(valore>500000) {
			if(resp instanceof Dirigente)
				responsabile=resp;
			else
				throw new IllegalArgumentException();
		}
		else {
			responsabile=resp;
		}
	}
	
	public void assegnaSquadra(Squadra sq) {
		if(sq.getOperai().size()>0) {
			
		}
	}



	private class Geolocalizzazione{
		
	}


}
