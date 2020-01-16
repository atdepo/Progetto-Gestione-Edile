package approvviggionamento;

import amministrativo.RepartoAmministrativo;
import eccezioni.ProdottoNonTrovatoException;

public class Tester {


	public static void main(String[] args) throws ProdottoNonTrovatoException {
		
		Fornitore f1= new Fornitore("Ciccio");
		f1.aggiungiProdotto(new Prodotto("ciccio", "boh", "tua madre", 12, 1, 2, 1, 12, 12, 12, 12));
		Fornitore f2= new Fornitore("Pasticcio");
		f2.aggiungiProdotto(new Prodotto("ciccio", "boh", "tua madre", 12, 1, 2, 2, 12, 12, 12, 12));
		
		//System.out.println(f1.getProdottiInVendita().get(0).getCaratteristicheProdotto()+"\n\n");
		//System.out.println(f2.getProdottiInVendita().get(0).getCaratteristicheProdotto()+"\n\n");

		RepartoAmministrativo r= new RepartoAmministrativo(1000, 10, 12145);
		r.aggiungiFornitore(f1);
		r.aggiungiFornitore(f2);
		
		r.aggiungiProdottoAlMagazzino(new Prodotto("ciccio", "boh", "tua madre", 12, 1, 2, 1, 12, 12, 12, 12));
	//	r.aggiungiProdottoAlMagazzino(new Prodotto("ciccio", "boh", "tua madre", 12, 1, 2, 1, 12, 12, 12, 12));
	//	r.aggiungiProdottoAlMagazzino(new Prodotto("ciccio", "boh", "tua madre", 12, 1, 2, 1, 12, 12, 12, 12));

	//	r.magazzino.stampa();

	
		Prodotto p1=r.getProdotto(new Prodotto("ciccio", "boh", "tua madre", 12, 1, 2, 1, 12, 12, 12, 13));
		System.out.println(p1);
		//System.out.println(p1.getSpazioOccupatoTotale());
		System.out.println("Ho trovato\n"+p1.getCaratteristicheProdotto());
	
	}
}
