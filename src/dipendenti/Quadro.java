package dipendenti;
/**
 * Questa classe cattura il concetto di un <b>Quadro</b>.
 * Un Quadro può essere responsabile di un cantiere quando il suo valore è al di sotto di 500.000 euro oppure essere il caposquadra 
 * di una squadra di operai.
 * In questo caso il quadro otterrà una maggiorazione al suo stipendio fisso del 6%.
 * Il codiceDipendente del Quadro inizia con <b>03 </b>
 */
public class Quadro extends Dipendente implements Responsabile{
	boolean isResponsabile;
	boolean isCaposquadra;
	
	public Quadro(String nome, String cognome, int eta,String matricola) {
		super(nome, cognome, eta);
		setCodiceDipendente(generateCodice(matricola));
		isResponsabile=false;
		isCaposquadra=false;
	}

	public String generateCodice(String matricola) {
		return "03"+matricola;
	}
	
	public boolean isResponsabile() {
		return isResponsabile;
	}
	
	public boolean isCaposquadra() {
		return isCaposquadra;
	}
	
	public void setResponsabile(boolean flag) {
		isResponsabile=flag;
	}
	
	public void setCaposquadra(boolean flag) {
		isCaposquadra=flag;
	}
	
	public String toString() {
		return super.toString()+"[isResponsabile="+isResponsabile+",isCaposquadra="+isCaposquadra+"]";
	}

	public boolean equals(Object o) {
		if(!super.equals(o))
			return false;
		Quadro q=(Quadro)o;
		return q.isCaposquadra==isCaposquadra&&q.isResponsabile==isResponsabile;
	}
	
	public Quadro clone() {
		return (Quadro)super.clone();
	}

	
}
