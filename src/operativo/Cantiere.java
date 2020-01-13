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
	/**
	 * Costruttore Cantiere
	 * @param val
	 * @param langitudine
	 * @param longitudine
	 * @param est
	 */
	public Cantiere(double val,double langitudine,double longitudine,double est) {
		this.valore=val;
		this.posizioneCantiere=new Geolocalizzazione(langitudine, longitudine);
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

	/**
	 * Metodo per aggiungere un Responsabile alla gestione della squadra.
	 * il responsabile inoltre non deve essere abilitato ad esserlo in base al valore del cantiere
	 * @param operaio 
	 * @param resp
	 */
	public void assegnaResponsabile(Responsabile responsabile) {
		
		if(valore>500000) {
			if(responsabile.isDirigente())
				this.responsabile=responsabile;
			else
				throw new IllegalArgumentException();
		}
		else {
			this.responsabile=responsabile;
		}
	}
	
	/**
	 * Metodo per aggiungere squadre alla lista delle squadre impegnate nel cantiere.
	 * @param operaio 
	 * @param squadra
	 */
	public void assegnaSquadra(Squadra squadra) {
		if(squadra.getOperai().size()>0) {
			Squadre.add(squadra);
		}
		else
			throw new IllegalArgumentException();
	}
	/**
	 * 
	 *Questa classe cattura l'astrazione di una coordinata geografica dove è situato il cantiere
	 *@param latitudine la latitudine del punto
	 *@param longitudine la longitudine del punto
	 */
	public class Geolocalizzazione{
		private double longitudine;
		private double latitudine;
		
		public Geolocalizzazione(double latitudine,double longitudine) {
			this.latitudine=latitudine;
			this.longitudine=longitudine;
		}
		
		public double getlongitudine() {
			return longitudine;
		}
		public double getlatitudine() {
			return latitudine;
		}
	}


}
