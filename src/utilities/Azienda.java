package utilities;

import java.io.Serializable;
import java.util.ArrayList;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.Prodotto;
import operativo.RepartoOperativo;

/**
 * Questa astrazione cattura il concetto di un azienda edile composta da un
 * Reparto Operativo che si occupa della gestione dei cantieri e dele squadre
 * assegnate ad esso e di un Reparto Amministrativo che si occupa della parte
 * burocratica dell'azienda come il pagamento dei dipendenti, l'assunzione e il licenziamento degli stessi
 * 
 * @author Antonio Della Porta
 *
 */
public class Azienda implements Serializable {
	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	/**
	 * Costruisce l'azienda a partire un reparto amministrativo e un reparto operativo
	 * @param amministrativo il reparto amministrativo
	 * @param operativo il reparto operativo
	 * @author Antonio Della Porta
	 */
	public Azienda(RepartoAmministrativo amministrativo, RepartoOperativo operativo) {
		repartoAmministrativo = amministrativo;
		repartoOperativo = operativo;
	}

	public RepartoAmministrativo getRepartoAmministrativo() {
		return repartoAmministrativo;
	}

	public RepartoOperativo getRepartoOperativo() {
		return repartoOperativo;
	}

}
