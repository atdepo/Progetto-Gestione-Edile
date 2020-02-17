package utilities;

/**
 * Interfaccia funzionale utilizzata per controllare se un oggetto di un certo tipo 
 * possa essere estratto o meno
 * 
 * @author Antonio Della Porta
 *
 * @param <T>
 */
public interface Estraibile<T> {
	boolean estrai(T d);
}
