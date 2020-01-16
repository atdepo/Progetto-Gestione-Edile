package approvviggionamento;

import java.util.ArrayList;
/**
 *	Questa classe implementa una fattura o più nota come scontrino fiscale
 *  conterrà tutti gli approvigionamenti necessari all'azienda che richiede di acquistare
 *  determinati prodotti da un fornitore 
 *  e conterrà il saldo dell'ordine
 *  è possibile inoltre aggiungere e rimuovere un numero stabilito di unità
 * 
 */
public class Fattura {
	
	private Fornitore fornitore;
	private ArrayList<Prodotto> prodotti;
	private double saldoFattura;
	
	public Fattura(Fornitore f) {
		prodotti=new ArrayList<Prodotto>();
		fornitore=f;
		saldoFattura=0;
	}
	
	public void addProd(int elem,int quantita) {
		for(int i=0;i<quantita;i++) {
			Prodotto pro=fornitore.getProdottiInVendita().get(elem);
			prodotti.add(pro);
			saldoFattura+=pro.getPrezzo();
		}
	}
	
	public double getSaldoFattura() {
		return saldoFattura;
	}
	/**
	 * metodo removeAllProd permette di rimuovere tutti i prodotti caricati nella fattura 
	 * di un determinato prodotto che fornisce il fornitore 
	 * @param elem
	 */
	public void removeAllProd(int elem){
		Prodotto proRemove=fornitore.getProdottiInVendita().get(elem);
		for(int i=0;i<prodotti.size();i++){
			Prodotto p=prodotti.get(i);
			if(p.equalsCaratteristiche(proRemove)) {
				prodotti.remove(i);
			}
		}
	}
	/**
	 * metodo removeQuantProd permette di rimuovere una quantità (specificata) di un prodotto caricati nella fattura 
	 * di un determinato prodotto che fornisce il fornitore 
	 * @param elem
	 * @param quant
	 */
	public void removeQuantProd(int elem,int quant){
		Prodotto proRemove=fornitore.getProdottiInVendita().get(elem);
		for(int i=0,j=0;i<prodotti.size()&&j<quant;i++){
			Prodotto p=prodotti.get(i);
			if(p.equalsCaratteristiche(proRemove)) {
				prodotti.remove(i);
				j++;
			}
		}
	}
	
	
	//implementare la funzione di pagamento
}
