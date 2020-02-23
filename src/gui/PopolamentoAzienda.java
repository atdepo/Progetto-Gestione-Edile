package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dirigente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.OperaioOccupatoException;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import operativo.Squadra;
import utilities.Azienda;

public class PopolamentoAzienda {
	private Azienda azienda;
	private RepartoOperativo ro;
	private RepartoAmministrativo ra;

	public PopolamentoAzienda() {
		ra = new RepartoAmministrativo(100000, 10, 100000);
		ro = new RepartoOperativo();
		azienda = new Azienda(ra, ro);
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public static void main(String[] args) {
		PopolamentoAzienda p = new PopolamentoAzienda();
		p.popola();
	}

	public void popola() {
		
		ra.assumiDipendente(new Operaio("Gerardo", "Pascale", 19, String.valueOf(ra.getNumOperai()) ,"IDRAULICO"));
		ra.assumiDipendente(new Operaio("Michele", "Farina", 34, String.valueOf(ra.getNumOperai()) , "MURATORE", 60));
		ra.assumiDipendente(new Operaio("Ferdinando", "Della Mura", 31,String.valueOf(ra.getNumOperai()) , "ELETTRICISTA"));
		ra.assumiDipendente(new Operaio("Giacomo", "Santaniello", 24,String.valueOf(ra.getNumOperai()) , "PIASTRELLISTA"));
		ra.assumiDipendente(new Operaio("Francesco", "Bertolacci", 42,String.valueOf(ra.getNumOperai()) ,"IDRAULICO"));
		ra.assumiDipendente(new Operaio("Michele", "Sangiorgi", 21, String.valueOf(ra.getNumOperai()) ,"PIASTRELLISTA", 50));
		ra.assumiDipendente(new Operaio("Giuseppe", "Tricarico", 36,String.valueOf(ra.getNumOperai()) ,"IDRAULICO"));
		ra.assumiDipendente(new Operaio("Gennaro", "Fusco", 39, String.valueOf(ra.getNumOperai()) ,"ELETTRICISTA", 30));
		ra.assumiDipendente(new Operaio("PierGiorgio", "Favino", 35, String.valueOf(ra.getNumOperai()) ,"ELETTRICISTA"));
		ra.assumiDipendente(new Operaio("Giovanni", "Masculo", 27,String.valueOf(ra.getNumOperai()) , "MURATORE"));
		ra.assumiDipendente(new Operaio("Salvatore", "Salierno", 48,String.valueOf(ra.getNumOperai()) , "MURATORE"));
		ra.assumiDipendente(new Operaio("Gioacchino", "Massimini", 57,String.valueOf(ra.getNumOperai()) , "MURATORE"));
		ra.assumiDipendente(new Operaio("Pierpaolo", "Panti", 41,String.valueOf(ra.getNumOperai()) , "IDRAULICO"));
		ra.assumiDipendente(new Operaio("Giovanni", "Mastrocinque", 34, String.valueOf(ra.getNumOperai()) ,"ELETTRICISTA"));

		ra.assumiDipendente(new Dirigente("Giuseppe", "Fognaro", 56,String.valueOf(ra.getNumDirigenti())));
		ra.assumiDipendente(new Dirigente("Mariasole", "Galasso", 20,String.valueOf(ra.getNumDirigenti())));
		ra.assumiDipendente(new Dirigente("Massimo", "D'Avino", 59,String.valueOf(ra.getNumDirigenti())));
		ra.assumiDipendente(new Dirigente("Ferdinando", "Gelsomini", 61,String.valueOf(ra.getNumDirigenti())));
		ra.assumiDipendente(new Dirigente("Fulvio", "Carotenuto", 22,String.valueOf(ra.getNumDirigenti())));

		ra.assumiDipendente(new Quadro("Filippo", "Massaro", 51,String.valueOf(ra.getNumQuadri())));
		ra.assumiDipendente(new Quadro("Raffaele", "Catena", 52,String.valueOf(ra.getNumQuadri())));
		ra.assumiDipendente(new Quadro("Domenico", "Ricca", 43,String.valueOf(ra.getNumQuadri())));
		ra.assumiDipendente(new Quadro("Federica", "Infantile", 23,String.valueOf(ra.getNumQuadri())));

		ra.assumiDipendente(new Impiegato("Giovanni", "Della Casa", 52,String.valueOf(ra.getNumImpiegati())));
		ra.assumiDipendente(new Impiegato("Gennaro", "Fortino", 32,String.valueOf(ra.getNumImpiegati())));
		ra.assumiDipendente(new Impiegato("Gabriella", "Stanzione", 26,String.valueOf(ra.getNumImpiegati())));
		ra.assumiDipendente(new Impiegato("Rocco", "Menichino", 22, String.valueOf(ra.getNumImpiegati()),3));

		Fornitore f1 = new Fornitore("Edilizia Facile");
		f1.aggiungiProdotto(new Prodotto("Vite", "Alluminio", "", 0.5, 0.2, 0.1, 0.05, 0, 0, 0.25, 700));
		f1.aggiungiProdotto(new Prodotto("Bullone", "Metallo", "", 0.2, 0.5, 0.0, 0.05, 0, 0, 0.10, 600));
		f1.aggiungiProdotto(new Prodotto("Chiave Inglese 13\"", "Alluminio", "", 0.5, 15, 8, 4, 0, 0, 12, 648));
		f1.aggiungiProdotto(new Prodotto("Pennello", "Metallo/fibre", "", 0.1, 16, 6, 3, 0, 0, 6, 500));
		f1.aggiungiProdotto(new Prodotto("Calcestruzzo", "Sabbia/Ghiaia", "", 1, 50, 50, 50, 0, 0, 6.5, 900));
		f1.aggiungiProdotto(new Prodotto("Martello", "Legno/Ferro", "", 0.9, 30, 7, 3, 0, 0, 14, 300));
		f1.aggiungiProdotto(new Prodotto("Martello", "Legno/Ferro", "", 2.5, 45, 18, 8, 0, 0, 14, 100));
		f1.aggiungiProdotto(new Prodotto("Avvitatore Meccanico", "", "Black + Decker", 4, 43, 23, 12, 25, 3, 30, 90));
		f1.aggiungiProdotto(
				new Prodotto("Kit completo Trapano+varie punte", "", "Black + Decker", 7, 90, 90, 25, 40, 4, 150, 70));

		Fornitore f2 = new Fornitore("Napoletano Materiali di Napoletano Vincenzo");
		f2.aggiungiProdotto(new Prodotto("Ghiaia", "Ghiaia", "", 1, 50, 50, 50, 0, 0, 2.5, 4000));
		f2.aggiungiProdotto(new Prodotto("Terriccio", "Terriccio", "", 1, 50, 50, 50, 0, 0, 3.5, 4500));
		f2.aggiungiProdotto(new Prodotto("Cemento", "Argilla/Calcare/Sabbia", "", 1, 50, 50, 50, 0, 0, 4.5, 5500));
		f2.aggiungiProdotto(new Prodotto("Tufo", "Tufo", "", 1, 70, 70, 70, 0, 0, 6.7, 6450));
		f2.aggiungiProdotto(new Prodotto("Ardesia", "Ardesia", "", 1, 60, 60, 60, 0, 0, 3.5, 7450));
		f2.aggiungiProdotto(new Prodotto("Laterizio", "Argilla Impura", "", 1, 65, 65, 65, 0, 0, 9.5, 4500));
		f2.aggiungiProdotto(new Prodotto("Acciaio", "Acciaio", "", 0, 50, 50, 25, 0, 0, 12.5, 10000));
		f2.aggiungiProdotto(new Prodotto("Ghisa", "Ghisa", "", 1, 25, 25, 25, 0, 0, 3.5, 4510));
		f2.aggiungiProdotto(new Prodotto("Calcestruzzo", "Calcestruzzo", "", 1, 30, 30, 30, 0, 0, 6.5, 4780));

		Fornitore f3 = new Fornitore("Prodotti per Cantieri - Vendita all'ingrosso");
		f3.aggiungiProdotto(new Prodotto("Impalcatura di Acciaio", "Acciaio", "tmp", 3, 80, 80, 150, 0, 0, 19, 5400));
		f3.aggiungiProdotto(
				new Prodotto("Finestra", "Vetro rinforzato", "Trusso Vetrai", 3, 100, 10, 100, 0, 0, 16, 4780));
		f3.aggiungiProdotto(new Prodotto("Rete protettiva", "nylon", "", 0.3, 100, 1, 100, 0, 0, 4.5, 9700));
		f3.aggiungiProdotto(new Prodotto("Giravite Meccanico", "", "Black + Decker", 4, 20, 20, 10, 9, 2, 19, 540));
		f3.aggiungiProdotto(new Prodotto("Set Chiavi Inglesi misure 10-50", "Acciaio", "MaXPower", 9, 150, 150, 20, 0,
				3, 150, 460));
		f3.aggiungiProdotto(
				new Prodotto("Tubi idraulici diametro 25", "Ferro/Nichel", "MaXPower", 2.5, 2, 30, 30, 0, 0, 25, 4500));
		f3.aggiungiProdotto(new Prodotto("Giunzione a T tubo idraulico", "Ferro/Nichel", "MaXPower", 1.5, 30, 30, 30, 0,
				0, 10, 4560));
		f3.aggiungiProdotto(new Prodotto("Fili di rame con copertura in gomma isolante", "Rame/Polimeri di Cellulosa",
				"", 2, 300, 10, 10, 0, 0, 15, 8000));
		f3.aggiungiProdotto(
				new Prodotto("Prolunga 5m", "Rame/Polimeri di Cellulosa", "", 2, 5, 20, 10, 0, 5, 25, 9700));
		f3.aggiungiProdotto(
				new Prodotto("Impastatore Meccanico", "", "SOMAIN ITALIA", 5, 150, 30, 50, 40, 10, 80, 800));

		Fornitore f4 = new Fornitore("Macchine per Cantieri e Attrezzi utili");
		f4.aggiungiProdotto(new Prodotto("Betoniera", "", "CTE", 40, 90, 110, 130, 80, 6, 160, 400));
		f4.aggiungiProdotto(new Prodotto("Mototroncatrice", "", "CTE", 35, 60, 60, 50, 35, 5, 130, 250));
		f4.aggiungiMacchina(new MacchineDaCantiere("Gru", "Alimak Keh", 4560, 650, 15000));
		f4.aggiungiMacchina(new MacchineDaCantiere("Carrelli elevatori", "Hikowa", 320, 30, 2500));
		f4.aggiungiMacchina(
				new MacchineDaCantiere("Impianto mobile calcestruzzo TECNOMIX ", "SAMI", 35000, 650, 27000));
		f4.aggiungiMacchina(new MacchineDaCantiere("Robot di piega", "SCHNELL", 340, 150, 15000));
		f4.aggiungiMacchina(new MacchineDaCantiere("Contenitori Industriali", "CARPENTERIE MUSSINI", 2500, 0, 300));
		f4.aggiungiMacchina(new MacchineDaCantiere("GENIE Z 40/23 N RJ", "CTE ", 225, 345, 32000));

		ra.aggiungiFornitore(f1);
		ra.aggiungiFornitore(f2);
		ra.aggiungiFornitore(f3);
		ra.aggiungiFornitore(f4);

		ra.aggiungiProdottoAlMagazzino(
				new Prodotto("Impalcatura di Acciaio", "Acciaio", "tmp", 3, 80, 80, 150, 0, 0, 19, 60));
		ra.aggiungiProdottoAlMagazzino(
				new Prodotto("Prolunga 5m", "Rame/Polimeri di Cellulosa", "", 2, 5, 20, 10, 0, 5, 25, 97));
		ra.aggiungiProdottoAlMagazzino(
				new Prodotto("Impastatore Meccanico", "", "SOMAIN ITALIA", 5, 150, 30, 50, 40, 10, 80, 8));
		ra.aggiungiProdottoAlMagazzino(new Prodotto("Ghiaia", "Ghiaia", "", 1, 50, 50, 50, 0, 0, 2.5, 400));
		ra.aggiungiProdottoAlMagazzino(
				new Prodotto("Tubi idraulici diametro 25", "Ferro/Nichel", "MaXPower", 2.5, 2, 30, 30, 0, 0, 25, 45));
		ra.aggiungiProdottoAlMagazzino(
				new Prodotto("Cemento", "Argilla/Calcare/Sabbia", "", 1, 50, 50, 50, 0, 0, 4.5, 550));
		ra.aggiungiProdottoAlMagazzino(new Prodotto("Fili di rame con copertura in gomma isolante",
				"Rame/Polimeri di Cellulosa", "", 2, 300, 10, 10, 0, 0, 15, 85));
		ra.aggiungiMacchinaAlMagazzino(new MacchineDaCantiere("Robot di piega", "SCHNELL", 340, 150, 15000));
		ra.aggiungiMacchinaAlMagazzino(new MacchineDaCantiere("Gru", "Alimak Keh", 4560, 650, 15000));
		ra.aggiungiMacchinaAlMagazzino(new MacchineDaCantiere("GENIE Z 40/23 N RJ", "CTE ", 225, 345, 32000));

		ro.apriCantiere(600000, 451.112, 15784.1547, 150, (Dirigente) ra.getDipendenti().get(14));
		Squadra s = new Squadra((Quadro) ra.getDipendenti().get(21));
		Squadra s1 = new Squadra((Quadro) ra.getDipendenti().get(22));
		try {
			s.aggiungiOperaio((Operaio) ra.getDipendenti().get(0));
			s.aggiungiOperaio((Operaio) ra.getDipendenti().get(1));
			s.aggiungiOperaio((Operaio) ra.getDipendenti().get(2));
			s.aggiungiOperaio((Operaio) ra.getDipendenti().get(3));
			s1.aggiungiOperaio((Operaio) ra.getDipendenti().get(4));
			s1.aggiungiOperaio((Operaio) ra.getDipendenti().get(5));
			s1.aggiungiOperaio((Operaio) ra.getDipendenti().get(6));
			s1.aggiungiOperaio((Operaio) ra.getDipendenti().get(7));

		} catch (OperaioOccupatoException e) {
			e.printStackTrace();
		}
		ro.assegnaSquadra(ro.getCantieri().get(0), s);
		ro.assegnaSquadra(ro.getCantieri().get(0), s1);

		ArrayList<Prodotto> materiali = new ArrayList<Prodotto>();
		materiali.add(
				ra.getProdotto(new Prodotto("Impalcatura di Acciaio", "Acciaio", "tmp", 3, 80, 80, 150, 0, 0, 19, 6)));
		materiali.add(
				new Prodotto("Tubi idraulici diametro 25", "Ferro/Nichel", "MaXPower", 2.5, 2, 30, 30, 0, 0, 25, 5));
		materiali.add(new Prodotto("Impastatore Meccanico", "", "SOMAIN ITALIA", 5, 150, 30, 50, 40, 10, 80, 2));

		ro.getCantieri().get(0).assegnaMateriali(materiali);
		ro.getCantieri().get(0).assegnaMacchina(new MacchineDaCantiere("Gru", "Alimak Keh", 4560, 650, 15000));
		ro.getCantieri().get(0).assegnaMacchina(new MacchineDaCantiere("GENIE Z 40/23 N RJ", "CTE ", 225, 345, 32000));
		ro.getCantieri().get(0).assegnaMacchina(new MacchineDaCantiere("Robot di piega", "SCHNELL", 340, 150, 15000));
	}

}
