package utilities;

import java.util.ArrayList;

import amministrativo.RepartoAmministrativo;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Operaio.lavoro;
import dipendenti.Responsabile;
import operativo.RepartoOperativo;

public class TesterAzienda {
	
	public static void main(String[] args) {
		
	
	RepartoAmministrativo ra= new RepartoAmministrativo(100000, 10, 100000);
	RepartoOperativo ro= new RepartoOperativo();
	ra.assumiOperaio("Ciccio", "pasticcio", 19, lavoro.IDRAULICO);
	ra.assumiDirigente("Pier", "Paolo", 41);
	ra.assumiOperaio("Budello", "De mignottis", 34, lavoro.MURATORE, 60);
	ra.assumiImpiegato("Mimmuccio", "The best", 36);
	ra.assumiImpiegato("Pierpy", "Dastero", 37, 6);
	ArrayList<Dipendente> dip= ra.getDipendenti();
	
	for(Dipendente d: dip) {
		System.out.println(d);
	}
	Dirigente d=(Dirigente) dip.get(1);
	//Responsabile r=(Responsabile) 
	ro.apriCantiere(41454, 4154, 415, 4154,d);
	
	
	}
	
	
	
}
