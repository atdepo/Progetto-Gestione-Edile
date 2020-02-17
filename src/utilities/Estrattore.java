package utilities;

import java.util.ArrayList;

import utilities.Estraibile;;

/**
 * Questra astrazione cattura il concetto di un estrattore parametrico che
 * estrae degli oggetti di un certo tipo da un insieme di input con un criterio
 * 
 * @author della
 *
 * @param <T>
 */
public class Estrattore<T> {
	ArrayList<T> insiemeInput;
	Estraibile<T> criterio;

	public Estrattore() {
		insiemeInput = new ArrayList<T>();
		criterio = null;
	}

	public Estrattore(Estraibile<T> criterio) {
		insiemeInput = new ArrayList<T>();
		this.criterio = criterio;
	}

	public Estrattore(ArrayList<T> insiemeInput) {
		this.insiemeInput = insiemeInput;
		criterio = null;
	}

	public Estrattore(ArrayList<T> insiemeInput, Estraibile<T> criterio) {
		this.insiemeInput = insiemeInput;
		this.criterio = criterio;
	}

	public void setInsiemeInput(ArrayList<T> nuovoInsieme) {
		insiemeInput = nuovoInsieme;
	}

	public void setCriterio(Estraibile<T> nuovoCriterio) {
		criterio = nuovoCriterio;
	}

	/**
	 * Metodo utilizzato per l'estrazione di oggetti di un certo tipo secondo un
	 * criterio indicato
	 * 
	 * @return un {@link ArrayList} contenete gli oggetti che soddisfano il criterio
	 * @author Antonio Della Porta
	 */
	public ArrayList<T> estrai() {
		ArrayList<T> output = new ArrayList<T>();
		for (T t : insiemeInput) {
			if (criterio.estrai(t))
				output.add(t);
		}
		return output;
	}
}
