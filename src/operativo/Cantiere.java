package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Responsabile;
/**
 * Questa classe cattura il concetto  di un Cantiere.
 * Ogni Cantiere possiede un Resposabile che gestisce il cantiere 
 * delle Squadre e un valore di cantiere
 * ha attributi che definiscono l'estenzione in m^2 del cantiere e la posizione geografica
 * 
 *
 */
public class Cantiere implements Serializable   {
	private Responsabile responsabile;
	private ArrayList<Squadra> Squadre;
	private ArrayList<Prodotto> listaMaterialiNecessari;
	private Geolocalizzazione posizioneCantiere;
	private double estensione;
	private double valore;
	
	/**
	 * Istanzia un cantiere
	 * @param valore valore del cantiere
	 * @param latitudine latitudine della posizione del cantiere
	 * @param longitudine longitudine della posizione del cantiere
	 * @param estensione estensione in m^2 del cantiere
	 */
	public Cantiere(double valore,double latitudine,double longitudine,double estensione) {
		this.valore=valore;
		this.posizioneCantiere=new Geolocalizzazione(latitudine, longitudine);
		this.estensione=estensione;
		Squadre=new ArrayList<Squadra>();
		listaMaterialiNecessari= new ArrayList<Prodotto>();
	}
	
	/**
	 * Istanzia un cantiere assegnandogli un responsabile
	 * 
	 * @param valore valore del cantiere
	 * @param latitudine latitudine della posizione del cantiere
	 * @param longitudine longitudine della posizione del cantiere
	 * @param estensione estensione in m^2 del cantiere
	 * @param responsabile il responsabile del cantiere
	 */
	public Cantiere(double valore,double latitudine,double longitudine,double estensione,Responsabile responsabile) {
		this.valore=valore;
		this.posizioneCantiere=new Geolocalizzazione(latitudine, longitudine);
		this.estensione=estensione;
		Squadre=new ArrayList<Squadra>();
		listaMaterialiNecessari= new ArrayList<Prodotto>();
		Dipendente d=(Dipendente)responsabile;
		if(d.isImpegnato())
			throw new IllegalArgumentException("Responsabile già impegnato");		
		if(valore>500000)
			if(Dipendente.isDirigente((Dipendente)responsabile))
				this.responsabile=responsabile;
			else
				throw new IllegalArgumentException();	
		this.responsabile=responsabile;
		
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
	
	public void assegnaMateriali(ArrayList<Prodotto> materiali) {
		materiali.addAll(materiali);
	}

	public Geolocalizzazione getPosizioneCantiere() {
		return posizioneCantiere;
	}

	public double getEstensione() {
		return estensione;
	}

	/**
	 * Metodo per aggiungere un Responsabile alla gestione della squadra.
	 * il responsabile inoltre non deve essere abilitato ad esserlo in base al valore del cantiere
	 * @param responsabile il responsabile da assegnare
	 */
	public void assegnaResponsabile(Responsabile responsabile) {
		
		if(valore>500000) {
			if(Dipendente.isDirigente((Dipendente)responsabile))
				this.responsabile=responsabile;
			else
				throw new IllegalArgumentException();
			this.responsabile=responsabile;
		}
	}
	
	/**
	 * Metodo per aggiungere squadre alla lista delle squadre impegnate nel cantiere.
	 * @param squadra la squadra da aggiungere
	 */
	public void assegnaSquadra(Squadra squadra) {
		if(squadra.getOperai().size()>0) {
			Squadre.add(squadra);
			squadra.assegnaSquadra();
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
		
		public String toString() {
			return "[latitudine= "+ latitudine + ",longitudine="+longitudine+"]";
		}
	}
}
