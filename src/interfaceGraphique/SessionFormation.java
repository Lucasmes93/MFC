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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import traitementDB.Tableau;
import traitementDB.TraitementFormation;

public class SessionFormation extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan, panNord, panCentre, panSud, panCentreD, panCentreDcentre, panCentreDsud;
	JTable tableSession, tableStagiaire;
	JLabel formation, formateur, salle, niveau;
	JButton retour, ajoutSession, modifSession, suppSession, ajoutStagiaire, suppStagiaire;
	JScrollPane SPsession, SPstagiaire;
	int IDsession = 0, IDstagiaire = 0;
	String datedebutS, datefinS;
	
	public SessionFormation(final int IDformationS, final String formationS, final String formateurS, final String salleS,final int niveauS) {
		setBounds(100, 100, 1050, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SessionFormation.class.getResource("/image/logo MFC.png")));
		setTitle("Liste des sessions de la formation "+formationS);
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(new BorderLayout(0, 5));
		
		// Mon Panel Nord
		panNord = new JPanel();
		pan.add(panNord, BorderLayout.NORTH);
		panNord.setLayout(new GridLayout(0, 2, 8, 8));
		
		JLabel vide = new JLabel("");
		panNord.add(vide);
		
		JLabel vide1 = new JLabel("");
		panNord.add(vide1);
		
		formation = new JLabel("          Formation : "+formationS);
		formation.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panNord.add(formation);
		
		salle = new JLabel("                                         Salle : "+salleS);
		salle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panNord.add(salle);
		
		niveau = new JLabel("          Niveau : "+niveauS);
		niveau.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panNord.add(niveau);
		
		formateur = new JLabel("                                         Formateur : "+formateurS);
		formateur.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panNord.add(formateur);
		
		// Mon Panel Centre
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new GridLayout(0, 2, 15, 0));
		
		// Mon Panel Centre Gauche		
		tableSession = new JTable(new Tableau().TableFormationSession(IDformationS));
		tailleTableSession();
		tableSession.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint(); 
                int row = tableSession.rowAtPoint(p);  
                	String ID = tableSession.getValueAt(row,0).toString();
                	IDsession = Integer.parseInt(ID);
                	datedebutS = tableSession.getValueAt(row,1).toString();
                	datefinS = tableSession.getValueAt(row,2).toString();
	                System.out.println(IDsession);
	                new Tableau().TableFormationSessionStagiaire(IDsession).fireTableDataChanged();
	                tableStagiaire.setModel(new Tableau().TableFormationSessionStagiaire(IDsession));
	        		tailleTableStagiaire();
            }	           
        });
		SPsession= new JScrollPane(tableSession);
		panCentre.add(SPsession);
		
		// Mon Panel Centre Droit
		panCentreD = new JPanel();
		panCentre.add(panCentreD);
		panCentreD.setLayout(new BorderLayout(0,0));
		
		tableStagiaire = new JTable(new Tableau().TableFormationSessionStagiaire(IDsession));
		tailleTableStagiaire();
		tableStagiaire.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint(); 
                int row = tableStagiaire.rowAtPoint(p);  
                	String ID = tableStagiaire.getValueAt(row,0).toString();
                	IDstagiaire = Integer.parseInt(ID);
            }	           
        });
		SPstagiaire = new JScrollPane(tableStagiaire);
		panCentreD.add(SPstagiaire,BorderLayout.CENTER);
		
		panCentreDsud = new JPanel();
		panCentreD.add(panCentreDsud, BorderLayout.SOUTH);
		panCentreDsud.setLayout(new GridLayout(0, 2, 0, 0));
		
		ajoutStagiaire = new JButton ("Ajouter un Stagiaire");
		ajoutStagiaire.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ajoutStagiaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(IDsession !=0) {
					AjoutStagiaireSession session = new AjoutStagiaireSession(IDsession, IDformationS, formationS, formateurS, salleS, niveauS);
					session.setVisible(true);
					dispose();
				} else {
		    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné une session !"); 
				}
			}
		});
		panCentreDsud.add(ajoutStagiaire);
		
		suppStagiaire = new JButton ("Supprimer un Stagiaire");
		suppStagiaire.setFont(new Font("Times New Roman", Font.BOLD, 12));
		suppStagiaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					new TraitementFormation().suppStagiaireSession(IDsession, IDstagiaire);
					new Tableau().TableFormationSessionStagiaire(IDsession).fireTableDataChanged();
		            tableStagiaire.setModel(new Tableau().TableFormationSessionStagiaire(IDsession));
		    		tailleTableStagiaire();
			}
		});
		panCentreDsud.add(suppStagiaire);
		
		// Mon Panel Sud
		panSud = new JPanel();
		pan.add(panSud, BorderLayout.SOUTH);
		panSud.setLayout(new GridLayout(0, 4, 0, 0));
		
		retour = new JButton ("  Retour");
		retour.setIcon(new ImageIcon(SessionFormation.class.getResource("/image/retour.png")));
		retour.setFont(new Font("Times New Roman", Font.BOLD, 15));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Formation session = new Formation();
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(retour);
		
		ajoutSession = new JButton ("Ajouter une Session");
		ajoutSession.setIcon(new ImageIcon(SessionFormation.class.getResource("/image/ajout.png")));
		ajoutSession.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ajoutSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AjoutSession session = new AjoutSession(IDformationS, formationS, formateurS, salleS, niveauS);
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(ajoutSession);
		
		modifSession = new JButton ("Modifier une Session");
		modifSession.setIcon(new ImageIcon(SessionFormation.class.getResource("/image/modif.png")));
		modifSession.setFont(new Font("Times New Roman", Font.BOLD, 15));
		modifSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TraitementFormation().modifSessionFormation(IDsession,datedebutS, datefinS, IDformationS);
				new Tableau().TableFormation().fireTableDataChanged();
                tableSession.setModel(new Tableau().TableFormationSession(IDformationS));
                tailleTableSession();
                IDsession = 0;
			}
		});
		panSud.add(modifSession);
		
		suppSession = new JButton ("Supprimer une Session");
		suppSession.setIcon(new ImageIcon(SessionFormation.class.getResource("/image/supp.png")));
		suppSession.setFont(new Font("Times New Roman", Font.BOLD, 15));
		suppSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TraitementFormation().suppSessionFormation(IDsession);
				new Tableau().TableFormation().fireTableDataChanged();
                tableSession.setModel(new Tableau().TableFormationSession(IDformationS));
                tailleTableSession();
                IDsession =0;
			}
		});
		panSud.add(suppSession);

	}

	public void tailleTableSession() {
		tableSession.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableSession.setRowHeight(30);
		tableSession.getColumnModel().getColumn(0).setPreferredWidth(39);
		tableSession.getColumnModel().getColumn(1).setPreferredWidth(220);
		tableSession.getColumnModel().getColumn(2).setPreferredWidth(220);
	}
	
	public void tailleTableStagiaire() {
		tableStagiaire.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableStagiaire.setRowHeight(30);
		tableStagiaire.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableStagiaire.getColumnModel().getColumn(1).setPreferredWidth(153);
		tableStagiaire.getColumnModel().getColumn(2).setPreferredWidth(153);
		tableStagiaire.getColumnModel().getColumn(3).setPreferredWidth(153);
	}
}
