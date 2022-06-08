package interfaceGraphique;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import traitementDB.TraitementFormateur;

public class AjoutFormateur extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan;
	JLabel nom, prenom, datenaissance, sexe, adresse, cp, ville, pays, mail,spe;
	JTextField TFnom, TFprenom, TFdatenaissance, TFadresse, TFcp, TFville, TFpays, TFmail,TFspe;
	JComboBox<String> CBsexe;
	JButton retour, ajout;
	String nomS="", prenomS="",datenaissanceS="",sexeS="",adresseS="",cpS="",villeS="",paysS="",mailS="",pseudo="",mdp="",speS="";
	
	public AjoutFormateur() {
		setTitle("Ajouter un Formateur");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AjoutFormateur.class.getResource("/image/logo MFC.png")));
		setBounds(100, 100, 820, 380);
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		nom = new JLabel("Nom :");
		nom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nom.setBounds(35, 26, 60, 26);
		pan.add(nom);
		
		TFnom = new JTextField();
		TFnom.setBounds(170, 30, 167, 20);
		pan.add(TFnom);
		TFnom.setColumns(10);
		
		prenom = new JLabel("Prénom :");
		prenom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		prenom.setBounds(515, 26, 71, 26);
		pan.add(prenom);
		
		TFprenom = new JTextField();
		TFprenom.setBounds(596, 30, 167, 20);
		pan.add(TFprenom);
		TFprenom.setColumns(10);
		
		datenaissance = new JLabel("Date de Naissance :");
		datenaissance.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		datenaissance.setBounds(35, 83, 119, 20);
		pan.add(datenaissance);
		
		TFdatenaissance = new JTextField("2000/01/01");
		TFdatenaissance.setBounds(170, 84, 167, 20);
		pan.add(TFdatenaissance);
		TFdatenaissance.setColumns(10);
		
		sexe = new JLabel("Sexe :");
		sexe.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sexe.setBounds(515, 84, 60, 18);
		pan.add(sexe);
		
		CBsexe = new JComboBox<String>();
		CBsexe.setBounds(596, 84, 41, 20);
		CBsexe.addItem("M");
		CBsexe.addItem("F");
		pan.add(CBsexe);
		
		adresse = new JLabel("Adresse :");
		adresse.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		adresse.setBounds(35, 135, 101, 20);
		pan.add(adresse);
		
		TFadresse = new JTextField();
		TFadresse.setBounds(170, 136, 593, 20);
		pan.add(TFadresse);
		TFadresse.setColumns(10);
		
		cp = new JLabel("Code Postal :");
		cp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cp.setBounds(35, 182, 89, 26);
		pan.add(cp);
		
		TFcp = new JTextField();
		TFcp.setBounds(170, 186, 89, 20);
		pan.add(TFcp);
		TFcp.setColumns(10);

		ville = new JLabel("Ville :");
		ville.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ville.setBounds(328, 185, 54, 21);
		pan.add(ville);
		
		TFville = new JTextField();
		TFville.setBounds(378, 186, 145, 20);
		pan.add(TFville);
		TFville.setColumns(10);
		
		pays = new JLabel("Pays :");
		pays.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pays.setBounds(588, 185, 46, 21);
		pan.add(pays);

		TFpays = new JTextField();
		TFpays.setBounds(644, 186, 119, 20);
		pan.add(TFpays);
		TFpays.setColumns(10);
		
		mail = new JLabel("Mail :");
		mail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mail.setBounds(35, 234, 76, 26);
		pan.add(mail);
		
		TFmail = new JTextField();
		TFmail.setBounds(170, 238, 244, 20);
		pan.add(TFmail);
		TFmail.setColumns(10);
		
		spe = new JLabel("Spécialité :");
		spe.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		spe.setBounds(515, 234, 76, 26);
		pan.add(spe);
		
		TFspe = new JTextField();
		TFspe.setBounds(596, 238, 167, 20);
		pan.add(TFspe);
		TFspe.setColumns(10);
		
		retour = new JButton("Retour");
		retour.setBounds(141, 291, 89, 23);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Formateur session = new Formateur();
				session.setVisible(true);
				dispose();
			}
		});
		pan.add(retour);
		
		ajout = new JButton("Ajouter");
		ajout.setBounds(548, 291, 89, 23);
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prenomS=TFprenom.getText();
				nomS=TFnom.getText();
				datenaissanceS=TFdatenaissance.getText();
				sexeS=CBsexe.getSelectedItem().toString();
				adresseS=TFadresse.getText();
				villeS=TFville.getText();
				cpS=TFcp.getText();
				paysS=TFpays.getText();
				mailS=TFmail.getText();
				pseudo=nomS+prenomS.substring(0, 2);
				mdp = "stagiaire";
				speS=TFspe.getText();
				new TraitementFormateur().ajoutFormateur(prenomS, nomS, datenaissanceS, sexeS, adresseS, villeS, cpS,
		 					 paysS, mailS, pseudo, mdp,speS);
				Formateur session = new Formateur();
				session.setVisible(true);
				dispose();
			}
		});
		pan.add(ajout);
	}
}
