package utilities;

import amministrativo.RepartoAmministrativo;
import operativo.RepartoOperativo;

public class Azienda {
	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	
	public Azienda(RepartoAmministrativo amministrativo,RepartoOperativo operativo) {
		repartoAmministrativo=amministrativo;
		repartoOperativo=operativo;
	}
	
	
	
}
