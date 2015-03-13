package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Subject;
import model.SubjectManager;
import vista.Tuition;


public class Controller implements ActionListener{
	
	private Tuition tui;
	private SubjectManager subM;
	private String[] subjects;
	
	/**
	  *This is the constructor of the Controller, responsible to instantiate objects from
	  *the other clases of the MVC scheme. It also sets the first year subjects to be loaded
	  * @param tui is an instance of the Tuition class
	  * @param subM is an instance of the SubjectManager class
	  */
	public Controller(Tuition tui, SubjectManager subM){
		this.tui=tui;
		this.subM = subM;
		
		subjects = new String[subM.getFirstYear().toArray().length];
    	for(int i=0; i<subjects.length; i++){
    		subjects[i] = subM.getFirstYear().get(i).toString();
    	}
    	tui.loadSubjects(subjects);
	}

	@Override
	//That's the method responsible to act towards posible user actions with GUI
	public void actionPerformed(ActionEvent e) {
			
			/**
			 * If any button is pressed, the method will check it's name
			 * Reseting the App if desired or adding a subject to the current tuition
			 * @throws a If there is not a credits/price setted by the user a prompt window will be displayed
			 * @throws ex If the file is corrupted
			 */
			if (e.getSource() instanceof JButton) {
		       
				String button = ((JButton)e.getSource()).getText();
				if (button.equals("Add")) {
					
					try{
						float price = subM.addToChart(Float.parseFloat(tui.getCreditPrice()), tui.subjectList.getSelectedItem().toString());
						String[] subjectANDcredit = tui.subjectList.getSelectedItem().toString().split(" - ");
						if(price != 0.0f){
							tui.updateTuition(price, subjectANDcredit[0]);
						}
					}catch(IllegalArgumentException a){
						JOptionPane.showMessageDialog(null, "Please, make sure to enter a price", "Error",JOptionPane.ERROR_MESSAGE);
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Corrupted File", "Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				if (button.equals("Reset")) {
					tui.resetTuition();
					subM.getSubjects().clear();
				} 
				
		    }
			
			/**
			 * If any ComboBox is changed the method will check if it's the one displayed for the
			 * courses and if it is, the value of this one is going to be taken
			 * finally, the subjects of the chossen cours are going to be loaded into an array sent to Tuition via loadSubjects
			 *  @see loadSubjects this method set the options for the subjects comboBox
			 */
			if (e.getSource() instanceof JComboBox) {
		        
		        if(e.getSource() == tui.yearList){
		        	
		        	String actualC = (String)tui.yearList.getSelectedItem();
		        	
		        	if(actualC.equals("first")){
		        		
		        		subjects = new String[subM.getFirstYear().toArray().length];
			        	for(int i=0; i<subjects.length; i++){
			        		subjects[i] = subM.getFirstYear().get(i).toString();
			        	}
			        	tui.loadSubjects(subjects);
			        	
		    		}
		    		if(actualC.equals("second")){
		    			
		    			subjects = new String[subM.getSecondYear().toArray().length];
			        	for(int i=0; i<subjects.length; i++){
			        		subjects[i] = subM.getSecondYear().get(i).toString();
			        	}
			        	tui.loadSubjects(subjects);
		    			
		    		}
		    		if(actualC.equals("third")){
		    			
		    			subjects = new String[subM.getThirdYear().toArray().length];
			        	for(int i=0; i<subjects.length; i++){
			        		subjects[i] = subM.getThirdYear().get(i).toString();
			        	}
			        	tui.loadSubjects(subjects);
		    			
		    		}
		    		if(actualC.equals("fourth")){
		    			
		    			subjects = new String[subM.getFourthYear().toArray().length];
			        	for(int i=0; i<subjects.length; i++){
			        		subjects[i] = subM.getFourthYear().get(i).toString();
			        	}
			        	tui.loadSubjects(subjects);
		    			
		    		} 
		    		if(actualC.equals("optatives")){
		    			
		    			subjects = new String[subM.getOptativas().toArray().length];
			        	for(int i=0; i<subjects.length; i++){
			        		subjects[i] = subM.getOptativas().get(i).toString();
			        	}
			        	tui.loadSubjects(subjects);
		    			
		    		}
		        	
		        }		        
		    }
	}
}
