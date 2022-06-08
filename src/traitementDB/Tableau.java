package traitementDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Tableau extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
// Partie Formation
	
		// Tableau Formation 
		public DefaultTableModel TableFormation(){
			
			DefaultTableModel dt = new DefaultTableModel();
			ResultSet rset;
			try{
				dt.addColumn("ID");
				dt.addColumn("Formation");
				dt.addColumn("Niv");
				dt.addColumn("Salle");
				dt.addColumn("Formateur");
				dt.addColumn("Prix");
		    	Statement st = new Connect_DB().connexion.createStatement();
				String query="SELECT F.ID, F.intituleFormation, F.niveauFormation, S.intituleSalle, " +
							 "P.nom, P.prenom, F.prixFormation FROM formation as F, salle as S, formateur as FR, personne AS P WHERE F.ID_salle=S.ID AND "+
							 "F.ID_formateur=FR.ID AND P.ID=FR.ID_personne";
				rset=st.executeQuery(query);

				while(rset.next()) {
					Object []tableau={rset.getString("ID"),rset.getString("intituleFormation"),rset.getString("niveauFormation"),rset.getString("intituleSalle"),
					rset.getString("nom")+" "+rset.getString("prenom") , rset.getString("prixFormation")+" €"};
					dt.addRow(tableau);
				}
				
		    } catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
			}	
			return dt;
		}
	
	// Tableau Session par Formation 
		public DefaultTableModel TableFormationSession(int ID) {
				
			DefaultTableModel dt = new DefaultTableModel();
			ResultSet rset;
			try{
				dt.addColumn("ID");
				dt.addColumn("date de début");
				dt.addColumn("date de fin");
		    	Statement st = new Connect_DB().connexion.createStatement();
				String query="SELECT S.ID, S.dateDebut, S.dateFin "+
	        			     "FROM session AS S, formation AS F WHERE F.ID=S.ID_formation AND F.ID="+ID;;
				rset=st.executeQuery(query);

				while(rset.next()){
					Object []tableau={rset.getString("ID"),rset.getString("datedebut"),rset.getString("datefin")};
					dt.addRow(tableau);
				}	
			}
			catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
			}	
			return dt;
		}
		// Tableau des stagiaire par rapport a la session au niveau des formations
		public DefaultTableModel TableFormationSessionStagiaire(int ID) {
			
		DefaultTableModel dt = new DefaultTableModel();
		ResultSet rset;
		try{
			dt.addColumn("ID");
			dt.addColumn("Nom");
			dt.addColumn("Prénom");
			dt.addColumn("Date de Naissance");
	    	Statement st = new Connect_DB().connexion.createStatement();
			String query="SELECT S.ID, P.nom, P.prenom, P.datenaissance FROM personne as P, stagiaire as S, suivre as SU, " +
				  		     "session as SE WHERE P.ID=S.ID_personne AND S.ID=SU.ID_stagiaire AND SU.ID_session=SE.ID AND SE.ID="+ID;
			rset=st.executeQuery(query);

			while(rset.next()){
				Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance")};
				dt.addRow(tableau);
			}	
		}
		catch(SQLException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
		}	
		return dt;
		}

// Partie Session
		
		// Tableau Session 
				public DefaultTableModel TableSession(){
					
					DefaultTableModel dt = new DefaultTableModel();
					ResultSet rset;
					try{
						dt.addColumn("ID");
						dt.addColumn("Formation");
						dt.addColumn("date de début");
						dt.addColumn("date de fin");
						dt.addColumn("information");
				    	Statement st = new Connect_DB().connexion.createStatement();
						String query="SELECT S.ID, F.intituleFormation, S.dateDebut, S.dateFin "+
			        			     "FROM session AS S, formation AS F WHERE F.ID=S.ID_formation";
						rset=st.executeQuery(query);

						while(rset.next()){
							Object []tableau={rset.getString("ID"),rset.getString("intituleFormation"),rset.getString("datedebut"),rset.getString("datefin")};
							dt.addRow(tableau);
						}	
					}
					catch(SQLException ex){
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
					}	
					return dt;
				}
				
