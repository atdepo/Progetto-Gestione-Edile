package utilities;

import java.io.Serializable;
import java.util.ArrayList;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.Prodotto;
import operativo.RepartoOperativo;

public class Azienda implements Serializable {
	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	
	public Azienda(RepartoAmministrativo amministrativo,RepartoOperativo operativo) {
		repartoAmministrativo=amministrativo;
		repartoOperativo=operativo;
	}

	public RepartoAmministrativo getRepartoAmministrativo() {
		return repartoAmministrativo;
	}
	
	public RepartoOperativo getRepartoOperativo() {
		return repartoOperativo;
	}
	
}
