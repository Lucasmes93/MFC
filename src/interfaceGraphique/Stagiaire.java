package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import traitementDB.Tableau;
import traitementDB.TraitementStagiaire;

public class Stagiaire extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan, panNord, panCentre, panSud, panNordEst;
	JLabel rechercherL;
	JTable tableStagiaire;
	JScrollPane SPstagiaire;
	JButton ajout, modif, supp, retour, recherche;
	int IDstagiaire;
	String nom,prenom,datenaissance,sexe,adresse,ville,cp,pays,mail,mdp, rechercheS;
	JTextField TFrecherche;
	
	
	public Stagiaire() {
		setBounds(100, 100, 1050, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Stagiaire.class.getResource("/image/logo MFC.png")));
		setTitle("Listes des Stagiaires");
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(new BorderLayout(0,0));
		
		// Mon Panel Nord
		panNord = new JPanel();
		pan.add(panNord, BorderLayout.NORTH);
		panNord.setLayout(new BorderLayout(0,0));
		
		panNordEst = new JPanel();
		panNord.add(panNordEst,  BorderLayout.EAST);
		
		rechercherL = new JLabel("Rechercher par nom : ");
		panNordEst.add(rechercherL);
		
		TFrecherche = new JTextField();
		panNordEst.add(TFrecherche);
		TFrecherche.setColumns(11);
		
		recherche = new JButton();
		recherche.setIcon(new ImageIcon(Stagiaire.class.getResource("/image/recherche.png")));
		recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechercheS=TFrecherche.getText();
                tableStagiaire.setModel(new Tableau().TableStagiaireRecherche(rechercheS));
                tailleTable();
                IDstagiaire = 0;
			}
		});
		panNordEst.add(recherche);
		
		// Mon Panel Centre
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new GridLayout(0,1,0,0));
		
		tableStagiaire = new JTable(new Tableau().TableStagiaire());
		tailleTable();
		tableStagiaire.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint(); 
                int row = tableStagiaire.rowAtPoint(p);  
                	String ID = tableStagiaire.getValueAt(row,0).toString();
                	IDstagiaire = Integer.parseInt(ID);
                	nom = tableStagiaire.getValueAt(row,1).toString();
                	prenom = tableStagiaire.getValueAt(row,2).toString();
                	datenaissance = tableStagiaire.getValueAt(row,3).toString();
                	sexe = tableStagiaire.getValueAt(row,4).toString();
                	adresse = tableStagiaire.getValueAt(row,5).toString();
                	ville = tableStagiaire.getValueAt(row,6).toString();
                	cp = tableStagiaire.getValueAt(row,7).toString();
                	pays = tableStagiaire.getValueAt(row,8).toString();
                	mail = tableStagiaire.getValueAt(row,9).toString();
                	mdp = tableStagiaire.getValueAt(row,10).toString(); 
            }	    
        
        });
		SPstagiaire = new JScrollPane(tableStagiaire);
		panCentre.add(SPstagiaire);
			
		// Mon Panel Sud
		panSud = new JPanel();
		pan.add(panSud, BorderLayout.SOUTH);
		panSud.setLayout(new GridLayout(0,4,0,0));
		
		retour = new JButton("  Retour");
		retour.setIcon(new ImageIcon(Stagiaire.class.getResource("/image/retour.png")));
		retour.setFont(new Font("Times New Roman", Font.BOLD, 15));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panSud.add(retour);
		
		ajout = new JButton("  Ajouter un Stagiaire");
		ajout.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ajout.setIcon(new ImageIcon(Stagiaire.class.getResource("/image/ajoutstag.png")));
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterStagiaire session = new AjouterStagiaire();
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(ajout);
		
		modif = new JButton("  Modifier un Stagiaire");
		modif.setFont(new Font("Times New Roman", Font.BOLD, 15));
		modif.setIcon(new ImageIcon(Stagiaire.class.getResource("/image/modifstag.png")));
		modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TraitementStagiaire().modifStagiaire(IDstagiaire, nom, prenom, datenaissance, sexe, adresse, ville, cp, pays, mail,mdp);
				new Tableau().TableStagiaire().fireTableDataChanged();
                tableStagiaire.setModel(new Tableau().TableStagiaire());
                tailleTable();
                IDstagiaire =0;
			}
		});
		panSud.add(modif);
		
		supp = new JButton("  Supprimer un Stagiaire");
		supp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		supp.setIcon(new ImageIcon(Stagiaire.class.getResource("/image/suppstag.png")));
		supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TraitementStagiaire().suppStagiaire(IDstagiaire, nom, prenom);
				new Tableau().TableStagiaire().fireTableDataChanged();
                tableStagiaire.setModel(new Tableau().TableStagiaire());
				tailleTable();
				IDstagiaire=0;
			}
		});
		panSud.add(supp);

	}
	
	public void tailleTable() {
		tableStagiaire.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableStagiaire.setRowHeight(30);
		tableStagiaire.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableStagiaire.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableStagiaire.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableStagiaire.getColumnModel().getColumn(3).setPreferredWidth(115);
		tableStagiaire.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableStagiaire.getColumnModel().getColumn(5).setPreferredWidth(250);
		tableStagiaire.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableStagiaire.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableStagiaire.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableStagiaire.getColumnModel().getColumn(9).setPreferredWidth(240);
		tableStagiaire.getColumnModel().getColumn(10).setPreferredWidth(250);
	}

}
