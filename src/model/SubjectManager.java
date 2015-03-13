package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SubjectManager {
	
	private ArrayList<String> firstYear = new ArrayList<String>();
	private ArrayList<String> secondYear = new ArrayList<String>();
	private ArrayList<String> thirdYear = new ArrayList<String>();
	private ArrayList<String> fourthYear = new ArrayList<String>();
	private ArrayList<String> optativas = new ArrayList<String>();
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	private Subject subject;
	
	public SubjectManager(){
		
	}
	
	/**
	  * This method saves the tuited subject into an arraylist to keep
	  * the information and calculate the price of the tuition according to the
	  * price per credit. It also stop updating the price if the subjects is already on the current tuit
	  * @param price is the price per credit
	  * @param subjecttoAdd it's the name of the subject
	  * @return this is the price of the subject tuition
	 
	  */
	public Float addToChart(float price, String subjecttoAdd){
		float subjectPrice = 0.0f;
		int credits;
		boolean isFound = false;
		
		String[] aux = subjecttoAdd.split(" - ");
		aux[1] = aux[1].replaceAll("\\s","");
		credits = Integer.parseInt(aux[1]);
		subjectPrice = credits * price;
		
		for(int i=0; i<subjects.size(); i++){
			if(subjects.get(i).getName().equals(aux[0])){
				isFound = true;
			}
		}
		
		if(isFound == false){
			subject = new Subject(aux[0], credits);
			subjects.add(subject);
			
			return subjectPrice;
		}else{
			return 0.0f;
		}
		
	}
	
	/**
	  * This method is responsible of reading the file wich it contents the subjects
	  * the format of this file is first the number of subjects in those year and in every new line
	  * there is one subject followed by " - " and the num of credits
	  * @throws If the file is not found there will be an exception thrown	 
	  */
	public void lecture () {
		try {
			InputStream in = getClass().getResourceAsStream("/res/assignatures.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
						
			int currentSubjects = Integer.parseInt(br.readLine());
			int i=0;
			while(i < currentSubjects) {
				firstYear.add(br.readLine());
				i++;
			}i=0;
			currentSubjects = Integer.parseInt(br.readLine());
			while(i < currentSubjects) {
				secondYear.add(br.readLine());
				i++;
			}i=0;
			currentSubjects = Integer.parseInt(br.readLine());
			while(i < currentSubjects) {
				thirdYear.add(br.readLine());
				i++;
			}i=0;
			currentSubjects = Integer.parseInt(br.readLine());
			while(i < currentSubjects) {
				fourthYear.add(br.readLine());
				i++;
			}i=0;
			currentSubjects = Integer.parseInt(br.readLine());
			while(i < currentSubjects) {
				optativas.add(br.readLine());
				i++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println ("No s'ha trobat el fitxer");
		}
	}

	public ArrayList<String> getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(ArrayList<String> firstYear) {
		this.firstYear = firstYear;
	}

	public ArrayList<String> getSecondYear() {
		return secondYear;
	}

	public void setSecondYear(ArrayList<String> secondYear) {
		this.secondYear = secondYear;
	}

	public ArrayList<String> getThirdYear() {
		return thirdYear;
	}

	public void setThirdYear(ArrayList<String> thirdYear) {
		this.thirdYear = thirdYear;
	}

	public ArrayList<String> getFourthYear() {
		return fourthYear;
	}

	public void setFourthYear(ArrayList<String> fourthYear) {
		this.fourthYear = fourthYear;
	}

	public ArrayList<String> getOptativas() {
		return optativas;
	}

	public void setOptativas(ArrayList<String> optativas) {
		this.optativas = optativas;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}
	

	
}
