package dipendenti;
/**
 * Questa classe cattura il concetto di un <b>Quadro</b>.
 * Un Quadro pu� essere responsabile di un cantiere quando il suo valore � al di sotto di 500.000 euro.
 * In questo caso il quadro otterr� una maggiorazione al suo stipendio fisso del 5% del valore del cantiere ogni mese che lo diriger�.
 * Mentre se un quadro � caposquadra di un gruppo di operai otterr�  una maggiorazione al suo stipendio base del 2% del valore del cantiere
 * per ogni mese che sar� caposquadra.
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

	public boolean isDirigente() {
		return false;		
	}
	
}
