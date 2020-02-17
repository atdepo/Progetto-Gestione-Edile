package amministrativoGUI;

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
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import utilities.Azienda;

public class AggiuntaFornitoreFrame extends JFrame {

	Azienda azienda;

	JList<String> prodotti;
	JList<String> macchine;

	DefaultListModel<String> mod1;
	DefaultListModel<String> mod2;

	ArrayList<Prodotto> prod;
	ArrayList<MacchineDaCantiere> ma;

	JPanel nome;
	JTextField n;

	JButton avanti;

	JFrame root;

	Fornitore fornitore;

	GridBagConstraints c;

	public AggiuntaFornitoreFrame(Azienda a) {
		super("Aggiungi Fornitore");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		this.setLayout(new GridBagLayout());
		root = this;
		azienda = a;

		n = new JTextField(20);
		nome = new JPanel();
		avanti = new JButton("Avanti");
		avanti.addActionListener(new Bottone());
		prod = new ArrayList<Prodotto>();
		ma = new ArrayList<MacchineDaCantiere>();
		nome.add(n);
		nome.setBorder(BorderFactory.createTitledBorder("Nome Fornitore"));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		this.add(nome, c);
		c.gridx = 1;
		c.gridy = 0;
		this.add(avanti, c);
	}

	public AggiuntaFornitoreFrame(Azienda a, Fornitore f) {
		super("Aggiungi Fornitore");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLayout(new GridBagLayout());
		root = this;
		root.setTitle("Fornitore: "+f.getNomeFornitore());
		azienda = a;
		fornitore=f;
		nome = new JPanel();
		nome.setVisible(false);
		avanti = new JButton("Avanti");
		avanti.addActionListener(new Bottone());
		prod = f.getProdottiInVendita();
		ma = f.getMacchineDaCantiere();
		root.add(createMainPanel());
	}

	public JPanel createMainPanel() {
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridBagLayout());
		GridBagConstraints l = new GridBagConstraints();
		l.weightx = 1.0;
		l.weighty = 1.0;
		l.gridx = 0;
		l.gridy = 0;
		l.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPane.add(createListe(), l);
		l.gridx = 1;
		l.gridy = 0;
		l.anchor = GridBagConstraints.EAST;
		mainPane.add(avanti, l);

		return mainPane;
	}

	public JPanel createListe() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(1, 2));
		pane.add(createListaProdotti());
		pane.add(createListaMacchine());
		return pane;
	}

	public JScrollPane createListaProdotti() {
		mod1 = new DefaultListModel<String>();
		for (Prodotto p : prod) {
			mod1.addElement(p.getNome());
		}
		mod1.addElement("Aggiungi Nuovo Prodotto");
		prodotti = new JList<String>(mod1);
		prodotti.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int index = prodotti.getSelectedIndex();
				if (index == prod.size()) {
					root.dispose();
					new AggiungiBeniFrame(azienda, fornitore, 0);
				}
			}
		});

		JScrollPane pane = new JScrollPane(prodotti);
		pane.setPreferredSize(new Dimension(200, 400));
		pane.setBorder(BorderFactory.createTitledBorder("Prodotti in possesso"));

		return pane;
	}

	public JScrollPane createListaMacchine() {
		mod2 = new DefaultListModel<String>();
		for (MacchineDaCantiere m : ma) {
			mod2.addElement(m.getNome());
		}
		mod2.addElement("Aggiungi Nuova Macchina");
		macchine = new JList<String>(mod2);
		macchine.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int index = macchine.getSelectedIndex();
				if (index == ma.size()) {
					root.dispose();
					new AggiungiBeniFrame(azienda, fornitore, 1);
				}
			}
		});

		JScrollPane pane = new JScrollPane(macchine);
		pane.setPreferredSize(new Dimension(200, 450));
		pane.setBorder(BorderFactory.createTitledBorder("Macchine in possesso"));
		return pane;
	}

	public class Bottone implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (nome.isVisible()) {
				if (!n.getText().isEmpty()) {
					fornitore = new Fornitore(n.getText());
					azienda.getRepartoAmministrativo().aggiungiFornitore(fornitore);
					root.setSize(500, 500);
					root.setName(n.getText());
					root.setTitle("Fornitore: " + n.getText());
					nome.setVisible(false);
					root.remove(avanti);
					c.anchor = GridBagConstraints.FIRST_LINE_START;
					root.add(createMainPanel(), c);
				} else {
					JOptionPane.showMessageDialog(null, "Inserisci il nome del fornitore", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				root.dispose();
				new RepartoAmministrativoFrame(azienda);
			}

		}

	}

}
