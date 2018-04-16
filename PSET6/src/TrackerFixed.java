import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

class Updater extends Thread {
	TrackerFixed tracker;

	public Updater (TrackerFixed tra) {
		this.tracker = tra;
	}

	public void run () {
		// increase coordinates by (10,10) every 2 seconds;
		while (true) {
			try {
				TrackerFixed.MutablePoint loc = tracker.getLocation("somestring");
				loc.x += 10;
				loc.y += 10;
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class TrackerFixed {
	// locking policy: private lock
	// every modification of an entry in the Map must require locking of the Map
	public ReentrantLock mutex = new ReentrantLock();
	private static volatile Map<String, MutablePoint> locations;

	// constructor must be done before an external method can access the
	// locations Map
	public TrackerFixed(Map<String, MutablePoint> map) {
		mutex.lock();
		locations = map;
		mutex.unlock();
	}

	// this method must not read before locations has been initialized,
	// hence we are required to lock it.
	public Map<String, MutablePoint> getLocations () {
		mutex.lock();
		Map<String, MutablePoint> map = locations;
		mutex.unlock();
		return map;
	}

	// Similar to previous method
	public MutablePoint getLocation (String id) {
		mutex.lock();
		MutablePoint loc = locations.get(id);
		mutex.unlock();
		return loc;
	}


	// This method is gets locations and sets an entry in it to
	// the coordinates. For threadsafe modification, no other thread
	// should be able to modify in the meantime.
	public void setLocation (String id, int x, int y) {
		mutex.lock();
		MutablePoint loc = locations.get(id);
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);
		}
		loc.x = x;
		loc.y = y;
		mutex.unlock();
	}

	//this class is not thread-safe (why?) and keep it unmodified.
	class MutablePoint {
		public int x, y;

		public MutablePoint (int x, int y) {
			this.x = x;
			this.y = y;
		}

		public MutablePoint (MutablePoint p) {
			this.x = p.x;
			this.y = p.y;
		}
	}
}
