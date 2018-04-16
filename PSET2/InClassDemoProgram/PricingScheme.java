//package Week3;

import java.util.ArrayList;

//Uses the Subject interface to update all Observers

public class PricingScheme implements iSubject {
	
	// maintains the list of all subscribers to the pricing scheme
	// However, not really aware of what type of subscribers they are (e.g. University/Corporate/Individual)
	private ArrayList<Observer> observers;
	private double Price;
	
	public PricingScheme(){
		
		// Creates an ArrayList to hold all observers
		
		observers = new ArrayList<Observer>();
	}
	
	public void register(Observer newObserver) {
		
		// Adds a new observer to the ArrayList
		
		observers.add(newObserver);
		
	}

	public void unregister(Observer deleteObserver) {
		
		// Get the index of the observer to delete
		
		int observerIndex = observers.indexOf(deleteObserver);
		
		// Print out message (Have to increment index to match)
		
		System.out.println("Observer " + (observerIndex+1) + " deleted");
		
		// Removes observer from the ArrayList
		
		observers.remove(observerIndex);
		
	}

	public void notifyObserver() {
		
		// Cycle through all observers and notifies them of
		// price changes
		
		for(Observer observer : observers){
			
			observer.update(Price);
			
		}
	}
	
	// Change pricing scheme and notifies observers of changes
	
	public void setPrice(double newPrice){
		
		this.Price = newPrice;
		
		notifyObserver();
		
	}
}
