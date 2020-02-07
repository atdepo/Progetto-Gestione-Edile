package operativo;

import java.util.ArrayList;

import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Operaio;
import dipendenti.Operaio.lavoro;
import dipendenti.Quadro;
import dipendenti.Responsabile;
import eccezioni.OperaioOccupatoException;

public class Tester {
	
	public static void main(String[] args) {
		System.out.println("Tester del pachetto 'operativo'");
		System.out.println("Creiamo un Dirigente e un Quadro");
		Dirigente d= new Dirigente("Francesco", "Fasulo", 45, "012");
		Quadro q= new Quadro("Giovanni", "Graziani", 34, "031");
		System.out.println(d+"\n"+q);
		System.out.println("Istanziamo un reparto operativo");
		RepartoOperativo ro= new RepartoOperativo();
		System.out.println("Apriamo un cantiere con un valore >500000 tenando di assegnargli come responsabile un Quadro");
		try {
			ro.apriCantiere(600000,4715.471, 13487.1478, 45,q);

		} catch (IllegalArgumentException e) {
			System.out.println("eccezione catturata");
		}
		System.out.println("Provvediamo ad assegnargli un dirigente");
		ro.apriCantiere(600000,4715.471, 13487.1478, 45,d);
		ArrayList<Cantiere> c=ro.getCantieri();
		System.out.println((Dirigente)c.get(0).getResponsabile());
		System.out.println("Creiamo una squadra di operai");
		Squadra s= new Squadra(q);
		try {
			s.aggiungiOperaio(new Operaio("Gerardo", "Pascale", 19, "012",lavoro.IDRAULICO));
			s.aggiungiOperaio(new Operaio("Francesco", "Bertolacci", 42,"0123",lavoro.IDRAULICO));
			s.aggiungiOperaio(new Operaio("Giovanni", "Masculo", 27,"0124",lavoro.MURATORE));
			System.out.println(s.getOperai().get(0));
			System.out.println(s.getOperai().get(1));
			System.out.println(s.getOperai().get(2));
			System.out.println("Proviamo ad aggiungere un operaio già occupato");
			Operaio o= new Operaio("Paolo", "Apostolico", 24, "012", lavoro.MURATORE);
			o.impegnaDipendente();
			System.out.println(o);
			s.aggiungiOperaio(o);
		} catch (OperaioOccupatoException e) {
			System.out.println("eccezione catturata");
		}
		System.out.println("Assegnamo la squadra al cantiere");
		Cantiere can=ro.getCantieri().get(0);
		ro.assegnaSquadra(can,s);
		System.out.println("Ora il dirigente ha "+d.getNumeroOperai()+ " Operai nel suo cantiere");
		System.out.println("Rimuoviamo un operaio dalla squadra");
		Operaio o=new Operaio("Giovanni", "Masculo", 27,"0124",lavoro.MURATORE);
		s.rimuoviOperaio(o);
		System.out.println(o);
		System.out.println("Rimuoviamo la squadra dal cantiere");
		ro.rimuoviSquadra(can,s);
		System.out.println(s.getOperai().get(0));
		System.out.println(s.getOperai().get(1));
		System.out.println(s.getOperai().get(2));
		System.out.println("Chiudiamo il cantiere");
		ro.chiudiCantiere(can);
	}
}