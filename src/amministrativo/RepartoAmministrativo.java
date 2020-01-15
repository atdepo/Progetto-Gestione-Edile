package amministrativo;

import java.util.ArrayList;

import approvviggionamento.Fornitore;
import dipendenti.Dipendente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;

/**
 * Questa classe cattura il concetto  di un RepartoAmministrativo di un cantiere.
 * un reparto amministrativo ha il compito di assumere i dipendenti e 
 *
 */
public class RepartoAmministrativo {
	
	//questa � una responsabilit� che deve avere la classe dipendente?
	
	protected static final double STIPENDIO_IMPIEGATO=7.0D;
	protected static final double STIPENDIO_OPERAIO_GIORNO=25.0D;
	protected static final double STIPENDIO_QUADRO=1780.0D;	
	protected static final double STIPENDIO_DIRIGENTE=2750.0D;
	protected static final double BONUS_QUADRO_CAPOCANTIERE=5;
	protected static final double BONUS_QUADRO_CAPOSQUADRA=3;
	protected static final double BONUS_IMPIEGATO=4.0D;
	protected static final double BONUS_OPERAIO=4.0D;
	//protected static final double BONUS_DIRIGENTE=5.0D;
	protected static final double BONUS_STRAORDINARIO_IMPIEGATO=9.0D;
	protected static final double BONUS_STRAORDINARIO_OPERAIO=8.0D;
	
	
	private ArrayList<Dipendente> dipendenti;
	private ArrayList<Fornitore> fornitori;
	
	private int numDirigenti;
	private int numQuadri;
	private int numOperai;
	private int numImpiegati;
	
	private Magazino magazino;
	
	
	public RepartoAmministrativo() {
		dipendenti=new ArrayList<Dipendente>();
		fornitori= new ArrayList<Fornitore>();
		numDirigenti=0;
		numQuadri=0;
		numOperai=0;
		numImpiegati=0;
		
	}
	
	public void assumiDipendente(Dipendente d) {
		if(Dipendente.isDirigente(d)) {
			d.setContratto(STIPENDIO_DIRIGENTE,0 );
			dipendenti.add(d);
			numDirigenti++;
		}
		else if(Dipendente.isImpiegato(d)) {
			d.setContratto(STIPENDIO_IMPIEGATO,BONUS_IMPIEGATO );
			dipendenti.add(d);
			numImpiegati++;
		}
		else if(Dipendente.isOperaio(d)) {
			d.setContratto(STIPENDIO_OPERAIO_GIORNO,BONUS_OPERAIO );
			dipendenti.add(d);
			numOperai++;
		}
		else if(Dipendente.isQuadro(d)) {
			d.setContratto(STIPENDIO_QUADRO,0);
			dipendenti.add(d);
			numQuadri++;
		}
		
	}
	
	static void pagaDirigente() {
		//da definire
	}
	
	static double pagaImpiegato(Dipendente d) {
		Contratto c=d.getContratto();
		Impiegato i= (Impiegato)d; 
		double saldo=i.getGiorniLavorati()*c.getStipendio()+i.getGiorniStraordinario()*c.getBonus();
		i.resetOre();
		return saldo;
	}
	
	static double pagaOperaio(Dipendente d) {
		Contratto c=d.getContratto();
		Operaio i= (Operaio)d;
		double saldo=i.getOre_lavorate()*c.getStipendio()+i.getOre_straordinario()*c.getBonus();
		i.resetOre();
		return saldo;
		
	}	
	
	static double pagaQuadro(Dipendente d) {
		Contratto c=d.getContratto();
		Quadro i= (Quadro)d;
		double saldo=c.getStipendio();
		if(i.isCaposquadra())
			saldo=saldo+c.getBonus();
		else if (i.isResponsabile())
			saldo=saldo+(c.getBonus()*2);
		
		return saldo;
	}
	
	public void pagamentoAssunti() {
		
		for(int i=0;i<dipendenti.size();i++) {
			Dipendente d=dipendenti.get(i);
					
			if(Dipendente.isDirigente(d)) {
				pagaDirigente();
			}
			else if(Dipendente.isImpiegato(d)) {
				pagaImpiegato(d);
			}
			else if(Dipendente.isOperaio(d)) {
				pagaOperaio(d);
			}
			else if(Dipendente.isQuadro(d)) {
				pagaQuadro(d);
			}		
		}
	}
	
	public int totale_Dipendenti() {
		return dipendenti.size();
	}

	public int getNumDirigenti() {
		return numDirigenti;
	}

	public int getNumQuadri() {
		return numQuadri;
	}

	public int getNumOperai() {
		return numOperai;
	}

	public int getNumImpiegati() {
		return numImpiegati;
	}
	
	public Dipendente licenziamentoDipendente(String codiceDipendente) {
		Dipendente rimosso = null;
		for(int i=0;i<dipendenti.size();i++) {
			Dipendente d=dipendenti.get(i);
			if(d.getCodiceDipendente().equals(codiceDipendente))
				rimosso=dipendenti.remove(i);
		}
	return rimosso;
	}
	
	
	
}

