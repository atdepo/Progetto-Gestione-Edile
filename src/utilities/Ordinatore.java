package utilities;

import java.util.ArrayList;
/**
 * Questa astrazione cattura il concetto di un ordinatore che ordina un insieme secondo un certo criterio
 * @author Antonio Della Porta
 *
 * @param <T>
 */
public class Ordinatore<T> {
	private Comparatore<T> criterio;
	public Ordinatore(Comparatore<T> c) {
		this.criterio = c;
	}
	/**
	 * Questo metodo viene utilizzato per ordinare gli elementi di un insieme secondo un criterio indicato
	 * @param daOrdinare l'insieme da ordinare
	 * @return l'insieme ordinato secondo il criterio
	 * @author Antonio Della Porta
	 */
	public ArrayList<T> ordina(ArrayList<T> daOrdinare){
		for(int i = 0; i < daOrdinare.size(); i++) {
			for(int j = 0; j < daOrdinare.size()-1; j++) {
				if(criterio.compara(daOrdinare.get(j), daOrdinare.get(j+1))> 0) {
					T temp = daOrdinare.get(j);
					daOrdinare.set(j, daOrdinare.get(j+1));
					daOrdinare.set(j+1, temp);
				}
			}
		}
		return daOrdinare;
	}
}
