package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan, panNord, panCentre;
	JLabel menu;
	JButton formation, stagiaire, formateur, deco;
	
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/image/logo MFC.png")));
		setTitle("Menu Principal");
		setBounds(100, 100, 1050, 600);
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);		
		pan.setLayout(new BorderLayout(0, 0));
		
		panNord = new JPanel();
		pan.add(panNord, BorderLayout.NORTH);
		
		menu = new JLabel("Menu Principal");
		menu.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panNord.add(menu);
		
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new GridLayout(0, 2, 40, 40));
		panCentre.setBorder(new EmptyBorder(5, 150, 5, 150));
		
		formation = new JButton();
		formation.setIcon(new ImageIcon(Menu.class.getResource("/image/formation.png")));
		formation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Formation session = new Formation();
				session.setVisible(true);
			}
		});
		panCentre.add(formation);

		stagiaire = new JButton();
		stagiaire.setIcon(new ImageIcon(Menu.class.getResource("/image/stag.png")));
		stagiaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stagiaire session = new Stagiaire();
				session.setVisible(true);
			}
		});
		panCentre.add(stagiaire);
		
		formateur = new JButton();
		formateur.setIcon(new ImageIcon(Menu.class.getResource("/image/form.png")));
		formateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Formateur session = new Formateur();
				session.setVisible(true);
			}
		});
		panCentre.add(formateur);
		
		deco = new JButton();
		deco.setIcon(new ImageIcon(Menu.class.getResource("/image/deco.png")));
		deco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	    		int i = JOptionPane.showConfirmDialog(null,"Etes-vous sur de vouloir vous déconnecter ?","", JOptionPane.YES_NO_OPTION);
					if (i==0) {	
						Identification session = new Identification();
						session.setVisible(true);
						dispose();
					}
			}
		});
		panCentre.add(deco);

	}
	

}
