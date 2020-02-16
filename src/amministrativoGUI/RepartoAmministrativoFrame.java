package amministrativoGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;
import operativo.RepartoOperativo;
import utilities.Azienda;

public class RepartoAmministrativoFrame extends JFrame {

	JList<String> dirigenti;
	JList<String> operai;
	JList<String> quadri;
	JList<String> impiegati;
	JList<String> prodotti;
	JList<String> macchine;
	JList<String> fornitori;

	DefaultListModel<String> mod;
	DefaultListModel<String> mod1;
	DefaultListModel<String> mod2;
	DefaultListModel<String> mod3;
	DefaultListModel<String> mod4;
	DefaultListModel<String> mod5;
	DefaultListModel<String> mod6;

	ArrayList<Dirigente> dir;
	ArrayList<Operaio> op;
	ArrayList<Quadro> qua;
	ArrayList<Impiegato> imp;
	ArrayList<Dipendente> dipendenti;
	ArrayList<Prodotto> prod;
	ArrayList<MacchineDaCantiere> ma;
	ArrayList<Fornitore> fo;

	JTextArea spec;

	JFrame root;

	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	Azienda azienda;

	public RepartoAmministrativoFrame(Azienda a) {
		super("Reparto Amministrativo");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(600, 350);
		this.setSize(1050, 700);
		this.setResizable(false);
		root = this;
		this.setLayout(new GridBagLayout());
		azienda = a;
		repartoAmministrativo = a.getRepartoAmministrativo();
		repartoOperativo = a.getRepartoOperativo();

		dipendenti = repartoAmministrativo.getDipendenti();
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(createListaDipendenti(), c);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		// this.add(createSpec(), c);
		this.add(creaInfo(), c);
	}

	public JPanel creaInfo() {
		JPanel p= new JPanel();
		p.setPreferredSize(new Dimension(600,260));
		p.setLayout(new GridLayout(1, 2));
		p.add(creaMagazzino());
		p.add(createListaFornitori());
		return p;
	}
	
