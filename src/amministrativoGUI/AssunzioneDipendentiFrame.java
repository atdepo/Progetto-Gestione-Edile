package amministrativoGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import amministrativo.RepartoAmministrativo;
import dipendenti.Dipendente;
import dipendenti.Dirigente;
import dipendenti.Impiegato;
import dipendenti.Operaio;
import dipendenti.Quadro;
import eccezioni.DipendenteNonAssumibileException;
import utilities.Azienda;

public class AssunzioneDipendentiFrame extends JFrame {

	JLabel nome;
	JLabel cognome;
	JLabel eta;
	JLabel giorni;

	JTextField n;
	JTextField c;
	JTextField e;
	JTextField g;

	JComboBox<Operaio.lavoro> lavori;

	JButton assumi;

	Azienda azienda;
	RepartoAmministrativo ra;
	JFrame root;
	JLabel ore;
	JTextField o;

	int t;

	public AssunzioneDipendentiFrame(int type, Azienda a) {
		super("Assunzione Dipendenti");
		azienda = a;
		root = this;
		ra = a.getRepartoAmministrativo();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t = type;
		if (type < 1 || type > 4)
			throw new IllegalArgumentException("Tipo non riconosciuto");

		this.setVisible(true);
		this.setLocationRelativeTo(null);
		assumi = new JButton("Assumi");
		assumi.addActionListener(new Aggiunta());
		this.setSize(new Dimension(460, 320));
		switch (type) {
		case 1:
			this.add(impiegatoPane());
			this.add(assumi);
			break;
		case 2:
			this.add(pannelloOperaio());
			this.add(assumi);
			break;
		case 3:
			this.add(pannelloQuadro());
			this.add(assumi);
			break;
		case 4:
			this.add(pannelloDirigente());
			this.add(assumi);
			break;
		}
	}

