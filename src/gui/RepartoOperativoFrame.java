package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Operaio;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import operativo.Squadra;
import utilities.Azienda;

public class RepartoOperativoFrame extends JFrame{
	
	private static final long serialVersionUID = 8219427827473530551L;
	RepartoOperativo ro;
	RepartoAmministrativo ra;
	
	JList<String> cantieri;
	JList<String> squadre;
	JList<String> materiali;
	JList<String> macchine;
	DefaultListModel<String> mod;
	DefaultListModel<String> mod1;
	DefaultListModel<String> mod2;
	DefaultListModel<String> mod3;
	
	ArrayList<Prodotto> prod;
	ArrayList<MacchineDaCantiere> ma;
	ArrayList<Squadra> sq;
	ArrayList<Cantiere> cant;
	
	JPanel listaCantieri;
	JPanel infoCantiere;
	JPanel infoPanel;
	
	JLabel respCantiere;
	JLabel valore;
	JLabel est;
	JLabel nSquadre;
	JLabel latCantiere;
	JLabel lonCantiere;
	
	JTextField setResp;
	JTextField setVal;
	JTextField setEst;
	JTextField setNSq;
	JTextField setLat;
	JTextField setLon;
	
	JTextArea spec;
	
	CardLayout cl;
	
	JFrame root;
	
	public RepartoOperativoFrame(Azienda a) {
		this.setVisible(true);
		this.setSize(800,620);
		this.setLocationRelativeTo(null);
		ro=a.getRepartoOperativo();
		ra=a.getRepartoAmministrativo();
		cant=ro.getCantieri();
		this.setLayout(new GridBagLayout());
		cl= new CardLayout();
		infoPanel= new JPanel();
		infoPanel.setLayout(cl);

		root=this;
		GridBagConstraints c= new GridBagConstraints();
		
		cl= new CardLayout();
		infoPanel= new JPanel();
		infoPanel.setLayout(cl);
		c.weightx=1.0;
		c.weighty=1.0;
		c.gridx=0;
		c.gridy=0;
		c.gridheight=10;
		c.gridwidth=4;
		c.fill=GridBagConstraints.VERTICAL;
		c.ipadx=20;
		c.anchor=GridBagConstraints.FIRST_LINE_END;
		this.add(infoPanel,c);
		
		c.weightx=1.0;
		c.weighty=1.0;
		c.gridx=0;
		c.gridy=0;
		c.gridheight=10;
		c.gridwidth=8;
		c.fill=GridBagConstraints.VERTICAL;
		c.anchor=GridBagConstraints.FIRST_LINE_START;
		
		this.add(createListaCantieriAperti(),c);
	}
	
