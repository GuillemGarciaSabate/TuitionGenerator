package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.SubjectManager;
import controlador.Controller;


public class Tuition extends JFrame{
	
	private SubjectManager subM = new SubjectManager();
	private JPanel mainBox;
	private JPanel userOptions;
	private JLabel logo;
	private String[] year = {"first", "second", "third", "fourth", "optatives"};
	public JComboBox yearList = new JComboBox(year);
	public JComboBox subjectList = new JComboBox();
	private JButton add = new JButton("Add");
	private JButton reset = new JButton("Reset");
	private JTextField price = new JTextField("",5);
	private JTextArea jtaTSubjects;
	private JScrollPane TuitedSubjects;
	private JLabel totalImport;
	private JPanel jpinferior = new JPanel();
	
	
	//That's the view constructor
	public Tuition(){
		//Preparamos un objeto ScrollPane para mostrar las assignaturas de las que nos matriculamos y insertamos en JTextArea
		jtaTSubjects = new JTextArea();
		jtaTSubjects.setLineWrap(true);
		jtaTSubjects.setLayout(new FlowLayout());
		jtaTSubjects.setEditable(false);
		TuitedSubjects = new JScrollPane(jtaTSubjects);
		TuitedSubjects.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1,
		        1, 1, Color.LIGHT_GRAY), "Assignatures",TitledBorder.ABOVE_TOP,TitledBorder.TOP,new Font("font name",Font.BOLD,17)));
		
		//Panell Dreta
		userOptions = new JPanel(new GridLayout(5,1));
		logo = new JLabel();
		try{
			logo.setIcon(new ImageIcon(getClass().getResource("/res/logo_laSalle.jpg")));
			logo.setHorizontalAlignment(0);
		}catch(NullPointerException ex){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			logo.setText(dateFormat.format(date)+"\n Welcome back!");
		}
		userOptions.add(logo);
		yearList.setSelectedIndex(0);
		
		JPanel jpAux1 = new JPanel(new FlowLayout());
		jpAux1.add(new JLabel("Curs "), FlowLayout.LEFT);
		jpAux1.add(yearList, FlowLayout.CENTER);
		userOptions.add(jpAux1);
		
		JPanel jpAux2 = new JPanel(new FlowLayout());
		jpAux2.add(new JLabel("Assignatures "), FlowLayout.LEFT);
		jpAux2.add(subjectList, FlowLayout.CENTER);
		userOptions.add(jpAux2);
		
		JPanel jpAux3 = new JPanel(new GridLayout(1,2));
		JPanel butonContainer = new JPanel(new FlowLayout());
		butonContainer.add(add);
		jpAux3.add(butonContainer);
		butonContainer.add(reset);
		jpAux3.add(butonContainer);
		userOptions.add(jpAux3);
		
		JPanel jpAux4 = new JPanel();
		jpAux4.setBorder(BorderFactory.createTitledBorder("PREU"));
		jpAux4.add(new JLabel("Preu / Credit (€) "), FlowLayout.LEFT);
		jpAux4.add(price, FlowLayout.CENTER);
		userOptions.add(jpAux4);
		
		
		// panell inferior
		totalImport = new JLabel("PREU TOTAL: 0.0 €");
		jpinferior.add(totalImport);
		jpinferior.setBackground(Color.orange);
		
		mainBox = new JPanel(new BorderLayout());
		mainBox.setBorder(BorderFactory.createTitledBorder("La Matricula"));
		mainBox.add(jpinferior, BorderLayout.SOUTH);
		mainBox.add(userOptions, BorderLayout.EAST);
		mainBox.add(TuitedSubjects,BorderLayout.CENTER);
		this.getContentPane().add(mainBox);
		this.setTitle("La Matricula");
		
		this.setSize(680, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//this method set the controller for all the elements that the user can interact with
	public void setControllers(Controller c){
		add.addActionListener(c);
		reset.addActionListener(c);
		yearList.addActionListener(c);
		subjectList.addActionListener(c);
		price.addActionListener(c);
	}

	
	public String getCreditPrice() {
		return price.getText();
	}
	
	/**
	  * This method sets the new price and update the subject list in the
	  * jtextArea
	  * @param priceToAdd is the price of the last added subject
	  * @param newSubject it's the name of the subject to be added
	  */
	public void updateTuition(float priceToAdd, String newSubject){
		jtaTSubjects.append(newSubject+"\n");
		String[] finalPrice;
		finalPrice = totalImport.getText().split(": ");
		finalPrice = finalPrice[1].split(" ");
		priceToAdd = priceToAdd + Float.parseFloat(finalPrice[0]);
		finalPrice[0] = Float.toString(priceToAdd);
		totalImport.setText("PREU TOTAL: "+finalPrice[0]+" €");
	}
	
	//reset all values of the App
	public void resetTuition(){
		totalImport.setText("PREU TOTAL: 0.0 €");
		jtaTSubjects.setText("");
		yearList.setSelectedItem("first");
	}
	
	//when changing the year-cours this method updates the JComboBox to
	//list the new subjects
	public void loadSubjects(String[] subjects){
		subjectList.removeAllItems();
        for (int i = 0; i < subjects.length; i++) {
            subjectList.addItem(subjects[i]);
        }
	}

}

