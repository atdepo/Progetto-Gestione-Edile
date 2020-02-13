package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Quadro;
import dipendenti.Responsabile;

/**
 * Questa classe cattura il concetto di un Cantiere. Ogni Cantiere possiede un
 * Resposabile che gestisce il cantiere delle Squadre e un valore di cantiere ha
 * attributi che definiscono l'estenzione in m^2 del cantiere e la posizione
 * geografica
 * 
 *
 */
public class Cantiere implements Serializable {
	private Responsabile responsabile;
	private ArrayList<Squadra> squadre;
	private ArrayList<Prodotto> listaMaterialiDisponibili;
	private ArrayList<MacchineDaCantiere> macchineImpiegate;
	private Geolocalizzazione posizioneCantiere;
	private double estensione;
	private double valore;

	/**
	 * Istanzia un cantiere assegnandogli un responsabile
	 * 
	 * @param valore       valore del cantiere
	 * @param latitudine   latitudine della posizione del cantiere
	 * @param longitudine  longitudine della posizione del cantiere
	 * @param estensione   estensione in m^2 del cantiere
	 * @param responsabile il responsabile del cantiere
	 */
	public Cantiere(double valore, double latitudine, double longitudine, double estensione,
			Responsabile responsabile) {
		this.valore = valore;
		this.posizioneCantiere = new Geolocalizzazione(latitudine, longitudine);
		this.estensione = estensione;
		squadre = new ArrayList<Squadra>();
		listaMaterialiDisponibili = new ArrayList<Prodotto>();
		macchineImpiegate = new ArrayList<MacchineDaCantiere>();
		assegnaResponsabile(responsabile);
	}

	public Responsabile getResponsabile() {
		return responsabile;
	}

	public double getValore() {
		return valore;
	}

	public ArrayList<Squadra> getSquadre() {
		return squadre;
	}

	public ArrayList<Prodotto> getMaterialiDisponibili() {
		return listaMaterialiDisponibili;
	}

	public void assegnaMateriali(ArrayList<Prodotto> materiali) {// uno solo
		listaMaterialiDisponibili.addAll(materiali);
	}

	public void assegnaMateriale(Prodotto toAdd) {
		for (Prodotto p : listaMaterialiDisponibili) {
			if (p.equalsCaratteristiche(toAdd)) {
				p.sommaDisponibilita(toAdd);
				return;
			}
		}
		listaMaterialiDisponibili.add(toAdd);
	}

	public void rimuoviMateriale(Prodotto toRemove) {

		listaMaterialiDisponibili.remove(toRemove);
	}

	public void rimuoviMacchina(MacchineDaCantiere toRemove) {

		macchineImpiegate.remove(toRemove);

	}

	public ArrayList<MacchineDaCantiere> getMacchineImpiegate() {
		return macchineImpiegate;
	}

	public void assegnaMacchina(MacchineDaCantiere macchina) {
		macchineImpiegate.add(macchina);
	}

	public int getNumeroOperaiCantiere() {
		int totale = 0;
		for (Squadra s : squadre) {
			totale += s.getNumeroOperai();
		}
		return totale;
	}

	public Geolocalizzazione getPosizioneCantiere() {
		return posizioneCantiere;
	}

	public double getEstensione() {
		return estensione;
	}

	/**
	 * Metodo per aggiungere un Responsabile alla gestione della squadra.
	 * 
	 * @param responsabile il responsabile da assegnare
	 */
	public void assegnaResponsabile(Responsabile responsabile) {

		Dipendente d = (Dipendente) responsabile;
		if (valore > 500000) {
			if (Dipendente.isDirigente(d) && !d.isImpegnato()) {
				Dirigente dir = (Dirigente) d;
				dir.impegnaDipendente();
				dir.aggiungiOperai(getNumeroOperaiCantiere());
				this.responsabile = responsabile;
				return;
			}
		} else {
			if (!d.isImpegnato()) {
				d.impegnaDipendente();
				this.responsabile = responsabile;
				return;
			}
		}
		throw new IllegalArgumentException();
	}

	public void licenziaResponsabile() {
		Dipendente d = (Dipendente) responsabile;
		d.liberaDipendente();
		responsabile = null;
	}

	/**
	 * 
	 * Questa classe cattura l'astrazione di una coordinata geografica dove è
	 * situato il cantiere
	 * 
	 * @param latitudine  la latitudine del punto
	 * @param longitudine la longitudine del punto
	 */
	public class Geolocalizzazione implements Serializable {
		private double longitudine;
		private double latitudine;

		public Geolocalizzazione(double latitudine, double longitudine) {
			this.latitudine = latitudine;
			this.longitudine = longitudine;
		}

		public double getlongitudine() {
			return longitudine;
		}

		public double getlatitudine() {
			return latitudine;
		}

		public String toString() {
			return "[latitudine= " + latitudine + ",longitudine=" + longitudine + "]";
		}
	}
}
