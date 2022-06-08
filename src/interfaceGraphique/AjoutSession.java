package interfaceGraphique;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import traitementDB.TraitementFormation;

public class AjoutSession extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan;
	JLabel formation,datedebut,datefin;
	JTextField TFdatedebut,TFdatefin;
	JButton retour, ajout;
	String datedebutS,datefinS;
	
	public AjoutSession(final int IDformationS, final String formationS, final String formateurS, final String salleS,final int niveauS) {
		setBounds(100, 100, 516, 330);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AjoutSession.class.getResource("/image/logo MFC.png")));
		setTitle("Ajouter une session pour la formation "+formationS);
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		formation = new JLabel("Formation : "+formationS);
		formation.setFont(new Font("Times New Roman", Font.BOLD, 15));
		formation.setBounds(39, 25, 635, 44);
		pan.add(formation);
		
		datedebut = new JLabel("Date du début de la session : ");
		datedebut.setFont(new Font("Times New Roman", Font.BOLD, 15));
		datedebut.setBounds(39, 91, 211, 44);
		pan.add(datedebut);
		
		TFdatedebut = new JTextField("2000/01/01");
		TFdatedebut.setBounds(260, 104, 178, 20);
		pan.add(TFdatedebut);
		TFdatedebut.setColumns(10);
		
		datefin = new JLabel("Date de la fin de la session : ");
		datefin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		datefin.setBounds(39, 157, 211, 46);
		pan.add(datefin);
		
		TFdatefin = new JTextField("2000/01/01");
		TFdatefin.setBounds(260, 171, 178, 20);
		pan.add(TFdatefin);
		TFdatefin.setColumns(10);
		
		retour = new JButton("Retour");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SessionFormation session = new SessionFormation(IDformationS, formationS, formateurS, salleS, niveauS);
				session.setVisible(true);
			}
		});
		retour.setBounds(85, 239, 89, 23);
		pan.add(retour);
		
		ajout = new JButton("Ajouter");
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datedebutS = TFdatedebut.getText();
				datefinS = TFdatefin.getText();
				new TraitementFormation().ajoutSessionFormation(IDformationS, datedebutS, datefinS);
				SessionFormation session = new SessionFormation(IDformationS, formationS, formateurS, salleS, niveauS);
				session.setVisible(true);
				dispose();
			}
		});
		ajout.setBounds(319, 239, 89, 23);
		pan.add(ajout);
		
	}

}
