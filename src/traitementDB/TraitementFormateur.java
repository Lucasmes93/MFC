package traitementDB;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TraitementFormateur {
	
int personneID = 0;

// Ajouter un formateur
	 public void ajoutFormateur(String prenom, String nom, String datenaissance, String sexe, String adresse, String ville, String CP,
			 					String pays, String mail, String pseudo, String mdp, String specialite) {
		 
		    try 
		    {
		    	Statement st = new Connect_DB().connexion.createStatement();
		    	st.executeUpdate("INSERT INTO personne(prenom, nom, dateNaissance, sexe, adresse, ville, CP, pays, mail, pseudo, password) VALUES('" +
		    					 prenom+"', '"+nom+"', '"+datenaissance+"', '"+sexe+"', '"+adresse+"', '"+ville+"', '"+CP+"', '" +
		    					 pays+"', '"+mail+"', '"+pseudo+"', '"+mdp+"')");  
		    	
		    	ResultSet requestpersonneID = st.executeQuery("SELECT max(ID) as ID FROM personne");
		    	if (requestpersonneID.next()) {
		    		personneID = requestpersonneID.getInt("ID");
		    	}
		    	requestpersonneID.close();
		    	
		    	if (personneID != 0) {
		    		st.executeUpdate("INSERT INTO formateur(ID_personne,specialite) VALUES("+personneID+",'"+specialite+"')");
		    	}
		    	JOptionPane.showMessageDialog(null,"Formateur Ajouté !"); 
		    }
		    catch (Exception e)
		    {
		    	JOptionPane.showMessageDialog(null,"Insertion Formateur Incorrect ! Note : les Apostrophes sont interdits."); 
		    	e.printStackTrace();
		    }
		  }
	 
	// modif un Formateur
	    public void modifFormateur(int ID, String nom, String prenom, String datenaissance, String sexe, String adresse, String ville,
	    						    String cp, String pays, String mail) {
		    try 
		    {
		    	if (ID != 0) {
	    		int i = JOptionPane.showConfirmDialog(null,"Avant de valider pensez à bien re-sélectionner la ligne modifiée, si cela est fait, confirmez vous la modification du formateur "+nom+" "+prenom+" ? ","", JOptionPane.YES_NO_OPTION);
	    			if (i==0) {
	    				Statement st = new Connect_DB().connexion.createStatement();
	    				ResultSet rs = st.executeQuery("SELECT ID_personne FROM formateur WHERE ID="+ID);
	    				if (rs.next()) {
	    					personneID = rs.getInt("ID_personne");
	    				}
	    			rs.close();		    
	    			st.executeUpdate("UPDATE personne SET nom='"+nom+"', prenom='"+prenom+"', datenaissance='"+datenaissance+"', sexe='"+
	    							 sexe+"', adresse='"+adresse+"', ville='"+ville+"', cp='"+cp+"', pays='"+pays+"', mail='"+mail+"' WHERE ID="+personneID); 
	    			}
		    	} else {
		    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné un formateur à modifier !"); 
			    }
		    }
		    catch (Exception e)
		    {
		    	JOptionPane.showMessageDialog(null,"Erreur modification ! Note : les Apostrophes sont interdits."); 
		    	e.printStackTrace();
		    }
		  }	 
 
		// supprimer un formateur
	    public void suppFormateur(int ID, String nomS, String prenomS) {
		    try 
		    {
		    	if (ID != 0) {
		    		int i = JOptionPane.showConfirmDialog(null,"Confirmez vous la suppression du formateur "+nomS+" "+prenomS+" ? ","", JOptionPane.YES_NO_OPTION);
		    		if (i==0) {
		    			Statement st = new Connect_DB().connexion.createStatement();
		    			ResultSet rs = st.executeQuery("SELECT ID_personne FROM formateur WHERE ID="+ID);
		    				if (rs.next()) {
		    					personneID = rs.getInt("ID_personne");
		    				}
		    				rs.close();
		    			st.executeUpdate("DELETE FROM formateur WHERE ID="+ID);
		    			st.executeUpdate("DELETE FROM personne WHERE ID="+personneID);
		    		}
		    	} else {
		    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné un formateur !"); 
		   		}
		    }
		    catch (Exception e)
		    {
		    	JOptionPane.showMessageDialog(null,"Erreur !"); 
		    	e.printStackTrace();
		    }
		  }	
}
