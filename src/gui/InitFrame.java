package gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import amministrativo.RepartoAmministrativo;
import amministrativoGUI.RepartoAmministrativoFrame;
import operativo.RepartoOperativo;
import operativoGUI.RepartoOperativoFrame;
import utilities.Azienda;

public class InitFrame extends JFrame {

	Azienda azienda;
	RepartoOperativo repartoOperativo;
	RepartoAmministrativo repartoAmministrativo;

	JRadioButton nuovo;
	JRadioButton carica;
	JRadioButton salva;

	JButton esegui;

	JFrame root;

	JTextField nomeFile;
	JTextField capacitaMagazzino;
	JTextField postiMacchine;
	JTextField capitale;

	JPanel nome;
	JPanel valoriNuovaAzienda;
	JPanel panel;
	JPanel sceltaReparto;

	public InitFrame() {
		super("Gestione Edile");
		this.setVisible(true);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(scelta(), BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
		root = this;
		// TODO eliminare
		PopolamentoAzienda p = new PopolamentoAzienda();
		p.popola();
		azienda = p.getAzienda();

	}
	
	public InitFrame(Azienda a) {
		super("Gestione Edile");
		this.setVisible(true);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(scelta(), BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
		root = this;
		azienda=a;
	}

	public JPanel scelta() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 250));

		esegui = new JButton("Esegui");
		esegui.addActionListener(new EseguiScelta());
		nomeFile = new JTextField(16);
		nomeFile.setText("saves.dat");

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 1;
		nome = new JPanel();
		nome.add(nomeFile);
		nome.setBorder(BorderFactory.createTitledBorder("Nome File"));
		panel.add(nome, c);

		c.gridx = 2;
		c.gridy = 1;
		panel.add(esegui, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 3;
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(valoriAzienda(), c);

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(radioPanel(), c);
		return panel;
	}

	public JPanel valoriAzienda() {
		valoriNuovaAzienda = new JPanel();
		valoriNuovaAzienda.setVisible(false);
		valoriNuovaAzienda.setLayout(new GridBagLayout());
		capacitaMagazzino = new JTextField(10);
		capacitaMagazzino.setVisible(true);
		postiMacchine = new JTextField(10);
		postiMacchine.setVisible(true);
		capitale = new JTextField(10);
		capitale.setVisible(true);

		valoriNuovaAzienda.setBorder(BorderFactory.createTitledBorder("Valori Azienda"));
		JPanel pane1 = new JPanel();
		pane1.add(capacitaMagazzino);
		pane1.setBorder(BorderFactory.createTitledBorder("Capacita Magazzino in m^2"));

		JPanel pane2 = new JPanel();
		pane2.add(capitale);
		pane2.setBorder(BorderFactory.createTitledBorder("Capitale Azienda"));

		JPanel pane3 = new JPanel();
		pane3.add(postiMacchine);
		pane3.setBorder(BorderFactory.createTitledBorder("Posti Macchine da Cantiere"));

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.ipadx = 40;
		c.gridx = 0;
		c.gridy = 0;
		valoriNuovaAzienda.add(pane1, c);
		c.gridx = 0;
		c.gridy = 1;
		valoriNuovaAzienda.add(pane2, c);
		c.gridx = 0;
		c.gridy = 2;
		valoriNuovaAzienda.add(pane3, c);
		return valoriNuovaAzienda;
	}

	public JPanel radioPanel() {
		panel = new JPanel();
		nuovo = new JRadioButton("Nuovo");
		nuovo.addActionListener(new ModificaPanel());
		carica = new JRadioButton("Carica");
		carica.addActionListener(new ModificaPanel());
		salva = new JRadioButton("Salva");
		salva.addActionListener(new ModificaPanel());
		ButtonGroup g = new ButtonGroup();
		carica.setSelected(true);
		g.add(carica);
		g.add(salva);
		g.add(nuovo);
		panel.setLayout(new GridLayout(3, 1));
		panel.add(nuovo);
		panel.add(carica);
		panel.add(salva);
		panel.setBorder(BorderFactory.createTitledBorder("Opzioni"));
		return panel;
	}

	public JPanel creaSceltaReparto() {
		sceltaReparto = new JPanel();
		sceltaReparto.setVisible(true);
		JButton ra = new JButton("Reparto Amministrativo");
		ra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getRoot(sceltaReparto);
				frame.dispose();
				new RepartoAmministrativoFrame(azienda);
			}
		});
		JButton ro = new JButton("Reparto Operativo");
		ro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getRoot(sceltaReparto);
				frame.dispose();
				new RepartoOperativoFrame(azienda);
			}
		});
		JPanel desc= new JPanel();
		desc.setLayout(new GridLayout(3,1));
		JLabel l = new JLabel();
		JLabel l1 = new JLabel();
		JLabel l2= new JLabel();
		l.setText("Questa Azienda ha: " + azienda.getRepartoAmministrativo().getDipendenti().size()
				+ " Dipendenti ");
		l1.setText("Un capitale di:" + String.valueOf(azienda.getRepartoAmministrativo().getCapitale())+" Euro");
		l2.setText("E "+azienda.getRepartoOperativo().getCantieri().size()+" Cantieri Aperti");
		desc.add(l);
		desc.add(l1);
		desc.add(l2);
		sceltaReparto.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		sceltaReparto.add(desc, c);

		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 10);
		sceltaReparto.add(ra, c);

		c.gridx = 3;
		c.gridy = 5;
		sceltaReparto.add(ro, c);
		return sceltaReparto;
	}

	private class EseguiScelta implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			/*
			 * panel.setVisible(false); nome.setVisible(false);
			 * valoriNuovaAzienda.setVisible(false); esegui.setVisible(false);
			 */
			if (salva.isSelected()) {

				File file = new File(nomeFile.getText());
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
					out.writeObject(azienda);
					out.close();
					// new RepartoOperativoFrame(azienda);

					// TODO CONFERMA SALVATAGGIO
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			else if (carica.isSelected()) {

				File file = new File(nomeFile.getText());
				if (file.exists()) {
					try {
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
						azienda = (Azienda) in.readObject();
						in.close();
						root.revalidate();
						root.repaint();
						root.dispose();
						new SceltaFrame();
						// new RepartoOperativoFrame(azienda);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else {
				if (!capacitaMagazzino.getText().isEmpty() && !postiMacchine.getText().isEmpty()
						&& !capitale.getText().isEmpty()) {
					nome.setVisible(false);

					repartoAmministrativo = new RepartoAmministrativo(Integer.parseInt(capacitaMagazzino.getText()),
							Integer.parseInt(postiMacchine.getText()), Double.parseDouble(capitale.getText()));
					repartoOperativo = new RepartoOperativo();
					azienda = new Azienda(repartoAmministrativo, repartoOperativo);
					root.dispose();
					new RepartoOperativoFrame(azienda);
				} else {
					JOptionPane.showMessageDialog(null, "Inserisci dei dati corretti", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private class ModificaPanel implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (nuovo.isSelected()) {
				nome.setVisible(false);
				valoriNuovaAzienda.setVisible(true);

			} else {
				valoriNuovaAzienda.setVisible(false);
				nome.setVisible(true);
			}
		}
	}

	public class SceltaFrame extends JFrame {

		public SceltaFrame() {
			super("Scelta Reparto");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setLocation(600,350);
			this.setSize(450, 300);
			this.setLayout(new BorderLayout());
			this.add(creaSceltaReparto(), BorderLayout.CENTER);

		}

	}

}
