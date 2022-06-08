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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import traitementDB.Tableau;
import traitementDB.TraitementFormation;

public class Formation extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel pan,panCentre,panSud;
	JTable table;
	JScrollPane SCtable;
	JButton retour, ajout, supp, modif, session;
	String formation, formateur, salle, prix;
	String [] tabFormateur = {"0"}, tabPrix = {"0"};
	int IDformation, niv;
	
	public Formation() {
		setBounds(100, 100, 1050, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Formation.class.getResource("/image/logo MFC.png")));
		setTitle("Liste des Formations");
		pan = new JPanel();
		pan.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pan);
		pan.setLayout(new BorderLayout(0, 0));

		// Objet présent dans mon panel Centre
		panCentre = new JPanel();
		pan.add(panCentre, BorderLayout.CENTER);
		panCentre.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(new Tableau().TableFormation());
		tailleTable();
		table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint(); 
                int row = table.rowAtPoint(p);  
                	String ID = table.getValueAt(row,0).toString();
                	IDformation = Integer.parseInt(ID);
                	String nive = table.getValueAt(row,2).toString();
                	niv = Integer.parseInt(nive);
                	formation = table.getValueAt(row,1).toString();
                	salle = table.getValueAt(row,3).toString();
                	formateur = table.getValueAt(row,4).toString();
                	tabFormateur = formateur.split(" ");
                	prix = table.getValueAt(row,5).toString();
                	tabPrix = prix.split(" ");
                	
	                System.out.println(IDformation);
            }	           
        });
		SCtable = new JScrollPane(table);		
		panCentre.add(SCtable, BorderLayout.CENTER);
		
		// Objet Présent dans mon panel sud 
		panSud = new JPanel();
		pan.add(panSud, BorderLayout.SOUTH);
		panSud.setLayout(new GridLayout(0, 5, 0, 0));
		
		retour = new JButton("  Retour");
		retour.setIcon(new ImageIcon(Formation.class.getResource("/image/retour.png")));
		retour.setFont(new Font("Times New Roman", Font.BOLD, 15));
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panSud.add(retour);
		
		ajout = new JButton("  Ajouter une Formation");
		ajout.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ajout.setIcon(new ImageIcon(Formation.class.getResource("/image/ajout.png")));
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutFormation session = new AjoutFormation();
				session.setVisible(true);
				dispose();
			}
		});
		panSud.add(ajout);
		
		modif = new JButton("  Modifier une Formation");
		modif.setFont(new Font("Times New Roman", Font.BOLD, 15));
		modif.setIcon(new ImageIcon(Formation.class.getResource("/image/modif.png")));
		modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	System.out.println("lolo "+e);
				TraitementFormation a = new TraitementFormation();
		    	System.out.println("lolo1 "+e);
		    	System.out.println("loloID "+IDformation);
		    	System.out.println("lolofor "+formation);
		    	System.out.println("loloniv "+niv);
		    	System.out.println("lolotabfor "+tabFormateur);
		    	System.out.println("lolosal "+salle);
		    	System.out.println("lolosal "+tabPrix[0]);
				a.modifFormation(IDformation, formation, niv, tabFormateur[0], salle, tabPrix[0]);
				new Tableau().TableFormation().fireTableDataChanged();
                table.setModel(new Tableau().TableFormation());
                tailleTable();
                IDformation = 0;
			}
		});
		panSud.add(modif);
		
		supp = new JButton("  Supprimer une Formation");
		supp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		supp.setIcon(new ImageIcon(Formation.class.getResource("/image/supp.png")));
		supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TraitementFormation().supFormation(IDformation, formation);
				new Tableau().TableFormation().fireTableDataChanged();
                table.setModel(new Tableau().TableFormation());
				tailleTable();
				IDformation=0;
			}
		});
		panSud.add(supp);
		
		session = new JButton("Voir les sessions");
		session.setFont(new Font("Times New Roman", Font.BOLD, 15));
		session.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				if (IDformation != 0) {
					SessionFormation frame = new SessionFormation(IDformation,formation,formateur,salle,niv);
					frame.setVisible(true);	
					dispose();
				} else {
		    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné une formation."); 
				}
			}
		});
		panSud.add(session);

	}
	
	public void tailleTable() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS );
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setPreferredWidth(175);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(51);
	}
}
