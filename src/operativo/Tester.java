package operativo;

import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Operaio.lavoro;
import dipendenti.Quadro;
import dipendenti.Responsabile;
import eccezioni.OperaioOccupatoException;

public class Tester {
	
	public static void main(String[] args) {
		

	try {
		Squadra s= new Squadra(new Quadro("Ciccio", "pasticcio", 19, "478"));
		s.aggiungiOperaio(new Operaio("tipo","a caso",21,"asd",lavoro.ELETTRICISTA));
		s.aggiungiOperaio(new Operaio("tipetto","a caso",21,"asd",lavoro.MURATORE));
		s.aggiungiOperaio(new Operaio("mignotta","a caso",21,"asd",lavoro.IDRAULICO));
		s.aggiungiOperaio(new Operaio("patt","a caso",21,"asd",lavoro.PIASTRELLISTA));
		//s.stampaSquadra();
		Cantiere c= new Cantiere(200000, 21, 21, 10);
		c.assegnaSquadra(s);
		//s.stampaSquadra();
	} catch (OperaioOccupatoException e) {
		e.printStackTrace();
	}
	

	
	}
}