	private JPanel impiegatoPane() {

		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(300, 200));
		pane.setBorder(BorderFactory.createTitledBorder("Dati dell'Impiegato"));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloNome(), cc);
		cc.gridx = 0;
		cc.gridy = 1;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloCognome(), cc);
		cc.gridx = 0;
		cc.gridy = 2;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloEta(), cc);
		cc.gridx = 0;
		cc.gridy = 3;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloGiorni(), cc);
		return pane;
	}

	private JPanel pannelloOperaio() {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(300, 200));
		pane.setBorder(BorderFactory.createTitledBorder("Dati dell'Operaio"));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloNome(), cc);
		cc.gridx = 0;
		cc.gridy = 1;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloCognome(), cc);
		cc.gridx = 0;
		cc.gridy = 2;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloEta(), cc);
		cc.gridx = 0;
		cc.gridy = 3;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloLavoro(), cc);
		cc.gridx = 0;
		cc.gridy = 4;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloOre(), cc);

		return pane;
	}

	private JPanel pannelloQuadro() {
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(300, 200));
		pane.setBorder(BorderFactory.createTitledBorder("Dati del Quadro"));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloNome(), cc);
		cc.gridx = 0;
		cc.gridy = 1;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloCognome(), cc);
		cc.gridx = 0;
		cc.gridy = 2;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloEta(), cc);
		return pane;
	}

	private JPanel pannelloDirigente() {

		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(300, 200));
		pane.setBorder(BorderFactory.createTitledBorder("Dati del Dirigente"));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloNome(), cc);
		cc.gridx = 0;
		cc.gridy = 1;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloCognome(), cc);
		cc.gridx = 0;
		cc.gridy = 2;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(pannelloEta(), cc);
		return pane;

	}

	private JPanel pannelloNome() {
		JPanel pane = new JPanel();
		this.setLayout(new GridBagLayout());
		JLabel nome = new JLabel("Nome        ");
		n = new JTextField(16);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(nome, cc);
		cc.gridx = 1;
		cc.gridy = 0;
		pane.add(n, cc);
		return pane;
	}

	private JPanel pannelloCognome() {
		JPanel pane = new JPanel();
		this.setLayout(new GridBagLayout());
		cognome = new JLabel("Cognome ");
		c = new JTextField(16);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(cognome, cc);
		cc.gridx = 1;
		cc.gridy = 0;
		pane.add(c, cc);
		return pane;
	}

	private JPanel pannelloEta() {
		JPanel pane = new JPanel();
		this.setLayout(new GridBagLayout());
		eta = new JLabel("Età             ");
		e = new JTextField(4);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(eta, cc);
		cc.gridx = 6;
		cc.gridy = 0;
		pane.add(e, cc);
		return pane;
	}

	private JPanel pannelloGiorni() {
		JPanel pane = new JPanel();
		this.setLayout(new GridBagLayout());
		giorni = new JLabel("Giorni da lavorare ");
		g = new JTextField(4);
		GridBagConstraints cc = new GridBagConstraints();
		cc.weightx = 1.0;
		cc.weighty = 1.0;
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;

		pane.add(giorni, cc);
		cc.gridx = 1;
		cc.gridy = 0;
		pane.add(g, cc);
		pane.setBorder(BorderFactory.createTitledBorder("Opzionale"));
		return pane;
	}

	public JPanel pannelloOre() {
		JPanel pane = new JPanel();
		this.setLayout(new GridBagLayout());
		ore = new JLabel("Ore da lavorare ");
		o = new JTextField(4);
		GridBagConstraints cc = new GridBagConstraints();
		cc.weightx = 1.0;
		cc.weighty = 1.0;
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;

		pane.add(ore, cc);
		cc.gridx = 1;
		cc.gridy = 0;
		pane.add(o, cc);
		pane.setBorder(BorderFactory.createTitledBorder("Opzionale"));
		return pane;
	}

	private JPanel pannelloLavoro() {
		JPanel pane = new JPanel();
		lavori = new JComboBox<Operaio.lavoro>();
		lavori.addItem(Operaio.lavoro.ELETTRICISTA);
		lavori.addItem(Operaio.lavoro.IDRAULICO);
		lavori.addItem(Operaio.lavoro.MURATORE);
		lavori.addItem(Operaio.lavoro.PIASTRELLISTA);
		JLabel label = new JLabel("Lavoro         ");
		pane.setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();
		cc.weightx = 1.0;
		cc.weighty = 1.0;
		cc.gridx = 0;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.WEST;
		pane.add(label, cc);
		cc.gridx = 1;
		cc.gridy = 0;
		pane.add(lavori, cc);
		return pane;
	}

	public class Aggiunta implements ActionListener {

		public void actionPerformed(ActionEvent e1) {
			String nome, cognome;

			int eta;
			if (n.getText().isEmpty() || c.getText().isEmpty() || e.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Inserire dati correttamente", "ERROR", JOptionPane.ERROR_MESSAGE);

				return;
			}
			try {
				nome = n.getText();
				cognome = c.getText();
				eta = Integer.parseInt(e.getText());
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, e.getText() + " non è un numero accetabile", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			switch (t) {
			case 1: {
				try {
					if (g.getText().isEmpty()) {
						ra.assumiDipendente(
								new Impiegato(nome, cognome, eta, String.valueOf(ra.getNumImpiegati() + 1)));
						JOptionPane.showMessageDialog(null,
								"Non è stato inserito il numero di giorni, ne verrà assegnato un numero standard");
					} else {
						ra.assumiDipendente(new Impiegato(nome, cognome, eta, String.valueOf(ra.getNumImpiegati() + 1),
								Integer.parseInt(g.getText())));
					}
					root.dispose();
					new RepartoAmministrativoFrame(azienda);
				} catch (DipendenteNonAssumibileException e) {
					JOptionPane.showMessageDialog(null, "Questo dipendente non ha l'età per poter essere assunto",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
				break;
			case 2:
				if (lavori.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Selezionare un lavoro", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if (o.getText().isEmpty()) {

						ra.assumiDipendente(new Operaio(nome, cognome, eta, String.valueOf(ra.getNumOperai() + 1),
								(Operaio.lavoro) lavori.getSelectedItem()));
						JOptionPane.showMessageDialog(null,
								"Non è stato inserito il numero di ore, ne verrà assegnato un numero standard");
					} else {

						ra.assumiDipendente(new Operaio(nome, cognome, eta, String.valueOf(ra.getNumOperai() + 1),
								(Operaio.lavoro) lavori.getSelectedItem(), Integer.parseInt(o.getText())));
					}
					root.dispose();
					new RepartoAmministrativoFrame(azienda);
				} catch (DipendenteNonAssumibileException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 3:
				try {
					ra.assumiDipendente(new Quadro(nome, cognome, eta, String.valueOf(ra.getNumQuadri() + 1)));
					root.dispose();
					new RepartoAmministrativoFrame(azienda);
				} catch (DipendenteNonAssumibileException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 4:
				try {
					ra.assumiDipendente(new Dirigente(nome, cognome, eta, String.valueOf(ra.getNumDirigenti() + 1)));
					root.dispose();
					new RepartoAmministrativoFrame(azienda);
				} catch (DipendenteNonAssumibileException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}

		}

	}
}
