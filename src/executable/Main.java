package executable;

import interfaceGraphique.Identification;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
			
		  JFrame frame = new Identification();
		      
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setTitle("Application de gestion de MFC");
	      frame.setVisible(true);
	      frame.setResizable(false);
	}


}


