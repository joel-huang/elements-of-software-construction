class Robot {
	String name;
	public IBehaviour behaviour;

	public Robot (String name) {
	    this.name = name;
	}

	public void behave () {
	    int move = behaviour.moveCommand();
        System.out.println(name + " moved " + move + " units");
    }

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}
	
	public void setBehavior(String b) {
		if (b.equalsIgnoreCase("aggressive")) {
		    behaviour = new AggressiveBehaviour();
        } else if (b.equalsIgnoreCase("defensive")) {
		    behaviour = new DefensiveBehaviour();
        } else if (b.equalsIgnoreCase("normal")) {
		    behaviour = new NormalBehaviour();
        } else {
            System.out.println("Invalid behaviour, not set.");
        }
	}
}

interface IBehaviour {
	public int moveCommand();
}

class AggressiveBehaviour implements IBehaviour {
    public int moveCommand() {
        return 10;
    }
}
class DefensiveBehaviour implements IBehaviour{
    public int moveCommand() {
        return 1;
    }
}
class NormalBehaviour implements IBehaviour{
    public int moveCommand() {
        return 5;
    }
}

public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior("aggressive");
		r2.setBehavior("normal");
		r3.setBehavior("defensive");
		
		r1.behave();
		r2.behave();
		r3.behave();

		//change the behaviors of each robot.
		r1.setBehavior("normal");
		r2.setBehavior("defensive");
		r3.setBehavior("aggressive");
		
		r1.behave();
		r2.behave();
		r3.behave();
	}
}
