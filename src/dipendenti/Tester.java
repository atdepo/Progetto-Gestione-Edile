package dipendenti;

public class Tester {

	
	
	public static void main(String[] args) {
		Impiegato i= new Impiegato("pippo","ciccio",12);
		Impiegato clone= i.clone();
		System.out.println(i);
		System.out.println(clone);
	}
	
	
	
}
