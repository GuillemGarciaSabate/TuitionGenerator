package model;

public class Subject {
	
	/**
	  *This is not a deprecated class as long as it's a good practice
	  *to save the users work into an Array/Linked list (present in subjectManager)
	  *to upkeep the information somewhere else than the GUI, additionaly it's used to check if the subjects are already tuited
	  */
	
	private String name;
	private int credits;
	
	public Subject(String name, int credits){
		this.name = name;
		this.credits = credits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

}