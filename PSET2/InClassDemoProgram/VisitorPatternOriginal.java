package Week3;

import java.util.ArrayList;

public class VisitorPatternOriginal {

	public static void main(String[] args){
		SUTD oneSUTD = new SUTD ();
		
		ArrayList<Employee> employee = oneSUTD.getEmployee();

		//auditing
		for (int i =0; i < employee.size(); i++) {
			if (employee.get(i) instanceof Professor) {
				System.out.println("Prof: " + ((Professor) employee.get(i)).getName() + ((Professor) employee.get(i)).getNo_of_publications());
			}
			if (employee.get(i) instanceof AdminStuff) {
				System.out.println("Prof: " + ((AdminStuff) employee.get(i)).getName() + ((AdminStuff) employee.get(i)).getEfficiency());
			}
			if (employee.get(i) instanceof Student) {
				System.out.println("Prof: " + ((Student) employee.get(i)).getName() + ((Student) employee.get(i)).getGPA());
			}
		}	
	}
}

class SUTD {
	private ArrayList<Employee> employee; 
	
	public SUTD () {
		employee = new ArrayList<Employee>(); 
		employee.add(new Professor("Sun Jun", 0));
		employee.add(new AdminStuff("Stacey", 5));
		employee.add(new Student("Allan", 3));		
	}
	
	public ArrayList<Employee> getEmployee () {
		return employee;
	}
}

class Employee {}

class Professor extends Employee {
	private String name;
	private int no_of_publications;
	
	public Professor (String name, int no_of_publications) {
		this.name = name;
		this.no_of_publications = no_of_publications;
	}
	
	public String getName () {
		return name;
	}

	public int getNo_of_publications() {
		return no_of_publications;
	}
}

class AdminStuff extends Employee {
	private String name;
	private float efficiency;
	
	public AdminStuff (String name, float efficiency) {
		this.name = name;
		this.efficiency = efficiency;
	}
	
	public String getName() {
		return name;
	}

	public float getEfficiency() {
		return efficiency;
	}
}

class Student extends Employee {
	private String name;
	private float GPA;
	
	public Student (String name, float GPA) {
		this.name = name;
		this.GPA = GPA;
	}
	
	public String getName() {
		return name;
	}

	public float getGPA() {
		return GPA;
	}
}
