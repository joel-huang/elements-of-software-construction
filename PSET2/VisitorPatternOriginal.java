import java.util.ArrayList;

public class VisitorPatternOriginal {

	public static void main(String[] args){
		SUTD oneSUTD = new SUTD ();
		
		ArrayList<Employee> employee = oneSUTD.getEmployee();
		AttributeDisplay disp = new AttributeDisplay();

		//auditing
		for (int i =0; i < employee.size(); i++) {
            System.out.printf(employee.get(i).getClass().getSimpleName() + ": ");
            employee.get(i).accept(disp);
		}	
	}
}

class SUTD {
	private ArrayList<Employee> employee; 
	
	public SUTD () {
		employee = new ArrayList<Employee>(); 
		employee.add(new Professor("Sun Jun", 0));
		employee.add(new AdminStaff("Stacey", 5));
		employee.add(new Student("Allan", 3));		
	}
	
	public ArrayList<Employee> getEmployee () {
		return employee;
	}
}



interface Visitor {
	void showAttributes(Professor prof);
	void showAttributes(AdminStaff staff);
	void showAttributes(Student student);
}

class AttributeDisplay implements Visitor {

	public void showAttributes(Professor prof) {
        System.out.printf(prof.getName() + ", ");
        System.out.println(prof.getNo_of_publications() + " publications");
	}

	public void showAttributes(AdminStaff staff) {
        System.out.printf(staff.getName() + ", ");
        System.out.println(staff.getEfficiency() + " efficiency");
    }

	public void showAttributes(Student student) {
        System.out.printf(student.getName() + ", ");
        System.out.println(student.getGPA() + " GPA");
    }
}










interface Employee {
	void accept(Visitor v);
}

class Professor implements Employee {
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

	public void accept(Visitor v) {
		v.showAttributes(this);
	}
}

class AdminStaff implements Employee {
	private String name;
	private float efficiency;
	
	public AdminStaff (String name, float efficiency) {
		this.name = name;
		this.efficiency = efficiency;
	}
	
	public String getName() {
		return name;
	}

	public float getEfficiency() {
		return efficiency;
	}

	public void accept(Visitor v) {
		v.showAttributes(this);
	}
}

class Student implements Employee {
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

	public void accept(Visitor v) {
		v.showAttributes(this);
	}
}
