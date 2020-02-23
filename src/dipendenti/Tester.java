package dipendenti;


public class Tester {

	
	
	public static void main(String[] args) {
		System.out.println("Tester del pacchetto 'dipendenti'\n");
		System.out.println("Istanzio un operaio con una specializzazione di muratore");
		Operaio o= new Operaio("Paolo", "Apostolico", 24, "012","MURATORE");
		System.out.println(o);
		
		System.out.println("Controllo se l'operaio è impegnato="+o.isImpegnato());
		System.out.println("Impegno l'operaio");
		o.impegnaDipendente();
		System.out.println("Controllo se l'operaio è impegnato="+o.isImpegnato());
		
		System.out.println("\nIstanzio un Impiegato che lavora solamente 4 giorni alla settimana");
		Impiegato i= new Impiegato("Giuseppe", "Contaldi", 21, "031", 4);
		System.out.println(i);
		
		System.out.println("\nIstanzio un Quadro");
		Quadro q= new Quadro("Antonio", "Montefusco", 20, "048");
		System.out.println(q);
		
		System.out.println("\nIstanzio un Dirigente");
		Dirigente d= new Dirigente("Mafalda", "Ingenito", 21, "041");
		System.out.println(d);
		System.out.println("Aggiungiamo 10 operai a carico del dirigente");
		d.aggiungiOperai(10);
		System.out.println(d);
		System.out.println("\nIstanzio un nuovo Dirigente");
		Dirigente d1=new Dirigente("Pamela", "Casullo", 21, "021");
		System.out.println(d1);
		
		System.out.println("Controllo se sono uguali:"+d.equals(d1));
		
		System.out.println("Ora clono il secondo Dirigente\n");
		Dirigente clone= d1.clone();
		System.out.println(clone);
		
		System.out.println("Controllo se sono uguali:"+clone.equals(d1));
		
		
		System.out.println("Controllo se "+d.getNome()+" "+d.getCognome()+" è un operaio\n");
		System.out.println(d.getNome()+" "+d.getCognome()+" è un operaio? "+Dipendente.isOperaio(d));
		
		System.out.println("Controlliamo quanto ogniuno dei Dipendenti guadagna mensilmente");
		System.out.println(o.getNome()+" "+o.getCognome()+" guadagna "+o.getPaga()+" euro");
		System.out.println(q.getNome()+" "+q.getCognome()+" guadagna "+q.getPaga()+" euro");
		System.out.println(i.getNome()+" "+i.getCognome()+" guadagna "+i.getPaga()+" euro");
		System.out.println(d.getNome()+" "+d.getCognome()+" guadagna "+d.getPaga()+" euro");

		
	}
	
	
	
}
