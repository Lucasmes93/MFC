package interfaceGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import traitementDB.Connect_DB;

public class Identification extends JFrame {
		
	private static final long serialVersionUID = 1L;
	JPanel pan;
	JLabel logo, utilisateur, password;
	JTextField TFutilisateur;
	JPasswordField TFpassword;
	JButton Bquitter, Bconnexion;
	String Dutilisateur,Dmdp,texteID, Duser;
		
	public Identification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Identification.class.getResource("/image/logo MFC.png")));
		setTitle("Application de gestion du centre de formation MFC");
		pan = new JPanel();
	    pan.setLayout(null);
		setSize(700,400);
		setContentPane(pan);
		setLocationRelativeTo(null);
			
		logo = new JLabel();
		logo.setIcon(new ImageIcon(Identification.class.getResource("/image/logo MFC.png")));
		logo.setBounds(280, 11, 163, 128);
		pan.add(logo);
			
		utilisateur = new JLabel("Utilisateur :");
		utilisateur.setFont(new Font("Times New Roman", Font.BOLD, 21));
		utilisateur.setBounds(86, 142, 151, 24);
		pan.add(utilisateur);
			
		password = new JLabel ("Mot de Passe :");
		password.setFont(new Font("Times New Roman", Font.BOLD, 21));
		password.setBounds(86, 198, 142, 24);
		pan.add(password);
			
		TFutilisateur = new JTextField();
		TFutilisateur.setBounds(250, 145, 275, 26);
		TFutilisateur.setColumns(10);
		pan.add(TFutilisateur);
			
		TFpassword = new JPasswordField();
		TFpassword.setBounds(250, 200, 275, 26);
		TFpassword.setColumns(10);
		pan.add(TFpassword);
			
		Bconnexion = new JButton ("Connexion");
		Bconnexion.setBackground(new Color(2, 152, 229));
		Bconnexion.setForeground(Color.PINK);
		Bconnexion.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		Bconnexion.setBounds(219, 265, 257, 46);
		Bconnexion.addActionListener(new ActionListener()  {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)  {
	    		Dutilisateur = TFutilisateur.getText();
	    		Dmdp = TFpassword.getText();
	    		String admin = new Connect_DB().verifID(Dutilisateur, Dmdp);
	    			if (admin == "accept") {
	    				Menu session = new Menu();
	    				session.setVisible(true);
	    				dispose();
	    			} 
		    	}
			});
			pan.add(Bconnexion);
			
		}
}
