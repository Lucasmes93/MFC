package traitementDB;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import traitementDB.Connect_DB;

public class TraitementFormation {
	
		
// Ajouter une formation
    public void ajoutFormation(String formation, int nivFormation, String formateur, String nomSalle, String prix) {
	    try 
	    {
	    	Statement st = new Connect_DB().connexion.createStatement();
	    	st.executeUpdate("INSERT INTO formation (intituleFormation, niveauFormation, ID_salle, ID_formateur, prixFormation) VALUES(" +
	    					 "'"+formation+"', '"+nivFormation+"', (SELECT ID FROM salle WHERE intituleSalle='"+nomSalle+"'), " +
	    					 "(SELECT F.ID FROM formateur AS F, personne AS P WHERE P.ID=F.ID_personne AND P.nom='"+formateur+"'), '"+prix+"')");
	    	JOptionPane.showMessageDialog(null,"Formation rajouté !");    	
	    }
	    catch (Exception e)
	    {
	    	JOptionPane.showMessageDialog(null,"Erreur ! Note : les Apostrophes sont interdits."); 
	    	e.printStackTrace();
	    }
	  }	 

 // modif une formation
    public void modifFormation(int IDformation, String formation, int nivFormation, String formateur, String nomSalle, String prix) {
	    try 
	    {
	    	System.out.println("lol "+IDformation);
	    	if (IDformation != 0) {
	    		int i = JOptionPane.showConfirmDialog(null,"Avant de valider pensez à bien re-sélectionner la ligne modifiée, si cela est fait, confirmez vous la modification de la formation : "+formation+" ? ","", JOptionPane.YES_NO_OPTION);
	    		if (i==0) {
	    			Statement st = new Connect_DB().connexion.createStatement();
	    			st.executeUpdate("UPDATE formation SET intituleFormation='"+formation+"', niveauformation="+nivFormation+", ID_formateur="+
	    							 "(SELECT F.ID FROM formateur as F, personne as P WHERE P.ID=F.ID_personne AND P.nom='"+formateur+"'), ID_salle="+
	    							 "(SELECT ID FROM salle WHERE intituleSalle='"+nomSalle+"'), prixFormation='"+prix+"' WHERE ID="+IDformation); 
	    			JOptionPane.showMessageDialog(null,"Modification effectué !"); 
	    		}
	    	} else {
	    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné la formation à modifier !"); 
	   		}  
	    }
	    catch (Exception e)
	    {
	    	JOptionPane.showMessageDialog(null,"Erreur ! Note : les Apostrophes sont interdits."); 
	    	e.printStackTrace();
	    }
	  }	 
    
// supprimer une formation
    public void supFormation(int ID, String formation) {
	    try 
	    {
	    	if (ID != 0) {
	    		int i = JOptionPane.showConfirmDialog(null,"Confirmez vous la suppression de la formation : "+formation+" ? ","", JOptionPane.YES_NO_OPTION);
	    		if (i==0) {
	    			Statement st = new Connect_DB().connexion.createStatement();
	    			st.executeUpdate("DELETE FROM formation WHERE ID="+ID); 
	    		}
	    	} else {
	    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné une formation à supprimer !"); 
	   		}
	    }
	    catch (Exception e)
	    {
	    	JOptionPane.showMessageDialog(null,"Erreur !"); 
	    	e.printStackTrace();
	    }
	  }	 

 // ComboBox des formateurs pour ajout d'une formation
     @SuppressWarnings({ "unchecked", "rawtypes" })
	public void CBformateurF( JComboBox formateur) {
 		 try 
 		    {
 		    	Statement st = new Connect_DB().connexion.createStatement();
 		    	ResultSet sr = st.executeQuery("SELECT P.* FROM personne as P, formateur as F WHERE P.ID=F.ID_personne");
 		    	while (sr.next()) {
 	    			String resultIformateur = sr.getString("P.nom");
 	    			String resultNformateur = sr.getString("P.prenom");
 	    			formateur.addItem(resultIformateur+" "+resultNformateur);
 		    	}
 		    }
 		    catch (Exception e)
 		    {
 		    	JOptionPane.showMessageDialog(null,"Insertion Session Incorrect !"); 
 		    	e.printStackTrace();
 		    }
 	 }
     