	public JPanel creaMagazzino() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(450, 260));
		p.setLayout(new GridLayout(1, 2));
		p.add(createListaProdotti());
		p.add(createListaMacchine());
		p.setBorder(BorderFactory.createTitledBorder("Magazzino"));
		return p;
	}

	public JScrollPane createListaProdotti() {
		mod4 = new DefaultListModel<String>();
		prod = new ArrayList<Prodotto>();
		for (Prodotto p : repartoAmministrativo.getMagazzino().getProdottiInMagazzino()) {
			mod4.addElement(p.getNome());
			prod.add(p);
		}
		mod4.addElement("Compra Prodotto");
		prodotti = new JList<String>(mod4);
		prodotti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = prodotti.getSelectedIndex();
				if (index == prod.size()) {
					// TODO aggiunta prodotto
				} else if (index >= 0) {
					spec.setText(prod.get(index).getCaratteristicheProdotto());
				}
			}
		});
		prodotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane p = new JScrollPane(prodotti);
		p.setBorder(BorderFactory.createTitledBorder("Prodotti"));
		return p;
	}

	public JScrollPane createListaMacchine() {
		mod5 = new DefaultListModel<String>();
		ma = new ArrayList<MacchineDaCantiere>();
		for (MacchineDaCantiere m : repartoAmministrativo.getMagazzino().getMacchineInMagazzino()) {
			mod5.addElement(m.getNome());
			ma.add(m);
		}
		mod5.addElement("Compra Macchina");
		macchine = new JList<String>(mod5);
		macchine.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = macchine.getSelectedIndex();
				if (index == ma.size()) {
					// TODO aggiunta macchina
				} else if (index >= 0) {
					spec.setText(ma.get(index).getCaratteristiche());
				}
			}
		});
		macchine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane p = new JScrollPane(macchine);
		p.setBorder(BorderFactory.createTitledBorder("Macchine da Cantiere"));

		return p;
	}

	public JScrollPane createListaFornitori() {

		mod6 = new DefaultListModel<String>();
		fo = new ArrayList<Fornitore>();
		for (Fornitore f : repartoAmministrativo.getFornitori()) {
			mod6.addElement(f.getNomeFornitore());
			fo.add(f);
		}
		mod6.addElement("Aggiungi Fornitore");
		fornitori = new JList<String>(mod6);
		fornitori.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = fornitori.getSelectedIndex();
				if (index == fo.size()) {
					// TODO aggiunta prodotto
				} else if (index >= 0) {
					// spec.setText(fo.get(index).);
					// TODO una finestra a parte o nei dettagli
				}
			}
		});
		fornitori.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane p = new JScrollPane(fornitori);
		p.setBorder(BorderFactory.createTitledBorder("Fornitori"));
		return p;
	}

	public JPanel createListaDipendenti() {

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(600, 400));
		mainPanel.setLayout(new GridLayout(1, 4));
		mainPanel.add(createListaDirigenti());
		mainPanel.add(createListaOperai());
		mainPanel.add(createListaQuadri());
		mainPanel.add(createListaImpiegati());
		mainPanel.setBorder(BorderFactory.createTitledBorder("Dipendenti"));
		return mainPanel;
	}

	public JScrollPane createSpec() {
		spec = new JTextArea();
		spec.setEditable(false);
		JScrollPane s = new JScrollPane(spec);
		s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JViewport view = s.getViewport();
		Dimension preferredSize = new Dimension(590, 220);
		view.setPreferredSize(preferredSize);
		s.setBorder(BorderFactory.createTitledBorder("Dettagli"));
		return s;
	}

	private JScrollPane createListaDirigenti() {
		mod = new DefaultListModel<String>();
		dir = new ArrayList<Dirigente>();
		for (Dipendente d : dipendenti) {
			if (Dipendente.isDirigente(d)) {
				dir.add((Dirigente) d);
				mod.addElement(d.getNome() + " " + d.getCognome());
			}
		}
		mod.addElement("Aggiungi Dirigente");
		dirigenti = new JList<String>(mod);
		dirigenti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int index = dirigenti.getSelectedIndex();
				if (index == dir.size()) {
					root.dispose();
					new AssunzioneDipendentiFrame(4, azienda);
				} else if (index >= 0) {
					spec.setText(dir.get(index).toString());
				}
				quadri.clearSelection();
				operai.clearSelection();
				impiegati.clearSelection();
			}
		});
		dirigenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane pane = new JScrollPane(dirigenti);
		pane.setBorder(BorderFactory.createTitledBorder("Dirigenti"));
		return pane;
	}

	private JScrollPane createListaOperai() {
		mod1 = new DefaultListModel<String>();
		op = new ArrayList<Operaio>();
		for (Dipendente d : dipendenti) {
			if (Dipendente.isOperaio(d)) {
				op.add((Operaio) d);
				mod1.addElement(d.getNome() + " " + d.getCognome());
			}
		}
		mod1.addElement("Aggiungi Operaio");

		operai = new JList<String>(mod1);
		operai.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		operai.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				int index = operai.getSelectedIndex();
				if (index == op.size()) {
					root.dispose();
					new AssunzioneDipendentiFrame(2, azienda);
				} else if (index >= 0) {
					spec.setText(op.get(index).toString());
				}
				dirigenti.clearSelection();
				quadri.clearSelection();
				impiegati.clearSelection();
			}
		});
		JScrollPane pane = new JScrollPane(operai);
		pane.setBorder(BorderFactory.createTitledBorder("Operai"));
		return pane;
	}

	private JScrollPane createListaQuadri() {
		mod2 = new DefaultListModel<String>();
		qua = new ArrayList<Quadro>();
		for (Dipendente d : dipendenti) {
			if (Dipendente.isQuadro(d)) {
				qua.add((Quadro) d);
				mod2.addElement(d.getNome() + " " + d.getCognome());
			}
		}
		mod2.addElement("Aggiungi Quadro");

		quadri = new JList<String>(mod2);
		quadri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		quadri.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				int index = quadri.getSelectedIndex();
				if (index == qua.size()) {
					root.dispose();
					new AssunzioneDipendentiFrame(3, azienda);
				} else if (index >= 0) {
					spec.setText(qua.get(index).toString());
				}
				dirigenti.clearSelection();
				operai.clearSelection();
				impiegati.clearSelection();
			}
		});
		JScrollPane pane = new JScrollPane(quadri);
		pane.setBorder(BorderFactory.createTitledBorder("Quadri"));
		return pane;
	}

	private JScrollPane createListaImpiegati() {
		mod3 = new DefaultListModel<String>();
		imp = new ArrayList<Impiegato>();
		for (Dipendente d : dipendenti) {
			if (Dipendente.isImpiegato(d)) {
				imp.add((Impiegato) d);
				mod3.addElement(d.getNome() + " " + d.getCognome());
			}
		}
		mod3.addElement("Aggiungi Impiegato");

		impiegati = new JList<String>(mod3);
		impiegati.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		impiegati.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				int index = impiegati.getSelectedIndex();
				if (index == imp.size()) {
					root.dispose();
					new AssunzioneDipendentiFrame(1, azienda);
				} else if (index >= 0) {
					spec.setText(imp.get(index).toString());
				}
				dirigenti.clearSelection();
				operai.clearSelection();
				quadri.clearSelection();
			}
		});
		JScrollPane pane = new JScrollPane(impiegati);
		pane.setBorder(BorderFactory.createTitledBorder("Impiegati"));
		return pane;
	}

}
