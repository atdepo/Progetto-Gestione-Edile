package utilities;

/**
 * Interfaccia funzionale utilizzata per comparare due oggetti di un certo tipo
 * 
 * @author Antonio Della Porta
 *
 * @param <T>
 */
public interface Comparatore<T> {
	int compara(T x, T y);
}
