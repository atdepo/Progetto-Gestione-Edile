package operativoGUI;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import dipendenti.Dipendente;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.OperaioOccupatoException;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import operativo.Squadra;
import utilities.Azienda;

public class CreazioneSquadraPanel extends JPanel {

	JList<Operaio> operai;
	JList<Quadro> quadri;

	DefaultListModel<Operaio> mod1;
	DefaultListModel<Quadro> mod2;

	ArrayList<Operaio> op;
	ArrayList<Quadro> qua;

	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	Cantiere cantiere;
	JScrollPane operaiPanel;
	JScrollPane quadriPanel;

	JPanel mainPane;
	JPanel contentPane;
	JPanel root;
	CardLayout cl;
	boolean flag;
	JButton assegna;

	Squadra squadra;

	public CreazioneSquadraPanel(RepartoOperativo ro, RepartoAmministrativo ra) {
		repartoAmministrativo = ra;
		repartoOperativo = ro;
		cantiere = repartoOperativo.getCantieri().get(repartoOperativo.getCantieri().size() - 1);
		flag = false;
		root = this;
		cl = new CardLayout();
		mainPane = new JPanel();
		contentPane = new JPanel();
		assegna = new JButton("Assegna");
		assegna.addActionListener(new Assegna());
		this.add(mainPane);
		mainPane.setLayout(cl);
		mainPane.add(contentPane, "main");
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 7;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		contentPane.add(createListaOperai(), c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 7;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.LINE_START;
		contentPane.add(createListaQuadri(), c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 10;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		contentPane.add(assegna, c);
	}

	public CreazioneSquadraPanel(RepartoOperativo ro, RepartoAmministrativo ra, Cantiere cant) {
		repartoAmministrativo = ra;
		repartoOperativo = ro;
		cantiere = cant;
		flag = true;
		root = this;
		cl = new CardLayout();
		mainPane = new JPanel();
		contentPane = new JPanel();
		assegna = new JButton("Assegna");
		assegna.addActionListener(new Assegna());
		this.add(mainPane);
		mainPane.setLayout(cl);
		mainPane.add(contentPane, "main");
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 7;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		contentPane.add(createListaOperai(), c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 7;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.LINE_START;
		contentPane.add(createListaQuadri(), c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 10;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		contentPane.add(assegna, c);
	}

	public CreazioneSquadraPanel(Azienda a, Squadra s, int opt) {
		this.setLayout(new GridBagLayout());
		root = this;
		GridBagConstraints c = new GridBagConstraints();
		repartoAmministrativo = a.getRepartoAmministrativo();
		assegna = new JButton("Assegna");
		if (opt == 0) {// solo quadro
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			this.add(createListaQuadri(), c);
			c.gridx = 1;
			c.gridy = 1;
			this.add(assegna, c);
		} else if (opt == 1) {
			c.gridx = 0;
			c.gridy = 0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			this.add(createListaOperai(), c);
			c.gridx = 1;
			c.gridy = 1;
			this.add(assegna, c);
			operaiPanel.setVisible(true);
		}
		assegna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opt == 0) {
					if (quadri.getSelectedIndex() >= 0) {
						s.assegnaCapoSquadra(qua.get(quadri.getSelectedIndex()));
						JFrame frame = (JFrame) SwingUtilities.getRoot(root);
						frame.dispose();
						new RepartoOperativoFrame(a);
					}
				} else {
					List<Operaio> add = operai.getSelectedValuesList();
					for (Operaio o : add) {
						try {
							s.aggiungiOperaio(o);
						} catch (OperaioOccupatoException e1) {
							return;
						}
					}
					JFrame frame = (JFrame) SwingUtilities.getRoot(root);
					frame.dispose();
					new RepartoOperativoFrame(a);
				}
			}
		});
	}

	public JScrollPane createListaOperai() {
		mod1 = new DefaultListModel<Operaio>();
		op = new ArrayList<Operaio>();
		for (Dipendente d : repartoAmministrativo.getDipendenti()) {
			if (Dipendente.isOperaio(d) && !d.isImpegnato()) {
				op.add((Operaio) d);
			}
		}

		for (Operaio o : op) {
			mod1.addElement(o);
		}
		operai = new JList<Operaio>(mod1);
		operai.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		operai.setVisibleRowCount(-1);
		operaiPanel = new JScrollPane(operai);
		operaiPanel.setVisible(false);
		operaiPanel.setPreferredSize(new Dimension(550, 400));
		operaiPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		operaiPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JViewport view = operaiPanel.getViewport();
		int h = view.getPreferredSize().height;
		int w = 680;
		Dimension preferredSize = new Dimension(w, h);
		view.setPreferredSize(preferredSize);
		operaiPanel.setBorder(BorderFactory.createTitledBorder("Operai"));
		return operaiPanel;
	}

	public JScrollPane createListaQuadri() {
		mod2 = new DefaultListModel<Quadro>();
		qua = new ArrayList<Quadro>();
		for (Dipendente d : repartoAmministrativo.getDipendenti()) {
			if (Dipendente.isQuadro(d)&&!d.isImpegnato()) {
				Quadro q = (Quadro) d;
				qua.add(q);
			}
		}

		for (Quadro q : qua) {
			mod2.addElement(q);
		}
		quadri = new JList<Quadro>(mod2);
		quadri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		quadriPanel = new JScrollPane(quadri);
		quadriPanel.setPreferredSize(new Dimension(550, 400));
		quadriPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		quadriPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JViewport view = quadriPanel.getViewport();
		int h = view.getPreferredSize().height;
		int w = 680;
		Dimension preferredSize = new Dimension(w, h);
		view.setPreferredSize(preferredSize);
		quadriPanel.setBorder(BorderFactory.createTitledBorder("Caposquadra"));
		return quadriPanel;
	}

	public class Assegna implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (quadriPanel.isVisible()) {
				int index = quadri.getSelectedIndex();
				if (index >= 0) {
					Quadro toAdd = qua.get(index);
					squadra = new Squadra(toAdd);
					quadriPanel.setVisible(false);
					operaiPanel.setVisible(true);
					revalidate();
					repaint();
				}
			} else {
				List<Operaio> add = operai.getSelectedValuesList();
				if (add.size() > 0) {
					for (Operaio o : add) {
						try {
							squadra.aggiungiOperaio(o);
						} catch (OperaioOccupatoException e1) {
							e1.printStackTrace();
						}
					}
					repartoOperativo.assegnaSquadra(cantiere, squadra);
					JFrame frame = (JFrame) SwingUtilities.getRoot(root);
					if (!flag) {
						frame.setSize(new Dimension(850, 500));
						mainPane.add(new AssegnazioneMaterialiMacchinePanel(repartoOperativo, repartoAmministrativo, 0),
								"assegnazione");
						cl.show(mainPane, "assegnazione");
					} else {
						frame.dispose();
						new RepartoOperativoFrame(new Azienda(repartoAmministrativo, repartoOperativo));
					}
				}
			}

		}
	}
}
