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
import traitementDB.TraitementFormateur;

public class Formateur extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan, panNord, panCentre, panSud, panNordEst;
	JLabel rechercherL;
	JTable tableFormateur;
	JScrollPane SPformateur;
	JButton ajout, modif, supp, retour, recherche;
	int IDformateur;
	String nom,prenom,datenaissance,sexe,adresse,ville,cp,pays,mail,specialite,rechercher;
	JTextField TFrecherche;
	
	
	public Formateur() {
		setBounds(100, 100, 1050, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Formateur.class.getResource("/image/logo MFC.png")));
		setTitle("Liste des Formateurs");
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
		TFrecherche.setColumns(10);
		
		recherche = new JButton();
		recherche.setIcon(new ImageIcon(Formateur.class.getResource("/image/recherche.png")));
		recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechercher= TFrecherche.getText();
                tableFormateur.setModel(new Tableau().TableFormateurRecherche(rechercher));
                tailleTable();		
                IDformateur = 0;
            }
		});
		panNordEst.add(recherche);
		
		// Mon Panel Centre
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new GridLayout(0,1,0,0));
		
		tableFormateur = new JTable(new Tableau().TableFormateur());
		tailleTable();
		tableFormateur.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint(); 
                int row = tableFormateur.rowAtPoint(p);  
                	String ID = tableFormateur.getValueAt(row,0).toString();
                	IDformateur = Integer.parseInt(ID);
                	nom = tableFormateur.getValueAt(row,1).toString();
                	prenom = tableFormateur.getValueAt(row,2).toString();
                	datenaissance = tableFormateur.getValueAt(row,3).toString();
                	sexe = tableFormateur.getValueAt(row,4).toString();
                	adresse = tableFormateur.getValueAt(row,5).toString();
                	ville = tableFormateur.getValueAt(row,6).toString();
                	cp = tableFormateur.getValueAt(row,7).toString();
                	pays = tableFormateur.getValueAt(row,8).toString();
                	mail = tableFormateur.getValueAt(row,9).toString();
            }	           
        });
		SPformateur = new JScrollPane(tableFormateur);
		panCentre.add(SPformateur);
			
		// Mon Panel Sud
		panSud = new JPanel();
		pan.add(panSud, BorderLayout.SOUTH);
		panSud.setLayout(new GridLayout(0,4,0,0));
		
		retour = new JButton("  Retour");
		retour.setIcon(new ImageIcon(Formateur.class.getResource("/image/retour.png")));
		retour.setFont(new Font("Times New Roman", Font.BOLD, 15));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panSud.add(retour);
		
		ajout = new JButton("Ajouter un Formateur");
		ajout.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ajout.setIcon(new ImageIcon(Formateur.class.getResource("/image/ajoutform.png")));
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutFormateur session = new AjoutFormateur();
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(ajout);
		
		modif = new JButton("Modifier un Formateur");
		modif.setFont(new Font("Times New Roman", Font.BOLD, 15));
		modif.setIcon(new ImageIcon(Formateur.class.getResource("/image/modifform.png")));
		modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TraitementFormateur().modifFormateur(IDformateur, nom, prenom, datenaissance, sexe, adresse, ville, cp, pays, mail);
				new Tableau().TableFormateur().fireTableDataChanged();
                tableFormateur.setModel(new Tableau().TableFormateur());
                tailleTable();
                IDformateur = 0;
			}
		});
		panSud.add(modif);
		
		supp = new JButton("Supprimer un Formateur");
		supp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		supp.setIcon(new ImageIcon(Formateur.class.getResource("/image/suppform.png")));
		supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TraitementFormateur().suppFormateur(IDformateur,nom,prenom);
				new Tableau().TableFormateur().fireTableDataChanged();
                tableFormateur.setModel(new Tableau().TableFormateur());
                tailleTable();
                IDformateur = 0;
			}
		});
		panSud.add(supp);

	}
	
	public void tailleTable() {
		tableFormateur.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableFormateur.setRowHeight(30);
		tableFormateur.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableFormateur.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableFormateur.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableFormateur.getColumnModel().getColumn(3).setPreferredWidth(115);
		tableFormateur.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableFormateur.getColumnModel().getColumn(5).setPreferredWidth(250);
		tableFormateur.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableFormateur.getColumnModel().getColumn(7).setPreferredWidth(40);
		tableFormateur.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableFormateur.getColumnModel().getColumn(9).setPreferredWidth(240);
	}

}
