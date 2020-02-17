package amministrativoGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import approvviggionamento.Fornitore;
import approvviggionamento.MacchineDaCantiere;
import approvviggionamento.Prodotto;
import utilities.Azienda;

public class AggiungiBeniFrame extends JFrame {

	Azienda azienda;
	Fornitore fornitore;
	int tipo;
	JPanel prodottiPanel;
	private JTextField nomeProdotto;
	private JTextField casaProduttrice;
	private JTextField materialeDiCostruzione;
	private JTextField peso; // Espresso in kg
	private JTextField lunghezza; // Espressa in cm
	private JTextField larghezza; // Espressa in cm
	private JTextField altezza; // Espressa in cm
	private JTextField consumoEnergetico; // Espressa in Watt
	private JTextField anniGaranzia;
	private JTextField prezzo; // Espresso in euro
	private JTextField numeroPezziDisponibili;
	private JTextField nomeMacchina;
	private JTextField casaMadre;
	private JTextField pesoMacchina; // espresso in kg
	private JTextField potenza; // espressa in cavalli
	private JTextField prezzoMacchina; // espressa in euro

	private JLabel nome;
	private JLabel casa;
	private JLabel materiale;
	private JLabel pe; // Espresso in kg
	private JLabel lun; // Espressa in cm
	private JLabel larg; // Espressa in cm
	private JLabel alt; // Espressa in cm
	private JLabel consumo; // Espressa in Watt
	private JLabel anni;
	private JLabel pre; // Espresso in euro
	private JLabel numeroPezzi;
	private JLabel nm;
	private JLabel cm;
	private JLabel pm;
	private JLabel p;
	private JLabel prm;

	private JButton aggiungi;

	private JFrame root;

