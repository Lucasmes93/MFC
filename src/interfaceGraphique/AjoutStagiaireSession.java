package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import traitementDB.Tableau;
import traitementDB.TraitementFormation;

public class AjoutStagiaireSession extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel pan,panNord,panSud,panCentre,panNordEst;
	JTable tableStagiaireS;
	JScrollPane sc;
	JLabel rechercher;
	JButton rechercherB, retourB, ajoutB;
	JTextField TFrechercher;
	int IDstagiaire;
	String rechercherS;
	
	public AjoutStagiaireSession (final int IDsession, final int IDformation, final String formationS, final String formateurS, final String salleS, final int niveauS) {
		setBounds(100, 100, 450, 500);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AjoutStagiaireSession.class.getResource("/image/logo MFC.png")));
		setTitle("Ajouter un stagiaire dans la session N°"+IDsession);
		pan = new JPanel();
		setContentPane(pan);
		pan.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		// Mon Panel Nord
		panNord = new JPanel();
		pan.add(panNord, BorderLayout.NORTH);
		panNord.setLayout(new BorderLayout(0,0));
		
		panNordEst = new JPanel();
		panNord.add(panNordEst, BorderLayout.EAST);
		
		rechercher = new JLabel("Rechercher par nom : ");
		panNordEst.add(rechercher);
		
		TFrechercher = new JTextField();
		panNordEst.add(TFrechercher);
		TFrechercher.setColumns(10);
		
		rechercherB = new JButton();
		rechercherB.setIcon(new ImageIcon(AjoutStagiaireSession.class.getResource("/image/recherche.png")));
		rechercherB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rechercherS=TFrechercher.getText();
                tableStagiaireS.setModel(new Tableau().TableStagiaireSessionRecherche(rechercherS));
                tailleTable();
			}
		});
		panNordEst.add(rechercherB);
		
		// Mon panel Centre
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new BorderLayout(0,0));
		
		tableStagiaireS = new JTable(new Tableau().TableStagiaireSession());
		tailleTable();
		tableStagiaireS.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint(); 
                int row = tableStagiaireS.rowAtPoint(p);  
                	String ID = tableStagiaireS.getValueAt(row,0).toString();
                	IDstagiaire = Integer.parseInt(ID);
            }	           
        });
		sc = new JScrollPane(tableStagiaireS);
		panCentre.add(sc,BorderLayout.CENTER);
		
		// Mon Panel Sud
		panSud = new JPanel();
		pan.add(panSud, BorderLayout.SOUTH);
		panSud.setLayout(new GridLayout(0,2,0,0));
		
		retourB = new JButton("  Retour");
		retourB.setIcon(new ImageIcon(AjoutStagiaireSession.class.getResource("/image/retour.png")));
		retourB.setFont(new Font("Times New Roman", Font.BOLD, 15));
		retourB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SessionFormation session = new SessionFormation(IDformation, formationS, formateurS, salleS, niveauS);
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(retourB);
		
		ajoutB = new JButton("  Ajouter");
		ajoutB.setIcon(new ImageIcon(AjoutStagiaireSession.class.getResource("/image/ajoutstag.png")));
		ajoutB.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ajoutB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TraitementFormation().ajoutStagiaireSession(IDsession, IDstagiaire);
	    		int i = JOptionPane.showConfirmDialog(null,"Voulez-vous ajoutez d'autres stagiaires dans cette session ?","", JOptionPane.YES_NO_OPTION);
	    			if(i==1) {
	    				SessionFormation session = new SessionFormation(IDformation, formationS, formateurS, salleS, niveauS);
	    				session.setVisible(true);
	    				dispose();
	    			}
			}
		});
		panSud.add(ajoutB);
		
	}
	
	public void tailleTable() {
		tableStagiaireS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableStagiaireS.setRowHeight(30);
		tableStagiaireS.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableStagiaireS.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableStagiaireS.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableStagiaireS.getColumnModel().getColumn(3).setPreferredWidth(160);
	}
}
