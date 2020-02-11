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

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import dipendenti.Operaio;
import dipendenti.Quadro;
import operativo.RepartoOperativo;
import utilities.Azienda;

public class AssegnazioneMaterialiMacchinePanel extends JPanel {

	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;

	CardLayout cl;

	JPanel mainPane;
	JPanel contentPane;
	JPanel pane;

	JScrollPane macchinePanel;
	JScrollPane prodottiPanel;

	JTextArea spec;

	JButton assegna;
	JButton fine;
	JList<String> prodotti;
	JList<MacchineDaCantiere> macchine;

	DefaultListModel<String> mod1;
	DefaultListModel<MacchineDaCantiere> mod2;

	ArrayList<Prodotto> prod;
	ArrayList<MacchineDaCantiere> ma;

	int opt;
	
	JPanel root;

	public AssegnazioneMaterialiMacchinePanel(RepartoOperativo ro, RepartoAmministrativo ra,int o) {
		repartoAmministrativo = ra;
		repartoOperativo = ro;
		spec = new JTextArea();
		if(o<0||o>2)
			throw new IllegalArgumentException();
		opt=o;
		assegna = new JButton("Assegna");
		fine = new JButton("Fine");
		assegna.addActionListener(new Aggiunta());
		fine.addActionListener(new Fine());
		cl = new CardLayout();
		root = this;
		mainPane = new JPanel();
		contentPane = new JPanel();
		this.add(mainPane);
		mainPane.setLayout(cl);
		mainPane.add(contentPane, "main");
		contentPane.setLayout(new BorderLayout());
		//contentPane.add(createListaMacchine(), BorderLayout.CENTER);
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 7;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		contentPane.add(createListaMacchine(), c);
		if(opt!=1) {
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 7;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.LINE_START;
		contentPane.add(createListaProdotti(), c);
		}
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 8;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		contentPane.add(assegna, c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 8;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		contentPane.add(fine, c);

	}

	public JScrollPane createListaMacchine() {

		mod2 = new DefaultListModel<MacchineDaCantiere>();
		ma = new ArrayList<MacchineDaCantiere>();
		for (MacchineDaCantiere m : repartoAmministrativo.getMagazzino().getMacchineInMagazzino()) {
			ma.add(m);
			mod2.addElement(m);
		}
		macchine = new JList<MacchineDaCantiere>(mod2);
		macchine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		macchinePanel = new JScrollPane(macchine);
		if(opt!=1)
			macchinePanel.setVisible(false);
		macchinePanel.setPreferredSize(new Dimension(600, 400));
		macchinePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		macchinePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JViewport view = macchinePanel.getViewport();
		int h = view.getPreferredSize().height;
		int w = 680;
		Dimension preferredSize = new Dimension(w, h);
		view.setPreferredSize(preferredSize);
		macchinePanel.setBorder(BorderFactory.createTitledBorder("Macchine"));
		return macchinePanel;
	}

	public JPanel createListaProdotti() {

		pane = new JPanel();
		pane.setLayout(new BorderLayout());
		JTextArea spec = new JTextArea();
		spec.setPreferredSize(new Dimension(350, 300));
		pane.add(spec, BorderLayout.EAST);
		mod1 = new DefaultListModel<String>();
		prod = new ArrayList<Prodotto>();
		for (Prodotto p : repartoAmministrativo.getMagazzino().getProdottiInMagazzino()) {
			prod.add(p);
			mod1.addElement(p.getNome());
		}
		prodotti = new JList<String>(mod1);
		prodotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prodotti.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				spec.setText(prod.get(prodotti.getSelectedIndex()).getCaratteristicheProdotto());
			}
		});
		prodottiPanel = new JScrollPane(prodotti);
		prodottiPanel.setPreferredSize(new Dimension(300, 400));
		prodottiPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		prodottiPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JViewport view = macchinePanel.getViewport();
		int h = view.getPreferredSize().height;
		int w = 680;
		Dimension preferredSize = new Dimension(w, h);
		view.setPreferredSize(preferredSize);
		prodottiPanel.setBorder(BorderFactory.createTitledBorder("Prodotti"));
		pane.add(prodottiPanel, BorderLayout.WEST);
		if(opt==1)
			pane.setVisible(false);
		return pane;

	}

	public class Aggiunta implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (opt!=1&&pane.isVisible()) {

				int q = Integer.parseInt(JOptionPane.showInputDialog("Quantità disponibile in magazzino:"
						+ prod.get(prodotti.getSelectedIndex()).getNumeroPezziDisponibili()));
				Prodotto buy = prod.get(prodotti.getSelectedIndex()).clone();
				buy.setNumeroPezziDisponibili(q);
				if (q > prod.get(prodotti.getSelectedIndex()).getNumeroPezziDisponibili()) {
					JOptionPane.showMessageDialog(null,
							"Il magazzino non possiede abbastanza risorse, il reparto amministrativo provvede a comprarle da un fornitore");
					Prodotto add = repartoAmministrativo.getProdotto(buy);
					if (add != null) {
						repartoOperativo.getCantieri().get(repartoOperativo.getCantieri().size() - 1)
								.assegnaMateriale(add);
					} else
						JOptionPane.showMessageDialog(null,
								"Nessun fornitore può soddisfare la richiesta di materiale supplementare", "ERROR",
								JOptionPane.ERROR_MESSAGE);

				} else {
					Prodotto add = repartoAmministrativo.getProdotto(buy);
					if (add != null) {
						repartoOperativo.getCantieri().get(repartoOperativo.getCantieri().size() - 1)
								.assegnaMateriale(add);
					} else {
						JOptionPane.showMessageDialog(null, "Qualcosa è andato storto, riprovare", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					System.out.println(buy.getCaratteristicheProdotto());
				}
			} else {
				MacchineDaCantiere m = ma.get(macchine.getSelectedIndex());
				mod2.removeElement(m);
				repartoOperativo.getCantieri().get(repartoOperativo.getCantieri().size() - 1).assegnaMacchina(m);
				revalidate();
				repaint();
			}

		}

	}

	public class Fine implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			if(opt!=0) {
				JFrame r = (JFrame) SwingUtilities.getRoot(root);
				r.dispose();
				Azienda a = new Azienda(repartoAmministrativo, repartoOperativo);
				new RepartoOperativoFrame(a);
			}
			if (pane.isVisible()) {
				pane.setVisible(false);
				macchinePanel.setVisible(true);
				revalidate();
				repaint();
			} else if (macchinePanel.isVisible()) {
				JFrame r = (JFrame) SwingUtilities.getRoot(root);
				r.dispose();
				Azienda a = new Azienda(repartoAmministrativo, repartoOperativo);
				new RepartoOperativoFrame(a);
			}
		}
	}
}