// Partie Stagiaire
				
		// Tableau Stagiaire 
		public DefaultTableModel TableStagiaire(){
					
		DefaultTableModel dt = new DefaultTableModel();
		ResultSet rset;
			try{
				dt.addColumn("ID");
				dt.addColumn("Nom");
				dt.addColumn("Prénom");
				dt.addColumn("Date de naissance");
				dt.addColumn("Sexe");
				dt.addColumn("adresse");
				dt.addColumn("ville");
				dt.addColumn("cp");
				dt.addColumn("pays");
				dt.addColumn("mail");
				dt.addColumn("mdp");
			   	Statement st = new Connect_DB().connexion.createStatement();
				String query="SELECT S.ID, P.nom, P.prenom, P.datenaissance, P.sexe, P.adresse, P.ville, P.CP, P.pays, P.mail FROM personne as P, stagiaire as S "+
							  "WHERE P.ID=S.ID_personne";
				rset=st.executeQuery(query);

				while(rset.next()){
					Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance"),
									  rset.getString("sexe"),rset.getString("adresse"),rset.getString("ville"),rset.getString("cp")
									  ,rset.getString("pays"),rset.getString("mail"),rset.getString("mdp")};
					dt.addRow(tableau);
				}	
			}
			catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
			}	
			return dt;
		}				
			
		// Tableau Stagiaire recherche
		public DefaultTableModel TableStagiaireRecherche(String recherche){
			
			DefaultTableModel dt = new DefaultTableModel();
			ResultSet rset=null;
			try{
				dt.addColumn("ID");
				dt.addColumn("Nom");
				dt.addColumn("Prénom");
				dt.addColumn("Date de naissance");
				dt.addColumn("Sexe");
				dt.addColumn("adresse");
				dt.addColumn("ville");
				dt.addColumn("cp");
				dt.addColumn("pays");
				dt.addColumn("mail");
				dt.addColumn("mdp");
			   	Statement st = new Connect_DB().connexion.createStatement();
				String[] tab=recherche.split(" ");
				
				for(int i=0,longueur=tab.length;i<longueur;i++){
					String query="SELECT S.ID, P.nom, P.prenom, P.datenaissance, P.sexe, P.adresse, P.ville, P.CP, P.pays, P.mail FROM personne as P, stagiaire as S "+
							     "WHERE P.ID=S.ID_personne AND P.nom LIKE '"+tab[i]+"%'";
					rset=st.executeQuery(query);
				}
				
				while(rset.next()){
					Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance"),
									  rset.getString("sexe"),rset.getString("adresse"),rset.getString("ville"),rset.getString("cp"),
									  rset.getString("pays"),rset.getString("mail"),rset.getString("mdp")};
					dt.addRow(tableau);			
				}
				
			} catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);	
			}
			return dt;
		}
			
		
		// Tableau ajout Stagiaire dans Session
		public DefaultTableModel TableStagiaireSession(){
					
		DefaultTableModel dt = new DefaultTableModel();
		ResultSet rset;
			try{
				dt.addColumn("ID");
				dt.addColumn("Nom");
				dt.addColumn("Prénom");
				dt.addColumn("Date de naissance");
			   	Statement st = new Connect_DB().connexion.createStatement();
				String query="SELECT S.ID, P.nom, P.prenom, P.datenaissance FROM personne as P, stagiaire as S "+
							  "WHERE P.ID=S.ID_personne";
				rset=st.executeQuery(query);

				while(rset.next()){
					Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance")};
					dt.addRow(tableau);
				}	
			}
			catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
			}	
			return dt;
		}	
		
		// Tableau ajout Stagiaire dans session bouton recherche
				public DefaultTableModel TableStagiaireSessionRecherche(String recherche){
					
					DefaultTableModel dt = new DefaultTableModel();
					ResultSet rset=null;
					try{
						dt.addColumn("ID");
						dt.addColumn("Nom");
						dt.addColumn("Prénom");
						dt.addColumn("Date de naissance");
					   	Statement st = new Connect_DB().connexion.createStatement();
						String[] tab=recherche.split(" ");
						
						for(int i=0,longueur=tab.length;i<longueur;i++){
							String query="SELECT S.ID, P.nom, P.prenom, P.datenaissance FROM personne as P, stagiaire as S "+
									     "WHERE P.ID=S.ID_personne AND P.nom LIKE '"+tab[i]+"%'";
							rset=st.executeQuery(query);
						}
						
						while(rset.next()){
							Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance")};
							dt.addRow(tableau);			
						}
						
					} catch(SQLException ex){
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);	
					}
					return dt;
				}
				
