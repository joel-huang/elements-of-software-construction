package Week3;
class Robot {
	String name;

	public Robot (String name)
	{
		this.name = name;
	}

	public void behave ()
	{
		//the robots behave differently
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBehavior() {
		//todo
	}
}

interface IBehaviour {
	public int moveCommand();
}

public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior();
		r2.setBehavior();
		r3.setBehavior();
		
		r1.behave();
		r2.behave();
		r3.behave();

		//change the behaviors of each robot.
		r1.setBehavior();
		r2.setBehavior();
		r3.setBehavior();
		
		r1.behave();
		r2.behave();
		r3.behave();
	}
}