	public AggiungiBeniFrame(Azienda a, Fornitore f, int tipo) {
		super("Aggiungi al Fornitore");
		root = this;
		azienda = a;
		fornitore = f;
		this.tipo = tipo;
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(400, 500));
		if (tipo == 0)
			this.add(prodottoPane());
		else {
			this.add(macchinaPane());
		}
	}

	public JPanel macchinaPane() {
		JPanel macchinePane = new JPanel();
		macchinePane.setLayout(new GridBagLayout());
		macchinePane.setBorder(BorderFactory.createTitledBorder("Caratteristiche Macchina"));
		nomeMacchina = new JTextField(16);
		casaMadre = new JTextField(16);
		pesoMacchina = new JTextField(5);
		potenza = new JTextField(5);
		prezzoMacchina = new JTextField(5);
		nm = new JLabel("Nome Macchina");
		cm = new JLabel("Casa Madre");
		pm = new JLabel("Peso Macchina");
		p = new JLabel("Potenza");
		prm = new JLabel("Prezzo Macchina");
		aggiungi = new JButton("Aggiungi");
		aggiungi.addActionListener(new Aggiunta());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		macchinePane.add(nm, c);
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 0);
		macchinePane.add(nomeMacchina, c);

		c.gridx = 0;
		c.gridy = 1;
		macchinePane.add(cm, c);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 0, 0);
		macchinePane.add(casaMadre, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);

		macchinePane.add(pm, c);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, -110, 0, 0);
		macchinePane.add(pesoMacchina, c);

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);

		macchinePane.add(p, c);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, -110, 0, 0);

		macchinePane.add(potenza, c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 0, 0);

		macchinePane.add(prm, c);
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0, -110, 0, 0);

		macchinePane.add(prezzoMacchina, c);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		macchinePane.add(aggiungi, c);
		return macchinePane;
	}

	public JPanel prodottoPane() {
		prodottiPanel = new JPanel();
		prodottiPanel.setLayout(new GridBagLayout());
		aggiungi = new JButton("Aggiungi");
		aggiungi.addActionListener(new Aggiunta());
		nome = new JLabel("Nome Prodotto");
		nomeProdotto = new JTextField(16);

		casa = new JLabel("Casa Produttrice");
		casaProduttrice = new JTextField(16);

		materiale = new JLabel("Materiale di Costruzione");
		materialeDiCostruzione = new JTextField(16);

		pe = new JLabel("Peso (kg)");
		peso = new JTextField(4);

		lun = new JLabel("Lunghezza (cm)");
		lunghezza = new JTextField(4);

		larg = new JLabel("Larghezza (cm)");
		larghezza = new JTextField(4);

		alt = new JLabel("Altezza (cm)");
		altezza = new JTextField(4);

		consumo = new JLabel("Consumo (Watt)");
		consumoEnergetico = new JTextField(4);

		anni = new JLabel("Anni di Garanzia");
		anniGaranzia = new JTextField(4);

		pre = new JLabel("Prezzo (€)");
		prezzo = new JTextField(8);

		numeroPezzi = new JLabel("Numero Pezzi Disponibili");
		numeroPezziDisponibili = new JTextField(8);

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 20);
		prodottiPanel.add(nome, c);
		c.gridx = 1;
		c.gridy = 0;
		prodottiPanel.add(nomeProdotto, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 20);
		prodottiPanel.add(casa, c);
		c.gridx = 1;
		c.gridy = 1;
		prodottiPanel.add(casaProduttrice, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 20);
		prodottiPanel.add(materiale, c);
		c.gridx = 1;
		c.gridy = 2;
		prodottiPanel.add(materialeDiCostruzione, c);

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		prodottiPanel.add(pe, c);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, -150, 0, 0);

		prodottiPanel.add(peso, c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(lun, c);
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(0, -150, 0, 0);

		prodottiPanel.add(lunghezza, c);

		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(larg, c);
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(0, -150, 0, 0);

		prodottiPanel.add(larghezza, c);

		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(alt, c);
		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(0, -150, 0, 0);

		prodottiPanel.add(altezza, c);

		c.gridx = 0;
		c.gridy = 7;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(consumo, c);
		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(0, -150, 0, 0);

		prodottiPanel.add(consumoEnergetico, c);

		c.gridx = 0;
		c.gridy = 8;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(anni, c);
		c.gridx = 1;
		c.gridy = 8;
		c.insets = new Insets(0, -150, 0, 0);

		prodottiPanel.add(anniGaranzia, c);

		c.gridx = 0;
		c.gridy = 9;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(pre, c);
		c.gridx = 1;
		c.gridy = 9;
		c.insets = new Insets(0, -105, 0, 0);

		prodottiPanel.add(prezzo, c);

		c.gridx = 0;
		c.gridy = 10;
		c.insets = new Insets(0, 0, 0, 0);

		prodottiPanel.add(numeroPezzi, c);
		c.gridx = 1;
		c.gridy = 10;
		c.insets = new Insets(0, -105, 0, 0);

		prodottiPanel.add(numeroPezziDisponibili, c);
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		prodottiPanel.add(aggiungi, c);
		prodottiPanel.setBorder(BorderFactory.createTitledBorder("Caratteristiche Prodotto"));
		return prodottiPanel;
	}

	public class Aggiunta implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (tipo == 0) {
				if (nomeProdotto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Inserisci il nome del prodotto", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String nome = nomeProdotto.getText();
				String materiale = materialeDiCostruzione.getText();
				String casaprod = casaProduttrice.getText();
				
				try {
				int pes;
				if (!peso.getText().isEmpty())
					pes = Integer.parseInt(peso.getText());
				else
					pes = 0;

				int lu;
				if (!lunghezza.getText().isEmpty())
					lu = Integer.parseInt(lunghezza.getText());
				else
					lu = 0;

				int la;
				if (!larghezza.getText().isEmpty())
					la = Integer.parseInt(larghezza.getText());
				else
					la = 0;

				int alt;
				if (!altezza.getText().isEmpty())
					alt = Integer.parseInt(altezza.getText());
				else
					alt = 0;

				int con;
				if (!consumoEnergetico.getText().isEmpty())
					con = Integer.parseInt(consumo.getText());
				else
					con = 0;

				int an;
				if (!anniGaranzia.getText().isEmpty())
					an = Integer.parseInt(anniGaranzia.getText());
				else
					an = 0;

				int pr;
				if (!prezzo.getText().isEmpty())
					pr = Integer.parseInt(prezzo.getText());
				pr = 0;

				int npd;
				if (!numeroPezziDisponibili.getText().isEmpty())
					npd = Integer.parseInt(numeroPezziDisponibili.getText());
				else
					npd = 0;
				fornitore.aggiungiProdotto(new Prodotto(nome, materiale, casaprod, pes, lu, la, alt, con, an, pr, npd));
				root.dispose();
				new AggiuntaFornitoreFrame(azienda, fornitore);}
				catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Inserisci un numero accettabile", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			} else {
				String nome = nomeMacchina.getText();
				String cm = casaMadre.getText();
				Double pes;
				try {
					if (!pesoMacchina.getText().isEmpty())
						pes = Double.valueOf(pesoMacchina.getText());
					else {
						JOptionPane.showMessageDialog(null, "Inserisci il peso della macchina", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Double pot;
					if (!potenza.getText().isEmpty())
						pot = Double.valueOf(potenza.getText());
					else {
						JOptionPane.showMessageDialog(null, "Inserisci la potenza della macchina", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Double pr;
					if (!prezzoMacchina.getText().isEmpty())
						pr = Double.valueOf(prezzoMacchina.getText());
					else {
						JOptionPane.showMessageDialog(null, "Inserisciil prezzo della macchina", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					fornitore.aggiungiMacchina(new MacchineDaCantiere(nome, cm, pes, pot, pr));
					root.dispose();
					new AggiuntaFornitoreFrame(azienda, fornitore);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Inserisci un numero accettabile", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}

		}

	}
}