// Partie Formateur
				
		// Tableau Formateur 
		public DefaultTableModel TableFormateur(){
					
		DefaultTableModel dt = new DefaultTableModel();
		ResultSet rset;
			try{
				dt.addColumn("ID");
				dt.addColumn("Nom");
				dt.addColumn("Prénom");
				dt.addColumn("Date de naissance");
				dt.addColumn("Sexe");
				dt.addColumn("adresse");
				dt.addColumn("ville");
				dt.addColumn("cp");
				dt.addColumn("pays");
				dt.addColumn("mail");
				dt.addColumn("mdp");
			   	Statement st = new Connect_DB().connexion.createStatement();
				String query="SELECT F.ID, P.nom, P.prenom, P.datenaissance, P.sexe, P.adresse, P.ville, P.CP, P.pays, P.mail FROM personne as P, Formateur as F "+
							  "WHERE P.ID=F.ID_personne";
				rset=st.executeQuery(query);

				while(rset.next()){
					Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance"),
									  rset.getString("sexe"),rset.getString("adresse"),rset.getString("ville"),rset.getString("cp")
									  ,rset.getString("pays"),rset.getString("mail"),rset.getString("mdp")};
					dt.addRow(tableau);
				}	
			}
			catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);			
			}	
			return dt;
		}
		
		// Tableau Formateur recherche
		public DefaultTableModel TableFormateurRecherche(String recherche){
			
			DefaultTableModel dt = new DefaultTableModel();
			ResultSet rset=null;
			try{
				dt.addColumn("ID");
				dt.addColumn("Nom");
				dt.addColumn("Prénom");
				dt.addColumn("Date de naissance");
				dt.addColumn("Sexe");
				dt.addColumn("adresse");
				dt.addColumn("ville");
				dt.addColumn("cp");
				dt.addColumn("pays");
				dt.addColumn("mail");
				
			   	Statement st = new Connect_DB().connexion.createStatement();
				String[] tab=recherche.split(" ");
				
				for(int i=0,longueur=tab.length;i<longueur;i++){
					String query="SELECT F.ID, P.nom, P.prenom, P.datenaissance, P.sexe, P.adresse, P.ville, P.CP, P.pays, P.mail FROM personne as P, formateur as F "+
							     "WHERE P.ID=F.ID_personne AND P.nom LIKE '"+tab[i]+"%'";
					rset=st.executeQuery(query);
				}
				
				while(rset.next()){
					Object []tableau={rset.getString("ID"),rset.getString("nom"),rset.getString("prenom"),rset.getString("datenaissance"),
									  rset.getString("sexe"),rset.getString("adresse"),rset.getString("ville"),rset.getString("cp"),
									  rset.getString("pays"),rset.getString("mail"),rset.getString("mdp")};
					dt.addRow(tableau);			
				}
				
			} catch(SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);	
			}
			return dt;
		}
		@Override
		public int getColumnCount() {
			return 0;
		}

		@Override
		public int getRowCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			return null;
		}
}