 // ComboBox des salles pour ajout d'une formation
     @SuppressWarnings({ "rawtypes", "unchecked" })
	public void CBsalleF(JComboBox salle) {
 		 try 
 		    {
 		    	Statement st = new Connect_DB().connexion.createStatement();
 		    	ResultSet sr = st.executeQuery("SELECT * FROM salle");
 		    	while (sr.next()) {
 	    			String resultSalle = sr.getString("intitulesalle");
 	    			salle.addItem(resultSalle);
 		    	}
 		    }
 		    catch (Exception e)
 		    {
 		    	JOptionPane.showMessageDialog(null,"Erreur !"); 
 		    	e.printStackTrace();
 		    }
 	 }   
     
// Ajout de session pour cette formation
	 public void ajoutSessionFormation(int formation, String datedebut, String datefin) {
		    try 
		    {
		    	Statement st = new Connect_DB().connexion.createStatement();
		    	st.executeUpdate("INSERT INTO session (dateDebut, dateFin, ID_formation) VALUES('"+datedebut+"', '"+datefin+"', "+formation+")");    	
			   	JOptionPane.showMessageDialog(null,"Session Ajoutée !"); 
		    }
		    catch (Exception e)
		    {
			   	JOptionPane.showMessageDialog(null,"Erreur ! Merci de respecter le format des dates (AAAA/MM/JJ)."); 
		    	e.printStackTrace();
		    }
	}
	 
// Modif de session pour cette formation
	 public void modifSessionFormation(int session, String datedebut, String datefin, int formation) {
		    try 
		    {
		    	if (session != 0) {
		    		int i = JOptionPane.showConfirmDialog(null,"Avant de valider pensez à bien re-sélectionner la ligne modifiée, si cela est fait, confirmez vous la modification de la session N° "+session+" ? ","", JOptionPane.YES_NO_OPTION);
		    		if (i==0) {
		    			Statement st = new Connect_DB().connexion.createStatement();
		    			st.executeUpdate("UPDATE session SET dateDebut='"+datedebut+"', dateFin='"+datefin+"', ID_formation="+formation+" WHERE ID="+session);    	
		    		}
		    	} else {
		    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné la session à modifier !"); 
		   		}  
		    	
		    }
		    catch (Exception e)
		    {
			   	JOptionPane.showMessageDialog(null,"Erreur ! Merci de respecter le format des dates (AAAA/MM/JJ)."); 
		    	e.printStackTrace();
		    }
	}

	// supprimer de session pour cette formation
		 public void suppSessionFormation(int session) {
			    try 
			    {
			    	if (session != 0) {
			    		int i = JOptionPane.showConfirmDialog(null,"Confirmez vous la suppression de la session N° "+session+" ? ","", JOptionPane.YES_NO_OPTION);
			    		if (i==0) {
			    			Statement st = new Connect_DB().connexion.createStatement();
			    			st.executeUpdate("DELETE FROM session WHERE ID="+session); 
			    		}
			    	} else {
			    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné une session à supprimer !"); 
			   		}   	
			    }
			    catch (Exception e)
			    {
				   	JOptionPane.showMessageDialog(null,"Erreur, Veuillez supprimer les stagiaires inscrit à cette session !"); 
			    	e.printStackTrace();
			    }
		}
		 
	// Ajouter un stagiaire dans session par rapport à une formation
		 public void ajoutStagiaireSession(int session, int stagiaire) {
			    try 
			    {
			    	if (stagiaire != 0) {
			    			Statement st = new Connect_DB().connexion.createStatement();
			    			st.executeUpdate("INSERT INTO suivre(ID_stagiaire, ID_session) VALUES("+stagiaire+", "+session+")");
				    		JOptionPane.showMessageDialog(null,"Stagiaire Ajouté !"); 
			    	} else {
			    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné un stagiaire !"); 
			   		}   	
			    }
			    catch (Exception e)
			    {
				   	JOptionPane.showMessageDialog(null,"Ce Stagiaire est déja inscrit à cette session !"); 
			    	e.printStackTrace();
			    }
		} 
	 
		// supprimer un stagiaire dans session par rapport à une formation
				 public void suppStagiaireSession(int session, int stagiaire) {
					    try 
					    {
					    	if (stagiaire != 0) {
					    			Statement st = new Connect_DB().connexion.createStatement();
					    			st.executeUpdate("DELETE FROM suivre WHERE ID_session="+session+" AND ID_stagiaire="+stagiaire);
						    		JOptionPane.showMessageDialog(null,"Stagiaire supprimé !"); 
					    	} else {
					    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné un stagiaire !"); 
					   		}   	
					    }
					    catch (Exception e)
					    {
						   	JOptionPane.showMessageDialog(null,"Erreur !"); 
					    	e.printStackTrace();
					    }
				} 
}

