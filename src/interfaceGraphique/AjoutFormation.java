package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import traitementDB.TraitementFormation;

public class AjoutFormation extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel pan, panCentre, panSud;
	JLabel Lformation, Lniveau, Lsalle, Lprof, Lprix;
	JTextField TFformation, TFprix;
	JSpinner Sniveau;
	@SuppressWarnings("rawtypes")
	JComboBox CBsalle,CBprof;
	JButton retour, ajouter;
	String formation, formateur, salle, prof, prix, niv;
	String [] tabFormateur;
	int niveau;
	
	@SuppressWarnings("rawtypes")
	public AjoutFormation() {
		setBounds(100, 100, 800, 370);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AjoutFormation.class.getResource("/image/logo MFC.png")));
		setTitle("Ajouter une Formation");
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 25, 5, 25));
		setContentPane(pan);
		pan.setLayout(new BorderLayout(20, 20));
		setResizable(false);	
		setLocationRelativeTo(null);
		
		// Mon Panel Centre
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new GridLayout(0,2,150,30));
		panCentre.setBorder(new EmptyBorder(15, 25, 5, 25));
		
		Lformation = new JLabel("Intitulé de la Formation : ");
		panCentre.add(Lformation);
		
		TFformation = new JTextField();
		panCentre.add(TFformation);
		
		Lniveau = new JLabel("Niveau de la Formation : ");
		panCentre.add(Lniveau);

		Sniveau = new JSpinner();
		panCentre.add(Sniveau);
		
		Lprof = new JLabel("Professeur de la formation : ");
		panCentre.add(Lprof);
		
		CBprof = new JComboBox();
		new TraitementFormation().CBformateurF(CBprof);
		panCentre.add(CBprof);
		
		Lsalle = new JLabel("Salle : ");
		panCentre.add(Lsalle);
		
		CBsalle = new JComboBox();
		new TraitementFormation().CBsalleF(CBsalle);
		panCentre.add(CBsalle);
		
		Lprix = new JLabel("Prix de la formation : ");
		panCentre.add(Lprix);
		
		TFprix = new JTextField();
		panCentre.add(TFprix);
		
		// Mon Panel Sud 
		panSud = new JPanel();
		pan.add(panSud, BorderLayout.SOUTH);
		panSud.setLayout(new GridLayout(0,2,220,10));
		
		retour = new JButton("Retour");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formation session = new Formation();
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(retour);
		
		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formation = TFformation.getText();
				niv = Sniveau.getValue().toString();
				niveau = Integer.parseInt(niv);
				formateur = CBprof.getSelectedItem().toString();
					if (formateur != " "){
						tabFormateur = formateur.split(" ");
						formateur = tabFormateur[0];
					}
				salle = CBsalle.getSelectedItem().toString();
				prix = TFprix.getText();
				new TraitementFormation().ajoutFormation(formation, niveau, formateur, salle, prix);
				Formation session = new Formation();
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(ajouter);
	}
}
