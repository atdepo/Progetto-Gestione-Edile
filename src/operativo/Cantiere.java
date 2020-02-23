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

	/**
	 * Metodo utilizzato per assegnare una lista di prodotti al cantiere
	 * 
	 * @param materiali la lista di materiali
	 * @author Antonio Della Porta
	 */
	public void assegnaMateriali(ArrayList<Prodotto> materiali) {// uno solo
		listaMaterialiDisponibili.addAll(materiali);
	}

	/**
	 * Metodo utilizzato per assegnare un singolo prodotto ad cantiere
	 * 
	 * @param toAdd il prodotto da aggiungere
	 * @author Antonio Della Porta
	 */
	public void assegnaMateriale(Prodotto toAdd) {
		for (Prodotto p : listaMaterialiDisponibili) {
			if (p.equalsCaratteristiche(toAdd)) {
				p.sommaDisponibilita(toAdd);
				return;
			}
		}
		listaMaterialiDisponibili.add(toAdd);
	}

	/**
	 * Metodo utilizzato per assegnare una macchina ad un cantiere
	 * 
	 * @param macchina la macchina da aggiungere
	 * @author Antonio Della Porta
	 */
	public void assegnaMacchina(MacchineDaCantiere macchina) {
		macchineImpiegate.add(macchina);
	}

	/**
	 * Metodo utilizzato per rimuovere un prodotto dal cantiere
	 * 
	 * @param toRemove
	 * @author Antonio Della Porta
	 */
	public void rimuoviMateriale(Prodotto toRemove) {

		listaMaterialiDisponibili.remove(toRemove);
	}

	/**
	 * Metodo utilizzato per rimuovere una macchina da un cantiere
	 * 
	 * @param toRemove macchina da rimuovere
	 * @author Antonio Della Porta
	 */
	public void rimuoviMacchina(MacchineDaCantiere toRemove) {
		macchineImpiegate.remove(toRemove);
	}

	public ArrayList<MacchineDaCantiere> getMacchineImpiegate() {
		return macchineImpiegate;
	}

	/**
	 * Metodo utilizzato per ottenere il numero di operai occupati nel cantiere
	 * 
	 * @return
	 * @author Antonio Della Porta
	 */
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
				if (Dipendente.isQuadro(d)) {
					Quadro q = (Quadro) d;
					q.setResponsabile(true);
				}
				this.responsabile = responsabile;
				return;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Metodo utilizzato per licenziare un responsabile. Se il responsabile del
	 * cantiere era un Dirigente, gli vengono sottratti tutti gli operai a carico,
	 * nel caso fosse un Quadro, il flag isResponsabile viene settato a false
	 * 
	 * @author Antonio Della Porta
	 */
	public void licenziaResponsabile() {
		Dipendente d = (Dipendente) responsabile;
		if (Dipendente.isDirigente(d)) {
			Dirigente dir = (Dirigente) d;
			dir.aggiungiOperai(-getNumeroOperaiCantiere());
		} else {
			Quadro q = (Quadro) d;
			q.setResponsabile(false);
		}
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
