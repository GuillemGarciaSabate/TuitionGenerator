package controlador;

import javax.swing.SwingUtilities;
import model.Subject;
import model.SubjectManager;
import vista.Tuition;

public class Principal {
	/**
	  * This is the main method, called to execute the program
	  * @version 1.0
	  * @author Guillem Garcia Sabate
	  */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// create view
				Tuition tui = new Tuition();
				// create model
				SubjectManager subM = new SubjectManager();
				subM.lecture();
				// create the controller and set the relation C->V i C->M
				Controller con = new Controller(tui, subM);
				// set the relation V--->C
				tui.setControllers(con);
				// make the view visible
				tui.setVisible(true);
			}
		});
		
	}

}
