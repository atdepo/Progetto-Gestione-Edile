package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.OperaioOccupatoException;
import operativo.RepartoOperativo;
import operativo.Squadra;

public class CreazioneSquadraPanel extends JPanel{

	JList<Operaio> operai;
	JList<Quadro> quadri;
	
	DefaultListModel<Operaio> mod1;
	DefaultListModel<Quadro> mod2;
	
	ArrayList<Operaio> op;
	ArrayList<Quadro> qua;
	
	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	
	JScrollPane operaiPanel;
	JScrollPane quadriPanel;
	
	JPanel mainPane;
	JPanel contentPane;
	
	CardLayout cl;
	
	JButton assegna;
	
	Squadra squadra;
	
	public CreazioneSquadraPanel(RepartoOperativo ro,RepartoAmministrativo ra) {
		repartoAmministrativo=ra;
		repartoOperativo=ro;
		cl= new CardLayout();
		mainPane= new JPanel();
		contentPane= new JPanel();
		assegna= new JButton("Assegna");
		assegna.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(quadriPanel.isVisible()) {
					Quadro toAdd=qua.get(quadri.getSelectedIndex());
					squadra= new Squadra(toAdd);
					quadriPanel.setVisible(false);
					operaiPanel.setVisible(true);
					revalidate();
					repaint(); 
				}
				else { 
					List<Operaio> add=operai.getSelectedValuesList();
					for(Operaio o:add) {
						try {
							squadra.aggiungiOperaio(o);
						} 
						catch (OperaioOccupatoException e1) {
							e1.printStackTrace();
						}
					}
					ro.assegnaSquadra(ro.getCantieri().get(ro.getCantieri().size()-1), squadra);
					//TODO chiamata lista materiali
				}
				
			}
		});
		this.add(mainPane);
		mainPane.setLayout(cl);
		mainPane.add(contentPane,"main");
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=1.0;
		c.gridx=0;
		c.gridy=0;
		c.gridheight=7;
		c.gridwidth=7;
		c.anchor=GridBagConstraints.FIRST_LINE_START;
		contentPane.add(createListaOperai(),c);
		c.weightx=1.0;
		c.weighty=1.0;
		c.gridx=0;
		c.gridy=8;
		c.gridheight=7;
		c.gridwidth=7;
		c.anchor=GridBagConstraints.LINE_START;
		contentPane.add(createListaQuadri(),c);
		c.weightx=1.0;
		c.weighty=1.0;
		c.gridx=10;
		c.gridy=10;
		c.anchor=GridBagConstraints.WEST;
		contentPane.add(assegna,c);
	}
	
	public JScrollPane createListaOperai() {
		mod1= new DefaultListModel<Operaio>();
		op= new ArrayList<Operaio>();
		for(Dipendente d:repartoAmministrativo.getDipendenti()) {
			if(Dipendente.isOperaio(d)&&!d.isImpegnato()) {
				op.add((Operaio)d);
			}
		}
		
		for(Operaio o:op) {
			mod1.addElement(o);
		}
		operai= new JList<Operaio>(mod1);
		operai.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		operai.setVisibleRowCount(-1);
		operaiPanel= new JScrollPane(operai);
		operaiPanel.setVisible(false);
		operaiPanel.setPreferredSize(new Dimension(400, 400));
		operaiPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		operaiPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JViewport view=operaiPanel.getViewport();
		int h = view.getPreferredSize().height;
        int w = 680;
        Dimension preferredSize = new Dimension(w, h);
        view.setPreferredSize(preferredSize);
        operaiPanel.setBorder(BorderFactory.createTitledBorder("Operai"));
		return operaiPanel;
	}
	
	public JScrollPane createListaQuadri() {
		mod2= new DefaultListModel<Quadro>();
		qua= new ArrayList<Quadro>();
		for(Dipendente d:repartoAmministrativo.getDipendenti()) {
			if(Dipendente.isQuadro(d)&&!d.isImpegnato()) {
				qua.add((Quadro)d);
			}
		}
		
		for(Quadro q:qua) {
			mod2.addElement(q);
		}
		quadri= new JList<Quadro>(mod2);
		quadri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		quadriPanel= new JScrollPane(quadri);
		quadriPanel.setPreferredSize(new Dimension(400, 400));
		quadriPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		quadriPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JViewport view=quadriPanel.getViewport();
		int h = view.getPreferredSize().height;
        int w = 680;
        Dimension preferredSize = new Dimension(w, h);
        view.setPreferredSize(preferredSize);
		quadriPanel.setBorder(BorderFactory.createTitledBorder("Caposquadra"));
		return quadriPanel;
	}
}
