package dipendenti;
/**
 * Questa classe cattura il concetto di Dirigente. Un <b>Dirigente</b> è incaricato di gestire
 * tutti i cantieri con una valutazione superiore a 500.000.
 * Un dirigente viene retribuito in base ad uno stipendio fisso a cui si aggiungono dei bonus,nel caso ne diriga uno,per ogni 
 * operaio nel suo cantiere. Il codiceDipendente di un dirigente è <b>04</b>
 */
public class Dirigente extends Dipendente implements Responsabile{

	private int numero_operai;
	
	public Dirigente(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		numero_operai=0;
	}

	
	public int getNumeroOperai() {
		return numero_operai;
	}
	
	public String generateCodice(String matricola) {
		return "04"+matricola;
		}
	
	public String toString() {
		return super.toString()+"[numero_operai= "+numero_operai+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Dirigente d=(Dirigente)o;
		return d.getNumeroOperai()==numero_operai;
	}
	
	public Dirigente clone() {
		return (Dirigente)super.clone();
		
	}
}
