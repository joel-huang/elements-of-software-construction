import java.util.Scanner;

public class FactoryPatternDemoTelephoneSubscriberFixed {

	public static void main(String[] args){
		Switch theSwitch = null;
				
		Scanner userInput = new Scanner(System.in);
			
		String callOption = "";
			
		System.out.println("What type of call? (L:Local or R:Remote)");
			
		if (userInput.hasNextLine()){	
			callOption = userInput.nextLine();				
		}
		
		SwitchFactory theSwitchFactory = new SwitchFactory();
		theSwitch = theSwitchFactory.makeSwitchForThisCall(callOption);
		
		makeCall(theSwitch);
	}
		
	public static void makeCall(Switch aSwitch){
		aSwitch.displaySwitch();
		aSwitch.handleCall();
	}
}

// Switch Factory handles the dynamic creation of switches for different calls
// The exact implementation of switch for its local/remote counterparts are 
// hidden from the subscriber
class SwitchFactory {
	
	// sudiptac: Encapsulation of all different subsystems in the switch
	public Switch makeSwitchForThisCall(String callOption){		
		Switch newSwitch = null;
		
		if (callOption.equals("L")){			
			return new localCallHandlingSystem();			
		} else 
		
		if (callOption.equals("R")){
			return new remoteCallHandlingSystem();
		} else 
			return null;
	}
}

//Switch implementation appears below
abstract class Switch {
	
	private String description;
	
	public String getDescription() { return description; }
	public void setDescription(String newDescription) { description = newDescription; }
	
	public void displaySwitch(){		
		System.out.println(getDescription() + " is on the screen");
	}
	
	public void handleCall(){		
		//code to handle call
	}
}

class localCallHandlingSystem extends Switch {
	public localCallHandlingSystem(){	
		setDescription("The subsystem handling local calls");		
	}
	// ==> code to handle operations related to local calls
	public void handleCall(){	
		//big code follows	
	}
}

class remoteCallHandlingSystem extends Switch {
	public remoteCallHandlingSystem(){	
		setDescription("The subsystem handling remote calls");		
	}
	// ==> code to handle operations related to remote calls
	public void handleCall(){
		//big code follows		
	}
}

