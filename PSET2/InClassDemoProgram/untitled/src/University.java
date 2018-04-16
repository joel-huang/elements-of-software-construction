//package Week3;

//Represents each Observer that is monitoring changes in the subject

public class University implements Observer {
	
	private double Price;
	
	// Static used as a counter
	
	private static int observerIDTracker = 0;
	
	// Used to track the observers
	
	private int observerID;
	
	// Will hold reference to the StockGrabber object
	
	private iSubject pricingScheme;
	
	public University(iSubject pricingScheme){
		
		// Store the reference to the pricingScheme object so
		// I can make calls to its methods
		
		this.pricingScheme = pricingScheme;
		
		// Assign an observer ID and increment the static counter
		
		this.observerID = ++observerIDTracker;
		
		// Message notifies user of new observer
		
		System.out.println("New Observer " + this.observerID);
		
		// Add the observer to the Subjects ArrayList
		
		pricingScheme.register(this);
		
	}
	
	// Called to update all observers
	
	public void update(double newPrice) {
		
		this.Price = newPrice;
		printThePrices();
		
	}
	
	public void printThePrices(){
		
		System.out.println(observerID + "\nPrice: " + Price + "\n");
		
	}
	
}
