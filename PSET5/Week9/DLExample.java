package Week9;

import java.util.HashSet;
import java.util.Set;


class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

	public synchronized Point getLocation() {
        return location;
    }

    // Lock the Taxi first, then the Dispatcher
    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination)) {
            System.out.println("Trying to notify available");
            dispatcher.notifyAvailable(this);
        }

    }

    public synchronized Point getDestination() {
        return destination;
    }
    public synchronized void setDestination(Point pt) { destination = pt; }
}

class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    public synchronized void addTaxi(Taxi taxi) {
        taxis.add(taxi);
    }

    public synchronized void notifyAvailable(Taxi taxi) {

        System.out.println("Notified available");
        availableTaxis.add(taxi);
    }

    // Lock the Dispatcher then the Taxis
    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis) {
            System.out.println("Trying to draw marker");
            image.drawMarker(t.getLocation());
        }
        return image;
    }
}

class Image {
    public void drawMarker(Point p) {
        System.out.println("Drawing marker");
    }
}

class Point {
	
}

class TaxiThread extends Thread {
    Taxi taxi;
    public void run() {
        System.out.println("Taxi thread running");
        taxi.setDestination(new Point());
        taxi.setLocation(taxi.getDestination());
    }
    public void setTaxi(Taxi taxiArg) {
        taxi = taxiArg;
    }
}

class DispatcherThread extends Thread {
    Dispatcher dispatcher;
    public void run() {
        System.out.println("Dispatcher thread running");
        dispatcher.getImage();
    }
    public void setDispatcher(Dispatcher d) {
        dispatcher = d;
    }
}