package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import dipendenti.Responsabile;

public class RepartoOperativo implements Serializable{

	ArrayList<Cantiere> cantieri;
	
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
		else
		cantieri.remove(index);
		
	}
	
}
