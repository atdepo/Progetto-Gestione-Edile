package amministrativoGUI;

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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import amministrativo.RepartoAmministrativo;
import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import utilities.Azienda;

public class CompraBeniFrame extends JFrame {

	JList<String> fornitori;
	JList<String> macchine;
	JList<String> prodotti;

	DefaultListModel<String> mod;
	DefaultListModel<String> mod1;
	DefaultListModel<String> mod2;

	ArrayList<Fornitore> fo;
	ArrayList<MacchineDaCantiere> ma;
	ArrayList<Prodotto> prod;

	JScrollPane macchinePane;
	JScrollPane specPane;

	JPanel infoPane;
	JPanel compraPanel;

	JTextArea spec;

	JButton compra;

	JLabel soldi;

	JTextField quantita;

	CardLayout cl;

	int cat;
	JFrame root;

	RepartoAmministrativo repartoAmministrativo;
	Azienda azienda;

	public CompraBeniFrame(Azienda a, int categoriaDaComprare) {
		super("Compra Beni");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(880, 450));
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		root = this;
		cat = categoriaDaComprare;
		cl = new CardLayout();
		infoPane = new JPanel();
		infoPane.setLayout(cl);
		repartoAmministrativo = a.getRepartoAmministrativo();
		azienda = a;
		soldi = new JLabel("Capitale:" + String.valueOf(repartoAmministrativo.getCapitale()));
		soldi.setVisible(false);
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(createListe(), c);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(soldi);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(createAcquisto(), c);

	}

	public JPanel createListe() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(1, 3));
		pane.add(createListaFornitori());
		pane.add(infoPane);
		pane.add(createSpec());
		return pane;
	}

	public JScrollPane createListaFornitori() {
		mod = new DefaultListModel<String>();
		fo = new ArrayList<Fornitore>();
		for (Fornitore f : repartoAmministrativo.getFornitori()) {

			if (cat == 1) {
				if (f.getMacchineDaCantiere().size() > 0) {
					mod.addElement(f.getNomeFornitore());
					fo.add(f);
				}
			} else if (cat == 0) {
				if (f.getProdottiInVendita().size() > 0) {
					mod.addElement(f.getNomeFornitore());
					fo.add(f);
				}

			}
		}
		fornitori = new JList<String>(mod);

		fornitori.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int index = fornitori.getSelectedIndex();
				if (index >= 0) {
					if (cat == 1) {
						ma = fo.get(index).getMacchineDaCantiere();
						infoPane.add(createListaMacchine(), "lista");
						cl.show(infoPane, "lista");
						revalidate();
						repaint();
					} else if (cat == 0) {
						prod = fo.get(index).getProdottiInVendita();
						infoPane.add(createListaProdotti(), "prod");
						cl.show(infoPane, "prod");
						revalidate();
						repaint();
					}
				}
			}
		});
		JScrollPane pane = new JScrollPane(fornitori);
		pane.setPreferredSize(new Dimension(250, 400));
		pane.setBorder(BorderFactory.createTitledBorder("Fornitori"));
		return pane;
	}

	public JScrollPane createListaMacchine() {
		mod1 = new DefaultListModel<String>();
		for (MacchineDaCantiere c : ma) {
			mod1.addElement(c.getNome());
		}
		macchine = new JList<String>(mod1);
		macchine.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				specPane.setVisible(true);
				compraPanel.setVisible(true);
				soldi.setVisible(true);
				int index = macchine.getSelectedIndex();
				if (index >= 0)
					spec.setText(ma.get(index).getCaratteristiche());

			}
		});
		macchinePane = new JScrollPane(macchine);
		macchinePane.setPreferredSize(new Dimension(250, 400));
		macchinePane.setBorder(BorderFactory.createTitledBorder("Macchine"));
		return macchinePane;
	}

	public JScrollPane createListaProdotti() {

		mod2 = new DefaultListModel<String>();
		for (Prodotto c : prod) {
			mod2.addElement(c.getNome());
		}
		prodotti = new JList<String>(mod2);
		prodotti.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				specPane.setVisible(true);
				compraPanel.setVisible(true);
				soldi.setVisible(true);
				int index = prodotti.getSelectedIndex();
				if (index >= 0)
					spec.setText(prod.get(index).getCaratteristicheProdotto());

			}
		});
		macchinePane = new JScrollPane(prodotti);
		macchinePane.setPreferredSize(new Dimension(250, 400));
		macchinePane.setBorder(BorderFactory.createTitledBorder("Prodotti"));
		return macchinePane;

	}

	public JScrollPane createSpec() {
		spec = new JTextArea();
		spec.setEditable(false);
		specPane = new JScrollPane(spec);
		specPane.setVisible(false);
		specPane.setPreferredSize(new Dimension(250, 400));
		specPane.setBorder(BorderFactory.createTitledBorder("Specifiche"));
		return specPane;
	}

	public JPanel createAcquisto() {
		compraPanel = new JPanel();
		compraPanel.setVisible(false);
		compra = new JButton("Compra");
		compra.addActionListener(new Compra());
		quantita = new JTextField(6);
		JPanel q = new JPanel();
		if (cat != 0) {
			q.setVisible(false);
		}
		q.add(quantita);
		q.setBorder(BorderFactory.createTitledBorder("Quantità"));
		compraPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		compraPanel.add(compra, c);
		c.gridx = 0;
		c.gridy = 1;
		compraPanel.add(q, c);
		return compraPanel;
	}

	public class Compra implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (cat == 0) {
				if (quantita.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Inserire la quantità da comprare", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int q = Integer.parseInt(quantita.getText());
						Prodotto toBuy = prod.get(prodotti.getSelectedIndex()).clone();
						if (q <= toBuy.getNumeroPezziDisponibili()) {
							toBuy.setNumeroPezziDisponibili(q);
							fo.get(fornitori.getSelectedIndex()).compraProdotto(toBuy);
							repartoAmministrativo.aggiungiProdottoAlMagazzino(toBuy);
							repartoAmministrativo.effettuaSpesa(toBuy.getPrezzo() * q);
							root.dispose();
							new RepartoAmministrativoFrame(azienda);
						} else {
							JOptionPane.showMessageDialog(null, "Quantità non acquistabile", "ERROR",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (IllegalArgumentException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}

				}
			} else {
				try {
					MacchineDaCantiere m = fo.get(fornitori.getSelectedIndex())
							.compraMacchina(ma.get(macchine.getSelectedIndex()));
					repartoAmministrativo.effettuaSpesa(m.getPrezzo());
					repartoAmministrativo.aggiungiMacchinaAlMagazzino(m);

					root.dispose();
					new RepartoAmministrativoFrame(azienda);
				} catch (IllegalArgumentException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		}

	}
}
