package amministrativoGUI;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.javafx.geom.Ellipse2D;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.ProdottoNonTrovatoException;
import gui.InitFrame;
import operativo.RepartoOperativo;
import operativoGUI.RepartoOperativoFrame;
import utilities.Azienda;
import utilities.Estraibile;
import utilities.Estrattore;

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
	JPanel straordinarioPane;
	JButton delete;
	JButton paga;
	JButton resetPaga;

	JTextField straordinario;

	JMenu menu;

	JMenuBar bar;

	JMenuItem item;
	JMenuItem item2;

	JComboBox<String> dirigentiLista;
	JComboBox<String> operaiLista;
	JComboBox<String> quadriLista;
	JComboBox<String> impiegatiLista;
	JComboBox<String> fornitoriLista;
	JComboBox<String> prodottiLista;
	JComboBox<String> macchineLista;

	JComboBox<String> sceltaCriterioOrdinamento;

	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	Azienda azienda;

	public RepartoAmministrativoFrame(Azienda a) {
		super("Reparto Amministrativo");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(600, 350);
		this.setSize(1200, 730);
		// this.setResizable(false);
		root = this;
		azienda = a;
		bar = new JMenuBar();
		menu = new JMenu("Opzioni");
		item = new JMenuItem("Vai al Reparto Operativo");
		item.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				root.dispose();
				new RepartoOperativoFrame(azienda);
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
		this.setLayout(new GridBagLayout());
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
		this.add(creaInfo(), c);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.add(createSpec(), c);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(creaOpzioni(), c);
	}

	public JPanel creaOpzioni() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(460, 260));
		p.setBorder(BorderFactory.createTitledBorder("Opzioni"));
		p.setLayout(new GridBagLayout());
		delete = new JButton();
		paga = new JButton("Paga Dipendenti");
		paga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double spesa=repartoAmministrativo.pagaDipendenti();
					System.out.println("Ho pagato tutti i dipendenti,spesda totale:"+spesa);
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		resetPaga = new JButton("Reset Pagamento");
		resetPaga.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				repartoAmministrativo.resetStatoPagamento();
			}
		});
		delete.addActionListener(new Eliminazione());
		delete.setVisible(false);

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		p.add(paga, c);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		p.add(resetPaga, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(-50, 0, 0, 0);
		p.add(straordinarioPanel(), c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(-90, 0, 0, 0);
		p.add(delete, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		p.add(creaPannelloReport(), c);

		return p;
	}

	public JPanel straordinarioPanel() {
		straordinarioPane = new JPanel();
		straordinarioPane.setVisible(false);
		straordinarioPane.setLayout(new FlowLayout());
		straordinario = new JTextField(3);
		JButton ok = new JButton("Assegna");
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!straordinario.getText().isEmpty()) {
					if (operai.getSelectedIndex() != -1) {
						try {
							int index = operai.getSelectedIndex();
							op.get(index).setLavoroStraordinario(Integer.parseInt(straordinario.getText()));
						} catch (IllegalArgumentException e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

						}
					} else if (impiegati.getSelectedIndex() != -1) {
						try {
							int index = impiegati.getSelectedIndex();
							imp.get(index).setLavoroStraordinario(Integer.parseInt(straordinario.getText()));
						} catch (IllegalArgumentException e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

						}
					}
				}
			}
		});
		straordinarioPane.add(straordinario);
		straordinarioPane.add(ok);
		straordinarioPane.setBorder(BorderFactory.createTitledBorder("Ore Straordinario"));
		return straordinarioPane;
	}

	public JPanel creaInfo() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(700, 260));
		p.setLayout(new GridLayout(1, 2));
		p.add(creaMagazzino());
		p.add(createListaFornitori());
		return p;
	}

	public JPanel creaPannelloReport() {
		JPanel p = new JPanel();
		// p.setPreferredSize(new Dimension(100, 200));
		JRadioButton perNome = new JRadioButton("Per Nome");
		JRadioButton stipendioRange = new JRadioButton("Per Range di Stipendio");

		JCheckBox d;
		JCheckBox i;
		JCheckBox o;
		JCheckBox q;

		JButton vai = new JButton("Genera Report");
		vai.setVisible(false);

		JTextField t1 = new JTextField(12);
		t1.setVisible(false);
		JTextField t2 = new JTextField(12);
		t2.setVisible(false);

		d = new JCheckBox("Dirigente");
		i = new JCheckBox("Impiegato");
		o = new JCheckBox("Operaio");
		q = new JCheckBox("Quadro");

		ButtonGroup g = new ButtonGroup();
		g.add(stipendioRange);
		g.add(perNome);

		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		p.add(perNome, c);
		c.gridx = 1;
		c.gridy = 0;
		p.add(stipendioRange, c);
		c.gridx = 0;
		c.gridy = 2;
		p.add(d, c);
		c.gridx = 0;
		c.gridy = 3;
		p.add(i, c);
		c.gridx = 1;
		c.gridy = 2;
		p.add(q, c);
		c.gridx = 1;
		c.gridy = 3;
		p.add(o, c);
		c.gridx = 0;
		c.gridy = 4;
		p.add(t1, c);
		c.gridx = 1;
		c.gridy = 4;
		p.add(t2, c);
		c.gridx = 2;
		c.gridy = 4;
		p.add(vai, c);

		perNome.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				t1.setVisible(true);
				t1.setBorder(BorderFactory.createTitledBorder("Nome Dipendente"));
				t2.setVisible(false);
				vai.setVisible(true);
				root.revalidate();
				root.repaint();
			}
		});

		stipendioRange.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				t1.setVisible(true);
				t1.setBorder(BorderFactory.createTitledBorder("Range Minimo"));
				t2.setBorder(BorderFactory.createTitledBorder("Range Massimo"));
				t2.setVisible(true);
				vai.setVisible(true);

				root.revalidate();
				root.repaint();
			}
		});

		vai.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (perNome.isSelected()) {
					if (!t1.getText().isEmpty()) {
						Estraibile<Dipendente> criterio = (a) -> {
							return a.getNome().equals(t1.getText());
						};
						ArrayList<Dipendente> toEx = new ArrayList<Dipendente>();

						for (Dipendente di : dipendenti) {
							if (d.isSelected() && Dipendente.isDirigente(di))
								toEx.add(di);
							if (o.isSelected() && Dipendente.isOperaio(di))
								toEx.add(di);
							if (q.isSelected() && Dipendente.isQuadro(di))
								toEx.add(di);
							if (i.isSelected() && Dipendente.isImpiegato(di))
								toEx.add(di);
						}

						Estrattore<Dipendente> r = new Estrattore<Dipendente>(toEx, criterio);
						ArrayList<Dipendente> dip = r.estrai();
						String s = "";
						for (Dipendente d : dip) {
							s += d;
							s += "\n";
						}
						spec.setText(s);
					}
				} else if (stipendioRange.isSelected()) {
					if (!t1.getText().isEmpty() && !t2.getText().isEmpty()) {
						Estraibile<Dipendente> criterio = (a) -> {
							double stipendio;
							if (Dipendente.isDirigente(a)) {
								Dirigente d = (Dirigente) a;
								stipendio = d.getContratto().getStipendio()
										+ d.getContratto().getBonus() * d.getNumeroOperai();
								if (stipendio >= Integer.parseInt(t1.getText())
										&& stipendio < Integer.parseInt(t2.getText()))
									return true;
								else
									return false;
							} else if (Dipendente.isQuadro(a)) {
								Quadro q = (Quadro) a;
								if (q.isResponsabile() || q.isCaposquadra()) {
									double tmp = q.getContratto().getStipendio();
									stipendio = tmp + ((tmp / 100) * q.getContratto().getBonus());
									if (stipendio >= Integer.parseInt(t1.getText())
											&& stipendio < Integer.parseInt(t2.getText()))
										return true;
									else
										return false;

								}
							}
							else if(Dipendente.isImpiegato(a)) {
								Impiegato i=(Impiegato)a;
								stipendio=4*i.getGiorniLavorati()*i.getContratto().getStipendio()+i.getGiorniStraordinario()*i.getContratto().getBonus();
								if (stipendio >= Integer.parseInt(t1.getText())
										&& stipendio < Integer.parseInt(t2.getText()))
									return true;
								else
									return false;
							}
							else {
								Operaio o=(Operaio)a;
								stipendio=4*o.getOreLavorate()*o.getContratto().getStipendio()+o.getOreStraordinario()*o.getContratto().getBonus();
								if (stipendio >= Integer.parseInt(t1.getText())
										&& stipendio < Integer.parseInt(t2.getText()))
									return true;
								else
									return false;
							}
							return false;
						};
						
						ArrayList<Dipendente> toEx = new ArrayList<Dipendente>();

						for (Dipendente di : dipendenti) {
							if (d.isSelected() && Dipendente.isDirigente(di))
								toEx.add(di);
							if (o.isSelected() && Dipendente.isOperaio(di))
								toEx.add(di);
							if (q.isSelected() && Dipendente.isQuadro(di))
								toEx.add(di);
							if (i.isSelected() && Dipendente.isImpiegato(di))
								toEx.add(di);
						}

						Estrattore<Dipendente> r = new Estrattore<Dipendente>(toEx, criterio);
						ArrayList<Dipendente> dip = r.estrai();
						String s = "";
						for (Dipendente d : dip) {
							s += d;
							s += "\n";
						}
						spec.setText(s);
					}
				}
			}
		});
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
				delete.setVisible(true);
				delete.setText("Elimina Prodotto");
				int index = prodotti.getSelectedIndex();
				if (index == prod.size()) {
					root.dispose();
					new CompraBeniFrame(azienda, 0);

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
				delete.setVisible(true);
				delete.setText("Elimina Macchina");
				int index = macchine.getSelectedIndex();
				if (index == ma.size()) {
					root.dispose();
					new CompraBeniFrame(azienda, 1);
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
				delete.setVisible(true);
				delete.setText("Elimina Fornitore");
				int index = fornitori.getSelectedIndex();
				if (index == fo.size()) {
					root.dispose();
					new AggiuntaFornitoreFrame(azienda);
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
		mainPanel.setPreferredSize(new Dimension(700, 400));
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
		JViewport view = s.getViewport();
		Dimension preferredSize = new Dimension(460, 357);
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
				delete.setVisible(true);
				straordinarioPane.setVisible(false);
				delete.setText("Licenzia Dirigente");

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
				straordinarioPane.setVisible(true);
				straordinarioPane.setBorder(BorderFactory.createTitledBorder("Ore Straordinario"));
				delete.setVisible(true);
				delete.setText("Licenzia Operaio");
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
				straordinarioPane.setVisible(false);

				delete.setVisible(true);
				delete.setText("Licenzia Quadro");
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
				straordinarioPane.setVisible(true);
				straordinarioPane.setBorder(BorderFactory.createTitledBorder("Giorni Straordinario"));

				delete.setVisible(true);
				delete.setText("Licenzia Impiegato");
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

	public class Eliminazione implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			String t = delete.getText();
			int index;
			if (t == "Licenzia Impiegato") {
				index = impiegati.getSelectedIndex();
				if (index >= 0) {
					try {
						repartoAmministrativo.licenziamentoDipendente(imp.get(index));
						mod3.remove(index);
						imp.remove(index);
						root.revalidate();
						root.repaint();
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else if (t == "Licenzia Operaio") {
				try {
					index = operai.getSelectedIndex();
					if (index >= 0) {
						repartoAmministrativo.licenziamentoDipendente(op.get(index));
						mod1.remove(index);
						op.remove(index);
						root.revalidate();
						root.repaint();
					}
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else if (t == "Licenzia Quadro") {
				try {
					index = quadri.getSelectedIndex();
					if (index >= 0) {
						repartoAmministrativo.licenziamentoDipendente(qua.get(index));
						mod2.remove(index);
						qua.remove(index);
						root.revalidate();
						root.repaint();
					}
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else if (t == "Licenzia Dirigente") {
				try {
					index = dirigenti.getSelectedIndex();
					if (index >= 0) {
						repartoAmministrativo.licenziamentoDipendente(dir.get(index));
						mod.remove(index);
						dir.remove(index);
						root.revalidate();
						root.repaint();
					}
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else if (t == "Elimina Fornitore") {
				index = fornitori.getSelectedIndex();
				if (index >= 0) {
					repartoAmministrativo.removeFornitore(fo.get(index));
					mod6.remove(index);
					fo.remove(index);
					root.revalidate();
					root.repaint();
				}

			} else if (t == "Elimina Prodotto") {
				index = prodotti.getSelectedIndex();
				if (index >= 0) {
					try {
						repartoAmministrativo.getMagazzino().rimuoviProdotto(prod.get(index));
						mod4.remove(index);
						prod.remove(index);
						root.revalidate();
						root.repaint();
					} catch (ProdottoNonTrovatoException e) {
					}

				}
			} else if (t == "Elimina Macchina") {
				index = macchine.getSelectedIndex();
				if (index >= 0) {
					mod5.remove(index);
					repartoAmministrativo.getMagazzino().prendiMacchina(ma.get(index));
					ma.remove(index);
					root.revalidate();
					root.repaint();
				}
			}
		}

	}

}
