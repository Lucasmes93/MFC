package traitementDB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Connect_DB {

	public Connection connexion;
	String url;
		
	// Connexion base de données
	public Connect_DB()	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = new String("jdbc:mysql://localhost:3306/mfc");
			connexion = (Connection) DriverManager.getConnection(url,"root","");
			System.out.println("Connexion réussi");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Identification 
    public String verifID(String utilisateur, String mdp) {	
    	String verif ="";
	    try 
	    {
	    	Statement st = new Connect_DB().connexion.createStatement();
	    	ResultSet request = st.executeQuery("SELECT * FROM personne as P, secretaire AS S WHERE P.ID=S.ID_personne AND " +
	    											"P.pseudo='"+utilisateur+"' AND P.password='"+mdp+"'");
	    	if (request.next()){
	    		verif = "accept";
	    	} else {
	    		JOptionPane.showMessageDialog(null,"Données Incorrect !");
	    	}
	    		request.close();
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Connexion Impossible");
	    e.printStackTrace();
	    }
	    return verif;
    }
    
}