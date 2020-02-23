package approvviggionamento;

import amministrativo.RepartoAmministrativo;
import eccezioni.ProdottoNonTrovatoException;
import gui.PopolamentoAzienda;
import utilities.Azienda;

public class Tester {

	public static void main(String[] args) throws ProdottoNonTrovatoException {

		System.out.println("Tester pacchetto 'approvvigionamento'");

		System.out.println("Creiamo dei prodotti");
		Prodotto p1 = new Prodotto("Vite", "Alluminio", "", 0.5, 0.2, 0.1, 0.05, 0, 0, 0.25, 700);
		Prodotto p2 = new Prodotto("Bullone", "Metallo", "", 0.2, 0.5, 0.0, 0.05, 0, 0, 0.10, 600);
		Prodotto p3 = new Prodotto("Chiave Inglese 13\"", "Alluminio", "", 0.5, 15, 8, 4, 0, 0, 12, 648);
		Prodotto p4 = new Prodotto("Pennello", "Metallo/fibre", "", 0.1, 16, 6, 3, 0, 0, 6, 500);
		System.out.println(p1.getCaratteristicheProdotto());
		System.out.println(p2.getCaratteristicheProdotto());
		System.out.println(p3.getCaratteristicheProdotto());
		System.out.println(p4.getCaratteristicheProdotto());

		System.out.println("Creiamo delle nuove macchine");
		MacchineDaCantiere m1 = new MacchineDaCantiere("Gru", "Alimak Keh", 4560, 650, 15000);
		MacchineDaCantiere m2 = new MacchineDaCantiere("Carrelli elevatori", "Hikowa", 320, 30, 2500);
		MacchineDaCantiere m3 = new MacchineDaCantiere("Impianto mobile calcestruzzo TECNOMIX ", "SAMI", 35000, 650,
				27000);
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);

		System.out.println("Creiamo un nuovo fornitore");
		Fornitore f1 = new Fornitore("Materiali Vari S.R.L");
		System.out.println("Aggiungiamogli i prodotti e le macchine precedentemente creati");
		f1.aggiungiProdotto(p1);
		f1.aggiungiProdotto(p2);
		f1.aggiungiProdotto(p3);
		f1.aggiungiProdotto(p4);
		f1.aggiungiMacchina(m1);
		f1.aggiungiMacchina(m2);
		f1.aggiungiMacchina(m3);

		Prodotto a = new Prodotto("Test", "Metallo/fibre", "", 500, 16, 6, 3, 0, 0, 6, 500);
		try {
			System.out.println("Cerchiamo un prodotto esistente\n");
			System.out.println("Prodotto Trovato!" + f1.cercaProdotto(p3).getCaratteristicheProdotto());
			System.out.println("Cerchiamo un prodotto inesistente");
			f1.cercaProdotto(a);
		} catch (ProdottoNonTrovatoException e) {
			System.out.println("Eccezione Catturata");
		}
		 
		System.out.println("\nVendiamo ad un azienda 10 Viti\n");
		Prodotto p=p1.clone();
		p.setNumeroPezziDisponibili(10);
		System.out.println("Prodotto Comprato: "+f1.compraProdotto(p).getCaratteristicheProdotto());
		System.out.println("Vendiamo ad un azienda un prodotto inesistente");
		
		Prodotto a1=f1.compraProdotto(a);
		System.out.println(a1);
		
		System.out.println("Rimuoviamo un prodotto dalla lista dei prodotti in vendita");
		f1.rimuoviProdotto(p4);
		
		for(Prodotto pr:f1.getProdottiInVendita()) {
			System.out.println(pr.getCaratteristicheProdotto());
		}
		
		System.out.println("Cerchiamo una macchina esistente");
		System.out.println("Macchina Trovata!"+f1.cercaMacchina(m1));
		System.out.println("Cerchiamo una macchina inesistente");
		MacchineDaCantiere e=new MacchineDaCantiere("Macchina non esistente", "SAMI", 35000, 650,27000);
		 try {
			f1.cercaMacchina(e);
		} catch (IllegalArgumentException e2) {
			System.out.println("Eccezzione catturata");
		}
		 
		System.out.println("Vendiamo ad un azienda una macchina non esistente");
		System.out.println(f1.compraMacchina(e));
		System.out.println("Rimuoviamo una macchina dalla lista");
		f1.rimuoviMacchina(m3);
		
		for(MacchineDaCantiere ma:f1.getMacchineDaCantiere()) {
			System.out.println(ma);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
