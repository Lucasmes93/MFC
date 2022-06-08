package traitementDB;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TraitementStagiaire {

	int personneID;

// Ajouter un stagiaire
	 public void ajoutStagiaire(String prenom, String nom, String datenaissance, String sexe, String adresse, String ville, String CP,
			 					String pays, String mail, String pseudo, String mdp) {
		 
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
		    		st.executeUpdate("INSERT INTO stagiaire(ID_personne) VALUES("+personneID+")");
		    	}
		    	JOptionPane.showMessageDialog(null,"Stagiaire Ajouté !"); 
		    }
		    catch (Exception e)
		    {
		    	JOptionPane.showMessageDialog(null,"Insertion Stagiaire Incorrect ! Note : les Apostrophes sont interdits."); 
		    	e.printStackTrace();
		    }
		  }
	 
	 
	 // modif un stagiaire
	    public void modifStagiaire(int ID, String nom, String prenom, String datenaissance, String sexe, String adresse, String ville,
	    						    String cp, String pays, String mail, String mdp) {
		    try 
		    {
		    	if (ID != 0) {
		    		int i = JOptionPane.showConfirmDialog(null,"Avant de valider pensez à bien re-sélectionner la ligne modifiée, si cela est fait, confirmez vous la modification du Stagiaire "+nom+" "+prenom+" ? ","", JOptionPane.YES_NO_OPTION);
		    		if (i==0) {
		    			Statement st = new Connect_DB().connexion.createStatement();
		    			ResultSet rs = st.executeQuery("SELECT ID_personne FROM stagiaire WHERE ID="+ID);
		    			if (rs.next()) {
		    				personneID = rs.getInt("ID_personne");
		    			}
		    			rs.close();		    
		    			st.executeUpdate("UPDATE personne SET nom='"+nom+"', prenom='"+prenom+"', datenaissance='"+datenaissance+"', sexe='"+
		    							sexe+"', adresse='"+adresse+"', ville='"+ville+"', cp='"+cp+"', pays='"+pays+"', mail='"+mail+", mot de passe' = "+mdp+", WHERE ID="+personneID); 
		    			}
		    	} else {
	    		JOptionPane.showMessageDialog(null,"Veuillez sélectionné un stagiaire à modifier ! Note : les Apostrophes sont interdits."); 
		    	}
		    }
		    catch (Exception e)
		    {
		    	JOptionPane.showMessageDialog(null,"Erreur !"); 
		    	e.printStackTrace();
		    }
		  }	 

	// supprimer un stagiaire
	    public void suppStagiaire(int ID, String nomS, String prenomS) {
		    try 
		    {
		    	if (ID != 0) {
		    		int i = JOptionPane.showConfirmDialog(null,"Confirmez vous la suppression du stagiaire "+nomS+" "+prenomS+" ? ","", JOptionPane.YES_NO_OPTION);
		    		if (i==0) {
		    			Statement st = new Connect_DB().connexion.createStatement();
		    			ResultSet rs = st.executeQuery("SELECT ID_personne FROM stagiaire WHERE ID="+ID);
		    				if (rs.next()) {
		    					personneID = rs.getInt("ID_personne");
		    				}
		    				rs.close();
			    		st.executeUpdate("DELETE FROM suivre WHERE ID_stagiaire="+ID);
		    			st.executeUpdate("DELETE FROM stagiaire WHERE ID="+ID);
		    			st.executeUpdate("DELETE FROM personne WHERE ID="+personneID);
		    		}
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
