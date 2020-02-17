package operativoGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import amministrativoGUI.RepartoAmministrativoFrame;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Responsabile;
import gui.InitFrame;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import operativo.Squadra;
import utilities.Azienda;
import utilities.Comparatore;
import utilities.Ordinatore;

public class RepartoOperativoFrame extends JFrame {

	private static final long serialVersionUID = 8219427827473530551L;
	RepartoOperativo ro;
	RepartoAmministrativo ra;
	Azienda azienda;

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
	JPanel inputPane;

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

	JComboBox<String> sceltaSquadre;
	JComboBox<String> sceltaProdotti;
	JComboBox<String> sceltaMateriali;
	JComboBox<String> dip;
	Ordinatore<Squadra> ord1;
	Ordinatore<MacchineDaCantiere> ord2;
	Ordinatore<Prodotto> ord3;

	Comparatore<Squadra> comp1;
	Comparatore<MacchineDaCantiere> comp2;
	Comparatore<Prodotto> comp3;

	JTextArea spec;

	JButton libera;
	JButton assumiButton;
	JButton deleteButton;
	JButton liberaResponsabile;

	JMenu menu;
	JMenuBar bar;
	JMenuItem item;
	JMenuItem item2;
	CardLayout cl;

	JFrame root;

	public RepartoOperativoFrame(Azienda a) {
		super("Reparto Operativo");
		this.setVisible(true);
		this.setSize(1000, 620);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root = this;

		bar = new JMenuBar();
		menu = new JMenu("Opzioni");
		item = new JMenuItem("Vai al Reparto Amministrativo");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.dispose();
				new RepartoAmministrativoFrame(azienda);
			}
		});
		item2 = new JMenuItem("Torna alla Home");
		item2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				root.dispose();
				new InitFrame(azienda);
			}
		});
		menu.add(item);
		menu.add(item2);
		bar.add(menu);
		this.setJMenuBar(bar);

		ro = a.getRepartoOperativo();
		ra = a.getRepartoAmministrativo();
		azienda = a;
		cant = ro.getCantieri();
		this.setLayout(new GridBagLayout());
		cl = new CardLayout();
		infoPanel = new JPanel();
		infoPanel.setLayout(cl);

		dip = new JComboBox<String>();
		libera = new JButton("Libera Dipendente");
		assumiButton = new JButton("Assumi Operaio");
		GridBagConstraints c = new GridBagConstraints();

		cl = new CardLayout();
		infoPanel = new JPanel();
		infoPanel.setLayout(cl);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 6;
		c.gridy = 0;
		c.gridheight = 10;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.VERTICAL;
		c.ipadx = 20;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.add(infoPanel, c);

		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 10;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;

		this.add(createListaCantieriAperti(), c);

		c.gridx = -1;
		c.gridy = -1;
		c.gridheight = 5;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.WEST;

		this.add(sceltaCriterio(), c);

	}

	public JPanel sceltaCriterio() {
		inputPane = new JPanel();
		inputPane.setVisible(false);
		inputPane.setPreferredSize(new Dimension(300, 400));
		inputPane.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		deleteButton = new JButton();
		deleteButton.setVisible(false);
		deleteButton.addActionListener(new Delete());
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(0, -120, 0, 0);
		inputPane.add(deleteButton, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(130, -0, 30, 0);
		inputPane.add(createSquadreBox(), c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(60, 0, 0, 0);
		inputPane.add(createProdottiBox(), c);

		c.insets = new Insets(100, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 3;
		inputPane.add(createMacchineBox(), c);

		return inputPane;

	}

	public JComboBox<String> createSquadreBox() {
		sceltaSquadre = new JComboBox<String>();
		sceltaSquadre.setBorder(BorderFactory.createTitledBorder("Ordina Squadre"));
		sceltaSquadre.addItem("Ordine lessicografico crescente");
		sceltaSquadre.addItem("Ordine crescente n°operai");
		sceltaSquadre.addItem("Ordine descrescente n°operai");
		sceltaSquadre.addItem("Ordine lessicografico descrescente");
		sceltaSquadre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int index = sceltaSquadre.getSelectedIndex();
				if (index == 0) {
					comp1 = (a, b) -> {
						return a.getCapoSquadra().getNome().concat(a.getCapoSquadra().getCognome())
								.compareTo(b.getCapoSquadra().getNome().concat(b.getCapoSquadra().getCognome()));
					};
				} else if (index == 1) {
					comp1 = (a, b) -> {
						return a.getNumeroOperai() - b.getNumeroOperai();
					};
				} else if (index == 2) {
					comp1 = (a, b) -> {
						return -(a.getNumeroOperai() - b.getNumeroOperai());
					};
				} else if (index == 3) {
					comp1 = (a, b) -> {
						return b.getCapoSquadra().getNome().concat(b.getCapoSquadra().getCognome())
								.compareTo(a.getCapoSquadra().getNome().concat(a.getCapoSquadra().getCognome()));
					};
				}

				ord1 = new Ordinatore<Squadra>(comp1);
				sq = ord1.ordina(sq);
				mod1.removeAllElements();
				for (Squadra s : sq) {
					mod1.addElement(
							"Squadra di " + s.getCapoSquadra().getNome() + " " + s.getCapoSquadra().getCognome());
				}
				mod1.addElement("Aggiungi Squadra");
			}
		});
		return sceltaSquadre;
	}

	public JComboBox<String> createMacchineBox() {
		sceltaMateriali = new JComboBox<String>();
		sceltaMateriali.setBorder(BorderFactory.createTitledBorder("Ordina Macchine"));
		sceltaMateriali.addItem("Ordine alfabetico");
		sceltaMateriali.addItem("Peso crescente");
		sceltaMateriali.addItem("Peso decrescente");
		sceltaMateriali.addItem("Potenza crescente");
		sceltaMateriali.addItem("Potenza decrescente");
		sceltaMateriali.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int index = sceltaMateriali.getSelectedIndex();
				if (index == 0) {
					comp2 = (a, b) -> {
						return a.getNome().compareTo(b.getNome());
					};
				} else if (index == 1) {
					comp2 = (a, b) -> {
						return (int) (a.getPeso() - b.getPeso());
					};
				} else if (index == 2) {
					comp2 = (a, b) -> {
						return (int) -(a.getPeso() - b.getPeso());
					};
				} else if (index == 3) {
					comp2 = (a, b) -> {
						return (int) (a.getPotenza() - b.getPotenza());
					};
				} else if (index == 4) {
					comp2 = (a, b) -> {
						return (int) -(a.getPotenza() - b.getPotenza());
					};
				}
				ord2 = new Ordinatore<MacchineDaCantiere>(comp2);
				ord2.ordina(ma);
				mod3.removeAllElements();
				for (MacchineDaCantiere m : ma) {
					mod3.addElement(m.getNome());
				}
				mod3.addElement("Aggiungi Macchina");
			}
		});
		return sceltaMateriali;

	}

	public JComboBox<String> createProdottiBox() {
		sceltaProdotti = new JComboBox<String>();
		sceltaProdotti.setBorder(BorderFactory.createTitledBorder("Ordina Prodotti"));
		sceltaProdotti.addItem("Peso crescente");
		sceltaProdotti.addItem("Peso decrescente");
		sceltaProdotti.addItem("Disponibilità crescente");
		sceltaProdotti.addItem("Disponibilità decrescente");
		sceltaProdotti.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int index = sceltaProdotti.getSelectedIndex();
				if (index == 0) {
					comp3 = (a, b) -> {
						return (int) (a.getPeso() - b.getPeso());
					};
				} else if (index == 1) {
					comp3 = (a, b) -> {
						return (int) -(a.getPeso() - b.getPeso());
					};
				} else if (index == 2) {
					comp3 = (a, b) -> {
						return a.getNumeroPezziDisponibili() - b.getNumeroPezziDisponibili();
					};
				} else if (index == 3) {
					comp3 = (a, b) -> {
						return -(a.getNumeroPezziDisponibili() - b.getNumeroPezziDisponibili());
					};
				}

				ord3 = new Ordinatore<Prodotto>(comp3);
				ord3.ordina(prod);
				mod2.removeAllElements();
				for (Prodotto p : prod) {
					mod2.addElement(p.getNome());
				}
				mod2.addElement("Aggiungi Materiali");
			}
		});
		return sceltaProdotti;
	}

	public JPanel createListaCantieriAperti() {
		listaCantieri = new JPanel();
		liberaResponsabile = new JButton("Libera Responsabile");
		liberaResponsabile.addActionListener(new Libera());
		mod = new DefaultListModel<String>();
		for (int i = 1; i <= cant.size(); i++) {
			mod.addElement("Cantiere " + (i));
		}
		mod.addElement("Aggiungi Cantiere");

		cantieri = new JList<String>(mod);

		cantieri.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				deleteButton.setText("Chiudi Cantiere");
				deleteButton.setVisible(true);
				inputPane.setVisible(true);
				infoPanel.setVisible(true);
				dip.setVisible(false);
				assumiButton.setVisible(false);
				libera.setVisible(false);
				liberaResponsabile.setVisible(true);
				int index = cantieri.getSelectedIndex();
				if (cant.size() == index) {
					root.dispose();
					new NuovoCantiereFrame(ro, ra);
				} else if (index >= 0) {
					Cantiere c = ro.getCantieri().get(index);
					GridBagConstraints l = new GridBagConstraints();
					l.gridx = 1;
					l.gridy = 0;
					l.insets = new Insets(0, -110, 0, 0);
					inputPane.add(liberaResponsabile, l);
					prod = c.getMaterialiDisponibili();
					ma = c.getMacchineImpiegate();
					sq = c.getSquadre();
					infoPanel.add(createInfo(c), "info");
					cl.show(infoPanel, "info");
				}
				if (squadre != null) {
					squadre.clearSelection();
					materiali.clearSelection();
					macchine.clearSelection();
				}
			}
		});

		listaCantieri.add(cantieri);
		listaCantieri.setBorder(BorderFactory.createTitledBorder("Cantieri Aperti"));
		return listaCantieri;

	}

	public JPanel createInfo(Cantiere c) {
		JPanel infoPane = new JPanel();
		infoPane.setLayout(new GridLayout(4, 1));
		infoPane.add(createInfoCantiere(c));
		infoPane.add(createInfoSquadre());
		infoPane.add(createInfoMateriali());
		infoPane.add(createInfoMacchine());
		spec = new JTextArea();
		JScrollPane p = new JScrollPane(spec);
		spec.setEditable(false);
		JPanel toReturn = new JPanel();
		toReturn.setLayout(new GridLayout(1, 2));
		toReturn.add(infoPane);
		toReturn.add(p);

		return toReturn;
	}

	public JPanel createInfoCantiere(Cantiere c) {
		infoCantiere = new JPanel();

		infoCantiere.setLayout(new GridLayout(6, 2));
		infoCantiere.setBorder(BorderFactory.createTitledBorder("Info Cantiere"));
		respCantiere = new JLabel("Responsabile");
		valore = new JLabel("Valore");
		est = new JLabel("Estensione");
		nSquadre = new JLabel("Numero Squadre");
		latCantiere = new JLabel("Latitudine Cantiere");
		lonCantiere = new JLabel("Longitudine Cantiere");
		setEst = new JTextField();
		setEst.setEditable(false);
		setEst.setText(String.valueOf(c.getEstensione()));
		setLat = new JTextField();
		setLat.setEditable(false);
		setLat.setText(String.valueOf(c.getPosizioneCantiere().getlatitudine()));
		setLon = new JTextField();
		setLon.setEditable(false);
		setLon.setText(String.valueOf(c.getPosizioneCantiere().getlongitudine()));
		setNSq = new JTextField();
		setNSq.setEditable(false);
		setNSq.setText(String.valueOf(c.getSquadre().size()));
		setResp = new JTextField();
		setResp.setEditable(false);
		Dipendente d = (Dipendente) c.getResponsabile();
		setResp.setText(d.getNome() + " " + d.getCognome());
		setVal = new JTextField();
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

	public JScrollPane createInfoSquadre() {
		JPanel pane = new JPanel();
		assumiButton.addActionListener(new Libera());
		libera.addActionListener(new Libera());
		mod1 = new DefaultListModel<String>();
		for (Squadra s : sq) {
			mod1.addElement("Squadra di " + s.getCapoSquadra().getNome() + " " + s.getCapoSquadra().getCognome());
		}
		mod1.addElement("Aggiungi Squadra");
		squadre = new JList<String>(mod1);

		squadre.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				deleteButton.setText("Elimina Squadra");
				deleteButton.setVisible(true);
				dip.setVisible(true);
				libera.setVisible(true);
				assumiButton.setVisible(true);
				liberaResponsabile.setVisible(false);

				int index = squadre.getSelectedIndex();
				if (index == sq.size()) {

					JFrame f = new JFrame();
					root.dispose();
					f.setVisible(true);
					f.setSize(new Dimension(700, 500));
					f.add(new CreazioneSquadraPanel(ro, ra, cant.get(cantieri.getSelectedIndex())));
					revalidate();
					repaint();
				} else if (index >= 0) {
					dip.removeAllItems();
					Squadra tmp = sq.get(index);
					String text = "";
					if (tmp.getCapoSquadra() != null) {
						dip.addItem(tmp.getCapoSquadra().getNome() + " " + tmp.getCapoSquadra().getCognome());
						text = tmp.getCapoSquadra().toString() + "\n";
					}
					for (Operaio o : tmp.getOperai()) {
						text += (o + "\n");
						dip.addItem(o.getNome() + " " + o.getCognome());

					}
					GridBagConstraints c = new GridBagConstraints();
					c.gridx = 1;
					c.gridy = 0;
					c.insets = new Insets(0, -110, 0, 0);
					inputPane.add(dip, c);
					c.gridx = 1;
					c.gridy = 1;
					c.insets = new Insets(-150, -120, 0, 0);
					inputPane.add(libera, c);
					c.gridx = 0;
					c.gridy = 1;
					c.insets = new Insets(-150, -120, 0, 0);
					inputPane.add(assumiButton, c);
					spec.setText(text);
					root.revalidate();
					root.repaint();
				}

				cantieri.clearSelection();
				materiali.clearSelection();
				macchine.clearSelection();
			}

		});

		pane.add(squadre);

		JScrollPane p = new JScrollPane(pane);
		p.setBorder(BorderFactory.createTitledBorder("Squadre"));
		return p;

	}

	public JScrollPane createInfoMateriali() {
		JPanel pane = new JPanel();
		mod2 = new DefaultListModel<String>();
		for (Prodotto p : prod) {
			mod2.addElement(p.getNome());
		}
		mod2.addElement("Aggiungi Materiali");
		materiali = new JList<String>(mod2);
		materiali.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				deleteButton.setText("Elimina Materiale");
				deleteButton.setVisible(true);
				libera.setVisible(false);
				assumiButton.setVisible(false);
				dip.setVisible(false);
				liberaResponsabile.setVisible(false);

				int index = materiali.getSelectedIndex();
				if (index == prod.size()) {
					JFrame f = new JFrame();
					root.dispose();
					f.setVisible(true);
					f.setSize(new Dimension(800, 500));
					f.setLocationRelativeTo(null);
					f.add(new AssegnazioneMaterialiMacchinePanel(ro, ra, 2));
					revalidate();
					repaint();
				} else if (index >= 0) {
					Prodotto p = prod.get(index);
					spec.setText(p.getCaratteristicheProdotto());
				}
				cantieri.clearSelection();
				squadre.clearSelection();
				macchine.clearSelection();
			}
		});

		pane.add(materiali);
		JScrollPane r = new JScrollPane(pane);
		r.setBorder(BorderFactory.createTitledBorder("Materiali"));
		return r;

	}

	public JScrollPane createInfoMacchine() {
		JPanel pane = new JPanel();
		mod3 = new DefaultListModel<String>();

		for (MacchineDaCantiere m : ma) {
			mod3.addElement(m.getNome());
		}
		mod3.addElement("Aggiungi Macchina");

		macchine = new JList<String>(mod3);
		macchine.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				deleteButton.setText("Elimina Macchina");
				deleteButton.setVisible(true);
				libera.setVisible(false);
				assumiButton.setVisible(false);
				dip.setVisible(false);
				int index = macchine.getSelectedIndex();
				if (index == ma.size()) {
					JFrame f = new JFrame();
					root.dispose();
					f.setVisible(true);
					f.setSize(new Dimension(700, 500));
					f.add(new AssegnazioneMaterialiMacchinePanel(ro, ra, 1));
					revalidate();
					repaint();
				} else if (index >= 0) {
					MacchineDaCantiere m = ma.get(index);
					spec.setText(m.getCaratteristiche());
				}
				cantieri.clearSelection();
				squadre.clearSelection();
				materiali.clearSelection();
			}
		});

		pane.add(macchine);
		JScrollPane r = new JScrollPane(pane);
		r.setBorder(BorderFactory.createTitledBorder("Macchine"));
		return r;

	}

	public class Libera implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == liberaResponsabile) {
				int i = cantieri.getSelectedIndex();
				if (i >= 0) {
					Cantiere c = cant.get(i);
					c.licenziaResponsabile();
					root.dispose();
					new DirigenteFrame(azienda, c);
					return;
				}
			}
			Squadra s = sq.get(squadre.getSelectedIndex());
			if (e.getSource() == assumiButton) {
				root.dispose();
				JFrame f = new JFrame();
				f.setVisible(true);
				f.setSize(700, 500);
				f.setLocationRelativeTo(null);
				f.add(new CreazioneSquadraPanel(azienda, s, 1));
				return;
			}

			int index = dip.getSelectedIndex();
			if (index == 0) {
				s.rimuoviCapoSquadra();
				root.dispose();
				JFrame f = new JFrame();
				f.setVisible(true);
				f.setSize(700, 500);
				f.setLocationRelativeTo(null);
				f.add(new CreazioneSquadraPanel(azienda, s, 0));
				return;
			}
			s.rimuoviOperaio(s.getOperai().get(index - 1));
			if (s.getOperai().size() == 0) {
				root.dispose();
				JFrame f = new JFrame();
				f.setVisible(true);
				f.setSize(700, 500);
				f.setLocationRelativeTo(null);
				f.add(new CreazioneSquadraPanel(azienda, s, 1));
				return;
			}

			root.revalidate();
			root.repaint();
		}
	}

	public class Delete implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int index;
			if (deleteButton.getText() == "Elimina Squadra") {
				index = squadre.getSelectedIndex();
				if (index >= 0) {
					Squadra s = sq.get(index);
					mod1.remove(index);
					s.liberaSquadra();
					sq.remove(s);
					root.revalidate();
					root.repaint();
				}
			} else if (deleteButton.getText() == "Chiudi Cantiere") {
				index = cantieri.getSelectedIndex();
				
				if (index >= 0) {
					Cantiere c = cant.get(index);
					ra.aumentaCapitale(c.getValore()/2);
					for (int i = c.getMaterialiDisponibili().size() - 1; i >= 0; i--) {
						ra.aggiungiProdottoAlMagazzino(c.getMaterialiDisponibili().get(i));
						c.rimuoviMateriale(c.getMaterialiDisponibili().get(i));
					}

					for (int j = c.getMacchineImpiegate().size() - 1; j >= 0; j--) {
						ra.aggiungiMacchinaAlMagazzino(c.getMacchineImpiegate().get(j));
						c.rimuoviMacchina(c.getMacchineImpiegate().get(j));
					}
					mod.remove(index);
					ro.chiudiCantiere(c);
					cant.remove(c);
					infoPanel.setVisible(false);
					inputPane.setVisible(false);
					root.revalidate();
					root.repaint();
				}
			} else if (deleteButton.getText() == "Elimina Materiale") {
				index = materiali.getSelectedIndex();
				if (index >= 0) {
					Prodotto p = prod.get(index);
					ra.aggiungiProdottoAlMagazzino(p);
					prod.remove(index);
					mod2.remove(index);
					root.revalidate();
					root.repaint();
				}
			} else if (deleteButton.getText() == "Elimina Macchina") {
				index = macchine.getSelectedIndex();
				if (index >= 0) {
					MacchineDaCantiere m = ma.get(index);
					ra.aggiungiMacchinaAlMagazzino(m);
					ma.remove(index);
					mod3.remove(index);
					root.revalidate();
					root.repaint();
				}
			}

		}

	}

	public class DirigenteFrame extends JFrame {

		JList<Dipendente> resp;
		DefaultListModel<Dipendente> mod;
		Azienda azienda;
		Cantiere cant;
		JFrame root;

		public DirigenteFrame(Azienda a, Cantiere c) {
			super("Scegli un nuovo Responsabile");
			this.setSize(1000, 280);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			root = this;
			azienda = a;
			cant = c;
			mod = new DefaultListModel<Dipendente>();
			for (Dipendente d : a.getRepartoAmministrativo().getDipendenti()) {
				if (c.getValore() <= 500000) {
					if (Dipendente.isDirigente(d) || Dipendente.isQuadro(d)) {
						mod.addElement(d);
					}
				} else {
					if (Dipendente.isDirigente(d)) {
						mod.addElement(d);
					}
				}
			}
			resp = new JList<Dipendente>(mod);
			resp.setBorder(BorderFactory.createTitledBorder("Responsabili idionei per il Cantiere"));
			resp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane p = new JScrollPane(resp);
			JButton aggiungi = new JButton("Aggiungi");
			aggiungi.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int index = resp.getSelectedIndex();
					if (index >= 0) {
						Dipendente r = mod.getElementAt(index);
						c.assegnaResponsabile((Responsabile) r);
						root.dispose();
						new RepartoOperativoFrame(azienda);
					}

				}
			});
			this.setLayout(new GridBagLayout());
			GridBagConstraints l = new GridBagConstraints();
			l.gridx = 0;
			l.gridy = 0;
			l.anchor = GridBagConstraints.FIRST_LINE_START;
			this.add(p, l);
			l.gridx = 1;
			l.gridy = 1;
			l.anchor = GridBagConstraints.LAST_LINE_END;
			this.add(aggiungi, l);

		}
	}

}