	public JPanel createListaCantieriAperti() {
		listaCantieri= new JPanel();
		mod= new DefaultListModel<String>();
		for(int i=0;i<cant.size();i++) {
		
		mod.addElement("Cantiere "+(++i));
		}
			mod.addElement("Aggiungi Cantiere");
		cantieri= new JList<String>(mod);
		cantieri.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println("called");
				int index=cantieri.getSelectedIndex();
				if(cant.size()==index) {
					root.dispose();
					new NuovoCantiereFrame(ro, ra);
				}
				else {
				System.out.println("indice= "+index);
				Cantiere c=ro.getCantieri().get(index);
				prod=c.getMaterialiDisponibili();
				ma=c.getMacchineImpiegate();
				sq=c.getSquadre();
				infoPanel.add(createInfo(c),"info");
				cl.show(infoPanel, "info");
				}
			}
		});
		
		listaCantieri.add(cantieri);
		listaCantieri.setBorder(BorderFactory.createTitledBorder("Cantieri Aperti"));
		revalidate();
		repaint();
		return listaCantieri;
	
	}
	
	public JPanel createInfo(Cantiere c) {
		JPanel infoPane= new JPanel();
		infoPane.setLayout(new GridLayout(4,1));
		infoPane.add(createInfoCantiere(c));
		infoPane.add(createInfoSquadre());
		infoPane.add(createInfoMateriali());
		infoPane.add(createInfoMacchine());
		spec= new JTextArea();
		JScrollPane p= new JScrollPane(spec);
		spec.setEditable(false);
		JPanel toReturn= new JPanel();
		toReturn.setLayout(new GridLayout(1,2));
		toReturn.add(infoPane);
		toReturn.add(p);
		
		return toReturn;
	}
	
	public JPanel createInfoCantiere(Cantiere c) {
		infoCantiere= new JPanel();
		
		infoCantiere.setLayout(new GridLayout(6,2));
		infoCantiere.setBorder(BorderFactory.createTitledBorder("Info Cantiere"));
		respCantiere= new JLabel("Responsabile");
		valore= new JLabel("Valore");
		est= new JLabel("Estensione");
		nSquadre= new JLabel("Numero Squadre");
		latCantiere= new JLabel("Latitudine Cantiere");
		lonCantiere= new JLabel("Longitudine Cantiere");
		setEst= new JTextField();
		setEst.setEditable(false);
		setEst.setText(String.valueOf(c.getEstensione()));
		setLat= new JTextField();
		setLat.setEditable(false);
		setLat.setText(String.valueOf(c.getPosizioneCantiere().getlatitudine()));
		setLon= new JTextField();
		setLon.setEditable(false);
		setLon.setText(String.valueOf(c.getPosizioneCantiere().getlongitudine()));
		setNSq= new JTextField();
		setNSq.setEditable(false);
		setNSq.setText(String.valueOf(c.getSquadre().size()));
		setResp= new JTextField();
		setResp.setEditable(false);
		Dipendente d=(Dipendente)c.getResponsabile();
		setResp.setText(d.getNome()+" "+d.getCognome());
		setVal= new JTextField();
		setVal.setEditable(false);
		setVal.setText(String.valueOf(c.getValore()));
		
		infoCantiere.add(respCantiere);
		infoCantiere.add(setResp);
		infoCantiere.add(valore);
		infoCantiere.add(setVal);
		infoCantiere.add(latCantiere);
		infoCantiere.add(setLat);
		infoCantiere.add(lonCantiere);
		infoCantiere.add(setLon);
		infoCantiere.add(est);
		infoCantiere.add(setEst);
		infoCantiere.add(nSquadre);
		infoCantiere.add(setNSq);
		
		return infoCantiere;
	}
	
	
	public JPanel createInfoSquadre() {
		JPanel pane= new JPanel();
		mod1= new DefaultListModel<String>();
		for(Squadra s:sq) {
			mod1.addElement("Squadra di "+s.getCapoSquadra().getNome()+" "+s.getCapoSquadra().getCognome());
		}
		squadre= new JList<String>(mod1);
		
		squadre.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				Squadra tmp=sq.get(squadre.getSelectedIndex());
				String text=tmp.getCapoSquadra().toString()+"\n";
				for(Operaio o:tmp.getOperai()) {
					text+=(o+"\n");
				}
				spec.setText(text);
			}
			
		
		});
		
		pane.add(squadre);
		pane.setBorder(BorderFactory.createTitledBorder("Squadre"));
		return pane;
		
	}
	
	public JScrollPane createInfoMateriali() {
		JPanel pane= new JPanel();
		mod2= new DefaultListModel<String>();
		for(Prodotto p:prod) {
			mod2.addElement(p.getNome());
		}
		

		materiali= new JList<String>(mod2);
		materiali.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				Prodotto p=prod.get(materiali.getSelectedIndex());
				spec.setText(p.getCaratteristicheProdotto());
				
			}
		});
		
		pane.setBorder(BorderFactory.createTitledBorder("Materiali"));
		pane.add(materiali);
		JScrollPane r= new JScrollPane(pane);
		return r;
		
	}
	
	public JScrollPane createInfoMacchine() {
		JPanel pane= new JPanel();
		mod3= new DefaultListModel<String>();
		
		for(MacchineDaCantiere m:ma) {
			mod3.addElement(m.getNome());
		}
		
		macchine= new JList<String>(mod3);
		macchine.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
			
				MacchineDaCantiere m=ma.get(macchine.getSelectedIndex());
				spec.setText(m.getCaratteristiche());
			}
		});
		pane.setBorder(BorderFactory.createTitledBorder("Macchine"));
		pane.add(macchine);
		JScrollPane r= new JScrollPane(pane);
		return r;

	}
	
}
