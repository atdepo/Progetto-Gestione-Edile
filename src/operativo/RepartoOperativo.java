package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Responsabile;

public class RepartoOperativo implements Serializable{

	private ArrayList<Cantiere> cantieri;
	
	public RepartoOperativo() {
		cantieri= new ArrayList<Cantiere>();
	}
	
	/**
	 * Istanzia un cantiere e lo inserisce nella lista dei cantieri
	 * 
	 * @param valore valore del cantiere
	 * @param latitudine latitudine della posizione del cantiere
	 * @param longitudine longitudine della posizione del cantiere
	 * @param estensione estensione in m^2 del cantiere
	 * @param responsabile il responsabile del cantiere
	 */
	public void apriCantiere(double valore,double latitudine,double longitudine,double estensione,Responsabile responsabile) {
		cantieri.add(new Cantiere(valore, latitudine, longitudine, estensione, responsabile));
	}
	/**
	 * Chiude il cantiere passato in input
	 * @param daChiudere
	 */
	public void chiudiCantiere(Cantiere daChiudere) {
		int index=cantieri.indexOf(daChiudere);
		if(index==-1)
			throw new IllegalArgumentException();
		else {
			Cantiere c=cantieri.get(index);
			c.licenziaResponsabile();
			for(Squadra s: c.getSquadre()) {
				rimuoviSquadra(c, s);
			}
			cantieri.remove(index);
		}
	}
	
	public ArrayList<Cantiere> getCantieri() {
		return cantieri;
	}
	
	
	/**
	 * Metodo per aggiungere squadre alla lista delle squadre impegnate nel cantiere.
	 * @param squadra la squadra da aggiungere
	 * @param cantiere il cantiere a cui assegnarla
	 */
	public void assegnaSquadra(Cantiere cantiere,Squadra squadra) {
		for(Cantiere c:cantieri) {
			if(c.equals(cantiere)) {
				if(squadra.getOperai().size()>0) {
					c.getSquadre().add(squadra);
					if(Dipendente.isDirigente((Dipendente)c.getResponsabile())) {
						int ndip=squadra.getNumeroOperai();
						Dirigente d=(Dirigente)c.getResponsabile();
						d.aggiungiOperai(ndip);
					}
					squadra.assegnaSquadra();
				}
				else
					throw new IllegalArgumentException();
			}
		}
		
	}
	
	public void rimuoviSquadra(Cantiere cantiere,Squadra squadra) {
		for(Cantiere c:cantieri) {
			Dipendente d=(Dipendente)c.getResponsabile();
			if(Dipendente.isDirigente(d)) {
				Dirigente dir=(Dirigente)d;
				dir.rimuoviOperai(squadra.getNumeroOperai());
			}
			squadra.liberaSquadra();
			c.getSquadre().remove(squadra);
		}
	}
	
	
}
