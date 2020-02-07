package amministrativo;

import java.util.ArrayList;

import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Operaio.lavoro;
import eccezioni.ProdottoNonTrovatoException;

public class TesterAmministrativo {

	public static void main(String[] args) {
		System.out.println("Questo tester si occupa di testare gli oggetti presenti nel pacchetto 'amministrativo'");
		System.out.println("Istanziamo un nuovo reparto amministrativo");
		RepartoAmministrativo ra= new RepartoAmministrativo(100000, 10000, 1548784);
		
		System.out.println("Assumo un operaio");
		ra.assumiOperaio("Gerardo", "Pascale", 19, lavoro.IDRAULICO);
		System.out.println(ra.getDipendenti().get(0));
		
		System.out.println("Assumo un dirigente");
		ra.assumiDirigente("Giuseppe", "Fognaro", 56);
		System.out.println(ra.getDipendenti().get(1));
		
		System.out.println("Assumo un Quadro");
		ra.assumiQuadro("Filippo", "Massaro", 51);
		System.out.println(ra.getDipendenti().get(2));
		
		System.out.println("Assumo un impiegato");
		ra.assumiImpiegato("Giovanni", "Della Casa", 52);
		System.out.println(ra.getDipendenti().get(3));
		
		System.out.println("Il capitale al momento è di: "+ra.getCapitale());
		System.out.println("Pago tutti i dipendenti assunti");
		ra.pagamentoAssunti();
		System.out.println("Il capitale ora è di: "+ra.getCapitale());
		
		System.out.println("I Dipendenti che abbiamo assunti attualmente sono:\n");
		ArrayList<Dipendente> dip=ra.getDipendenti();
		for(Dipendente d:dip)
			System.out.println(d);
		System.out.println("Licenziamo l'operaio"+ra.getDipendenti().get(0).getNome()+" "+ra.getDipendenti().get(0).getCognome());
		ra.licenziamentoDipendente(ra.getDipendenti().get(0));
		System.out.println("I Dipendenti ora sono:\n");
		for(Dipendente d:dip)
			System.out.println(d);
		
		System.out.println("Aggiungiamo un fornitore");
		Fornitore f1=new Fornitore("Ferramenta D'Alessandro");
		f1.aggiungiProdotto(new Prodotto("Viti", "metallo", "", 0.01, 0.5, 0.2,0.3,0,0,0.30, 100));
		f1.aggiungiMacchina(new MacchineDaCantiere("Bulldozer", "CAT", 	154000, 900, 14800));
		ra.aggiungiFornitore(f1);
		System.out.println(f1.getNomeFornitore());
		System.out.println("Compriamo delle viti");  
		try {
			Prodotto comprato=ra.compraProdotto(new Prodotto("Viti", "metallo", "", 0.01, 0.5, 0.2,0.3,0,0,0.30, 10));
			System.out.println(comprato.getCaratteristicheProdotto());
			System.out.println("Aggiungiamo il prodotto al magazzino");
			ra.aggiungiProdottoAlMagazzino(comprato);
			System.out.println("Proviamo a comprare un prodotto non esistente");
			ra.compraProdotto(new Prodotto("Vite", "metallo", "", 0.01, 0.5, 0.2,0.3,0,0,0.30, 1));
		} catch (ProdottoNonTrovatoException e) {
			System.out.println("eccezione catturata");
		}
		System.out.println("Ora proviamo a prendere 100 viti da restituire ipoteticamente ad un cantiere");
		System.out.println("Il nostro fornitore ne ha a disposizione 90, mentre nel nostro magazzino ne sono presenti 10");
		Prodotto daRestituire=ra.getProdotto(new Prodotto("Viti", "metallo", "", 0.01, 0.5, 0.2,0.3,0,0,0.30, 100));
		System.out.println(daRestituire.getCaratteristicheProdotto());
		ra.aggiungiProdottoAlMagazzino(daRestituire);
		System.out.println("Proviamo a comprare una macchina da cantiere");
		MacchineDaCantiere macchina=ra.compraMacchina(new MacchineDaCantiere("Bulldozer", "CAT",154000, 900, 14800));
		System.out.println(macchina.getCaratteristiche());
		System.out.println("Inseriamo la macchina nel magazzino");
		ra.aggiungiMacchinaAlMagazzino(macchina);
		System.out.println(ra.getMagazzino().getMacchineInMagazzino().get(0).getCaratteristiche());
		System.out.println("Rimuoviamo 10 Viti dal magazzino");
		ArrayList<Prodotto>l=ra.getMagazzino().getProdottiInMagazzino();
		
	try {
		ra.getMagazzino().rimuoviProdotto(new Prodotto("Viti", "metallo", "", 0.01, 0.5, 0.2,0.3,0,0,0.30, 10));
		System.out.println(l.get(0).getCaratteristicheProdotto());
		System.out.println("Proviamo a rimuovere un prodotto non esistente");
		ra.getMagazzino().rimuoviProdotto(new Prodotto("Vite", "metallo", "", 0.01, 0.5, 0.2,0.3,0,0,0.30, 1));
	} catch (ProdottoNonTrovatoException e) {
		System.out.println("Eccezzione catturata");
	}
	}
	
	
}
