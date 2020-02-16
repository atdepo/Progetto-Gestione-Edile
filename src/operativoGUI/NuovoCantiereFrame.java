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
import dipendenti.Responsabile;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import utilities.Azienda;

public class NuovoCantiereFrame extends JFrame {

	RepartoAmministrativo repartoAmministrativo;
	RepartoOperativo repartoOperativo;
	JLabel responsabili;
	JLabel valoreCantiere;
	JLabel latitudine;
	JLabel longitudine;
	JLabel estensione;

	JTextField valore;
	JTextField lat;
	JTextField lon;
	JTextField est;
	JComboBox<String> resp;

	JButton avanti;

	JPanel content;
	JPanel mainPanel;

	ArrayList<Responsabile> scelta;

	CardLayout cl;

	JFrame root;

	public NuovoCantiereFrame(RepartoOperativo ro, RepartoAmministrativo ra) {
		this.setVisible(true);
		this.setSize(new Dimension(550, 450));
		this.setLocationRelativeTo(null);
		root = this;
		repartoAmministrativo = ra;
		repartoOperativo = ro;
		cl = new CardLayout();
		scelta = new ArrayList<Responsabile>();
		for (Dipendente d : ra.getDipendenti()) {
			if ((Dipendente.isDirigente(d) || Dipendente.isQuadro(d)) && !d.isImpegnato()) {
				scelta.add((Responsabile) d);
			}
		}
		resp = new JComboBox<String>();
		for (Responsabile r : scelta) {
			Dipendente d = (Dipendente) r;
			if (Dipendente.isQuadro(d))
				resp.addItem("(Quadro)" + d.getNome() + " " + d.getCognome());
			else
				resp.addItem("(Dirigente)" + d.getNome() + " " + d.getCognome());
		}

		avanti = new JButton("Avanti");
		avanti.addActionListener(new Create());
		JPanel pane = new JPanel();
		content = new JPanel();
		content.setLayout(cl);
		this.add(content);
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridheight = 5;
		c.gridwidth = 4;

		pane.add(creazioneCantiere(), c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 10;
		c.gridy = 10;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(avanti, c);
		content.add(pane, "main");
	}

	public JPanel creazioneCantiere() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 2, 50, 50));
		mainPanel.setBorder(BorderFactory.createTitledBorder("Creazione Cantiere"));
		responsabili = new JLabel("Responsabile");
		valoreCantiere = new JLabel("Valore Cantiere");
		latitudine = new JLabel("Latitudine");
		longitudine = new JLabel("Longitudine");
		estensione = new JLabel("Estensione");
		valore = new JTextField();
		lat = new JTextField();
		lon = new JTextField();
		est = new JTextField();

		mainPanel.add(valoreCantiere);
		mainPanel.add(valore);
		mainPanel.add(latitudine);
		mainPanel.add(lat);
		mainPanel.add(longitudine);
		mainPanel.add(lon);
		mainPanel.add(estensione);
		mainPanel.add(est);
		mainPanel.add(responsabili);
		mainPanel.add(resp);
		return mainPanel;

	}

	private class Create implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!valore.getText().isEmpty() && Double.parseDouble(valore.getText()) > 500000.0D) {
				Responsabile r = scelta.get(resp.getSelectedIndex());
				Dipendente d = (Dipendente) r;
				if (!Dipendente.isDirigente(d)) {
					JOptionPane.showMessageDialog(mainPanel, "Valore cantiere >500000, assegna un Dirigente", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if(!scelta.isEmpty()) {
			if (lat.getText().isEmpty() || lon.getText().isEmpty() || est.getText().isEmpty()
					|| Double.parseDouble(valore.getText()) <= 0 || Double.parseDouble(est.getText()) < 0) {
				JOptionPane.showMessageDialog(mainPanel, "Inserisci dei dati corretti", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			}
			else {
				JOptionPane.showMessageDialog(mainPanel, "Inserisci un responsabile", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			return;
			}

			repartoOperativo.apriCantiere(Double.parseDouble(valore.getText()), Double.parseDouble(lat.getText()),
					Double.parseDouble(lon.getText()), Double.parseDouble(est.getText()),
					scelta.get(resp.getSelectedIndex()));
			System.out.println("Qua ho finito");
			root.setSize(new Dimension(700, 500));
			root.setLocationRelativeTo(null);
			content.add(new CreazioneSquadraPanel(repartoOperativo, repartoAmministrativo), "creazione");
			cl.show(content, "creazione");

		}

	}

}
