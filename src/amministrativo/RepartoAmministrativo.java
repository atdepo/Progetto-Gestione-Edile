package amministrativo;

import java.util.ArrayList;

import approvviggionamento.Fornitore;
import dipendenti.Dipendente;

/**
 * Questa classe cattura il concetto  di un RepartoAmministrativo di un cantiere.
 * un reparto amministrativo ha il compito di assumere i dipendenti e 
 *
 */
public class RepartoAmministrativo {
	
	//questa è una responsabilità che deve avere la classe dipendente?
	
	protected static final double STIPENDIO_IMPIEGATO=1500.0D;
	protected static final double STIPENDIO_OPERAIO=1450.0D;
	protected static final double STIPENDIO_QUADRO=1780.0D;	
	protected static final double STIPENDIO_DIRIGENTE=2750.0D;
	protected static final double QUADRO_CAPOCANTIERE=5;
	protected static final double QUADRO_CAPOSQUADRA=3;
	protected static final double BONUS_IMPIEGATO=4.0D;
	protected static final double BONUS_OPERAIO=4.0D;
	protected static final double STRAORDINARIO_IMPIEGATO=4.0D;
	protected static final double STRAORDINARIO_OPERAIO=4.0D;
	
	private ArrayList<Dipendente> dipendenti;
	private ArrayList<Fornitore> fornitori;
	
	//magazino
	
	

	//assunzioni e licenziamenti 
	//
	
	
	public RepartoAmministrativo() {
		dipendenti=new ArrayList<Dipendente>();
		fornitori= new ArrayList<Fornitore>();
	}
	
	
	
	
	
	
	

	
	
}


